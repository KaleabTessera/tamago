/**
 * 
 */
package tamagotest.report;

import java.util.ArrayList;
import java.util.Iterator;

import tamagocc.api.TOpeName;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AInitialisation;
import tamagocc.ast.api.AModule;
import tamagocc.ast.api.ASet;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVariable;
import tamagocc.ast.impl.AIAffectation;
import tamagocc.ast.impl.AIBool;
import tamagocc.ast.impl.AICall;
import tamagocc.ast.impl.AIFor;
import tamagocc.ast.impl.AIForeach;
import tamagocc.ast.impl.AIIdent;
import tamagocc.ast.impl.AIImplements;
import tamagocc.ast.impl.AIInLabel;
import tamagocc.ast.impl.AIInitialisation;
import tamagocc.ast.impl.AIInteger;
import tamagocc.ast.impl.AILanguageExpr;
import tamagocc.ast.impl.AIModule;
import tamagocc.ast.impl.AINamespace;
import tamagocc.ast.impl.AINil;
import tamagocc.ast.impl.AINot;
import tamagocc.ast.impl.AIOperator;
import tamagocc.ast.impl.AIParameter;
import tamagocc.ast.impl.AIReal;
import tamagocc.ast.impl.AISequence;
import tamagocc.ast.impl.AISet;
import tamagocc.ast.impl.AIString;
import tamagocc.ast.impl.AIType;
import tamagocc.ast.impl.AIVariable;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GAccess;
import tamagocc.generic.api.GAllow;
import tamagocc.generic.api.GAssemblyContainer;
import tamagocc.generic.api.GAtPre;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GBind;
import tamagocc.generic.api.GBool;
import tamagocc.generic.api.GCall;
import tamagocc.generic.api.GCast;
import tamagocc.generic.api.GCategory;
import tamagocc.generic.api.GComponentContainer;
import tamagocc.generic.api.GCompositeContainer;
import tamagocc.generic.api.GCondition;
import tamagocc.generic.api.GExistColl;
import tamagocc.generic.api.GExistRange;
import tamagocc.generic.api.GExistSet;
import tamagocc.generic.api.GExport;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GExtendService;
import tamagocc.generic.api.GForallColl;
import tamagocc.generic.api.GForallRange;
import tamagocc.generic.api.GForallSet;
import tamagocc.generic.api.GImplements;
import tamagocc.generic.api.GInLabel;
import tamagocc.generic.api.GInState;
import tamagocc.generic.api.GInitialisation;
import tamagocc.generic.api.GInstantiateComponent;
import tamagocc.generic.api.GInteger;
import tamagocc.generic.api.GInvariant;
import tamagocc.generic.api.GIsBound;
import tamagocc.generic.api.GLanguageExpr;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GModule;
import tamagocc.generic.api.GNamespace;
import tamagocc.generic.api.GNil;
import tamagocc.generic.api.GNoContract;
import tamagocc.generic.api.GNot;
import tamagocc.generic.api.GObject;
import tamagocc.generic.api.GOperator;
import tamagocc.generic.api.GParameter;
import tamagocc.generic.api.GPercolator;
import tamagocc.generic.api.GPreInitialisation;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GProvide;
import tamagocc.generic.api.GRead;
import tamagocc.generic.api.GReal;
import tamagocc.generic.api.GRequire;
import tamagocc.generic.api.GReturn;
import tamagocc.generic.api.GServiceInterface;
import tamagocc.generic.api.GSet;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GString;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.generic.api.GTransition;
import tamagocc.generic.api.GType;
import tamagocc.generic.api.GVariable;
import tamagocc.generic.impl.GICast;
import tamagocc.generic.impl.GIQuantifierVariable;
import tamagocc.util.NilCollection;

/**
 * permet de fixer le scope vers un groupe de variable
 * 
 * @author Hakim Belhaouari
 *
 */
public class ConverterScope implements TamagoCCGVisitor {
	
	protected String propertyToMethod(String name) {
		return (name.substring(0,1).toUpperCase()+name.substring(1));
	}
	
	protected static AIdent ident(String id) {
		return new AIIdent(id);
	}
	
	protected static AModule module(GModule module) {
		return new AIModule(module.getFullModule());		
	}
	
	protected static AType type(GType type) {
		return AIType.generateType(type.getType());// new AIType(type.catType(),type.getType());
	}
	
