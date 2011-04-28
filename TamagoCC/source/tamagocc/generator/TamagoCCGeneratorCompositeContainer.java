package tamagocc.generator;

import java.util.ArrayList;
import java.util.Iterator;

import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AImplements;
import tamagocc.ast.api.AInLabel;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AMethod;
import tamagocc.ast.api.ANamespace;
import tamagocc.ast.api.ATamagoNew;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVariable;
import tamagocc.ast.impl.AConstructor;
import tamagocc.ast.impl.AIAffectation;
import tamagocc.ast.impl.AICall;
import tamagocc.ast.impl.AIConvertType;
import tamagocc.ast.impl.AIDocComment;
import tamagocc.ast.impl.AIImplements;
import tamagocc.ast.impl.AIInLabel;
import tamagocc.ast.impl.AIInitialisation;
import tamagocc.ast.impl.AIInlineComment;
import tamagocc.ast.impl.AIInstExpression;
import tamagocc.ast.impl.AIInteger;
import tamagocc.ast.impl.AILongComment;
import tamagocc.ast.impl.AIMemberVariable;
import tamagocc.ast.impl.AIMethod;
import tamagocc.ast.impl.AIModule;
import tamagocc.ast.impl.AINamespace;
import tamagocc.ast.impl.AINewArray;
import tamagocc.ast.impl.AINoInstruction;
import tamagocc.ast.impl.AIParameter;
import tamagocc.ast.impl.AIReturn;
import tamagocc.ast.impl.AISequence;
import tamagocc.ast.impl.AIString;
import tamagocc.ast.impl.AITamagoNew;
import tamagocc.ast.impl.AIType;
import tamagocc.ast.impl.AIVariable;
import tamagocc.ast.impl.AIVisibility;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GBind;
import tamagocc.generic.api.GCompositeContainer;
import tamagocc.generic.api.GExport;
import tamagocc.generic.api.GExtendService;
import tamagocc.generic.api.GImplements;
import tamagocc.generic.api.GInstantiateComponent;
import tamagocc.generic.api.GInvariant;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GNamespace;
import tamagocc.generic.api.GPercolator;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GProvide;
import tamagocc.generic.api.GRequire;
import tamagocc.generic.api.GTamagoEntity;

