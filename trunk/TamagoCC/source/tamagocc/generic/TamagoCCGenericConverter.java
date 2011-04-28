/**
 * 
 */
package tamagocc.generic;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Stack;

import tamagocc.api.TAccess;
import tamagocc.api.TAllow;
import tamagocc.api.TAssembly;
import tamagocc.api.TAtPre;
import tamagocc.api.TBehavior;
import tamagocc.api.TBind;
import tamagocc.api.TBool;
import tamagocc.api.TCall;
import tamagocc.api.TCast;
import tamagocc.api.TCategory;
import tamagocc.api.TComponent;
import tamagocc.api.TComposite;
import tamagocc.api.TCondition;
import tamagocc.api.TDefinition;
import tamagocc.api.TExistColl;
import tamagocc.api.TExistRange;
import tamagocc.api.TExistSet;
import tamagocc.api.TExport;
import tamagocc.api.TExpression;
import tamagocc.api.TExtendService;
import tamagocc.api.TForallColl;
import tamagocc.api.TForallRange;
import tamagocc.api.TForallSet;
import tamagocc.api.TImplements;
import tamagocc.api.TInLabel;
import tamagocc.api.TInState;
import tamagocc.api.TIncludeService;
import tamagocc.api.TInteger;
import tamagocc.api.TInvariant;
import tamagocc.api.TLanguageExpr;
import tamagocc.api.TMethod;
import tamagocc.api.TNamespace;
import tamagocc.api.TNil;
import tamagocc.api.TNoContract;
import tamagocc.api.TNot;
import tamagocc.api.TObject;
import tamagocc.api.TOpeName;
import tamagocc.api.TOperator;
import tamagocc.api.TParameter;
import tamagocc.api.TPercolator;
import tamagocc.api.TProperty;
import tamagocc.api.TProvide;
import tamagocc.api.TRead;
import tamagocc.api.TReal;
import tamagocc.api.TRefineService;
import tamagocc.api.TRequire;
import tamagocc.api.TReturn;
import tamagocc.api.TService;
import tamagocc.api.TSet;
import tamagocc.api.TState;
import tamagocc.api.TString;
import tamagocc.api.TTamago;
import tamagocc.api.TTamagoEntity;
import tamagocc.api.TTransition;
import tamagocc.api.TType;
import tamagocc.api.TVariable;
import tamagocc.exception.TamagoCCException;
import tamagocc.exception.TamagoCCNotFoundState;
import tamagocc.generic.api.GAccess;
import tamagocc.generic.api.GAllow;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GBind;
import tamagocc.generic.api.GCategory;
import tamagocc.generic.api.GCondition;
import tamagocc.generic.api.GExistRange;
import tamagocc.generic.api.GExport;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GExtendService;
import tamagocc.generic.api.GImplements;
import tamagocc.generic.api.GInState;
import tamagocc.generic.api.GInstantiateComponent;
import tamagocc.generic.api.GInvariant;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GModule;
import tamagocc.generic.api.GNamespace;
import tamagocc.generic.api.GParameter;
import tamagocc.generic.api.GPercolator;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GProvide;
import tamagocc.generic.api.GRequire;
import tamagocc.generic.api.GReturn;
import tamagocc.generic.api.GServiceInterface;
import tamagocc.generic.api.GSet;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.generic.api.GTransition;
import tamagocc.generic.api.GType;
import tamagocc.generic.api.GVariable;
import tamagocc.generic.impl.GIAccess;
import tamagocc.generic.impl.GIAllow;
import tamagocc.generic.impl.GIAssemblyContainer;
import tamagocc.generic.impl.GIAtPre;
import tamagocc.generic.impl.GIBehavior;
import tamagocc.generic.impl.GIBind;
import tamagocc.generic.impl.GIBool;
import tamagocc.generic.impl.GICall;
import tamagocc.generic.impl.GICast;
import tamagocc.generic.impl.GICategory;
import tamagocc.generic.impl.GIComponentContainer;
import tamagocc.generic.impl.GICompositeContainer;
import tamagocc.generic.impl.GICondition;
import tamagocc.generic.impl.GIExistColl;
import tamagocc.generic.impl.GIExistRange;
import tamagocc.generic.impl.GIExistSet;
import tamagocc.generic.impl.GIExport;
import tamagocc.generic.impl.GIForallColl;
import tamagocc.generic.impl.GIForallRange;
import tamagocc.generic.impl.GIForallSet;
import tamagocc.generic.impl.GIImplements;
import tamagocc.generic.impl.GIInLabel;
import tamagocc.generic.impl.GIInState;
import tamagocc.generic.impl.GIIncludeService;
import tamagocc.generic.impl.GIInstantiateComponent;
import tamagocc.generic.impl.GIInteger;
import tamagocc.generic.impl.GIInvariant;
import tamagocc.generic.impl.GILanguageExpr;
import tamagocc.generic.impl.GIMethod;
import tamagocc.generic.impl.GIModule;
import tamagocc.generic.impl.GINamespace;
import tamagocc.generic.impl.GINil;
import tamagocc.generic.impl.GINoContract;
import tamagocc.generic.impl.GINot;
import tamagocc.generic.impl.GIOperator;
import tamagocc.generic.impl.GIParameter;
import tamagocc.generic.impl.GIPercolator;
import tamagocc.generic.impl.GIProperty;
import tamagocc.generic.impl.GIProvide;
import tamagocc.generic.impl.GIRead;
import tamagocc.generic.impl.GIReal;
import tamagocc.generic.impl.GIRefineService;
import tamagocc.generic.impl.GIRequire;
import tamagocc.generic.impl.GIReturn;
import tamagocc.generic.impl.GIServiceInterface;
import tamagocc.generic.impl.GISet;
import tamagocc.generic.impl.GIState;
import tamagocc.generic.impl.GIString;
import tamagocc.generic.impl.GITamago;
import tamagocc.generic.impl.GITransition;
import tamagocc.generic.impl.GIType;
import tamagocc.generic.impl.GIVariable;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.percolation.TamagoCCPercolation;
import tamagocc.util.TamagoCCSearchType;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCGenericConverter extends TamagoCCConverter implements
TamagoCCVisitor {


	private TTamagoEntity original_entity;
	private GTamagoEntity final_entity;

	/* cette variable sert a retenir la derniere entite visiste, en cas de null alors que l'on a besoin pour
	 * retrouver une information, cela genere une erreur*/
	private TTamagoEntity last_entity;
	/*
	 * pareil que precedement mais pour les methodes.
	 */
	private TMethod last_method;

	private GIMethod last_gmethod;

	private Hashtable<String, TType> env;

	private Stack<TInLabel> scopes;

	/**
	 * 
	 */
	public TamagoCCGenericConverter(TTamagoEntity entity) {
		this.original_entity = entity;
		last_entity = entity;
		final_entity = null;
		last_method = null;
		last_gmethod = null;
		env = new Hashtable<String, TType>();
		scopes = new Stack<TInLabel>();
	}

	/**
	 * @see tamagocc.generic.TamagoCCConverter#getGTamagoEntity()
	 */
	public GTamagoEntity getGTamagoEntity() throws TamagoCCException {
		if(final_entity == null) {
			final_entity = (GTamagoEntity)original_entity.visit(this);
			TamagoCCGPool.getDefaultTamagoCCGPool().registerGTamagoEntity(final_entity);
		}
		return final_entity;
	}

	/**
	 * @see tamagocc.generic.TamagoCCConverter#getTTamagoEntity()
	 */
	public TTamagoEntity getTTamagoEntity() {
		return original_entity;
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitAccess(tamagocc.api.TAccess)
	 */
	public Object visitAccess(TAccess access) throws TamagoCCException {
		return new GIAccess(access.canRead(),access.canWrite());
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitAllow(tamagocc.api.TAllow)
	 */
	public Object visitAllow(TAllow allow) throws TamagoCCException {
		return new GIAllow(allow.getMethod());
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitAssembly(tamagocc.api.TAssembly)
	 */
	public Object visitAssembly(TAssembly assembly) throws TamagoCCException {
		String name = assembly.getName();
		GModule module = new GIModule(assembly.getModule());


		ArrayList<GBind> binds = new ArrayList<GBind>();
		ArrayList<GInstantiateComponent> instantiates = new ArrayList<GInstantiateComponent>();
		ArrayList<GInvariant> invariants = new ArrayList<GInvariant>();
		ArrayList<GProperty> properties = new ArrayList<GProperty>();
		GIAssemblyContainer container = new GIAssemblyContainer(name,module,binds,instantiates,invariants,GIBehavior.getEmptyBehavior(),properties, new ArrayList<GType>());
		final_entity = container;

		// Chercher les types parametre
		for (TType type : assembly.getParametrizedTypes()) {
			container.addParametricType(GIType.generateType(type.getType()));
		}

		// pas de propriete
		// pas de behavior
		// pas d'invaraint dans les assemblages pour le moment

		Iterator<TBind> ibinds = assembly.getBinds();
		while(ibinds.hasNext()) {
			TBind bind = (TBind) ibinds.next();
			GBind gbind = (GBind) bind.visit(this);
			binds.add(gbind);
		}

		Iterator<TDefinition> defs = assembly.getDefinitions();
		while(defs.hasNext()) {
			TDefinition def = (TDefinition)defs.next();
			GInstantiateComponent inst = (GInstantiateComponent)def.visit(this);
			instantiates.add(inst);
		}		

		return container;
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitAtPre(tamagocc.api.TAtPre)
	 */
	public Object visitAtPre(TAtPre pre) throws TamagoCCException {
		GExpression val = (GExpression)pre.getExpression().visit(this);
		TamagoCCSearchType typeur = new TamagoCCSearchType((TTamago)original_entity,last_method,scopes,pre.getExpression());
		TType type = typeur.getType();

		return new GIAtPre(val,(GType)type.visit(this));
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitBehavior(tamagocc.api.TBehavior)
	 */
	public Object visitBehavior(TBehavior behavior) throws TamagoCCException {
		GIBehavior gbehavior = (GIBehavior) ((GTamago)final_entity).getBehavior();

		Iterator<TState> tstates = behavior.getStates();
		while(tstates.hasNext()) {
			TState state = tstates.next();
			Iterator<TAllow> allows =state.getAllow();
			ArrayList<GAllow> gallows = new ArrayList<GAllow>();
			while(allows.hasNext()) {
				gallows.add(new GIAllow(allows.next()));
			}
			GIState gstate = new GIState(final_entity,state.getState(),gallows);
			gbehavior.addDeclaredState(gstate);
		}

		if(behavior.getDefaultState() != null && (!behavior.getDefaultState().equals(""))) {
			GState def = null;
			try {
				def = gbehavior.getState(behavior.getDefaultState());
			}
			catch(TamagoCCNotFoundState nfs) {
				TamagoCCLogger.println(3, "*Warning* The default state "+behavior.getDefaultState()+" is not declared, " +
						"but I creat an implicit state without activable functionalities " +
				"(they will be determined in next steps)");
				def = new GIState(final_entity,behavior.getDefaultState(),new ArrayList<GAllow>(),true);
				gbehavior.addDeclaredState(def);
			}
			gbehavior.addDefaultState(def);
		}

		Iterator<TTransition> ttransitions = behavior.getTransitions();
		while(ttransitions.hasNext()) {
			TTransition trans = ttransitions.next();
			GState ori;
			GState dest;

			try {
				ori = gbehavior.getState(trans.getFrom());
			}
			catch(TamagoCCNotFoundState nfs) {
				TamagoCCLogger.println(3, "*Warning* The state "+trans.getFrom()+" is not declared, " +
						"but I creat an implicit state without activable functionalities " +
				"(they will be determined in next step)");
				ori = new GIState(final_entity,trans.getFrom(),new ArrayList<GAllow>(),true);
				ori = gbehavior.addInheritedState(ori);
			}

			try {
				dest= gbehavior.getState(trans.getTo());
			}
			catch(TamagoCCNotFoundState nfs) {
				TamagoCCLogger.println(3, "*Warning* The state "+trans.getTo()+" is not declared, " +
						"but I creat an implicit state without activable functionalities " +
				"(they will be determined in next step)");
				dest = new GIState(final_entity,trans.getTo(),new ArrayList<GAllow>(),true);
				dest = gbehavior.addInheritedState(dest);
			}

			GExpression cond = (GExpression)trans.getCondition().visit(this);

			GTransition gtrans = new GITransition(ori,dest,trans.getMethod(),cond);
			gbehavior.addTransition(gtrans);
		}
		return gbehavior;
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitBind(tamagocc.api.TBind)
	 */
	public Object visitBind(TBind bind) throws TamagoCCException {
		GIBind gbind;
		if(bind.forcedService()) {
			GServiceInterface service = (GServiceInterface)TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(bind.getServiceName(), bind.getServiceModule());
			gbind = new GIBind(service, bind.getProvider(),bind.getRequirer(),bind.getLabel());
		}
		else {
			throw new TamagoCCException("Infer the service for binding is not yet implemented");
		}
		return gbind;
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitBool(tamagocc.api.TBool)
	 */
	public Object visitBool(TBool bool) throws TamagoCCException {
		return new GIBool(bool.getValue());
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitCall(tamagocc.api.TCall)
	 */
	public Object visitCall(TCall call) throws TamagoCCException {
		String name = call.getName();

		Iterator<TExpression> args = call.getArguments(); 
		ArrayList<GExpression> gargs = new ArrayList<GExpression>();
		while(args.hasNext()) {
			TExpression targument = args.next();
			gargs.add((GExpression)targument.visit(this));
		}
		return new GICall(name,gargs);
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitCategory(tamagocc.api.TCategory)
	 */
	public Object visitCategory(TCategory category) throws TamagoCCException {
		return new GICategory(category.getName());
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitComponent(tamagocc.api.TComponent)
	 */
	public Object visitComponent(TComponent component) throws TamagoCCException {
		String name = component.getName();
		GModule module = new GIModule(component.getModule());

		GIComponentContainer container = new GIComponentContainer(name,module,
				new ArrayList<GPercolator>(), 
				new ArrayList<GRequire>(), 
				new ArrayList<GMethod>(), 
				new ArrayList<GProvide>(), 
				new ArrayList<GProperty>(), 
				new ArrayList<GInvariant>(), 
				GIBehavior.getEmptyBehavior(),
				new ArrayList<GImplements>(),
				new ArrayList<GNamespace>(), new ArrayList<GType>());
		final_entity = container;

		// Chercher les types parametre
		for (TType type : component.getParametrizedTypes()) {
			container.addParametricType(GIType.generateType(type.getType()));
		}

		// il doit etre le premier a appele pour eviter la suppression 
		// des etats declarer
		component.getBehavior().visit(this);


		Iterator<TInvariant> invariants = component.getInvariants();
		while(invariants.hasNext()) {
			TInvariant inv =(TInvariant)invariants.next();
			GInvariant invariant = (GInvariant)inv.visit(this);
			container.addInvariant(invariant);
		}


		Iterator<TProperty> properties = component.getProperties();
		while(properties.hasNext()) {
			TProperty property = (TProperty)properties.next();
			GProperty gproperty = (GProperty)property.visit(this);
			container.addProperty(gproperty);
		}

		Iterator<TProvide> provides = component.getProvides();
		while(provides.hasNext()) {
			TProvide provide = (TProvide) provides.next();
			GProvide gprovide = (GProvide) provide.visit(this);
			container.addProvide(gprovide);
		}

		Iterator<TRequire> requires = component.getRequires();
		while(requires.hasNext()) {
			TRequire require = (TRequire) requires.next();
			GRequire grequire = (GRequire) require.visit(this);
			container.addRequire(grequire);			
		}

		Iterator<TMethod> methods = component.getMethods();
		while(methods.hasNext()) {
			TMethod method = methods.next();
			GMethod gmethod = (GMethod) method.visit(this);
			container.addMethod(gmethod);	
		}

		Iterator<TPercolator> percolators = component.getAllowedPercolators();
		while(percolators.hasNext()) {
			TPercolator percolator = percolators.next();
			percolator.visit(this);
			// ne pas utiliser le resultat
		}

		// on indique aussi les elements implementant le sous-type
		for (TImplements impls : component.getImplements()) {
			GImplements gimpls = (GImplements) impls.visit(this);
			container.addImplements(gimpls);
		}

		for(TNamespace ns : component.getUsedNamespace()) {
			GNamespace gns = (GNamespace) ns.visit(this);
			container.addNamespace(gns);
		}

		// on renvoit l'objet que l'on a fini de creer
		return container;
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitComposite(tamagocc.api.TComposite)
	 */
	public Object visitComposite(TComposite composite) throws TamagoCCException {
		String name = composite.getName();
		GModule module = new GIModule(composite.getModule());

		GICompositeContainer container = new GICompositeContainer(name,module, 
				new ArrayList<GPercolator>(), // all percolateur
				new ArrayList<GRequire>(), // all requires
				new ArrayList<GMethod>(), // all methods
				new ArrayList<GProvide>(), // all provide
				new ArrayList<GProperty>(), // all properties
				new ArrayList<GBind>(), // all binds
				new ArrayList<GInstantiateComponent>(), // all instantiates
				new ArrayList<GInvariant>(), // all invariant
				GIBehavior.getEmptyBehavior(), // no behavior
				new ArrayList<GExport>(), // all exportation
				new ArrayList<GImplements>(), // all implemented interface
				new ArrayList<GNamespace>(),
				new ArrayList<GType>()
		);
		final_entity = container;

		// Chercher les types parametre
		for (TType type : composite.getParametrizedTypes()) {
			container.addParametricType(GIType.generateType(type.getType()));
		}

		// il doit etre le premier a appele pour eviter la suppression 
		// des etats declarer
		GBehavior behavior = (GBehavior)composite.getBehavior().visit(this);
		container.setBehavior(behavior);

		Iterator<TInvariant> invariants = composite.getInvariants();
		while(invariants.hasNext()) {
			TInvariant inv =(TInvariant)invariants.next();
			GInvariant invariant = (GInvariant)inv.visit(this);
			container.addInvariant(invariant);
		}

		Iterator<TProperty> properties = composite.getProperties();
		while(properties.hasNext()) {
			TProperty property = (TProperty)properties.next();
			GProperty gproperty = (GProperty)property.visit(this);
			container.addProperty(gproperty);
		}

		Iterator<TProvide> provides = composite.getProvides();
		while(provides.hasNext()) {
			TProvide provide = (TProvide) provides.next();
			GProvide gprovide = (GProvide) provide.visit(this);
			container.addProvide(gprovide);
		}

		Iterator<TRequire> requires = composite.getRequires();
		while(requires.hasNext()) {
			TRequire require = (TRequire) requires.next();
			GRequire grequire = (GRequire) require.visit(this);
			container.addRequire(grequire);			
		}

		Iterator<TMethod> methods = composite.getMethods();
		while(methods.hasNext()) {
			TMethod method = (TMethod) methods.next();
			GMethod gmethod = (GMethod) method.visit(this);
			container.addMethod(gmethod);	
		}

		Iterator<TPercolator> percolators = composite.getAllowedPercolators();
		while(percolators.hasNext()) {
			TPercolator percolator = (TPercolator)percolators.next();
			percolator.visit(this);
			// ne pas utiliser le resultat
		}

		Iterator<TExport> iexportations = composite.getExports();
		while(iexportations.hasNext()) {
			TExport export = (TExport)iexportations.next();
			GExport gexport = (GExport) export.visit(this);
			container.addExportation(gexport);
		}

		Iterator<TBind> ibinds = composite.getBinds();
		while(ibinds.hasNext()) {
			TBind bind = (TBind) ibinds.next();
			GBind gbind = (GBind) bind.visit(this);
			container.addBind(gbind);
		}

		Iterator<TDefinition> defs = composite.getDefinitions();
		while(defs.hasNext()) {
			TDefinition def = (TDefinition)defs.next();
			GInstantiateComponent inst = (GInstantiateComponent)def.visit(this);
			container.addInsatiatedComponent(inst);
		}

		// on indique aussi les elements implementant le sous-type
		for (TImplements impls : composite.getImplements()) {
			GImplements gimpls = (GImplements) impls.visit(this);
			container.addImplements(gimpls);
		}

		for(TNamespace ns : composite.getUsedNamespace()) {
			GNamespace gns = (GNamespace) ns.visit(this);
			container.addNamespace(gns);
		}

		// on renvoit l'objet que l'on a fini de creer
		return container;
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitCondition(tamagocc.api.TCondition)
	 */
	public Object visitCondition(TCondition condition) throws TamagoCCException {
		GCategory category = (GCategory)condition.getCategory().visit(this);
		GExpression expression = (GExpression) condition.getExpression().visit(this);
		String message = condition.getMessage();		
		return new GICondition(category,expression,message);
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitDefinition(tamagocc.api.TDefinition)
	 */
	public Object visitDefinition(TDefinition definition)
	throws TamagoCCException {
		GInstantiateComponent ins= new GIInstantiateComponent(definition.getComponentName(),
				new GIModule(definition.getComponentModule()),
				definition.getComponentLabel(),
				definition.getPercolator());
		return ins;
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitExistRange(tamagocc.api.TExistRange)
	 */
	public Object visitExistRange(TExistRange existrange)
	throws TamagoCCException {
		boolean alreadyExist = env.containsKey(existrange.getVariable().getVariable());
		TType stype;

		stype = env.put(existrange.getVariable().getVariable(), existrange.getType());
		GVariable variable = (GVariable)existrange.getVariable().visit(this);
		GExpression gbody  = (GExpression)existrange.getBody().visit(this);
		GType type = (GType) existrange.getType().visit(this);
		GExpression min = (GExpression)existrange.getMin().visit(this);
		GExpression max = (GExpression)existrange.getMax().visit(this);
		GExistRange exis = new GIExistRange(type,variable,min,max,gbody);

		if(alreadyExist) {
			env.put(existrange.getVariable().getVariable(), stype);
		}
		else
			env.remove(existrange.getVariable().getVariable());

		return exis.getResultExpression();
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitExistSet(tamagocc.api.TExistSet)
	 */
	public Object visitExistSet(TExistSet existset) throws TamagoCCException {
		boolean alreadyExist = env.containsKey(existset.getVariable().getVariable());
		TType stype;

		stype = env.put(existset.getVariable().getVariable(), existset.getType());
		GVariable variable = (GVariable)existset.getVariable().visit(this);
		GExpression gbody  = (GExpression)existset.getBody().visit(this);
		GType type = (GType) existset.getType().visit(this);
		GSet set = (GSet) existset.getSet().visit(this);
		GIExistSet gi =  new GIExistSet(type,variable,set,gbody);

		if(alreadyExist) {
			env.put(existset.getVariable().getVariable(), stype);
		}
		else
			env.remove(existset.getVariable().getVariable());

		return gi.getResultExpression();
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitExport(tamagocc.api.TExport)
	 */
	public Object visitExport(TExport export) throws TamagoCCException {
		GIModule module = new GIModule(export.getServiceModule());

		GExpression expr = (GExpression)export.getProvider().visit(this);

		GIExport gexport = new GIExport(export.getService(),module,expr);
		return gexport;
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitExpression(tamagocc.api.TExpression)
	 */
	public Object visitExpression(TExpression e) throws TamagoCCException {
		throw new TamagoCCException("You can't call visitExpression directly in the converter. Expression type :("+e.getCat()+")");
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitExtendService(tamagocc.api.TExtendService)
	 */
	public Object visitExtendService(TExtendService extendservice)
	throws TamagoCCException {
		throw new TamagoCCException("You can't call visitExtendService directly in the converter.");
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitForallRange(tamagocc.api.TForallRange)
	 */
	public Object visitForallRange(TForallRange forallrange)
	throws TamagoCCException {
		boolean alreadyExist = env.containsKey(forallrange.getVariable().getVariable());
		TType stype;

		stype = env.put(forallrange.getVariable().getVariable(), forallrange.getType());

		GVariable variable = (GVariable)forallrange.getVariable().visit(this);
		GExpression gbody  = (GExpression)forallrange.getBody().visit(this);
		GType type = (GType) forallrange.getType().visit(this);		
		GExpression min = (GExpression)forallrange.getMin().visit(this);
		GExpression max = (GExpression)forallrange.getMax().visit(this);
		GIForallRange gi = new GIForallRange(type,variable,min,max,gbody);

		if(alreadyExist) {
			env.put(forallrange.getVariable().getVariable(), stype);
		}
		else
			env.remove(forallrange.getVariable().getVariable());

		return gi.getResultExpression();
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitForallSet(tamagocc.api.TForallSet)
	 */
	public Object visitForallSet(TForallSet forallset) throws TamagoCCException {
		boolean alreadyExist = env.containsKey(forallset.getVariable().getVariable());
		TType stype;

		stype = env.put(forallset.getVariable().getVariable(), forallset.getType());
		GVariable variable = (GVariable)forallset.getVariable().visit(this);
		GExpression gbody  = (GExpression)forallset.getBody().visit(this);
		GType type = (GType) forallset.getType().visit(this);
		GSet set = (GSet) forallset.getSet().visit(this);
		GIForallSet gi = new GIForallSet(type,variable,set,gbody);

		if(alreadyExist) {
			env.put(forallset.getVariable().getVariable(), stype);
		}
		else
			env.remove(forallset.getVariable().getVariable());

		return gi.getResultExpression();
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitInLabel(tamagocc.api.TInLabel)
	 */
	public Object visitInLabel(TInLabel inlabel) throws TamagoCCException {
		GExpression target = (GExpression) inlabel.getTarget().visit(this);
		scopes.push(inlabel);
		GExpression subexpr = (GExpression)inlabel.getSubTerm().visit(this);
		scopes.pop();
		return new GIInLabel(target,subexpr);
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitIncludeService(tamagocc.api.TIncludeService)
	 */
	public Object visitIncludeService(TIncludeService includeservice)
	throws TamagoCCException {
		GIIncludeService rs =  new GIIncludeService(includeservice.getServiceName(),new GIModule(includeservice.getModuleService()));

		GServiceInterface service = rs.getService();

		// maintenant il faut ajouter tous les proprietes
		for (GProperty prop : service.getProperties()) {
			((GITamago)final_entity).registerProperty(prop);
		}

		// ajouts des methodes
		for (GMethod meth : service.getAllMethods()) {
			((GITamago)final_entity).registerMethod(meth);
		}

		// ajouts des etats
		GIBehavior behavior = (GIBehavior)((GITamago)final_entity).getBehavior();

		Hashtable<GState, GState> hashstates = new Hashtable<GState, GState>();
		for (GState state : service.getBehavior().getStates()) {
			hashstates.put(state, behavior.addIncludedState(state));
		}

		//for (GState defaultstate : service.getBehavior().getDefaultStates()) {
		//	behavior.addDefaultState(defaultstate);
		//}

		for (GTransition trans : service.getBehavior().getTransitions()) {
			GState ori = hashstates.get(trans.getOrigin());
			GState dest = hashstates.get(trans.getFinal());

			GITransition ntrans = new GITransition(ori,dest,trans.getMethodID(),trans.getCondition());
			behavior.addTransition(ntrans);
		}

		return rs;
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitInteger(tamagocc.api.TInteger)
	 */
	public Object visitInteger(TInteger integer) throws TamagoCCException {
		return new GIInteger(integer.getValue());
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitInvariant(tamagocc.api.TInvariant)
	 */
	public Object visitInvariant(TInvariant invariant) throws TamagoCCException {
		GCondition cond = (GCondition)this.visitCondition(invariant);		
		return new GIInvariant(final_entity,cond);
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitMethod(tamagocc.api.TMethod)
	 */
	public Object visitMethod(TMethod method) throws TamagoCCException {
		if(last_entity == null)
			throw new TamagoCCException("TamagoCCConverter<visitMethod> : To convert this method <"+method.getName()+">, I need an entity");

		TMethod bak_last_method = last_method;
		last_method = method;


		GType gtype = GIType.generateType(method.getType().getType());

		GIMethod declmethod = new GIMethod(method.getName(),gtype,
				method.getID(),null,null,null);
		last_gmethod = declmethod;

		GCondition precondition =(GCondition)method.getPrecondition().visit(this);
		declmethod.setPrecondition(precondition);

		GCondition postcondition = (GCondition) method.getPostcondition().visit(this);
		declmethod.setPostcondition(postcondition);

		Iterator<TParameter> parameters = method.getParameters();
		while(parameters.hasNext()) {
			TParameter parameter = parameters.next();
			GParameter gparameter = (GParameter)parameter.visit(this);
			last_gmethod.addParameter(gparameter);
		}

		//GDeclareMethod declmethod = new GIDeclareMethod(method.getName(),gtype,method.getID(),gparameters,precondition,postcondition);
		// on remet la valeur de la derniere method en cours de traitement en etat.
		last_method = bak_last_method;
		return declmethod;
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitNil(tamagocc.api.TNil)
	 */
	public Object visitNil(TNil nil) throws TamagoCCException {
		return new GINil();
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitNoContract(tamagocc.api.TNoContract)
	 */
	public Object visitNoContract(TNoContract nocontract)
	throws TamagoCCException {
		return new GINoContract();
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitNot(tamagocc.api.TNot)
	 */
	public Object visitNot(TNot not) throws TamagoCCException {
		return new GINot((GExpression)not.getTerm().visit(this));
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitObject(tamagocc.api.TObject)
	 */
	public Object visitObject(TObject object) throws TamagoCCException {
		throw new TamagoCCException("You can't call visitObject");
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitOpeName(tamagocc.api.TOpeName)
	 */
	public Object visitOpeName(TOpeName opeName) throws TamagoCCException {
		return opeName;
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitOperator(tamagocc.api.TOperator)
	 */
	public Object visitOperator(TOperator operator) throws TamagoCCException {
		Iterator<TExpression> ioperands = operator.getOperands();
		ArrayList<GExpression> operands = new ArrayList<GExpression>();
		while(ioperands.hasNext()) {
			TExpression ope = ioperands.next();
			GExpression ope2 = (GExpression)ope.visit(this);
			operands.add(ope2);
		}
		return new GIOperator(operator.getOperator(),operands.iterator());
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitParameter(tamagocc.api.TParameter)
	 */
	public Object visitParameter(TParameter parameter) throws TamagoCCException {
		return new GIParameter(parameter.getName(),(GType)parameter.getType().visit(this));
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitPercolator(tamagocc.api.TPercolator)
	 */
	public Object visitPercolator(TPercolator percolator)
	throws TamagoCCException {
		if("*".equals(percolator.getName())) {
			String percolatorname[] = TamagoCCPercolation.getAllPercolatorName();
			for(int i =0;i < percolatorname.length;i++) {
				GIPercolator p = new GIPercolator(percolatorname[i]);
				((GIComponentContainer)final_entity).addAllowedPercolator(p);
			}
			return null;
		}
		else {
			GIPercolator p = new GIPercolator(percolator.getName());
			((GIComponentContainer)final_entity).addAllowedPercolator(p);
			return null;
		}
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitProperty(tamagocc.api.TProperty)
	 */
	public Object visitProperty(TProperty property) throws TamagoCCException {
		return new GIProperty(property.getName(),
				(GType)property.getType().visit(this),
				(GAccess)property.getAccess().visit(this));
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitProvide(tamagocc.api.TProvide)
	 */
	public Object visitProvide(TProvide provide) throws TamagoCCException {
		GServiceInterface service = (GServiceInterface)TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(provide.getServiceName(),provide.getModule());

		// maintenant il faut ajouter tous les proprietes
		for (GProperty prop : service.getProperties()) {
			((GITamago)final_entity).registerProperty(prop);
		}

		// ajouts des methodes
		for (GMethod meth : service.getAllMethods()) {
			((GITamago)final_entity).registerMethod(meth);
		}

		// ajouts des invariants
		for (GInvariant inv : service.getInvariants()) {
			((GITamago)final_entity).addInvariant(inv);
		}

		// ajouts des etats
		GIBehavior behavior = (GIBehavior)((GITamago)final_entity).getBehavior();

		Hashtable<GState, GState> hashstates = new Hashtable<GState, GState>();
		for (GState state : service.getBehavior().getStates()) {
			hashstates.put(state, behavior.addInheritedState(state));
		}

		for (GState defaultstate : service.getBehavior().getDefaultStates()) {
			behavior.addDefaultState(defaultstate);
		}

		for (GTransition trans : service.getBehavior().getTransitions()) {
			GState ori = hashstates.get(trans.getOrigin());
			GState dest = hashstates.get(trans.getFinal());

			GITransition ntrans = new GITransition(ori,dest,trans.getMethodID(),trans.getCondition());
			behavior.addTransition(ntrans);
		}

		// ------- GENERIC PART
		GType[] gtypes = convertArrayTType(provide.getParametrizedType()); 		
		return new GIProvide(service,provide.getServiceName(),new GIModule(provide.getModule()),false,gtypes);
	}
	
	public static GType[] convertArrayTType(TType[] tgenerics) {
		GType[] ggenerics = new GType[tgenerics.length];
		for(int i = 0; i < tgenerics.length;i++) {
			ggenerics[i] = GIType.generateType(tgenerics[i].getType());
		}
		return ggenerics;
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitRead(tamagocc.api.TRead)
	 */
	public Object visitRead(TRead read) throws TamagoCCException {
		// TODO : verifier si la propriete est bien lisible sinon c'est le role d'un autre outil		
		String name = read.getName();
		if(read.hasIndex()) {
			GExpression expr = (GExpression) read.getIndex().visit(this);
			return new GIRead(name,expr);
		}
		else 
			return new GIRead(name);
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitReal(tamagocc.api.TReal)
	 */
	public Object visitReal(TReal real) throws TamagoCCException {
		return new GIReal(real.getValue());
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitRefineService(tamagocc.api.TRefineService)
	 */
	public Object visitRefineService(TRefineService refineService)
	throws TamagoCCException {
		GIRefineService rs= new GIRefineService(refineService.getServiceName(),new GIModule(refineService.getModuleService()));

		GServiceInterface service = rs.getService();

		// maintenant il faut ajouter tous les proprietes
		for (GProperty prop : service.getProperties()) {
			((GITamago)final_entity).registerProperty(prop);
		}

		// ajouts des methodes
		for (GMethod meth : service.getAllMethods()) {
			((GITamago)final_entity).registerMethod(meth);
		}

		// ajouts des invariants
		for (GInvariant inv : service.getInvariants()) {
			((GITamago)final_entity).addInvariant(inv);
		}

		// ajouts des etats
		GIBehavior behavior = (GIBehavior)((GITamago)final_entity).getBehavior();

		Hashtable<GState, GState> hashstates = new Hashtable<GState, GState>();
		for (GState state : service.getBehavior().getStates()) {
			hashstates.put(state, behavior.addInheritedState(state));
		}

		for (GState defaultstate : service.getBehavior().getDefaultStates()) {
			behavior.addDefaultState(defaultstate);
		}

		for (GTransition trans : service.getBehavior().getTransitions()) {
			GState ori = hashstates.get(trans.getOrigin());
			GState dest = hashstates.get(trans.getFinal());

			GITransition ntrans = new GITransition(ori,dest,trans.getMethodID(),trans.getCondition());
			behavior.addTransition(ntrans);
		}

		return rs;
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitRequire(tamagocc.api.TRequire)
	 */
	public Object visitRequire(TRequire require) throws TamagoCCException {
		GType[] gtypes = convertArrayTType(require.getParametrizedType());
		return new GIRequire(require.getServiceName(),new GIModule(require.getModule()), require.getLabel(),gtypes);
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitReturn(tamagocc.api.TReturn)
	 */
	public Object visitReturn(TReturn ret) throws TamagoCCException {
		if((last_method == null)||(last_gmethod == null)) {
			TamagoCCLogger.println(3,"TamagoCCGenericConverter<visitReturn> : Erreur : the @return element must be defined in a method.");
			throw new TamagoCCException("TamagoCCGenericConverter<visitReturn> : the @return element must be defined in a method.");
		}

		if(ret.hasIndex()) {
			GExpression idx = (GExpression) ret.getIndex().visit(this);
			GIVariable var = new GIVariable(last_gmethod.getSavedResult().getName(),last_gmethod.getType(),idx);
			GIReturn greturn = new GIReturn(var,idx);
			return greturn;
		}
		else {		
			GReturn greturn = new GIReturn(last_gmethod.getSavedResult());
			return greturn;
		}
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitService(tamagocc.api.TService)
	 */
	public Object visitService(TService service) throws TamagoCCException {
		GIServiceInterface gservice = new GIServiceInterface(service.getName(),new GIModule(service.getModule()), 
				new ArrayList<GExtendService>(), new ArrayList<GMethod>(), GIBehavior.getEmptyBehavior(), 
				new ArrayList<GInvariant>(),new ArrayList<GProperty>(),new ArrayList<GImplements>(),
				new ArrayList<GNamespace>(), new ArrayList<GType>());
		final_entity = gservice;

		// Chercher les types parametre
		if(service.isParametric()) {
			for (TType type : service.getParametrizedTypes()) {
				gservice.addParametricType(GIType.generateType(type.getType()));
			}
		}

		// Il faut chercher tous les elements declarer

		// Ajoute le service behavior visite
		TBehavior behavior = service.getBehavior();
		GIBehavior gbehavior = (GIBehavior)behavior.visit(this);		
		gservice.setBehavior(gbehavior);


		// ajoute toutes les methodes
		Iterator<TMethod> methods = service.getMethods();
		while(methods.hasNext()) {
			TMethod method = (TMethod)methods.next();
			GMethod gmethod = (GMethod)method.visit(this);
			gservice.addDeclaredMethod(gmethod);
		}

		// ajoute toutes les proprietes
		Iterator<TProperty> properties = service.getProperties();
		while(properties.hasNext()) {
			TProperty property = (TProperty) properties.next();
			GProperty gproperty = (GProperty)property.visit(this);
			gservice.addProperty(gproperty);
		}


		// insere les invariants
		Iterator<TInvariant> invariants = service.getInvariants();
		while(invariants.hasNext()) {
			TInvariant inv = (TInvariant)invariants.next();
			GInvariant invariant = (GInvariant)inv.visit(this);
			gservice.addInvariant(invariant);
		}		

		// les services raffine ou inclus
		// On ne prends que les service RAFFINE (les inclus sont a 
		// prendre EN DERNIER !!)
		Iterator<?> extension = service.getExtends();
		while(extension.hasNext()) {
			TExtendService ext = (TExtendService)extension.next();
			if(ext instanceof TRefineService) {
				GExtendService gext = (GExtendService)ext.visit(this);
				gservice.addExtend(gext);
			}
		}

		// ---------------------------------- INCLUSION
		extension = service.getExtends();
		while(extension.hasNext()) {
			TExtendService ext = (TExtendService)extension.next();
			if(ext instanceof TIncludeService) {
				GExtendService gext = (GExtendService)ext.visit(this);
				gservice.addExtend(gext);
			}
		}

		// on indique aussi les elements implementant le sous-type
		for (TImplements impls : service.getImplements()) {
			GImplements gimpls = (GImplements) impls.visit(this);
			gservice.addImplements(gimpls);
		}

		for(TNamespace ns : service.getUsedNamespace()) {
			GNamespace gns = (GNamespace) ns.visit(this);
			gservice.addNamespace(gns);
		}

		return gservice;
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitSet(tamagocc.api.TSet)
	 */
	public Object visitSet(TSet set) throws TamagoCCException {
		GType type = (GType) set.getType().visit(this);
		ArrayList<GExpression> c = new ArrayList<GExpression>();
		for(TExpression e : set.getElements()) {
			GExpression ge = (GExpression)e.visit(this);
			c.add(ge);
		}
		return new GISet(type,c);
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitString(tamagocc.api.TString)
	 */
	public Object visitString(TString string) throws TamagoCCException {
		return new GIString(string);
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitTamago(tamagocc.api.TTamago)
	 */
	public Object visitTamago(TTamago tamago) throws TamagoCCException {
		throw new TamagoCCException("You can't call visitTamago directly in the generic converter.");
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitTamagoEntity(tamagocc.api.TTamagoEntity)
	 */
	public Object visitTamagoEntity(TTamagoEntity entity)
	throws TamagoCCException {
		throw new TamagoCCException("You can't call visitTamagoEntity directly in the converter.");
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitType(tamagocc.api.TType)
	 */
	public Object visitType(TType type) throws TamagoCCException {
		return GIType.generateType(type.getType());
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitVariable(tamagocc.api.TVariable)
	 */
	public Object visitVariable(TVariable variable) throws TamagoCCException {
		// bon faut trouver le type, en prenant d'abord le cas ou on est dans une methode ou non
		// si oui regarder les parametres
		String name = variable.getVariable();
		GType type = null;
		if(variable.forcedType()) {
			type = GIType.generateType(variable.getType().getType());
			if(variable.hasIndex()) {
				GExpression expr = (GExpression) variable.getIndex().visit(this);
				return new GIVariable(name,type,expr);
			}
			else {
				return new GIVariable(name,type);
			}
		}

		if(last_method != null) {
			Iterator<TParameter> parameters = last_method.getParameters();
			while(parameters.hasNext()) {
				TParameter parameter = (TParameter)parameters.next();
				if(parameter.getName().equals(variable.getVariable())) {
					type = GIType.generateType(parameter.getType().getType());
					if(variable.hasIndex()) {
						GExpression expr = (GExpression) variable.getIndex().visit(this);
						return new GIVariable(name,type,expr);
					}
					else {
						return new GIVariable(name,type);
					}
				}
			}
		}

		if(env.containsKey(name)) {
			TType parameter = env.get(name);
			type = GIType.generateType(parameter.getType());
			if(variable.hasIndex()) {
				GExpression expr = (GExpression) variable.getIndex().visit(this);
				return new GIVariable(name,type,expr);
			}
			else 
				return new GIVariable(name,type);
		}
		TamagoCCLogger.println(3,"*Warning* TamagoCCGenericConverter<visitVariable> : unfind type of the variable "+name);
		TamagoCCLogger.println(3,"\tI continue but an error can occur");

		// je suis dans un inlabel

		return new GIVariable(name,null);
		//throw new TamagoCCException("TamagoCCGenericConverter<visitVariable> : unfind type of the variable "+name);
	}


	public Object visitImplements(TImplements impl) throws TamagoCCException {
		GType type= GIType.generateType(impl.getModule()+"."+impl.getNameType());
		GModule module = new GIModule(impl.getModule());
		return new GIImplements(type,module);
	}

	public Object visitExistColl(TExistColl coll) throws TamagoCCException {
		boolean alreadyExist = env.containsKey(coll.getVariable().getVariable());
		TType stype;

		stype = env.put(coll.getVariable().getVariable(), coll.getType());

		GType type = GIType.generateType(coll.getType().getType()); 
		GVariable var = (GVariable) coll.getVariable().visit(this);
		GExpression expr = (GExpression) coll.getBody().visit(this);
		GExpression co = (GExpression) coll.getCollection().visit(this);

		GIExistColl excoll = new GIExistColl(type,var,co,expr);

		if(alreadyExist)
			env.put(coll.getVariable().getVariable(), stype);
		else
			env.remove(coll.getVariable().getVariable());
		return excoll.getResultExpression();
	}

	public Object visitForallColl(TForallColl coll) throws TamagoCCException {
		boolean alreadyExist = env.containsKey(coll.getVariable().getVariable());
		TType stype;

		stype = env.put(coll.getVariable().getVariable(), coll.getType());

		GType type = GIType.generateType(coll.getType().getType()); 
		GVariable var = (GVariable) coll.getVariable().visit(this);
		GExpression expr = (GExpression) coll.getBody().visit(this);
		GExpression co = (GExpression) coll.getCollection().visit(this);

		GIForallColl forcoll = new GIForallColl(type,var,co,expr);

		if(alreadyExist)
			env.put(coll.getVariable().getVariable(), stype);
		else
			env.remove(coll.getVariable().getVariable());
		return forcoll.getResultExpression();
	}

	public Object visitLanguageExpr(TLanguageExpr languageExpr)
	throws TamagoCCException {
		return new GILanguageExpr(languageExpr.getExpression());
	}

	public Object visitNamespace(TNamespace namespace) throws TamagoCCException {
		return new GINamespace(namespace.getNamespace(),namespace.getType());
	}

	/**
	 * @see tamagocc.util.TamagoCCVisitor#visitCast(tamagocc.api.TCast)
	 */
	public Object visitCast(TCast cast) throws TamagoCCException {
		GType type = GIType.generateType(cast.getType().getType());
		GExpression expr = (GExpression) cast.getExpression().visit(this);
		return new GICast(type,expr);
	}

	@Override
	public Object visitInState(TInState instate) throws TamagoCCException {
		GIInState e = new GIInState(instate.getInState());
		return e;
	}
}
