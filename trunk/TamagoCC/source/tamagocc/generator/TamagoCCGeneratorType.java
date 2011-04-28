/**
 * 
 */
package tamagocc.generator;

import java.util.Iterator;

import tamagocc.api.TOpeName;
import tamagocc.ast.api.AEntity;
import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AMethod;
import tamagocc.ast.api.AReturn;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVariable;
import tamagocc.ast.impl.AConstructor;
import tamagocc.ast.impl.AIAffectation;
import tamagocc.ast.impl.AIBool;
import tamagocc.ast.impl.AICall;
import tamagocc.ast.impl.AIConvertType;
import tamagocc.ast.impl.AIDocComment;
import tamagocc.ast.impl.AIIfThenElse;
import tamagocc.ast.impl.AIImplements;
import tamagocc.ast.impl.AIInLabel;
import tamagocc.ast.impl.AIInitialisation;
import tamagocc.ast.impl.AIInstExpression;
import tamagocc.ast.impl.AIInteger;
import tamagocc.ast.impl.AIMethod;
import tamagocc.ast.impl.AIModule;
import tamagocc.ast.impl.AINamespace;
import tamagocc.ast.impl.AINew;
import tamagocc.ast.impl.AINewArray;
import tamagocc.ast.impl.AINoInstruction;
import tamagocc.ast.impl.AIOperator;
import tamagocc.ast.impl.AIParameter;
import tamagocc.ast.impl.AIReturn;
import tamagocc.ast.impl.AISelf;
import tamagocc.ast.impl.AISequence;
import tamagocc.ast.impl.AIString;
import tamagocc.ast.impl.AIType;
import tamagocc.ast.impl.AIVariable;
import tamagocc.ast.impl.AIVisibility;
import tamagocc.ast.impl.AIWhile;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GAssemblyContainer;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GComponentContainer;
import tamagocc.generic.api.GCompositeContainer;
import tamagocc.generic.api.GExport;
import tamagocc.generic.api.GExtendService;
import tamagocc.generic.api.GPercolator;
import tamagocc.generic.api.GProvide;
import tamagocc.generic.api.GRead;
import tamagocc.generic.api.GRequire;
import tamagocc.generic.api.GServiceInterface;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.util.NilIterator;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class TamagoCCGeneratorType extends TamagoCCGeneratorCommon implements TamagoCCGVisitor {

	private AIMethod getname; 
	private AIMethod getimplementation;
	private AIType stringtype;
	private AIType classtype;
	private AConstructor constructor;
	private AISequence seqconstructor;
	
	
	/**
	 * 
	 */
	public TamagoCCGeneratorType(TamagoCCIGenerator owner,GTamagoEntity entity) {
		super(owner,entity);
		ast.setName(entity.getName()+"TamagoCCType");
		ast.setCategory(AEntity.CLASS);
		stringtype = (AIType) AIType.generateType("String");
		classtype = (AIType) AIType.generateType("Class");
		ast.setParametrizedTypes(new AType[0]);
		
		AIImplements implementTamagoCCType = new AIImplements("TamagoCCType",new AIModule("tamago.ext.tamagocc"));
		ast.addImplement(implementTamagoCCType);
		
		AINamespace ns = new AINamespace("tamago.ext.tamagocc","TamagoCCType");
		ast.addUsedNamespaces(ns);
		
		ns = new AINamespace("tamago.ext.tamagocc","TamagoCCPercolator");
		ast.addUsedNamespaces(ns);
		
		constructor = new AConstructor(ident(ast.getName()),AIVisibility.PUBLIC_VISIBILITY);
		seqconstructor = new AISequence();
		constructor.setBody(seqconstructor);
		
		ast.addMethod(constructor);
		
		creatGetNameMethod();
		creatGetModuleMethod();
		creatGetImplementation();
		creatIsSuperType();
		creatIsStrictSuperType();
		creatIsSubType();
		creatIsStrictSubType();
		creatisSameType();
		creatGetExtends();
		creatGetTamagoType();
		creatGetAvailablePercolator();
	}
	
	private void creatGetModuleMethod() {
		AIMethod getmodule = new AIMethod(AMethod.IMPLEMENTED,ident("getModule"),stringtype,AIVisibility.PUBLIC_VISIBILITY);
		AIString smodule = new AIString(entity.getModule().getFullModule());
		AReturn ret = new AIReturn(smodule);
		getmodule.setBody(ret);		
		ast.addMethod(getmodule);
	}

	
	private void creatGetTamagoType() {
		AIMethod gettamagotype = new AIMethod(AMethod.IMPLEMENTED,ident("getTamagoCCType"),(AIType) AIType.generateType("int"),AIVisibility.PUBLIC_VISIBILITY);
		AIVariable variable = null;
		switch(entity.getCategoryEntity()) {
		case GTamagoEntity.TAMAGO_ASSEMBLY:
			variable = new AIVariable(ident("TYPE_ASSEMBLY"));
			break;
		case GTamagoEntity.TAMAGO_COMPONENT:
			variable = new AIVariable(ident("TYPE_COMPONENT"));
			break;
		case GTamagoEntity.TAMAGO_COMPOSITE:
			variable = new AIVariable(ident("TYPE_COMPOSITE"));
			break;
		case GTamagoEntity.TAMAGO_SERVICE:
			variable = new AIVariable(ident("TYPE_SERVICE"));
			break;
		}
		
		AIReturn ret = new AIReturn(variable);
		gettamagotype.setBody(ret);
		
		ast.addMethod(gettamagotype);
	}
	
	
	private void creatGetExtends()  {
		AIMethod getextends = new AIMethod(AMethod.IMPLEMENTED,ident("getExtends"),(AIType) AIType.generateType("java.util.Iterator"),AIVisibility.PUBLIC_VISIBILITY);
		AISequence sequence = new AISequence();
		AIdent idlist = ident("list");
		AVariable list = variable(idlist);
		AICall iterator = new AICall(ident("iterator"));
		AIType typelist = (AIType) AIType.generateType("java.util.ArrayList");
		
		AINew inew = new AINew(typelist);
		AIInitialisation init = new AIInitialisation(idlist,typelist,inew);
		sequence.addInstruction(init);
		
		switch(entity.getCategoryEntity()) {
		case GTamagoEntity.TAMAGO_ASSEMBLY:
			break;
		case GTamagoEntity.TAMAGO_COMPONENT:
			searchProvides((GComponentContainer)entity,sequence);
			break;
		case GTamagoEntity.TAMAGO_COMPOSITE:
			searchProvides((GCompositeContainer)entity,sequence);
			break;
		case GTamagoEntity.TAMAGO_SERVICE:
			searchService((GServiceInterface)entity,sequence);
			break;
		}
		
		
		AIInLabel listiterator = new AIInLabel(list,iterator);
		AReturn ret = new AIReturn(listiterator);
		
		sequence.addInstruction(ret);
		getextends.setBody(sequence);
		ast.addMethod(getextends);
	}
	
	void searchProvides(GComponentContainer component,AISequence sequence) {
		Iterator<GProvide> provides = component.getAllProvide();
		while(provides.hasNext()) {
			GProvide provide = provides.next();
			AINamespace ns = new AINamespace(provide.getModule().getFullModule(),provide.getName()+"TamagoCCType");
			ast.addUsedNamespaces(ns);
			AINew itemList = new AINew((AIType) AIType.generateType(provide.getName()+"TamagoCCType"));
			AICall add = new AICall(ident("add"));
			add.addArgument(itemList);
			AIInLabel inlabel = new AIInLabel(variable("list"),add);
			sequence.addInstruction(new AIInstExpression(inlabel));
		}
	}
	
	void searchService(GServiceInterface service,AISequence sequence) {
		Iterator<GExtendService> refines = service.getRefines();
		while(refines.hasNext()) {
			GExtendService refine = refines.next();
			AINamespace ns = new AINamespace(refine.getModule().getFullModule(),refine.getServiceName()+"TamagoCCType");
			ast.addUsedNamespaces(ns);
			AINew itemList = new AINew((AIType) AIType.generateType(refine.getServiceName()+"TamagoCCType"));
			AICall add = new AICall(ident("add"));
			add.addArgument(itemList);
			AIInLabel inlabel = new AIInLabel(variable("list"),add);
			sequence.addInstruction(new AIInstExpression(inlabel));
		}
	}
	
	private void creatGetNameMethod() {
		getname = new AIMethod(AMethod.IMPLEMENTED,ident("getName"),stringtype,AIVisibility.PUBLIC_VISIBILITY);
		AIString string = new AIString(ast.getName());
		AIReturn re = new AIReturn(string);
		getname.setBody(re);
		getname.setComment(new AIDocComment("Return the name of the entity"));
		ast.addMethod(getname);
	}
	
	private void creatGetImplementation() {
		getimplementation = new AIMethod(AMethod.IMPLEMENTED,ident("getImplementation"),classtype,AIVisibility.PUBLIC_VISIBILITY);
		AVariable var = new AIVariable(ident("null"));
		AIReturn ret = new AIReturn(var);
		getimplementation.setBody(ret);
		getimplementation.setComment(new AIDocComment("Return the class of the service"));
				
		ast.addMethod(getimplementation);
	}

	private void creatIsSuperType() {
		AIMethod method = new AIMethod(AMethod.IMPLEMENTED,ident("isSuperType"),(AIType) AIType.generateType("bool"),AIVisibility.PUBLIC_VISIBILITY);
		AIParameter  service = new AIParameter(ident("service"),(AIType) AIType.generateType("TamagoCCType"));
		method.addParameters(service);
		
		AICall call = new AICall(ident("isSubType"));
		call.addArgument(new AISelf());
		
		AIInLabel inservice = new AIInLabel(variable("service"),call);
		AIReturn ret = new AIReturn(inservice);		
		method.setBody(ret);
		
		ast.addMethod(method);
	}
	
	private void creatIsStrictSuperType() {
		AIMethod method = new AIMethod(AMethod.IMPLEMENTED,ident("isStrictSuperType"),(AIType) AIType.generateType("bool"),AIVisibility.PUBLIC_VISIBILITY);
		AIParameter  service = new AIParameter(ident("service"),(AIType) AIType.generateType("TamagoCCType"));
		method.addParameters(service);
		
		AICall call = new AICall(ident("isStrictSubType"));
		call.addArgument(new AISelf());
		
		AIInLabel inservice = new AIInLabel(variable("service"),call);
		AIReturn ret = new AIReturn(inservice);		
		method.setBody(ret);
		
		ast.addMethod(method);
	}
	
	private void creatIsSubType() {
		AType servicetype = (AIType) AIType.generateType("TamagoCCType");
		AIMethod method = new AIMethod(AMethod.IMPLEMENTED,ident("isSubType"),(AIType) AIType.generateType("bool"),AIVisibility.PUBLIC_VISIBILITY);
		AIParameter  service = new AIParameter(ident("service"),servicetype);
		method.addParameters(service);
		
		
		AICall call = new AICall(ident("getExtends"));
		AIInitialisation init = new AIInitialisation(ident("iextends"),(AIType) AIType.generateType("java.util.Iterator"),call);
		
		AICall testsuite = new AICall(ident("hasNext"));
		AIInLabel inlabel = new AIInLabel(variable("iextends"),testsuite);
		
		// Premiere expression du corps
		AICall nextextend = new AICall(ident("next"));
		AIInLabel tinlabel = new AIInLabel(variable("iextends"),nextextend);
		AIConvertType convert = new AIConvertType(servicetype,tinlabel);
		AIInitialisation initserv = new AIInitialisation(ident("serv"),servicetype,convert);
		
		
		// Deuxieme expression du corps
		AVariable varservice = new AIVariable(ident("service"));
		
		AICall isSameType = new AICall(ident("isSameType"));
		isSameType.addArgument(varservice);
		AIInLabel serviceIsSameType = new AIInLabel(variable("serv"),isSameType);
		
		
		AICall isSubType = new AICall(ident("isSubType"));
		isSubType.addArgument(varservice);
		AIInLabel serviceIsSubType = new AIInLabel(variable("serv"),isSubType);
		
		AIOperator cond = new AIOperator(TOpeName.opOr);
		cond.addOperand(serviceIsSameType);
		cond.addOperand(serviceIsSubType);
		
		
		AIBool btrue = new AIBool(true);
		AIReturn rettrue = new AIReturn(btrue);
		
		AIIfThenElse iif = new AIIfThenElse(cond,rettrue,new AINoInstruction());
		
		// Reunion en une sequence
		AISequence seq = new AISequence();
		seq.addInstruction(initserv);
		seq.addInstruction(iif);
		
		AIWhile iwhile = new AIWhile(inlabel,seq);
		
		// Renvoie fals
		AIBool bfalse = new AIBool(false);
		AIReturn retfalse = new AIReturn(bfalse);
		
		// Corps complet 
		
		AISequence corps = new AISequence();
		corps.addInstruction(init);
		corps.addInstruction(iwhile);
		corps.addInstruction(retfalse);
		
		method.setBody(corps);
		ast.addMethod(method);
		// TODO : ajouter si le type courant est bien le type demande au sens large
	}
	
	private void creatisSameType() {
		AType servicetype = (AIType) AIType.generateType("TamagoCCType");
		AIMethod method = new AIMethod(AIMethod.IMPLEMENTED,ident("isSameType"),(AIType) AIType.generateType("bool"),AIVisibility.PUBLIC_VISIBILITY);
		AIParameter  service = new AIParameter(ident("service"),servicetype);
		method.addParameters(service);
		
		AICall thisgetname = new AICall(ident("getName"));
		AIInitialisation thisname = new AIInitialisation(ident("name"),(AIType) AIType.generateType("string"),thisgetname);
		
		AICall thisgetmodule = new AICall(ident("getModule"));
		AIInitialisation thismodule = new AIInitialisation(ident("module"),(AIType) AIType.generateType("string"),thisgetmodule);
		
		AICall servicegetname = new AICall(ident("getName"));
		AIInLabel inservicegetname = new AIInLabel(variable("service"),servicegetname);
		
		AICall servicegetmodule = new AICall(ident("getModule"));
		AIInLabel inservicegetmodule = new AIInLabel(variable("service"),servicegetmodule);
		
		
		AICall nameequals = new AICall(ident("equals"));
		nameequals.addArgument(inservicegetname);
		AIInLabel innameequals = new AIInLabel(variable("name"),nameequals);
		
		AICall moduleequals = new AICall(ident("equals"));
		moduleequals.addArgument(inservicegetmodule);
		AIInLabel inmoduleequals = new AIInLabel(variable("module"),moduleequals);
		
		AIOperator and = new AIOperator(TOpeName.opAnd);
		and.addOperand(inmoduleequals);
		and.addOperand(innameequals);
		
		AIReturn ret = new AIReturn(and);
		
		AISequence seq = new AISequence();
		seq.addInstruction(thisname);
		seq.addInstruction(thismodule);
		seq.addInstruction(ret);
		method.setBody(seq);
		ast.addMethod(method);
	}
	
	private void creatIsStrictSubType() {
		AType servicetype = (AIType) AIType.generateType("TamagoCCType");
		AIMethod method = new AIMethod(AMethod.IMPLEMENTED,ident("isStrictSubType"),(AIType) AIType.generateType("bool"),AIVisibility.PUBLIC_VISIBILITY);
		AIParameter  service = new AIParameter(ident("service"),servicetype);
		method.addParameters(service);
		
		
		AICall call = new AICall(ident("getExtends"));
		AIInitialisation init = new AIInitialisation(ident("iextends"),(AIType) AIType.generateType("java.util.Iterator"),call);
		
		AICall testsuite = new AICall(ident("hasNext"));
		AIInLabel inlabel = new AIInLabel(variable("iextends"),testsuite);
		
		// Premiere expression du corps
		AICall nextextend = new AICall(ident("next"));
		AIInLabel tinlabel = new AIInLabel(variable("iextends"),nextextend);
		AIConvertType convert = new AIConvertType(servicetype,tinlabel);
		AIInitialisation initserv = new AIInitialisation(ident("serv"),servicetype,convert);
		
		
		
		// Deuxieme expression du corps
		AVariable varservice = new AIVariable(ident("service"));
		
		AICall isSameType = new AICall(ident("isSameType"));
		isSameType.addArgument(varservice);
		AIInLabel serviceIsSameType = new AIInLabel(variable("serv"),isSameType);
		
		
		AICall isSubType = new AICall(ident("isSubType"));
		isSubType.addArgument(varservice);
		AIInLabel serviceIsSubType = new AIInLabel(variable("serv"),isSubType);
		
		AIOperator cond = new AIOperator(TOpeName.opOr);
		cond.addOperand(serviceIsSameType);
		cond.addOperand(serviceIsSubType);
		
		
		AIBool btrue = new AIBool(true);
		AIReturn rettrue = new AIReturn(btrue);
		
		AIIfThenElse iif = new AIIfThenElse(cond,rettrue,new AINoInstruction());
		
		// Reunion en une sequence
		AISequence seq = new AISequence();
		seq.addInstruction(initserv);
		seq.addInstruction(iif);
		
		AIWhile iwhile = new AIWhile(inlabel,seq);
		
		// Renvoie fals
		AIBool bfalse = new AIBool(false);
		AIReturn retfalse = new AIReturn(bfalse);
		
		// Corps complet 
		
		AISequence corps = new AISequence();
		corps.addInstruction(init);
		corps.addInstruction(iwhile);
		corps.addInstruction(retfalse);
		
		method.setBody(corps);
		ast.addMethod(method);
	}
	
	
	
	@SuppressWarnings("unchecked")
	private void creatGetAvailablePercolator() {
		AType percolatortype = (AIType) AIType.generateType("TamagoCCPercolator");
		AType percolatortypearray = AIType.generateType("TamagoCCPercolator[]");//new AIType(new AIType(CatType.OBJECT,"TamagoCCPercolator"));
		AIMethod method = new AIMethod(AMethod.IMPLEMENTED,ident("getAvailablePercolator"),percolatortypearray,AIVisibility.PUBLIC_VISIBILITY);
		int nbpercolator = 0;
		Iterator ps = new NilIterator();
		
		switch(entity.getCategoryEntity() ) {
		case GTamagoEntity.TAMAGO_COMPONENT:
			ps = ((GComponentContainer)entity).getAllowedPercolators();
			break;
		case GTamagoEntity.TAMAGO_COMPOSITE:
			ps = ((GCompositeContainer)entity).getAllowedPercolators();
			break;
		default: 
		}
		
		AISequence body =new AISequence();
		// -- debut
		AIdent varrenvoie = ident("renvoie");
		AINewArray newarray = new AINewArray(percolatortype,13);
		AIInitialisation init = new AIInitialisation(varrenvoie,percolatortypearray,newarray);
		
		body.addInstruction(init);
		
		while(ps.hasNext()) {
			GPercolator perco = (GPercolator)ps.next();
			AIVariable left = new AIVariable(varrenvoie, new AIInteger(nbpercolator));
			nbpercolator++;
			AINew newperco = new AINew(percolatortype);
			newperco.addArguments(new AIString(perco.getName()));
			
			AIAffectation affec = new AIAffectation(left,newperco);
			body.addInstruction(affec);
		}
		
		newarray.setTaille(nbpercolator);
		
		// -- fin
		
		AIReturn ret = new AIReturn(new AIVariable(varrenvoie));
		body.addInstruction(ret);
		method.setBody(body);
		ast.addMethod(method);
	}
	
	
	// ----------------------------------------------------------------------------------------------
	
	public AEntity getEntity() {
		return ast;
	}
	
	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitAssembly(tamagocc.generic.api.GAssemblyContainer)
	 */
	public Object visitAssembly(GAssemblyContainer assembly)
			throws TamagoCCException {
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitBehavior(tamagocc.generic.api.GBehavior)
	 */
	public Object visitBehavior(GBehavior behavior) throws TamagoCCException {
		return null;
	}
	
	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitComponent(tamagocc.generic.api.GComponentContainer)
	 */
	public Object visitComponent(GComponentContainer component)
			throws TamagoCCException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitComposite(tamagocc.generic.api.GCompositeContainer)
	 */
	public Object visitComposite(GCompositeContainer composite)
			throws TamagoCCException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitExport(tamagocc.generic.api.GExport)
	 */
	public Object visitExport(GExport export) throws TamagoCCException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitExtendService(tamagocc.generic.api.GExtendService)
	 */
	public Object visitExtendService(GExtendService extendservice)
			throws TamagoCCException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitProvide(tamagocc.generic.api.GProvide)
	 */
	public Object visitProvide(GProvide provide) throws TamagoCCException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitRead(tamagocc.generic.api.GRead)
	 */
	public Object visitRead(GRead read) throws TamagoCCException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitRequire(tamagocc.generic.api.GRequire)
	 */
	public Object visitRequire(GRequire require) throws TamagoCCException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitService(tamagocc.generic.api.GServiceInterface)
	 */
	public Object visitService(GServiceInterface service)
			throws TamagoCCException {
		// TODO Auto-generated method stub
		return null;
	}
}
