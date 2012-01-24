/**
 * 
 */
package fr.lacl.tamago.aca;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import tamagocc.api.TOpeName;
import tamagocc.ast.api.AEntity;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AImplements;
import tamagocc.ast.api.AInLabel;
import tamagocc.ast.api.AInitialisation;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AMethod;
import tamagocc.ast.api.ANamespace;
import tamagocc.ast.api.AParameter;
import tamagocc.ast.api.AReturn;
import tamagocc.ast.api.ASequence;
import tamagocc.ast.api.AString;
import tamagocc.ast.api.AThrowsException;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVariable;
import tamagocc.ast.api.AVisibility;
import tamagocc.ast.impl.AConstructor;
import tamagocc.ast.impl.AIAffectation;
import tamagocc.ast.impl.AIBodyMethodContainer;
import tamagocc.ast.impl.AICall;
import tamagocc.ast.impl.AIConvertType;
import tamagocc.ast.impl.AIDocComment;
import tamagocc.ast.impl.AIIdent;
import tamagocc.ast.impl.AIIfThenElse;
import tamagocc.ast.impl.AIInLabel;
import tamagocc.ast.impl.AIInitialisation;
import tamagocc.ast.impl.AIInlineComment;
import tamagocc.ast.impl.AIInstExpression;
import tamagocc.ast.impl.AIInteger;
import tamagocc.ast.impl.AILanguageExpr;
import tamagocc.ast.impl.AILongComment;
import tamagocc.ast.impl.AIMemberVariable;
import tamagocc.ast.impl.AIMethod;
import tamagocc.ast.impl.AINamespace;
import tamagocc.ast.impl.AINew;
import tamagocc.ast.impl.AINewArray;
import tamagocc.ast.impl.AINil;
import tamagocc.ast.impl.AINoInstruction;
import tamagocc.ast.impl.AINot;
import tamagocc.ast.impl.AIOperator;
import tamagocc.ast.impl.AIParameter;
import tamagocc.ast.impl.AIProperty;
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
import tamagocc.generator.TamagoCCIGenerator;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.api.GAtPre;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GBool;
import tamagocc.generic.api.GCall;
import tamagocc.generic.api.GCast;
import tamagocc.generic.api.GComponentContainer;
import tamagocc.generic.api.GCondition;
import tamagocc.generic.api.GExistColl;
import tamagocc.generic.api.GExistRange;
import tamagocc.generic.api.GExistSet;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GExtendService;
import tamagocc.generic.api.GForallColl;
import tamagocc.generic.api.GForallRange;
import tamagocc.generic.api.GForallSet;
import tamagocc.generic.api.GImplements;
import tamagocc.generic.api.GInLabel;
import tamagocc.generic.api.GInState;
import tamagocc.generic.api.GInitialisation;
import tamagocc.generic.api.GInteger;
import tamagocc.generic.api.GInvariant;
import tamagocc.generic.api.GIsBound;
import tamagocc.generic.api.GLanguageExpr;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GNamespace;
import tamagocc.generic.api.GNil;
import tamagocc.generic.api.GNoContract;
import tamagocc.generic.api.GNot;
import tamagocc.generic.api.GOperator;
import tamagocc.generic.api.GParameter;
import tamagocc.generic.api.GPreExpression;
import tamagocc.generic.api.GPreInitialisation;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GProvide;
import tamagocc.generic.api.GRead;
import tamagocc.generic.api.GReal;
import tamagocc.generic.api.GRequire;
import tamagocc.generic.api.GReturn;
import tamagocc.generic.api.GServiceInterface;
import tamagocc.generic.api.GSet;
import tamagocc.generic.api.GString;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GType;
import tamagocc.generic.api.GVariable;
import tamagocc.generic.api.GExpression.GExprType;
import tamagocc.generic.impl.GIAtPre;
import tamagocc.generic.impl.GICall;
import tamagocc.generic.impl.GICast;
import tamagocc.generic.impl.GIComponentContainer;
import tamagocc.generic.impl.GIExistColl;
import tamagocc.generic.impl.GIExistRange;
import tamagocc.generic.impl.GIExistSet;
import tamagocc.generic.impl.GIForallColl;
import tamagocc.generic.impl.GIForallRange;
import tamagocc.generic.impl.GIForallSet;
import tamagocc.generic.impl.GIInLabel;
import tamagocc.generic.impl.GIInitialisation;
import tamagocc.generic.impl.GINot;
import tamagocc.generic.impl.GIOperator;
import tamagocc.generic.impl.GIPreInitialisation;
import tamagocc.generic.impl.GIQuantifier;
import tamagocc.generic.impl.GIQuantifierVariable;
import tamagocc.generic.impl.GIRead;
import tamagocc.generic.impl.GISet;
import tamagocc.generic.impl.GIType;
import tamagocc.generic.impl.GIVariable;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.TamagoCCBehavior;
import tamagocc.util.TamagoCCMakeReadableGExpression;
import tamagocc.util.TamagoCCSynchronizeProduct;

