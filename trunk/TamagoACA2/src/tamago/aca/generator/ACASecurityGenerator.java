/**
 * 
 */
package tamago.aca.generator;

import java.util.ArrayList;
import java.util.Iterator;

import tamagocc.api.TOpeName;
import tamagocc.ast.api.AEntity;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AInLabel;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AMethod;
import tamagocc.ast.api.AThrowsException;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVariable;
import tamagocc.ast.impl.AConstructor;
import tamagocc.ast.impl.AIAffectation;
import tamagocc.ast.impl.AIBool;
import tamagocc.ast.impl.AICall;
import tamagocc.ast.impl.AIConvertType;
import tamagocc.ast.impl.AIIdent;
import tamagocc.ast.impl.AIIfThenElse;
import tamagocc.ast.impl.AIInLabel;
import tamagocc.ast.impl.AIInitialisation;
import tamagocc.ast.impl.AIInlineComment;
import tamagocc.ast.impl.AIInstExpression;
import tamagocc.ast.impl.AIInteger;
import tamagocc.ast.impl.AILongComment;
import tamagocc.ast.impl.AIMemberVariable;
import tamagocc.ast.impl.AIMethod;
import tamagocc.ast.impl.AINamespace;
import tamagocc.ast.impl.AINew;
import tamagocc.ast.impl.AINewArray;
import tamagocc.ast.impl.AINil;
import tamagocc.ast.impl.AINoInstruction;
import tamagocc.ast.impl.AIOperator;
import tamagocc.ast.impl.AIParameter;
import tamagocc.ast.impl.AIReturn;
import tamagocc.ast.impl.AISequence;
import tamagocc.ast.impl.AIString;
import tamagocc.ast.impl.AIThrowException;
import tamagocc.ast.impl.AIThrowsException;
import tamagocc.ast.impl.AIType;
import tamagocc.ast.impl.AIVariable;
import tamagocc.ast.impl.AIVisibility;
import tamagocc.exception.TamagoCCException;
import tamagocc.generator.TamagoCCGeneratorCommon;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GComponentContainer;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GRequire;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.generic.impl.GIRead;

/**
 * @author hbelhaou
 *
 */
public class ACASecurityGenerator extends TamagoCCGeneratorCommon implements TamagoCCGVisitor {

	/** Represents the default throws exception (Equivalent to TamagoException) */
	protected AThrowsException ate;
	
	/** This arraylist contains the sequence to check all invariants (and inherited invariant). It's avoid redundancy compute */
	protected ArrayList<AInstruction> astpreinvariants;
	
	protected ArrayList<AInstruction> astpostinvariants;
	
	/** Correspond to the inner business logic (the implementation) */
	protected AIMemberVariable component;
	
	/** Contains the id of the business logic reference */
	protected AIdent idcomponent;
	
	/** Reference the default constructor */
	protected AConstructor constructor;
	
	/**
	 * @throws TamagoCCException 
	 * 
	 */
	public ACASecurityGenerator(GTamagoEntity entity) throws TamagoCCException {
		super(null, entity);
		
		astpreinvariants = new ArrayList<AInstruction>();
		astpostinvariants = new ArrayList<AInstruction>();
		
		ate = new AIThrowsException((AIType) AIType.generateType("tamago.TamagoException"));
		
		idcomponent = ident("tamago_decorated_component");
		component = new AIMemberVariable(idcomponent,(AType) visitType(entity.getNameAsType()),AIVisibility.PRIVATE_VISIBILITY);
		ast.addVariablesMembers(component);
		
		ast.addUsedNamespaces(new AINamespace("tamago.ext.tamagocc"));
		ast.addUsedNamespaces(new AINamespace("tamago"));
		ast.addUsedNamespaces(new AINamespace("tamago.ext.tamagoaca2"));
		
		ast.setName(owner.getBuilder().generateContainerInterfaceName(entity)+"ACASecurity");
		ast.setCategory(AEntity.CLASS);
		
		makeDefaultConstructor();
		
		ast.addMethod(getRequiredServices((GComponentContainer)entity));
		ast.addMethod(Bind());
		ast.addMethod(isBinded((GComponentContainer)entity));
		ast.addMethod(isBound((GComponentContainer)entity));
		ast.addMethod(set_core_property((GComponentContainer)entity));
		ast.addMethod(hotswapping());
		
	}
	