	protected static AVariable variable(String id) {
		return new AIVariable(ident(id));
	}
	protected static AVariable variable(AIdent ident) {
		return new AIVariable(ident);
	}
	
	
	protected GTamago entity;
	protected AExpression scope;
	protected ArrayList<String> exceptionprops;
	protected ArrayList<String> exceptionvars;
		
	public ConverterScope(AExpression scope,GTamago entity) {
		this.entity = entity;
		this.scope = scope;
		exceptionvars = new ArrayList<String>();
		exceptionprops = new ArrayList<String>();
	}
	
	public void addProp(String id) {
		exceptionprops.add(id);
	}
	
	public void addVar(String id) {
		exceptionvars.add(id);
	}
	
	public void removeProp(String id) {
		exceptionprops.remove(id);
	}
	
	public void removeVar(String id) {
		exceptionvars.remove(id);
	}
	
	public Object visitAccess(GAccess access) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitAllow(GAllow allow) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitAssembly(GAssemblyContainer assembly) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitBehavior(GBehavior behavior) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitBind(GBind bind) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitBool(GBool bool) throws TamagoCCException {
		return new AIBool(bool.getValue());
	}

	

	public Object visitCategory(GCategory category) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitComponent(GComponentContainer component) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitComposite(GCompositeContainer composite) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitCondition(GCondition condition) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitDefinition(GInstantiateComponent definition) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitExport(GExport export) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitExpression(GExpression e) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitExtendService(GExtendService extendservice) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitInitialisation(GInitialisation initialisation) throws TamagoCCException {
		AExpression expr = (AExpression)initialisation.getInitValue().visit(this);
		initialisation.getVariable().getName();
		
		AIInitialisation init = new AIInitialisation(
				new AIIdent(initialisation.getVariable().getName()),
				(AType)initialisation.getVariable().getType().visit(this),
				expr);
		return init;
	}

	public Object visitInvariant(GInvariant invariant) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitInteger(GInteger integer) throws TamagoCCException {
		return new AIInteger(integer.getValue());
	}

	public Object visitInstantiateComponent(GInstantiateComponent component) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitMethod(GMethod method) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitModule(GModule module) throws TamagoCCException {
		return module(module);
	}

	public Object visitNoContract(GNoContract nocontract) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitNot(GNot not) throws TamagoCCException {
		AExpression term = (AExpression)not.getTerm().visit(this);
		return new AINot(term);
	}