/**
 * @author hakim
 *
 */
public class CAComponentGenerator extends TamagoCCGeneratorCommon {
	public CAComponentGenerator(TamagoCCIGenerator owner, GIComponentContainer entity) throws TamagoCCException {
		super(owner, entity);
		astpreinvariants = new ArrayList<AInstruction>();
		astpostinvariants = new ArrayList<AInstruction>();
		String type = entity.getModule().getFullModule()+"."+entity.getName();
		entity.setName(entity.getName()+"ACA");
		
		cscope = new ChangerScope("tamago_decorated_component",GIType.generateType(type));
		
		ate = new AIThrowsException((AIType) AIType.generateType("tamago.TamagoException"));
		
		idcomponent = ident("tamago_decorated_component");
		component = new AIMemberVariable(idcomponent,AIType.generateType(type),AIVisibility.PRIVATE_VISIBILITY);
		ast.addVariablesMembers(component);
		
		ast.addUsedNamespaces(new AINamespace("tamago.ext.tamagocc"));
		ast.addUsedNamespaces(new AINamespace("tamago"));
		
		ast.setName(entity.getName());
		ast.setCategory(AEntity.CLASS);
		hasBehavior = false;
		behavior = null;
		
		((GTamago)entity).getBehavior().visit(this);
		
		makeDefaultConstructor();
		
		ast.addMethod(getRequiredServices((GComponentContainer)entity));
		ast.addMethod(Bind());
		ast.addMethod(isBinded((GComponentContainer)entity));
		ast.addMethod(set_core_property((GComponentContainer)entity));
		ast.addMethod(hotswapping());
	}

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

	protected boolean hasBehavior;
	protected TamagoCCBehavior behavior;
	protected ChangerScope cscope;
	
	
	/**
	 * Internal use of this class by the Component container generator in order to, re-compute
	 * the correct scope of some methods/variable in the entity.
	 * This manipulation avoid some reentrance problems in the contract execution.
	 * @author Hakim Belhaouari
	 *
	 */
	protected class ChangerScope implements TamagoCCGExpressionVisitor,TamagoCCGPreExpressionVisitor {
		private transient Stack<GExpression> scope;
		private GVariable global_target;
		
		boolean rename = false;
		