	public static AMethod getRequiredServices(GComponentContainer container) {
		AIdent idvar = ident("rs");
		AType vartype= AIType.generateType("RequiredService[]");// new AIType(new AIType(CatType.OBJECT,"RequiredService"));
		AType requiretype = (AIType) AIType.generateType("RequiredService");
		AVariable var = new AIVariable(idvar);
		
		
		Iterator<GRequire> requires = container.getAllRequire();
		ArrayList<GRequire> al = new ArrayList<GRequire>();
		while(requires.hasNext()) {
			GRequire require = (GRequire)requires.next();
			al.add(require);
		}
		
		AISequence body = new AISequence();
		
		AINewArray nouveauTableau = new AINewArray(requiretype,al.size());
		AIInitialisation init = new AIInitialisation(idvar,vartype,nouveauTableau);
		body.addInstruction(init);
		
		for(int i=0;i < al.size();i++) {
			AIVariable ivar = new AIVariable(idvar, new AIInteger(i));
			GRequire require = (GRequire)al.get(i);
			AINew newirequire = new AINew(requiretype);
			newirequire.addArguments(new AIString(require.getServiceName()));
			newirequire.addArguments(new AIString(require.getServiceModule().getFullModule()));
			newirequire.addArguments(new AIString(require.getLabel()));
			
			AIAffectation iaff = new AIAffectation(ivar,newirequire);
			body.addInstruction(iaff);
		}
		
		AIReturn renvoietab = new AIReturn(var);		
		body.addInstruction(renvoietab);
		// Fin du corps
		
		AIMethod method = new AIMethod(AMethod.IMPLEMENTED,ident("getRequiredServices"),vartype,AIVisibility.PUBLIC_VISIBILITY);
		method.setBody(body);
		return method;
	}
	
	private AMethod Bind() {
		AIdent idlabel = ident("label");
		AIdent idservice = ident("service");
		AIMethod method = new AIMethod(AMethod.IMPLEMENTED,ident("bind"),(AIType) AIType.generateType("void"),AIVisibility.PUBLIC_VISIBILITY);
		method.addParameters(new AIParameter(idlabel,(AIType) AIType.generateType("string")));
		method.addParameters(new AIParameter(idservice,(AIType) AIType.generateType("TamagoCCService")));
		AIThrowsException aste = new AIThrowsException((AIType) AIType.generateType("ServiceBindException"));
		method.addThrowsException(aste);
		
		AICall call = new AICall(ident("bind"));
		AInLabel inlabel = new AIInLabel(component.getCallMe(),call);
		call.addArgument(new AIVariable(idlabel));
		call.addArgument(new AIVariable(idservice));
		
		method.setBody(new AIInstExpression(inlabel));
		
		return method;
	}
	
	private AMethod isBinded(GComponentContainer container) {
		AIMethod method = new AIMethod(AMethod.IMPLEMENTED,ident("isBound"),(AIType) AIType.generateType("bool"),AIVisibility.PUBLIC_VISIBILITY);
		
		AIOperator ope = new AIOperator(TOpeName.opEg);
		ope.addOperand(component.getCallMe());
		ope.addOperand(new AINil());
		
		method.setBody(new AIReturn(ope));
		return method;
	}
	
	private AICall callRequiredService(GRequire require) {
		AIdent ident = new AIIdent(require.getLabel());
		return new AICall(ident);
	}
		
	private AMethod isBound(GComponentContainer container) {
		AIMethod method = new AIMethod(AMethod.IMPLEMENTED,ident("isBound"),(AIType) AIType.generateType("bool"),AIVisibility.PUBLIC_VISIBILITY);
		AIdent label = ident("label");
		method.addParameters(new AIParameter(label, AIType.typeSTRING));
		
		AISequence seq = new AISequence();
		method.setBody(seq);
		
		
		
		Iterator<GRequire> requires = container.getAllRequire();
		while(requires.hasNext()) {
			GRequire req = requires.next();
			
			// label.equals("label");
			AICall eg = new AICall(ident("equals"));
			eg.addArgument(new AIString(req.getLabel()));
			AIInLabel equals = new AIInLabel(new AIVariable(label), eg);
			
			// return req != null
			AIOperator res = new AIOperator(TOpeName.opNe);
			res.addOperand(callRequiredService(req));
			res.addOperand(new AINil());
			
			AIIfThenElse cons = new AIIfThenElse(equals, new AIReturn(res), AINoInstruction.getNoInstruction());
			seq.addInstruction(cons);
		}
		
		seq.addInstruction(new AIReturn(new AIBool(false)));
		return method;
	}
	