	public Object visitObject(GObject object) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}
	
	
	
	

	public Object visitOperator(GOperator operator) throws TamagoCCException {
		ArrayList<AExpression> operands = new ArrayList<AExpression>();
		Iterator<? extends GExpression> ioperands = operator.getOperands();
		
		while(ioperands.hasNext()) {
			AExpression expr = (AExpression) (((GExpression)ioperands.next()).visit(this));
			operands.add(expr);
		}
		return new AIOperator(operator.getOperator(),operands);
	}

	public Object visitParameter(GParameter parameter) throws TamagoCCException {
		return new AIParameter(ident(parameter.getName()),type(parameter.getType()));
	}

	public Object visitPre(GAtPre pre) throws TamagoCCException {
		return pre.getTerm().visit(this); 
	}

	public Object visitProperty(GProperty property) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitProvide(GProvide provide) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}



	public Object visitReal(GReal real) throws TamagoCCException {
		return new AIReal(real.getValue());
	}

	public Object visitRequire(GRequire require) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitReturn(GReturn ret) throws TamagoCCException {
		if(ret.hasIndex())
			return new AIVariable(ident(ret.getVariable().getName()),(AExpression) ret.getIndex().visit(this));
		else
			return new AIVariable(ident(ret.getVariable().getName()));
	}

	public Object visitService(GServiceInterface service) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitState(GState state) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitString(GString string) throws TamagoCCException {
		return new AIString(string.getValue());
	}

	public Object visitTamagoEntity(GTamagoEntity entity) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitTransition(GTransition transition) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}

	public Object visitType(GType type) throws TamagoCCException {
		return AIType.generateType(type.getType());// new AIType(type.catType(),type.getType());
	}

	public Object visitVariable(GVariable variable) throws TamagoCCException {
		return new AIVariable(ident(variable.getName()));
	}
	
	public Object visitRead(GRead read) throws TamagoCCException {
		if(scope == null || exceptionprops.contains(read.getName())) {
			return new AICall(ident("get"+propertyToMethod(read.getName())),new NilCollection<AExpression>());
		}
		else {
			AIInLabel change = new AIInLabel(scope,new AICall(ident("get"+propertyToMethod(read.getName())),new NilCollection<AExpression>()));
			return change;
		}
	}

	public Object visitInLabel(GInLabel inlabel) throws TamagoCCException {
		/*AExpression scope = (AExpression) inlabel.getTarget().visit(this);
		AExpression oldscope = this.scope;
		this.scope = null;		
		AExpression expr = (AExpression)inlabel.getSubExpression().visit(this);
		this.scope = oldscope;
		
		if(scope == null)
			return new AIInLabel(scope, expr);
		else {
			AIInLabel change = new AIInLabel(scope,new AIInLabel(scope, expr));
			return change;
		}*/
		
		
		AExpression cscope = (AExpression) inlabel.getTarget().visit(this);
		
		AExpression oldscope = this.scope;
		this.scope = null;		
		AExpression expr = (AExpression)inlabel.getSubExpression().visit(this);
		this.scope = oldscope;
		
		//if(scope == null)
			return new AIInLabel(cscope, expr);
		/*else {
			AIInLabel change = new AIInLabel(scope,new AIInLabel(cscope, expr));
			return change;
		}*/
	}
	
	public Object visitCall(GCall call) throws TamagoCCException {
		ArrayList<AExpression> args = new ArrayList<AExpression>();
		Iterator<? extends GExpression> iargs = call.getArguments();
		while(iargs.hasNext()) {
			GExpression expression = (GExpression)iargs.next();
			AExpression expr = (AExpression)expression.visit(this);
			args.add(expr);
		}
		
		if(scope == null)		
			return new AICall(ident(call.getName()),args);
		else {
			AIInLabel change = new AIInLabel(scope,new AICall(ident(call.getName()),args));
			return change;
		}
	}
	
	
	public Object visitPercolator(GPercolator percolator) throws TamagoCCException {
		throw new TamagoCCException("ConverterScope : Not supported element");
	}
	
	public Object visitExistRange(GExistRange r) throws TamagoCCException {
		/* int i;
		 * boolean __exist_range_i;
		 * for(i = min; i <= max;i=i+1) {
		 * 		__exist_range_i = __exist_range_i || r.body();
		 * }
		 */
		AIType typebool = AIType.typeBOOLEAN;
		AType type = (AType)r.getType().visit(this);
		
		switch(type.getCategoryType()) {
		case BOOL:
		case OBJECT:
		case STRING:
		case VOID:
		case ARRAY:
		case GENERIC:
			throw new TamagoCCException("Not enumerative type in the exist contract");
		default:
			
		}
		
		AVariable var = (AVariable)r.getVariable().visit(this);
		AInitialisation vari = new AIInitialisation(var.getIdent(),type);
		// TODO : PROBLEME DE TYPE ICI !!!!
		// l'utilisateur ne sait pas que c'est le type AVariable qui est renvoye.
		AVariable resvar = (AVariable) r.getResultExpression().visit(this);
		AInitialisation resvari = new AIInitialisation(resvar.getIdent(),typebool,new AIBool(false));
		
		AExpression min = (AExpression)r.getMin().visit(this);
		AIAffectation affect = new AIAffectation(var,min);
		
		AIOperator cond = new AIOperator(TOpeName.opInfEg);
		cond.addOperand(var);
		AExpression max = (AExpression)r.getMax().visit(this);
		cond.addOperand(max);
		
		AIOperator incr = new AIOperator(TOpeName.opPlus);
		incr.addOperand(var);
		incr.addOperand(new  AIInteger(1));
		AIAffectation affectincr = new AIAffectation(var,incr);
		
		AIOperator orelse = new AIOperator(TOpeName.opOr);
		AExpression body = (AExpression)r.getBody().visit(this);
		orelse.addOperand(body);
		orelse.addOperand(resvar);
		AIAffectation resbody = new AIAffectation(resvar,orelse);
		
		AIFor loop = new AIFor(affect,cond,affectincr,resbody);
		
		AISequence seq = new AISequence();
		seq.addInstruction(vari);
		seq.addInstruction(resvari);
		seq.addInstruction(loop);
		
		return seq;
	}


	public Object visitForallRange(GForallRange r) throws TamagoCCException {
		/* int i;
		 * boolean __forall_range_i;
		 * for(i = min; i <= max;i=i+1) {
		 * 		__forall_range_i = __forall_range_i && r.body();
		 * }
		 */
		AIType typebool = AIType.typeBOOLEAN;
		AType type = (AType)r.getType().visit(this);
		
		switch(type.getCategoryType()) {
		case BOOL:
		case OBJECT:
		case STRING:
		case VOID:
		case ARRAY:
		case GENERIC:
			throw new TamagoCCException("Not enumerative type in the exist contract");
		default:
			
		}
		
		AVariable var = (AVariable)r.getVariable().visit(this);
		AInitialisation vari = new AIInitialisation(var.getIdent(),type);
		// DONE : PROBLEME DE TYPE ICI !!!!
		// l'utilisateur ne sait pas que c'est le type AVariable qui est renvoye.
		AVariable resvar =  (AVariable)r.getResultExpression().getSimpleVariable().visit(this);
		AInitialisation resvari = new AIInitialisation(resvar.getIdent(),typebool,new AIBool(true));
		
		AExpression min = (AExpression)r.getMin().visit(this);
		AIAffectation affect = new AIAffectation(var,min);
		
		AIOperator cond = new AIOperator(TOpeName.opInfEg);
		cond.addOperand(var);
		AExpression max = (AExpression)r.getMax().visit(this);
		cond.addOperand(max);
		
		AIOperator incr = new AIOperator(TOpeName.opPlus);
		incr.addOperand(var);
		incr.addOperand(new  AIInteger(1));
		AIAffectation affectincr = new AIAffectation(var,incr);
		
		AIOperator orelse = new AIOperator(TOpeName.opAnd);
		AExpression body = (AExpression)r.getBody().visit(this);
		orelse.addOperand(body);
		orelse.addOperand(resvar);
		AIAffectation resbody = new AIAffectation(resvar,orelse);
		
		AIFor loop = new AIFor(affect,cond,affectincr,resbody);
		
		AISequence seq = new AISequence();
		seq.addInstruction(vari);
		seq.addInstruction(resvari);
		seq.addInstruction(loop);
		
		return seq;
	}
	

	public Object visitExistSet(GExistSet r) throws TamagoCCException {
		AIType typebool = AIType.typeBOOLEAN;
		AType type = (AType)r.getType().visit(this);
		AVariable var = (AVariable)r.getVariable().visit(this);
		
		AVariable resvar =  (AVariable)r.getResultExpression().getSimpleVariable().visit(this);
		AInitialisation resvari = new AIInitialisation(resvar.getIdent(),typebool,new AIBool(false));
		
		AIOperator orelse = new AIOperator(TOpeName.opOr);
		AExpression body = (AExpression)r.getBody().visit(this);
		orelse.addOperand(body);
		orelse.addOperand(resvar);
		AIAffectation resbody = new AIAffectation(resvar,orelse);
		
		ASet set = (ASet)r.getSet().visit(this);
		
		AIForeach foreach = new AIForeach(var,type,new AIVariable(new AIIdent(set.getName())),resbody);
		
		AISequence seq = new AISequence();
		seq.addInstruction(resvari);
		seq.addInstruction(set.getInitialize());
		seq.addInstruction(foreach);
		
		return seq;
	}

	public Object visitForallSet(GForallSet r) throws TamagoCCException {
		AIType typebool = AIType.typeBOOLEAN;
		AType type = (AType)r.getType().visit(this);
		AVariable var = (AVariable)r.getVariable().visit(this);
		
		AVariable resvar =  (AVariable)r.getResultExpression().getSimpleVariable().visit(this);
		AInitialisation resvari = new AIInitialisation(resvar.getIdent(),typebool,new AIBool(true));
		
		AIOperator orelse = new AIOperator(TOpeName.opAnd);
		AExpression body = (AExpression)r.getBody().visit(this);
		orelse.addOperand(body);
		orelse.addOperand(resvar);
		AIAffectation resbody = new AIAffectation(resvar,orelse);
		
		ASet set = (ASet)r.getSet().visit(this);
		
		AIForeach foreach = new AIForeach(var,type,new AIVariable(new AIIdent(set.getName())),resbody);
		
		AISequence seq = new AISequence();
		seq.addInstruction(resvari);
		seq.addInstruction(set.getInitialize());
		seq.addInstruction(foreach);
		
		return seq;
	}
	
	public Object visitExistColl(GExistColl f) throws TamagoCCException {
		AIType typebool = AIType.typeBOOLEAN;
		AType type = (AType)f.getType().visit(this);
		AVariable var = (AVariable)f.getVariable().visit(this);
		
		AVariable resvar = (AVariable)f.getResultExpression().getSimpleVariable().visit(this);
		AInitialisation resvari = new AIInitialisation(resvar.getIdent(),typebool,new AIBool(true));
		
		AIOperator orelse = new AIOperator(TOpeName.opOr);
		AExpression body = (AExpression)f.getBody().visit(this);
		orelse.addOperand(body);
		orelse.addOperand(resvar);
		AIAffectation resbody = new AIAffectation(resvar,orelse);
		
		AExpression collection = (AExpression) f.getCollection().visit(this);
		
		AIForeach foreach = new AIForeach(var,type,collection,resbody);
		
		AISequence seq = new AISequence();
		seq.addInstruction(resvari);
		seq.addInstruction(foreach);
		
		return seq;
	}

	public Object visitForallColl(GForallColl f) throws TamagoCCException {
		AIType typebool = AIType.typeBOOLEAN;
		AType type = (AType)f.getType().visit(this);
		AVariable var = (AVariable)f.getVariable().visit(this);
		
		AVariable resvar = (AVariable)f.getResultExpression().getSimpleVariable().visit(this);
		AInitialisation resvari = new AIInitialisation(resvar.getIdent(),typebool,new AIBool(true));
		
		AIOperator andalso = new AIOperator(TOpeName.opAnd);
		AExpression body = (AExpression)f.getBody().visit(this);
		andalso.addOperand(body);
		andalso.addOperand(resvar);
		AIAffectation resbody = new AIAffectation(resvar,andalso);
		
		AExpression collection = (AExpression) f.getCollection().visit(this);
		
		AIForeach foreach = new AIForeach(var,type,collection,resbody);
		
		AISequence seq = new AISequence();
		seq.addInstruction(resvari);
		seq.addInstruction(foreach);
		
		return seq;
	}


	public Object visitSet(GSet set) throws TamagoCCException {
		ArrayList<AExpression> expr = new ArrayList<AExpression>();
		
		for (GExpression e : set.getElements()) {
			expr.add((AExpression)e.visit(this));
		}
		
		AISet aset = new AISet(expr); 
		return aset;
	}
	
	public Object visitQuantifierVariable(GIQuantifierVariable variable) throws TamagoCCException {
		/*switch(variable.getQuantifier().getQuantifierType()) {
		case EXISTRANGE:
			return visitExistRange((GExistRange) variable.getQuantifier());
		case EXISTSET:
			return visitExistSet((GExistSet) variable.getQuantifier());
		case FORALLRANGE:
			return visitForallRange((GForallRange) variable.getQuantifier());
		case FORALLSET:
			return visitForallSet((GForallSet) variable.getQuantifier());
		}
		throw new TamagoCCException("Unsupported element");*/
		return visitVariable(variable);
	}
	

	public Object visitNil(GNil nil) throws TamagoCCException {
		return new AINil();
	}
	
	public Object visitImplements(GImplements impl) throws TamagoCCException {
		return new AIImplements((AType)impl.getType().visit(this));
	}
	
	public Object visitNamespace(GNamespace namespace) throws TamagoCCException {
		return new AINamespace(namespace.getNamespace(),namespace.getType());
	}

	public Object visitLanguageExpr(GLanguageExpr languageExpr)
			throws TamagoCCException {
		return new AILanguageExpr(languageExpr.getExpression());
	}

	/**
	 * TODO pas sur de l'implantation
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitCast(tamagocc.generic.api.GCast)
	 */
	public Object visitCast(GCast cast) throws TamagoCCException {
		return new GICast(cast.getType(), (GExpression)cast.getExpression().visit(this));
	}

	public Object visitPreInitialisation(GPreInitialisation initialisation) throws TamagoCCException {
		AExpression expr = (AExpression)initialisation.getInitValue().visit(this);
		initialisation.getVariable().getName();
		
		AIInitialisation init = new AIInitialisation(
				new AIIdent(initialisation.getVariable().getName()),
				(AType)initialisation.getVariable().getType().visit(this),
				expr);
		return init;
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
