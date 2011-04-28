/**
 * 
 */
package tamagocc.generator;

import java.util.Iterator;

import tamagocc.ast.api.AEntity;
import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AImplements;
import tamagocc.ast.api.AInLabel;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AMethod;
import tamagocc.ast.api.ANamespace;
import tamagocc.ast.api.AParameter;
import tamagocc.ast.api.ATamagoNew;
import tamagocc.ast.api.AThrowsException;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVariable;
import tamagocc.ast.api.AVisibility;
import tamagocc.ast.impl.AConstructor;
import tamagocc.ast.impl.AIAffectation;
import tamagocc.ast.impl.AIBool;
import tamagocc.ast.impl.AICall;
import tamagocc.ast.impl.AIConvertType;
import tamagocc.ast.impl.AIDocComment;
import tamagocc.ast.impl.AIIdent;
import tamagocc.ast.impl.AIIfThenElse;
import tamagocc.ast.impl.AIImplements;
import tamagocc.ast.impl.AIInLabel;
import tamagocc.ast.impl.AIInlineComment;
import tamagocc.ast.impl.AIInstExpression;
import tamagocc.ast.impl.AIInteger;
import tamagocc.ast.impl.AILongComment;
import tamagocc.ast.impl.AIMemberVariable;
import tamagocc.ast.impl.AIMethod;
import tamagocc.ast.impl.AIModule;
import tamagocc.ast.impl.AINamespace;
import tamagocc.ast.impl.AINil;
import tamagocc.ast.impl.AINoInstruction;
import tamagocc.ast.impl.AIParameter;
import tamagocc.ast.impl.AIProperty;
import tamagocc.ast.impl.AIReal;
import tamagocc.ast.impl.AIReturn;
import tamagocc.ast.impl.AISequence;
import tamagocc.ast.impl.AIString;
import tamagocc.ast.impl.AITamagoNew;
import tamagocc.ast.impl.AIThrowException;
import tamagocc.ast.impl.AIThrowsException;
import tamagocc.ast.impl.AIType;
import tamagocc.ast.impl.AIVariable;
import tamagocc.ast.impl.AIVisibility;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GBind;
import tamagocc.generic.api.GComponentContainer;
import tamagocc.generic.api.GCompositeContainer;
import tamagocc.generic.api.GExtendService;
import tamagocc.generic.api.GImplements;
import tamagocc.generic.api.GInstantiateComponent;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GNamespace;
import tamagocc.generic.api.GParameter;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GProvide;
import tamagocc.generic.api.GRequire;
import tamagocc.generic.api.GServiceInterface;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.util.TamagoCCGenUtil;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCGeneratorSkel extends TamagoCCGeneratorCommon implements TamagoCCGVisitor  {

	private AThrowsException ate;
	
	/**
	 * @param entity
	 * @throws TamagoCCException 
	 */
	public TamagoCCGeneratorSkel(TamagoCCIGenerator owner,GTamagoEntity entity) throws TamagoCCException {
		super(owner,entity);
		ast.setComment(new AILongComment(TamagoCCGenUtil.headerSkeletonFile(entity.getName())));
		
		ate = new AIThrowsException((AIType) AIType.generateType("tamago.TamagoException"));
		ast.setName(owner.getBuilder().generateSkeletonName((GComponentContainer)entity));
		ast.addMethod(Bind());
		ast.addMethod(set_core_property());
		ast.setCategory(AEntity.CLASS);
		ast.addUsedNamespaces(new AINamespace("tamago"));
		ast.addUsedNamespaces(new AINamespace("tamago.ext.tamagocc"));
		
		AIImplements impl = new AIImplements((AType)visitType(entity.getNameAsType()));
		ast.addImplement(impl);
	}
	
	
	
	public static AInstruction emptyBody(AMethod m) throws TamagoCCException {
		AIReturn renvoie = new AIReturn();
		renvoie.setComment(new AIInlineComment("TODO : complete this method"));		
		switch(m.getType().getCategoryType()) {
		case BOOL:
			renvoie.setExpression(new AIBool(false));
			break;
		case INTEGER:
			renvoie.setExpression(new AIInteger(0));
			break;
		case ARRAY:
		case OBJECT:
			renvoie.setExpression(new AINil());
			break;
		case REAL:
			renvoie.setExpression(new AIReal(0.0));
			break;
		case STRING:
			renvoie.setExpression(new AIString(""));
			break;
		case VOID:
			AINoInstruction emptybody = new AINoInstruction();
			emptybody.setComment(new AIInlineComment("TODO : complete this method"));
			return emptybody;
		default:
			throw new TamagoCCException("Unknow type");
		}
		return renvoie;
	}

	public Object visitComponent(GComponentContainer component) throws TamagoCCException {
		AConstructor constructor = new AConstructor(ident(ast.getName()),AIVisibility.PUBLIC_VISIBILITY);
		ast.addMethod(constructor);
		
		Iterator<GProperty> properties = component.getProperties().iterator();
		while(properties.hasNext()) {
			GProperty property = properties.next();
			property.visit(this);
		}
		
		
		Iterator<GProvide> provides = component.getAllProvide();
		while(provides.hasNext()) {
			GProvide es = provides.next();
			es.visit(this);
		}
		
		Iterator<GMethod> methods = component.getUniqueMethods().iterator();
		while(methods.hasNext()) {
			GMethod declmeth = methods.next();
			declmeth.visit(this);
		}
		
		Iterator<GRequire> requires = component.getAllRequire();
		while(requires.hasNext()) {
			GRequire declreq = requires.next();
			declreq.visit(this);
		}
		
		// on indique aussi les elements implementant le sous-type
		for (GImplements impls : component.getImplements()) {
			AImplements gimpls = (AImplements) impls.visit(this);
			ast.addImplement(gimpls);
		}
		
		for(GNamespace ns : component.getNamespaces()) {
			ANamespace ans = (ANamespace) ns.visit(this);
			ast.addUsedNamespaces(ans);
		}
		
		return ast;
	}

	public Object visitComposite(GCompositeContainer composite) throws TamagoCCException {
		
		Iterator<GProperty> properties = composite.getProperties().iterator();
		while(properties.hasNext()) {
			GProperty property = properties.next();
			property.visit(this);
		}
		
		
		Iterator<GProvide> provides = composite.getAllProvide();
		while(provides.hasNext()) {
			GProvide es = provides.next();
			es.visit(this);
		}
		
		Iterator<GMethod> methods = composite.getUniqueMethods().iterator();
		while(methods.hasNext()) {
			GMethod declmeth = methods.next();
			declmeth.visit(this);
		}
		
		Iterator<GRequire> requires = composite.getAllRequire();
		while(requires.hasNext()) {
			GRequire declreq = requires.next();
			declreq.visit(this);
		}
		
		// on indique aussi les elements implementant le sous-type
		for (GImplements impls : composite.getImplements()) {
			AImplements gimpls = (AImplements) impls.visit(this);
			ast.addImplement(gimpls);
		}
		
		for(GNamespace ns : composite.getNamespaces()) {
			ANamespace ans = (ANamespace) ns.visit(this);
			ast.addUsedNamespaces(ans);
		}
		makeConstructorComposite();
		
		return ast;
	}
	
	public void makeConstructorComposite() throws TamagoCCException {
		AConstructor cons = new AConstructor(new AIIdent("constructor"),AIVisibility.PUBLIC_VISIBILITY);
		AISequence body = new AISequence();
		cons.setBody(body);
		
		AINoInstruction info = new AINoInstruction();
		info.setComment(new AIInlineComment("Instantiate components by the framework"));
		body.addInstruction(info);
		
		GCompositeContainer entitycomposite = (GCompositeContainer)entity;
		Iterator<GInstantiateComponent> listDefinitions = entitycomposite.getInstatiateComponent();
		while(listDefinitions.hasNext()) {
			GInstantiateComponent inst = (GInstantiateComponent)listDefinitions.next();
			
			body.addInstruction((AInstruction)inst.visit(this));
		}
		
		info = new AINoInstruction();
		info.setComment(new AIInlineComment("End instanciate\n\n\n"));
		body.addInstruction(info);
		
		info = new AINoInstruction();
		info.setComment(new AIInlineComment("Binding of all components declared in specifications"));
		body.addInstruction(info);
		
		Iterator<GBind> listBind = entitycomposite.getBinds();
		while(listBind.hasNext()) {
			GBind bind = (GBind)listBind.next();
			body.addInstruction((AInstruction)bind.visit(this));
		}
		info = new AINoInstruction();
		info.setComment(new AIInlineComment("End binding"));
		ast.addMethod(cons);
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitBind(tamagocc.generic.api.GBind)
	 */
	public Object visitBind(GBind bind) throws TamagoCCException {
		String provider = bind.getProvider();
		String requirer = bind.getRequirer();
		String label = bind.getLabel();
		
		String requirermethod = "get"+propertyToMethod(requirer); // ici le requirer a le meme nom
		String providermethod = "get"+propertyToMethod(provider);
		
		AICall callrequirer = new AICall(ident(requirermethod));
		AICall callprovider = new AICall(ident(providermethod));
		
		AICall callbinder = new AICall(ident("Bind"));
		callbinder.addArgument(callrequirer);
		callbinder.addArgument(new AIString(label));
		callbinder.addArgument(callprovider);
		
		// Call to the platform
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
		AIAffectation affectation = new AIAffectation(var,tamagonew);
		return affectation;
	}
	
	
	public Object visitExtendService(GExtendService extendservice) throws TamagoCCException {
		if(extendservice.isRefined()) {
			AIImplements impl = new AIImplements(extendservice.getServiceName(),module(extendservice.getModule()));
			ast.addImplement(impl);
		}
		extendservice.getService().visit(this);
		return null;
	}

	public Object visitMethod(GMethod method) throws TamagoCCException {
		AType type= type(method.getType());
		AIdent ident = ident(method.getName());
		AVisibility visibility = AIVisibility.PUBLIC_VISIBILITY;
		
		AIMethod m = new AIMethod(AMethod.IMPLEMENTED,ident,type,visibility);
		
		m.addThrowsException(ate);		
		Iterator<GParameter> parameters = method.getParameters();
		while(parameters.hasNext()) {
			GParameter parameter = parameters.next();
			AParameter aparam = (AParameter)parameter.visit(this);
			m.addParameters(aparam);
		}
		
		m.setBody(emptyBody(m));
		
		ast.addMethod(m);
		return m;
	}
	
	public Object visitProperty(GProperty property) throws TamagoCCException {
		/*String part = propertyToMethod(property.getName());
		AType type = type(property.getType());
		AVisibility visibility = AIVisibility.PUBLIC_VISIBILITY;
		
		if(property.getAccess().canRead()) {
			AIdent name = ident("get"+part);
			ADocComment comment = new AIDocComment("Getter of the property "+ property.getName());
			AIMethod getter = new AIMethod(AMethod.IMPLEMENTED,name,type,visibility);
			getter.setComment(comment);
			getter.setBody(emptyBody(getter));
			ast.addMethod(getter);
		}
		
		if(property.getAccess().canWrite()) {
			AIdent name = ident("set"+part);
			AType vtype = new AIType(CatType.VOID,"void");
			ADocComment comment = new AIDocComment("Setter of the property "+ property.getName());
			AIMethod setter = new AIMethod(AMethod.IMPLEMENTED,name,vtype,visibility);
			AIParameter parameter = new AIParameter(ident(property.getName()),type);
			setter.addParameters(parameter);
			
			setter.setComment(comment);
			setter.setBody(emptyBody(setter));
			ast.addMethod(setter);
		}*/

		AIProperty prop = new AIProperty(true,property.getName(),(AType) property.getType().visit(this),
				property.getAccess().canRead(),property.getAccess().canWrite());
		this.ast.addProperty(prop);
		return null;
	}

	public Object visitProvide(GProvide provide) throws TamagoCCException {
		/* TODO: DONE inutile car c fait dans l'interface du composant 
		 * AIImplements impl = new AIImplements((AType) visitType(provide.getNameAsType()));
		ast.addImplement(impl);*/
		
		
		provide.getService().visit(this);
		
		AINamespace ns = new AINamespace(provide.getModule().getFullModule(),provide.getName());
		ast.addUsedNamespaces(ns);
		
		for(GNamespace nsp : provide.getService().getNamespaces()) {
			ANamespace ans = (ANamespace) nsp.visit(this);
			ast.addUsedNamespaces(ans);
		}
				
		return null;
	}

	public Object visitRequire(GRequire require) throws TamagoCCException {
		
		AIdent ident = new AIIdent(require.getLabel());
		//GServiceInterface service = (GServiceInterface)TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(require.getServiceName(),require.getServiceModule());
		//String fullname = owner.getBuilder().generateInterfaceName(service);
		AType type = (AType) visitType(require.getNameAsType());//(AIType) AIType.generateType(fullname); 
		
		// Declare la reference au sein du composant
		AIMemberVariable member = new AIMemberVariable(ident,type,AIVisibility.PRIVATE_VISIBILITY);
		member.setComment(new AIInlineComment("Don't manage this variable, let the framework manage it."));
		ast.addVariablesMembers(member);
		
		
		
		AIMethod accesseur = new AIMethod(AMethod.IMPLEMENTED,ident,type,AIVisibility.PUBLIC_VISIBILITY);
		AVariable var = new AIVariable(ident);
		AIReturn renvoie = new AIReturn(var);
		renvoie.setComment(new AIInlineComment("DON'T MODIFY THIS METHOD"));
		accesseur.setBody(renvoie);
		ast.addMethod(accesseur);
		
		
		AIdent id = ident("set"+require.getLabel());
		AIMethod modifieur = new AIMethod(AMethod.IMPLEMENTED,id,(AIType) AIType.generateType("void"),AIVisibility.PUBLIC_VISIBILITY);
		AIdent iparam = ident("tamago_param");
		AIParameter param = new AIParameter(iparam,type);
		modifieur.addParameters(param);
		
		AIAffectation affect = new AIAffectation(var,new AIVariable(iparam));
		affect.setComment(new AIInlineComment("DON'T MODIFY THIS METHOD"));
		modifieur.setBody(affect);
		ast.addMethod(modifieur);
		
		AINamespace ns = new AINamespace(require.getServiceModule().getFullModule(),require.getServiceName());
		ast.addUsedNamespaces(ns);
		
		return null;
	}

	public Object visitService(GServiceInterface service) throws TamagoCCException {
		// a priori inutile
		
		return null;
	}

	
	public AMethod Bind() {
		AISequence body = new AISequence();
		AIdent idlabel = ident("label");
		AIdent idservice = ident("service");
		AIMethod method = new AIMethod(AMethod.IMPLEMENTED,ident("bind"),(AIType) AIType.generateType("void"),AIVisibility.PUBLIC_VISIBILITY);
		method.setComment(new AIInlineComment("DON'T MODIFY THIS METHOD"));
		method.addParameters(new AIParameter(idlabel,(AIType) AIType.generateType("string")));
		method.addParameters(new AIParameter(idservice,(AIType) AIType.generateType("TamagoCCService")));
		AIThrowsException aste = new AIThrowsException((AIType) AIType.generateType("ServiceBindException"));
		method.addThrowsException(aste);
		method.setBody(body);
		// debut du corps
		
		Iterator<GRequire> requires = ((GComponentContainer)entity).getAllRequire();
		while(requires.hasNext()) {
			GRequire require = requires.next();
			AIString str = new AIString(require.getLabel());
			
			// condition
			AICall equals = new AICall(ident("equals"));
			equals.addArgument(str);
			AIInLabel instr = new AIInLabel(variable(idlabel),equals);
			
			// consequence
			AIVariable vlabel = new AIVariable(ident(require.getLabel()));
			AIVariable vservice = new AIVariable(idservice);
			AIType mtype = (AIType) AIType.generateType(require.getServiceName());
			AIConvertType convert = new AIConvertType(mtype,vservice);
			
			
			AIAffectation affect = new AIAffectation(vlabel,convert);
			AIReturn ret = new AIReturn();
			AISequence consequence = new AISequence();
			consequence.addInstruction(affect);
			consequence.addInstruction(ret);
			
			AIIfThenElse ifthenelse = new AIIfThenElse(instr,consequence,AINoInstruction.getNoInstruction());
			body.addInstruction(ifthenelse);
		}
		
		// Cas particulier pour les bindings nommes
		
		// fin du corps
		AIThrowException ate = new AIThrowException((AIType) AIType.generateType("ServiceBindException"));
		ate.addArgument(new AIVariable(idlabel));
		
		body.addInstruction(ate);
		return method;
	}
	
	private AMethod set_core_property() {
		AIMethod method = new AIMethod(AMethod.IMPLEMENTED,ident("core_set_properties"),AIType.typeVOID,AIVisibility.PUBLIC_VISIBILITY);
		method.addParameters(new AIParameter(ident("property"),AIType.typeSTRING));
		method.addParameters(new AIParameter(ident("value"),(AIType) AIType.generateType("Object")));
		// --
		AINoInstruction cons = new AINoInstruction("Complete the affectation of the observable properties.");
		// --
		AISequence seq = new AISequence();
		for (GProperty prop : ((GTamago)entity).getProperties()) {
			AIString propname = new AIString(prop.getName());
			AICall equals = new AICall(ident("equals"));
			equals.addArgument(new AIVariable(ident("property")));
			
			AIInLabel propnameequals = new AIInLabel(propname,equals);
			
			AIIfThenElse flot = new AIIfThenElse(propnameequals,cons,AINoInstruction.getNoInstruction());
			seq.addInstruction(flot);
		}
		method.setBody(seq);
		// --
		return method;
	}
}