	private AMethod set_core_property(GComponentContainer container) {
		AIMethod method = new AIMethod(AMethod.IMPLEMENTED,ident("core_set_properties"),AIType.typeVOID,AIVisibility.PUBLIC_VISIBILITY);
		method.addParameters(new AIParameter(ident("property"),AIType.typeSTRING));
		method.addParameters(new AIParameter(ident("value"),(AIType) AIType.generateType("Object")));
		// --
		
		AIThrowException err = new AIThrowException((AIType) AIType.generateType("TamagoCCHotSwappingException"));
		err.setComment(new AIInlineComment("You can't directly call this method for security problem !"));
		method.setBody(err);
		
		// --
		return method;
	}
	
	private AMethod hotswapping() throws TamagoCCException {
		AIMethod method = new AIMethod(AMethod.IMPLEMENTED,ident("tamago_hotswapping"),AIType.typeVOID,AIVisibility.PUBLIC_VISIBILITY);
		method.addParameters(new AIParameter(ident("component"),(AIType) AIType.generateType("TamagoCCComponent")));
		AISequence seq = new AISequence();
		method.setBody(seq);
		
		GTamago contract = (GTamago)entity;
		// step 0: lock component for concurrency
		// TODO : verouiller le conteneur
		// TODO : extends save for all property (not only those in read-only mode)
		
		// step 1 : save current observable value
		for (GProperty property : contract.getProperties()) {
			if(property.getAccess().canRead()) {
				GIRead read = new GIRead(property.getName());
				AExpression call = (AExpression) read.visit(this); 
				AIInitialisation init = new AIInitialisation(ident("sav_"+property.getName()),type(property.getType()),call);
				seq.addInstruction(init);
			}
		}
		
		// step 2 : change the underlying component
		AIConvertType conv = new AIConvertType((AIType) visitType(entity.getNameAsType()),new AIVariable(ident("component")));
		AIAffectation affect = new AIAffectation(new AIVariable(idcomponent),conv);
		seq.addInstruction(affect);
		
		// step 3 : affect to the new  
		for (GProperty property : contract.getProperties()) {
			if(property.getAccess().canRead()) {
				AIVariable var = new AIVariable(ident("sav_"+property.getName()));

				AICall call = new AICall(ident("core_set_properties"));
				call.addArgument(new AIString(property.getName()));
				call.addArgument(var);

				AIVariable comp = new AIVariable(idcomponent);

				AIInLabel inlab = new AIInLabel(comp,call);
				seq.addInstruction(new AIInstExpression(inlab));
			}
		}
		return method;
	}
	
	protected void makeDefaultConstructor() throws TamagoCCException {
		constructor = new AConstructor(ident(ast.getName()),AIVisibility.PUBLIC_VISIBILITY);
		constructor.setComment(new AILongComment("Don't modify this constructor !\n"));
		AIParameter param = new AIParameter(ident("component"),component.getType());
		constructor.addParameter(param);
		
		AISequence body = new AISequence();
		
		// on affecte le composant encapsule
		AIConvertType conv = new AIConvertType((AIType) visitType(entity.getNameAsType()),new AIVariable(ident("component")));
		AIAffectation affect = new AIAffectation(new AIVariable(idcomponent),conv);
		body.addInstruction(affect);
		
		AINoInstruction debutuser = new AINoInstruction();
		debutuser.setComment(new AIInlineComment("----- if necessary you can add some code under this ligne ----"));
		body.addInstruction(debutuser);
		
		constructor.setBody(body);
		ast.addMethod(constructor);
	}

	
	
}