		public ChangerScope(String global_target,GType type) {
			scope = new Stack<GExpression>();
			this.global_target = new GIVariable(global_target,type); 
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitBool(tamagocc.generic.api.GBool)
		 */
		public Object visitBool(GBool bool) throws TamagoCCException {
			return bool;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitCall(tamagocc.generic.api.GCall)
		 */
		public Object visitCall(GCall call) throws TamagoCCException {
			ArrayList<GExpression> nargs = new ArrayList<GExpression>();
			
			Stack<GExpression> scopebackup = scope;
			
			scope = new Stack<GExpression>();
			Iterator<GExpression> args = call.getArguments();
			while(args.hasNext()) {
				GExpression arg = args.next();
				nargs.add((GExpression) arg.visitExpression(this));
			}
			scope = scopebackup;
			
			GICall ncall = new GICall(call.getName(),nargs);
			
			if(scope.size() == 0) {
				return new GIInLabel(global_target,ncall);
			}
			else
				return ncall;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitCast(tamagocc.generic.api.GCast)
		 */
		public Object visitCast(GCast cast) throws TamagoCCException {
			return new GICast(cast.getType(),(GExpression) cast.getExpression().visitExpression(this));
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitExpression(tamagocc.generic.api.GExpression)
		 */
		public Object visitExpression(GExpression expression)
				throws TamagoCCException {
			throw new TamagoCCException("ChangeScope: Unsupported element");
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitInLabel(tamagocc.generic.api.GInLabel)
		 */
		public Object visitInLabel(GInLabel inlabel) throws TamagoCCException {
			GExpression target = (GExpression) inlabel.getTarget().visitExpression(this);
			int idx = scope.size();
			scope.add(target);
			GExpression subexpr = (GExpression) inlabel.getSubExpression().visitExpression(this);
			scope.remove(idx);
			return new GIInLabel(target,subexpr);
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitInteger(tamagocc.generic.api.GInteger)
		 */
		public Object visitInteger(GInteger interger) throws TamagoCCException {
			return interger;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitLanguageExpr(tamagocc.generic.api.GLanguageExpr)
		 */
		public Object visitLanguageExpr(GLanguageExpr languageExpr)
				throws TamagoCCException {
			return languageExpr;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitNil(tamagocc.generic.api.GNil)
		 */
		public Object visitNil(GNil nil) throws TamagoCCException {
			return nil;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitNoContract(tamagocc.generic.api.GNoContract)
		 */
		public Object visitNoContract(GNoContract nocontract)
				throws TamagoCCException {
			return nocontract;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitNot(tamagocc.generic.api.GNot)
		 */
		public Object visitNot(GNot not) throws TamagoCCException {
			GINot nnot = new GINot((GExpression) not.getTerm().visitExpression(this));
			return nnot;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitOperator(tamagocc.generic.api.GOperator)
		 */
		public Object visitOperator(GOperator operator)
				throws TamagoCCException {
			ArrayList<GExpression> operands = new ArrayList<GExpression>();
			Iterator<GExpression> opes = operator.getOperands();
			while(opes.hasNext()) {
				GExpression expr = (GExpression) opes.next().visitExpression(this);
				operands.add(expr);
			}
			return new GIOperator(operator.getOperator(),operands.iterator());
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitPre(tamagocc.generic.api.GAtPre)
		 */
		public Object visitPre(GAtPre atpre) throws TamagoCCException {
			//GVariable var = (GVariable) atpre.getTerm();
			
			//GIAtPre pre = new GIAtPre(var.getName(), (GExpression) atpre.getInitialisation().visitExpression(this),var.getType());
			GIAtPre pre = new GIAtPre(atpre.getRawName(),(GExpression) atpre.getRawExpr().visitExpression(this), atpre.getRawType());
			return pre;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitQuantifierVariable(tamagocc.generic.impl.GIQuantifierVariable)
		 */
		public Object visitQuantifierVariable(GIQuantifierVariable variable)
				throws TamagoCCException {
			String tmp = variable.getName();
			GIQuantifier quant = (GIQuantifier) variable.getQuantifier().visitGQuantifier(this);
			quant.setName(tmp);
			return quant.getResultExpression();
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitRead(tamagocc.generic.api.GRead)
		 */
		public Object visitRead(GRead read) throws TamagoCCException {
			if(scope.size() == 0) {
				return new GIInLabel(global_target,read);
			}
			else
				return read; 
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitReal(tamagocc.generic.api.GReal)
		 */
		public Object visitReal(GReal real) throws TamagoCCException {
			return real;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitReturn(tamagocc.generic.api.GReturn)
		 */
		public Object visitReturn(GReturn ret) throws TamagoCCException {
			return ret;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitString(tamagocc.generic.api.GString)
		 */
		public Object visitString(GString string) throws TamagoCCException {
			return string;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitVariable(tamagocc.generic.api.GVariable)
		 */
		public Object visitVariable(GVariable variable)
				throws TamagoCCException {
			return variable;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitExistColl(tamagocc.generic.api.GExistColl)
		 */
		public Object visitExistColl(GExistColl f) throws TamagoCCException {
			GExpression gcoll = (GExpression) f.getCollection().visitExpression(this);
			GExpression gbody = (GExpression) f.getBody().visitExpression(this);
			GIExistColl coll = new GIExistColl(f.getType(),f.getVariable(),gcoll,gbody);
			
			//((GIVariable)coll.getResultExpression()).setName(f.getResultExpression().getName());
			coll.setName(f.getResultExpression().getName());
			return coll;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitExistRange(tamagocc.generic.api.GExistRange)
		 */
		public Object visitExistRange(GExistRange r) throws TamagoCCException {
			GExpression gmin = (GExpression) r.getMin().visitExpression(this);
			GExpression gmax = (GExpression) r.getMax().visitExpression(this);
			GExpression gbody = (GExpression) r.getBody().visitExpression(this);
			GIExistRange range = new GIExistRange(r.getType(),r.getVariable(),gmin,gmax,gbody);
			range.setName(r.getResultExpression().getName());
			return range;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitExistSet(tamagocc.generic.api.GExistSet)
		 */
		public Object visitExistSet(GExistSet s) throws TamagoCCException {
			
			ArrayList<GExpression> coll = new ArrayList<GExpression>();
			
			for (GExpression expression : s.getSet().getElements()) {
				coll.add((GExpression) expression.visitExpression(this));
			}
			
			GISet set = new GISet(s.getSet().getType(),coll);
			
			GExpression body =  (GExpression)s.getBody().visitExpression(this);
			
			GIExistSet eset = new GIExistSet(s.getType(),s.getVariable(),set,body);
			
			eset.setName(s.getResultExpression().getName());
			
			return eset;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitForallColl(tamagocc.generic.api.GForallColl)
		 */
		public Object visitForallColl(GForallColl f) throws TamagoCCException {
			GExpression gcoll = (GExpression) f.getCollection().visitExpression(this);
			GExpression gbody = (GExpression) f.getBody().visitExpression(this);
			GIForallColl coll = new GIForallColl(f.getType(),f.getVariable(),gcoll,gbody);
			
			coll.setName(f.getResultExpression().getName());
			return coll;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitForallRange(tamagocc.generic.api.GForallRange)
		 */
		public Object visitForallRange(GForallRange r) throws TamagoCCException {
			GExpression gmin = (GExpression) r.getMin().visitExpression(this);
			GExpression gmax = (GExpression) r.getMax().visitExpression(this);
			GExpression gbody = (GExpression) r.getBody().visitExpression(this);
			GIForallRange range = new GIForallRange(r.getType(),r.getVariable(),gmin,gmax,gbody);
			range.setName(r.getResultExpression().getName());
			return range;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitForallSet(tamagocc.generic.api.GForallSet)
		 */
		public Object visitForallSet(GForallSet s) throws TamagoCCException {
			ArrayList<GExpression> coll = new ArrayList<GExpression>();
			
			for (GExpression expression : s.getSet().getElements()) {
				coll.add((GExpression) expression.visitExpression(this));
			}
			
			GISet set = new GISet(s.getSet().getType(),coll);
			
			GExpression body =  (GExpression)s.getBody().visitExpression(this);
			
			GIForallSet eset = new GIForallSet(s.getType(),s.getVariable(),set,body);
			
			eset.setName(s.getResultExpression().getName());
			
			return eset;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitSet(tamagocc.generic.api.GSet)
		 */
		public Object visitSet(GSet set) throws TamagoCCException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGPreExpressionVisitor#visitInitialisation(tamagocc.generic.api.GInitialisation)
		 */
		public Object visitInitialisation(GInitialisation initialisation)
				throws TamagoCCException {
			GIInitialisation init = new GIInitialisation(initialisation.getVariable(),(GExpression) initialisation.getInitValue().visitExpression(this));
			return init;
		}

		/**
		 * @see tamagocc.generic.TamagoCCGPreExpressionVisitor#visitPreInitialisation(tamagocc.generic.api.GPreInitialisation)
		 */
		public Object visitPreInitialisation(
				GPreInitialisation preInitialisation) throws TamagoCCException {
			GIPreInitialisation prei = new GIPreInitialisation((GAtPre)preInitialisation.getOwner().visitPreExpression(cscope),
					preInitialisation.getVariable(),
					(GExpression) preInitialisation.getInitValue().visitExpression(cscope));
											
			return prei;
		}

		@Override
		public Object visitInState(GInState giInState) throws TamagoCCException {
			return giInState;
		}

		@Override
		public Object visitIsBound(GIsBound giIsBound) throws TamagoCCException {
			return giIsBound;
		}
		
		
		
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
		
		if(hasBehavior) {
			//ast.addUsedNamespaces(new AINamespace("tamago.ext.tamagocc"));
			ast.addUsedNamespaces(new AINamespace("java.util","Hashtable"));
			ast.addUsedNamespaces(new AINamespace("java.util","ArrayList"));
			behavior.generateConstructor(body);
			ast.addMethod(behavior.makeFunctionFetch());
		}
		
		AINoInstruction debutuser = new AINoInstruction();
		debutuser.setComment(new AIInlineComment("----- if necessary you can add some code under this ligne ----"));
		body.addInstruction(debutuser);
		
		constructor.setBody(body);
		ast.addMethod(constructor);
	}
	
	
	/** 
	 * @see tamagocc.generic.TamagoCCGVisitor#visitBehavior(tamagocc.generic.api.GBehavior)
	 */
	public Object visitBehavior(GBehavior behavior) throws TamagoCCException {
		// TODO Auto-generated method stub
		GTamago tamago = (GTamago)this.entity;
		
		if(tamago.getBehavior().countStates() > 0) {
			TamagoCCLogger.info(3,"behavior detected");
			TamagoCCSynchronizeProduct sync = new TamagoCCSynchronizeProduct(tamago);
			GBehavior merged = sync.getProduct();

			if (merged.countStates() > 0) {
				hasBehavior = true;
				this.behavior = new TamagoCCBehavior(this, (GTamago) entity,merged);
				TamagoCCLogger.info(3,"behavior compiled with success");
			}
		}
		else {
			hasBehavior = false;
			TamagoCCLogger.info(3,"No behavior detected");
		}
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitComponent(tamagocc.generic.api.GComponentContainer)
	 */
	public Object visitComponent(GComponentContainer component)
			throws TamagoCCException {
	
		Iterator<GProperty> properties = component.getProperties().iterator();
		while(properties.hasNext()) {
			GProperty property = properties.next();
			property.visit(this);
		}
				
		Iterator<GInvariant> invariants = component.getInvariants().iterator();
		while(invariants.hasNext()) {
			GInvariant inv = invariants.next();
			inv.visit(this);
		}
		
		
		Iterator<GMethod> methods = component.getUniqueMethods().iterator();
		while(methods.hasNext()) {
			GMethod meth = methods.next();
			meth.visit(this);
			
		}
		
		Iterator<GProvide> provides = component.getAllProvide();
		while(provides.hasNext()) {
			GProvide es = provides.next();
			es.visit(this);
		}		
		
		Iterator<GRequire> requires = component.getAllRequire();
		while(requires.hasNext()) {
			requires.next().visit(this);
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

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitCondition(tamagocc.generic.api.GCondition)
	 */
	public Object visitCondition(GCondition condition) throws TamagoCCException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitExtendService(tamagocc.generic.api.GExtendService)
	 */
	public Object visitExtendService(GExtendService extendservice)
			throws TamagoCCException {
		/*if(extendservice.isRefined()) {
			AIImplements impl = new AIImplements(extendservice.getServiceName(),module(extendservice.getModule()));
			ast.addImplement(impl);
		}*/
		extendservice.getService().visit(this);
		return null;
	}

	private boolean enrichPostInvariant(AInstruction inst) {
		boolean added = false;
		switch(inst.getInstructionType()) {
		case AInstruction.INITIALISATION:
			AInitialisation init = (AInitialisation)inst;
			if(init.getInitial().getExpressionType() != AExpression.NOEXPRESSION) {
				AIVariable var = new AIVariable(init.getIdent());
				AIAffectation affect = new AIAffectation(var,init.getInitial());
				affect.setComment(init.getComment());
				astpostinvariants.add(affect);
			}
			return false;
		case AInstruction.SEQUENCE:
			ASequence seq = (ASequence)inst;
			Iterator<AInstruction> insts = seq.getInstructions();
			while(insts.hasNext()) {
				added = added || enrichPostInvariant(insts.next());
			}
			return added;
		default:
			astpostinvariants.add(inst);
			return true;
		}
	}
	
	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitInvariant(tamagocc.generic.api.GInvariant)
	 */
	public Object visitInvariant(GInvariant invariant) throws TamagoCCException {
		
		if(invariant.getExpression().getCategory() != GExpression.GExprType.NOCONTRACT) {
			for (GPreExpression preexp : invariant.getExpression().getPreExpression()) {
				GPreExpression p = (GPreExpression) preexp.visitPreExpression(cscope);
				AInstruction obj = (AInstruction)p.visit(this);
				astpreinvariants.add(obj);
				
				p = (GPreExpression) preexp.visitPreExpression(cscope);
				obj = (AInstruction)p.visit(this);
				enrichPostInvariant(obj);
				//astpostinvariants.add(obj);
			}
			
			GExpression invexpr = (GExpression) invariant.getExpression().visitExpression(cscope);
			AExpression cond = (AExpression)invexpr.visit(this);
			TamagoCCMakeReadableGExpression gen = new TamagoCCMakeReadableGExpression(invariant.getExpression());
			
			AIThrowException throwfailcondition = new AIThrowException((AIType) AIType.generateType("TamagoCCFailInvariant"));
			throwfailcondition.addArgument(new AIString(gen.getStrExpression()));
			if(invariant.getMessage().length() > 0) {
				
				AString strmessage = new AIString(invariant.getMessage());
				throwfailcondition.addArgument(strmessage);
			}
			throwfailcondition.addArgument(new AILanguageExpr("InvariantPosition.BEFORE"));
			AINot invtest = new AINot(cond);
			AIIfThenElse test = new AIIfThenElse(invtest,throwfailcondition,AINoInstruction.getNoInstruction());
			astpreinvariants.add(test);
			
			throwfailcondition = new AIThrowException((AIType) AIType.generateType("TamagoCCFailInvariant"));
			throwfailcondition.addArgument(new AIString(gen.getStrExpression()));
			if(invariant.getMessage().length() > 0) {
				
				AString strmessage = new AIString(invariant.getMessage());
				throwfailcondition.addArgument(strmessage);
			}
			throwfailcondition.addArgument(new AILanguageExpr("InvariantPosition.AFTER"));
			invtest = new AINot(cond);
			test = new AIIfThenElse(invtest,throwfailcondition,AINoInstruction.getNoInstruction());
			astpostinvariants.add(test);
			
			
		}
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
		
		AIBodyMethodContainer body = new AIBodyMethodContainer();
		// Now we fill the body of the method
		
		// call invariant
		Iterator<? extends AInstruction> invs = astpreinvariants.iterator();
		while(invs.hasNext()) {
			AInstruction inst = invs.next();
			body.addPreInvariantInstruction(inst);
		}
		
		// can call method
		if(hasBehavior)
		{
			AIString strMethodID = new AIString(method.getID());
			AICall canCallMethod = new AICall(ident("canCallMethod"));
			AICall methodID = new AICall(ident("methodID"));
			methodID.addArgument(strMethodID);
			canCallMethod.addArgument(methodID);
			AINot condition = new AINot(canCallMethod);

			AIThrowException notactivate = new AIThrowException((AIType) AIType.generateType("TamagoCCBehaviorException"));
			notactivate.addArgument(methodID);
			
			AIIfThenElse ifcancall = new AIIfThenElse(condition,notactivate,AINoInstruction.getNoInstruction());
			body.addInitPrecondition(ifcancall);
		}
		
		// appelle recursif
		AVariable varidrenvoie = null;
		AIdent identifiantrenvoie = null;
		
		if(method.mustSaveResult()) {
			GVariable variable = method.getSavedResult();
			varidrenvoie  = (AVariable)variable.visit(this);
			identifiantrenvoie = varidrenvoie.getIdent();
			
			AIInitialisation init = new AIInitialisation(identifiantrenvoie,type);
			body.addInitBodyInstruction(init);
		}
		AICall callsubmeth = new AICall(ident);
		Iterator<AParameter> aparameters = m.getParameters();
		while(aparameters.hasNext()) {
			AParameter parameter = aparameters.next();
			callsubmeth.addArgument(new AIVariable(parameter.getIdent()));
		}
		AIInLabel insubcomponent = new AIInLabel(component.getCallMe(),callsubmeth);
			
		
		if(method.mustSaveResult()) {
			AIAffectation affect = new AIAffectation(varidrenvoie,insubcomponent);
			body.addRedirectionInstruction(affect);
		}
		else
			body.addRedirectionInstruction(new AIInstExpression(insubcomponent));
		
		// fin de l'appelle recursif
		
		if(hasBehavior)
		{
			AIString strMethodID = new AIString(method.getID());
			AICall methodID = new AICall(ident("methodID"));
			methodID.addArgument(strMethodID);
			
			AICall fetch = new AICall(ident("fetchServiceBehavior"));
			fetch.addArgument(methodID);
			
			body.addUpdateBehaviorInstruction(new AIInstExpression(fetch));
		}
				
		// invariant encore
		invs = astpostinvariants.iterator();
		while(invs.hasNext()) {
			AInstruction inst = (AInstruction)invs.next();
			body.addPostInvariantInstruction(inst);
		}
		
		// renvoie de la valeur si necessaire
		if(method.mustSaveResult()) {
			AIReturn renvoie = new AIReturn(varidrenvoie);
			body.addReturnResult(renvoie);
		}
		
		//end of body
		m.setBody(body);
		ast.addMethod(m);
		return m;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitProperty(tamagocc.generic.api.GProperty)
	 */
	public Object visitProperty(GProperty property) throws TamagoCCException {
		AIProperty prop = new AIProperty(false,property.getName(),(AType) property.getType().visit(this),
				property.getAccess().canRead(),property.getAccess().canWrite());
		this.ast.addProperty(prop);
		
		/*
		String part = propertyToMethod(property.getName());
		AType type = type(property.getType());
		AVisibility visibility = AIVisibility.PUBLIC_VISIBILITY;
		
		if(property.getAccess().canRead()) {
			AIdent name = ident("get"+part);
			ADocComment comment = new AIDocComment("Getter of the property "+ property.getName());
			AIMethod getter = new AIMethod(AMethod.IMPLEMENTED,name,type,visibility);
			
			// body of the method
			AICall callsubmeth = new AICall(name);
			AIInLabel insubcomponent = new AIInLabel(component.getCallMe(),callsubmeth);
			AIReturn ret = new AIReturn(insubcomponent);
			getter.setBody(ret);
			// end of body
			
			
			getter.setComment(comment);
			ast.addMethod(getter);
		}
		
		if(property.getAccess().canWrite()) {
			AIdent name = ident("set"+part);
			AType vtype = new AIType(CatType.VOID,"void");
			ADocComment comment = new AIDocComment("Setter of the property "+ property.getName());
			AIMethod setter = new AIMethod(AMethod.IMPLEMENTED,name,vtype,visibility);
			AIParameter parameter = new AIParameter(ident(property.getName()),type);
			setter.addParameters(parameter);
			
			// set the property
			AICall setprop = new AICall(name);
			setprop.addArgument(new AIVariable(parameter.getIdent()));
			AIInLabel insub = new AIInLabel(component.getCallMe(),setprop);
			setter.setBody(new AIInstExpression(insub));
			
			// end of the body
			
			setter.setComment(comment);
			ast.addMethod(setter);
		}
		*/
		return null;
	}

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitProvide(tamagocc.generic.api.GProvide)
	 */
	public Object visitProvide(GProvide provide) throws TamagoCCException {
		/*AIImplements impl = new AIImplements((AType) visitType(provide.getNameAsType()));
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

	/**
	 * @see tamagocc.generic.TamagoCCGVisitor#visitRequire(tamagocc.generic.api.GRequire)
	 */
	public Object visitRequire(GRequire require) throws TamagoCCException {
		AIdent ident = new AIIdent(require.getLabel());
		//GServiceInterface service = (GServiceInterface)TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(require.getServiceName(),require.getServiceModule());
		//String fullname = owner.getBuilder().generateInterfaceName(service);
		AType type = (AIType) AIType.generateType(require.getNameAsType().getType()); 
		
		AIDocComment doccomment = new AIDocComment("This method allow user to access to the required service." +
				"(You do not need to implement this method, TamagoCC generates it in the skeleton)");
		
		AIMethod accesseur = new AIMethod(AMethod.IMPLEMENTED,ident,type,AIVisibility.PUBLIC_VISIBILITY);
		accesseur.setComment(doccomment);
				
		AICall sublabel = new AICall(ident);
		AExpression scope = new AIInLabel(component.getCallMe(),sublabel);
		AReturn ret = new AIReturn(scope);
		accesseur.setBody(ret);
		ast.addMethod(accesseur);
		
		AINamespace ns = new AINamespace(require.getServiceModule().getFullModule(),require.getServiceName());
		ast.addUsedNamespaces(ns);
		
		return null;
	}
		
	public Object visitService(GServiceInterface service)throws TamagoCCException {
		AINamespace ns = new AINamespace(service.getModule().getFullModule(),service.getName());
		ast.addUsedNamespaces(ns);
		return null;
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
		AIMethod method = new AIMethod(AMethod.IMPLEMENTED,ident("isBinded"),(AIType) AIType.generateType("bool"),AIVisibility.PUBLIC_VISIBILITY);
		
		AIOperator ope = new AIOperator(TOpeName.opEg);
		ope.addOperand(component.getCallMe());
		ope.addOperand(new AINil());
		
		method.setBody(new AIReturn(ope));
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
	
	public Object visitInLabel(GInLabel inlabel) throws TamagoCCException {
		if(inlabel.getTarget().getCategory() == GExprType.VARIABLE) {
			String label = ((GVariable)inlabel.getTarget()).getName();
			GComponentContainer comp = (GComponentContainer) entity;
			Iterator<GRequire> reqs = comp.getAllRequire();
			while(reqs.hasNext()) {
				GRequire req = reqs.next();
				if(req.getLabel().equals(label)) {
					AExpression expr = (AExpression)inlabel.getSubExpression().visit(this);
					
					AExpression scope = new AICall(ident(label));
					return new AIInLabel(scope,expr);
				}
					
			}
		}
		
		AExpression expr = (AExpression)inlabel.getSubExpression().visit(this);
		AExpression scope = (AExpression)inlabel.getTarget().visit(this);
				
		return new AIInLabel(scope, expr);
	}
	
}