/**
 * This generator is dedicated for composite container.
 * It generates all functionality indirection, and
 *  code verification.
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCGeneratorCompositeContainer extends TamagoCCGeneratorComponentContainer implements TamagoCCGVisitor {

	/**
	 * 
	 */
	public TamagoCCGeneratorCompositeContainer(TamagoCCIGenerator owner, GTamagoEntity entity,GPercolator percolator)
		throws TamagoCCException
	{
		super(owner, entity,percolator);
				
//		makeDefaultConstructor(); appele par le parent
		
		ast.addMethod(GetExportations((GCompositeContainer)entity));	
	}
	
	protected void makeDefaultConstructor() throws TamagoCCException {
		constructor = new AConstructor(ident(ast.getName()),AIVisibility.PUBLIC_VISIBILITY);
		constructor.setComment(new AILongComment("Don't modify this constructor !\n"));
		AIParameter param = new AIParameter(ident("component"),component.getType());
		constructor.addParameter(param);
		
		AISequence body = new AISequence();
		
		// on affecte le composant encapsule
		AIConvertType conv = new AIConvertType((AIType) AIType.generateType(entity.getName()),new AIVariable(ident("component")));
		AIAffectation affect = new AIAffectation(new AIVariable(idcomponent),conv);
		body.addInstruction(affect);
		
		
		if(hasBehavior) {
			//ast.addUsedNamespaces(new AINamespace("tamago.ext.tamagocc"));
			ast.addUsedNamespaces(new AINamespace("java.util","Hashtable"));
			ast.addUsedNamespaces(new AINamespace("java.util","ArrayList"));
			behavior.generateConstructor(body);
			ast.addMethod(behavior.makeFunctionFetch());
		}
		
		
		// Partie pour la declaration des bindings
		
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
		
		
		
		
		// Partie pour les utilisateurs
		AINoInstruction debutuser = new AINoInstruction();
		debutuser.setComment(new AIInlineComment("----- if necessary you can add some code under this ligne ----"));
		body.addInstruction(debutuser);
		
		constructor.setBody(body);
		ast.addMethod(constructor);
		
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitBind(tamagocc.generic.api.GBind)
	 */
	public Object visitBind(GBind bind) throws TamagoCCException {
		String provider = bind.getProvider();
		String requirer = bind.getRequirer();
		String label = bind.getLabel();
		
		// TODO : urgent ici les composites ne correspondent pas a ce qu'il faut
		// il semblerait que je doivent recuperer la liste des identifiants des labels
		// et voir en consequence.
		
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
	 * @see tamagocc.generic.TamagoCCGVisitor#visitComposite(tamagocc.generic.api.GCompositeContainer)
	 */
	public Object visitComposite(GCompositeContainer composite)
			throws TamagoCCException {
		Iterator<GProperty> properties = composite.getProperties().iterator();
		while(properties.hasNext()) {
			GProperty property = properties.next();
			property.visit(this);
		}
	
		// les invariants doivent traite avant les methodes
		Iterator<GInvariant> invariants = composite.getInvariants().iterator();
		while(invariants.hasNext()) {
			GInvariant inv = invariants.next();
			inv.visit(this);
		}
		
		
		Iterator<GMethod> methods = composite.getUniqueMethods().iterator();
		while(methods.hasNext()) {
			GMethod declmeth = methods.next();
			declmeth.visit(this);
			
		}
		
		Iterator<GProvide> provides = composite.getAllProvide();
		while(provides.hasNext()) {
			GProvide es = provides.next();
			es.visit(this);
		}
		
		Iterator<GRequire> requires = composite.getAllRequire();
		while(requires.hasNext()) {
			requires.next().visit(this);
		}
		
		/*Iterator<GInstantiateComponent> definitions = composite.getInstatiateComponent();
		while(definitions.hasNext()) {
			definitions.next().visit(this);
		}	*/	
		
		// on indique aussi les elements implementant le sous-type
		for (GImplements impls : composite.getImplements()) {
			AImplements gimpls = (AImplements) impls.visit(this);
			ast.addImplement(gimpls);
		}
		
		for(GNamespace ns : composite.getNamespaces()) {
			ANamespace ans = (ANamespace) ns.visit(this);
			ast.addUsedNamespaces(ans);
		}
		return ast;
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
		if(extendservice.isRefined()) {
			AIImplements impl = new AIImplements(extendservice.getServiceName(),module(extendservice.getModule()));
			ast.addImplement(impl);
		}
		extendservice.getService().visit(this);
		return null;
	}


	
	private AMethod GetExportations(GCompositeContainer container) throws TamagoCCException {
		AIdent idvar = ident("rs");
		AIdent idlabel = ident("getExportations");
		AType rettype= AIType.generateType("tamago.ext.tamagocc.TamagoCCService[]");//new AIType(new AIType(CatType.OBJECT,"tamago.ext.tamagocc.TamagoCCService"));
		AType typeservice = AIType.generateType("tamago.ext.tamagocc.TamagoCCService");//new AIType( new AIType(CatType.OBJECT,"tamago.ext.tamagocc.TamagoCCService"));
		
		AIMethod method = new AIMethod(AMethod.IMPLEMENTED,idlabel,rettype,AIVisibility.PUBLIC_VISIBILITY);
		Iterator<GExport> exportations = container.getExportations();
		ArrayList<GExport> ex = new ArrayList<GExport>();
		while(exportations.hasNext()) {
			GExport export = (GExport)exportations.next();
			ex.add(export);
		}
		
		AISequence body = new AISequence();
		
		AINewArray nouveauTableau = new AINewArray(typeservice,ex.size());
		AIInitialisation init = new AIInitialisation(idvar,rettype,nouveauTableau);
		body.addInstruction(init);
		
		for(int i=0;i < ex.size();i++) {
			AIVariable ivar = new AIVariable(idvar, new AIInteger(i));
			GExport export = (GExport)ex.get(i);
			AExpression expr = (AExpression) export.getProvider().visit(this);
			
			AIAffectation iaff = new AIAffectation(ivar,expr);
			body.addInstruction(iaff);
		}
		
		AIReturn renvoietab = new AIReturn(new AIVariable(idvar));
		body.addInstruction(renvoietab);
		// Fin du corps
		
		method.setBody(body);
		return method;
	}
	
}
