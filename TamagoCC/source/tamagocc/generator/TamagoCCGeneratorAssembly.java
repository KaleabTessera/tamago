/**
 * 
 */
package tamagocc.generator;

import java.util.ArrayList;
import java.util.Iterator;

import tamagocc.ast.api.AEntity;
import tamagocc.ast.api.AInLabel;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AMethod;
import tamagocc.ast.api.ATamagoNew;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVariable;
import tamagocc.ast.impl.AConstructor;
import tamagocc.ast.impl.AIAffectation;
import tamagocc.ast.impl.AICall;
import tamagocc.ast.impl.AIConvertType;
import tamagocc.ast.impl.AIDocComment;
import tamagocc.ast.impl.AIInLabel;
import tamagocc.ast.impl.AIInlineComment;
import tamagocc.ast.impl.AIInstExpression;
import tamagocc.ast.impl.AIMemberVariable;
import tamagocc.ast.impl.AIMethod;
import tamagocc.ast.impl.AIModule;
import tamagocc.ast.impl.AINamespace;
import tamagocc.ast.impl.AINoInstruction;
import tamagocc.ast.impl.AIReturn;
import tamagocc.ast.impl.AISequence;
import tamagocc.ast.impl.AIString;
import tamagocc.ast.impl.AITamagoNew;
import tamagocc.ast.impl.AIThrowsException;
import tamagocc.ast.impl.AIType;
import tamagocc.ast.impl.AIVariable;
import tamagocc.ast.impl.AIVisibility;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GAssemblyContainer;
import tamagocc.generic.api.GBind;
import tamagocc.generic.api.GInstantiateComponent;
import tamagocc.generic.api.GTamagoEntity;

/**
 * This generator is specialized for generating assembly.
 * 
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCGeneratorAssembly extends TamagoCCGeneratorCommon implements TamagoCCGVisitor {

	AISequence seqconstructor;
	

	/**
	 * 
	 * @param owner
	 * @param entity
	 */
	public TamagoCCGeneratorAssembly(TamagoCCIGenerator owner,GTamagoEntity entity) {
		super(owner, entity);
		ast.setName(owner.getBuilder().generateInterfaceName(entity));
		ast.setCategory(AEntity.CLASS);
		AINamespace nstamago = new AINamespace("tamago");
		AINamespace nstamagocc = new AINamespace("tamago.ext.tamagocc");
		ast.addUsedNamespaces(nstamago);
		ast.addUsedNamespaces(nstamagocc);
		
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitAssembly(tamagocc.generic.api.GAssemblyContainer)
	 */
	public Object visitAssembly(GAssemblyContainer assembly)
			throws TamagoCCException {		
		seqconstructor = new AISequence(new ArrayList<AInstruction>());
		
		AINoInstruction info = new AINoInstruction();
		info.setComment(new AIInlineComment("Instantiate components by the framework"));
		seqconstructor.addInstruction(info);
		
		Iterator<GInstantiateComponent> listDefinitions = assembly.getInstatiateComponent();
		while(listDefinitions.hasNext()) {
			GInstantiateComponent inst = (GInstantiateComponent)listDefinitions.next();
			
			seqconstructor.addInstruction((AInstruction)inst.visit(this));
		}
		
		info = new AINoInstruction();
		info.setComment(new AIInlineComment("End instanciate\n\n\n"));
		seqconstructor.addInstruction(info);
		
		info = new AINoInstruction();
		info.setComment(new AIInlineComment("Binding of all components declared in specifications"));
		seqconstructor.addInstruction(info);
		
		Iterator<GBind> listBind = assembly.getBinds();
		while(listBind.hasNext()) {
			GBind bind = listBind.next();
			seqconstructor.addInstruction((AInstruction)bind.visit(this));
		}
		info = new AINoInstruction();
		info.setComment(new AIInlineComment("End binding"));
		
		AConstructor constructor = new AConstructor(ident(ast.getName()),AIVisibility.PUBLIC_VISIBILITY); 
		constructor.addThrowsException(new AIThrowsException((AIType) AIType.generateType("tamago.ServiceBindException")));
		constructor.addThrowsException(new AIThrowsException((AIType) AIType.generateType("tamago.TamagoException")));
		
		constructor.setBody(seqconstructor);
		ast.addMethod(constructor);
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitBind(tamagocc.generic.api.GBind)
	 */
	public Object visitBind(GBind bind) throws TamagoCCException {
		String provider = bind.getProvider();
		String requirer = bind.getRequirer();
		String label = bind.getLabel();
		
		String requirermethod = "get"+propertyToMethod(requirer);
		String providermethod = "get"+propertyToMethod(provider);
		
		AICall callrequirer = new AICall(ident(requirermethod));
		AICall callprovider = new AICall(ident(providermethod));
		
		AICall callbinder = new AICall(ident("Bind"));
		callbinder.addArgument(callrequirer);
		callbinder.addArgument(new AIString(label));
		callbinder.addArgument(callprovider);
		
		
		AInLabel inTamagoCC = new AIInLabel(variable("TamagoCC"),callbinder);
		AInLabel intamagocc = new AIInLabel(variable("tamagocc"),inTamagoCC);
		AInLabel inext = new AIInLabel(variable("ext"),intamagocc);
		AInLabel intamago = new AIInLabel(variable("tamago"),inext);
		
		return new AIInstExpression(intamago);
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitDefinition(tamagocc.generic.api.GInstantiateComponent)
	 */
	public Object visitDefinition(GInstantiateComponent inst)
			throws TamagoCCException {
		AType type= (AIType) AIType.generateType(inst.getComponentName());
		AINamespace module = new AINamespace(inst.getComponentModule().getFullModule(),inst.getComponentName());
		this.ast.addUsedNamespaces(module);

		String methodname = "get"+propertyToMethod(inst.getComponentLabel());
		
		
		AIMemberVariable membre = new AIMemberVariable(ident(inst.getComponentLabel()),type,AIVisibility.PRIVATE_VISIBILITY);
		membre.setComment(new AIInlineComment("If you modify this name, you must modify the method called : "+methodname+" !"));
		ast.addVariablesMembers(membre);
		
		// Ajouter l'initiatlisation de la variable membre.
		
		AVariable var = new AIVariable(ident(inst.getComponentLabel()));
		AIReturn body = new AIReturn(var);
		body.setComment(new AIInlineComment("return the value"));
		AIMethod m = new AIMethod(AMethod.IMPLEMENTED,ident(methodname),type,AIVisibility.PUBLIC_VISIBILITY);
		m.setBody(body);
		m.setComment(new AIDocComment("This method allow to get the component binded to the label : "+inst.getComponentLabel()));
		ast.addMethod(m);
	
		AITamagoNew tamagonew;
		if(inst.getPercolatorName().length() > 0)
			 tamagonew = new AITamagoNew(type,new AIModule(inst.getComponentModule().getFullModule()),ATamagoNew.COMPONENT,inst.getPercolatorName());
		else
			tamagonew = new AITamagoNew(type,new AIModule(inst.getComponentModule().getFullModule()),ATamagoNew.COMPONENT);
		//AIConvertType convtype = new AIConvertType(type,tamagonew);
		AIAffectation affectation = new AIAffectation(var,tamagonew);
		return affectation;
	}	
}
