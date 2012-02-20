package tamagocc.generator;

import java.util.ArrayList;
import java.util.Iterator;

import tamagocc.api.TOpeName;
import tamagocc.ast.api.AConvertType;
import tamagocc.ast.api.AEntity;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AInLabel;
import tamagocc.ast.api.AInitialisation;
import tamagocc.ast.api.AModule;
import tamagocc.ast.api.ANot;
import tamagocc.ast.api.ASet;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVariable;
import tamagocc.ast.impl.AIAffectation;
import tamagocc.ast.impl.AIBool;
import tamagocc.ast.impl.AICall;
import tamagocc.ast.impl.AIConvertType;
import tamagocc.ast.impl.AIEntity;
import tamagocc.ast.impl.AIFor;
import tamagocc.ast.impl.AIForeach;
import tamagocc.ast.impl.AIIdent;
import tamagocc.ast.impl.AIImplements;
import tamagocc.ast.impl.AIInLabel;
import tamagocc.ast.impl.AIInState;
import tamagocc.ast.impl.AIInitialisation;
import tamagocc.ast.impl.AIInteger;
import tamagocc.ast.impl.AIIsBound;
import tamagocc.ast.impl.AILanguageExpr;
import tamagocc.ast.impl.AILongComment;
import tamagocc.ast.impl.AIModule;
import tamagocc.ast.impl.AINamespace;
import tamagocc.ast.impl.AINil;
import tamagocc.ast.impl.AINot;
import tamagocc.ast.impl.AIOperator;
import tamagocc.ast.impl.AIParameter;
import tamagocc.ast.impl.AIRead;
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
import tamagocc.generic.api.GExpression.GExprType;
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
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.generic.api.GTransition;
import tamagocc.generic.api.GType;
import tamagocc.generic.api.GVariable;
import tamagocc.generic.impl.GIQuantifierVariable;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.TamagoCCGenUtil;
import tamagocc.util.TamagoCCMakeReadableGExpression;


/**
 * This class is the root of all generator
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCGeneratorCommon implements TamagoCCGVisitor {

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
	
	
	protected AExpression globalscope;
	protected GTamagoEntity entity;
	protected AIEntity ast;
	protected boolean convertingFirstExpression;
	private boolean visited;
	protected TamagoCCIGenerator owner;
	
	
	public TamagoCCGeneratorCommon(TamagoCCIGenerator owner,GTamagoEntity entity) {
		this.entity = entity;
		this.owner = owner;
		if(owner == null) {
			this.owner = TamagoCCDefaultIGeneratorJava.getInstance();
		}
		
		
		GType[] types = entity.getParametrizedTypes();
		AType[] atypes = new AType[types.length];
		for(int i = 0; i < types.length; i++) {
			atypes[i] = AIType.generateType(types[i].getType());
		}
		
		this.ast = new AIEntity(entity.getName(),module(entity.getModule()),atypes);
		TamagoCCLogger.println(5, "///////////////////////////AIEntity avec :"+entity.getName());
		
		ast.setComment(new AILongComment(TamagoCCGenUtil.headerFile(entity.getName())));
		visited = false;
		convertingFirstExpression = false;
	}
	
	public AEntity getASTEntity() throws TamagoCCException{
		if(!visited) {
			entity.visit(this);
			visited = true;
		}
		return ast;
	}

	public Object visitAccess(GAccess access) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitAllow(GAllow allow) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitAssembly(GAssemblyContainer assembly) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitBehavior(GBehavior behavior) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitBind(GBind bind) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitBool(GBool bool) throws TamagoCCException {
		return new AIBool(bool.getValue());
	}

	public Object visitCall(GCall call) throws TamagoCCException {
		ArrayList<AExpression> args = new ArrayList<AExpression>();
		Iterator<? extends GExpression> iargs = call.getArguments();
		while(iargs.hasNext()) {
			GExpression expression = (GExpression)iargs.next();
			AExpression expr = (AExpression)expression.visit(this);
			args.add(expr);
		}		
		return new AICall(ident(call.getName()),args);
	}

	public Object visitCategory(GCategory category) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitComponent(GComponentContainer component) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitComposite(GCompositeContainer composite) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitCondition(GCondition condition) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitDefinition(GInstantiateComponent definition) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitExport(GExport export) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitExpression(GExpression e) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitExtendService(GExtendService extendservice) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
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
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitInteger(GInteger integer) throws TamagoCCException {
		return new AIInteger(integer.getValue());
	}

	public Object visitInstantiateComponent(GInstantiateComponent component) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitMethod(GMethod method) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitModule(GModule module) throws TamagoCCException {
		return module(module);
	}

	public Object visitNoContract(GNoContract nocontract) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitNot(GNot not) throws TamagoCCException {
		AExpression term = (AExpression)not.getTerm().visit(this);
		return new AINot(term);
	}

	public Object visitObject(GObject object) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}
	
	/*
	private AExpression equalOper(boolean eq,GExpression g, GExpression d) throws TamagoCCException {
		if(isSimpleType(g) && isSimpleType(d)) {
			TamagoCCLogger.infoln(3,"\t2 simple types");
			ArrayList<AExpression> operands = new ArrayList<AExpression>();
			operands.add((AExpression) g.visit(this));
			operands.add((AExpression) d.visit(this));
			if(eq)
				return new AIOperator(TOpeName.opEg,operands);
			else
				return new AIOperator(TOpeName.opNe,operands);
		}
		else if(isSimpleType(g) && !isSimpleType(d)) {
			TamagoCCLogger.infoln(3,"\tsimple type on left, and not on right");
			AICall call = new AICall(ident("equals"));
			call.addArgument((AExpression) g.visit(this));
			AInLabel inlabel = new AIInLabel((AExpression) d.visit(this),call);
			
			if(eq)
				return inlabel;
			else
				return new AINot(inlabel);
		}
		else {
			TamagoCCLogger.infoln(3,"\tother cases");
			AICall call = new AICall(ident("equals"));
			call.addArgument((AExpression) d.visit(this));
			AInLabel inlabel = new AIInLabel((AExpression) g.visit(this),call);
			
			if(eq)
				return inlabel;
			else
				return new AINot(inlabel);
		}
	}

	private boolean isSimpleType(GExpression d) {
		switch(d.getCategory()) {
		case BOOL:
		case INT:
		case NOT:
		case REAL:
			TamagoCCLogger.infoln(3,"\tSimple expression detected");
			return true;
		case CALL:
			TamagoCCLogger.infoln(3,"\tCall expression detected");
			GCall call = (GCall)d;
			for (GMethod method : ((GTamago) entity).getMetodsByName(call.getName())) {
				if(method.getParameterNumber() == call.getArgCount()) {
					TamagoCCLogger.println(3,"\tmethod found");
					return isSimpleType(method.getType());
				}
			}
			TamagoCCLogger.println(3,"\tmethod unfound: "+call.getName());
			break;
		case OPERATOR: {
			TamagoCCLogger.println(2,"*Warning* expression not fully implemented (0x25d689)");
			return true;
		}
		case READ:
			TamagoCCLogger.infoln(3,"\tProperty expression detected");
			GProperty prop = ((GTamago) entity).searchProperty(((GRead)d).getName());
			return isSimpleType(prop.getType());
		case INLABEL:
			// TODO verifier les scopes de toutes les variables.
			//return isSimpleType(((GInLabel)d).getSubExpression());
			return true; // au pire on inverse l'expression
		}
		return false;
	}

	private boolean isSimpleType(GType type) {
		switch(type.catType()) {
		case BOOL:
		case INTEGER:
		case REAL:
			return true;
		default:
			return false;
		}
	}
 	*/
	
	public Object visitOperator(GOperator operator) throws TamagoCCException {
		ArrayList<AExpression> operands = new ArrayList<AExpression>();
		Iterator<? extends GExpression> ioperands = operator.getOperands();
		
		/*if(operator.getArity() == 2) {
			TamagoCCLogger.println(3, "Binary equality operator");
			GExpression g = operator.getOperand(0);
			GExpression d = operator.getOperand(1);
			if ((operator.getOperator().getID() == TOpeName.EG)) return equalOper(true, g, d);
			if ((operator.getOperator().getID() == TOpeName.NE)) return equalOper(false, g, d);
		}*/
		
		while(ioperands.hasNext()) {
			GExpression gexpr = (GExpression)ioperands.next();
			if(gexpr.getCategory() != GExprType.NOCONTRACT) { 
				AExpression expr = (AExpression) (gexpr.visit(this));
				operands.add(expr);
			}
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
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitProvide(GProvide provide) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitRead(GRead read) throws TamagoCCException {
		//return new AICall(ident("get"+propertyToMethod(read.getName())),new NilCollection<AExpression>());
		if(read.hasIndex()) {
			AExpression expr = (AExpression) read.getIndex().visit(this);
			return new AIRead(read.getName(),expr);
		}
		else
			return new AIRead(read.getName());
	}

	public Object visitReal(GReal real) throws TamagoCCException {
		return new AIReal(real.getValue());
	}

	public Object visitRequire(GRequire require) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitReturn(GReturn ret) throws TamagoCCException {
		if(ret.hasIndex()) {
			AExpression idx = (AExpression) ret.getIndex().visitExpression(this);
			return new AIVariable(ident(ret.getVariable().getName()),idx);
		}
		else
			return new AIVariable(ident(ret.getVariable().getName()));
	}
	
	public Object visitService(GServiceInterface service) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitState(GState state) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitString(GString string) throws TamagoCCException {
		return new AIString(string.getValue());
	}

	public Object visitTamagoEntity(GTamagoEntity entity) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitTransition(GTransition transition) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
	}

	public Object visitType(GType type) throws TamagoCCException {
		return AIType.generateType(type.getType());// new AIType(type.catType(),type.getType());
	}

	public Object visitVariable(GVariable variable) throws TamagoCCException {
		if(variable.hasIndex()) {
			GExpression idx = variable.getIndex();
			AExpression index = (AExpression) idx.visitExpression(this);
			return new AIVariable(ident(variable.getName()), index);
		}
		else
			return new AIVariable(ident(variable.getName()));
	}

	public Object visitInLabel(GInLabel inlabel) throws TamagoCCException {
		AExpression expr = (AExpression)inlabel.getSubExpression().visit(this);
		AExpression scope = (AExpression) inlabel.getTarget().visit(this);
				
		return new AIInLabel(scope, expr);
	}
	
	
	public Object visitPercolator(GPercolator percolator) throws TamagoCCException {
		throw new TamagoCCException("TamagoCCGeneratorCommon : Not supported element");
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
		
		// DONE : PROBLEME DE TYPE ICI !!!!
		// l'utilisateur ne sait pas que c'est le type AVariable qui est renvoye.
		AVariable resvar =  (AVariable)r.getResultExpression().getSimpleVariable().visit(this);
		AInitialisation resvari = new AIInitialisation(resvar.getIdent(),typebool,new AIBool(true));
		
		
		
		AExpression min = (AExpression)r.getMin().visit(this);
		
		AVariable var = (AVariable)r.getVariable().visit(this);
		AInitialisation vari = new AIInitialisation(var.getIdent(),type,min);
		
		//AIAffectation affect = new AIAffectation(var,min);
		
		AIOperator cond = new AIOperator(TOpeName.opInfEg);
		cond.addOperand(var);
		AExpression max = (AExpression)r.getMax().visit(this);
		cond.addOperand(max);
		
		AIOperator incr = new AIOperator(TOpeName.opPlus);
		incr.addOperand(var);
		incr.addOperand(new  AIInteger(1));
		AIAffectation affectincr = new AIAffectation(var,incr);
		
		AIOperator orelse = new AIOperator(TOpeName.opAnd);
		
		TamagoCCLogger.println(3, "ForallRange:"+TamagoCCMakeReadableGExpression.toString(r.getBody()));
		
		
		AExpression body = (AExpression)r.getBody().visit(this);
		orelse.addOperand(body);
		orelse.addOperand(resvar);
		AIAffectation resbody = new AIAffectation(resvar,orelse);
		
		AIFor loop = new AIFor(vari,cond,affectincr,resbody);
		
		AISequence seq = new AISequence();
		//seq.addInstruction(vari);
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
		AInitialisation resvari = new AIInitialisation(resvar.getIdent(),typebool,new AIBool(false));
		
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
	 * @see tamagocc.generic.TamagoCCGExpressionVisitor#visitCast(tamagocc.generic.api.GCast)
	 */
	public Object visitCast(GCast cast) throws TamagoCCException {
		AType type = AIType.generateType(cast.getType().getType());
		AExpression expr = (AExpression) cast.getExpression().visit(this);
		return new AIConvertType(type,expr);
	}

	/**
	 * @see tamagocc.generic.TamagoCCGPreExpressionVisitor#visitPreInitialisation(tamagocc.generic.api.GPreInitialisation)
	 */
	public Object visitPreInitialisation(GPreInitialisation initialisation)
			throws TamagoCCException {
		AExpression expr = (AExpression)initialisation.getInitValue().visit(this);
		
		switch(initialisation.getVariable().getType().catType()) {
		case BOOL:
		case GENERIC:
		case INTEGER:
		case REAL:
		case VOID:
			TamagoCCLogger.println(3, "AtPre operator on a simple type");
			break;
		case ARRAY:
		case OBJECT:
		case STRING:
			TamagoCCLogger.println(3, "AtPre operator on a complex type ("+initialisation.getVariable().getType().getType()+")");
			expr = clonePreExpr(expr);
			break;
		}
		
		
		
		AIInitialisation init = new AIInitialisation(
				new AIIdent(initialisation.getVariable().getName()),
				(AType)initialisation.getVariable().getType().visit(this),
				expr);
		return init;
	}

	/**
	 * @param expr
	 * @return
	 */
	private AExpression clonePreExpr(AExpression expr) {
		switch(expr.getExpressionType()) {
		case AExpression.CALL: 
		case AExpression.VARIABLE:
		case AExpression.TAMAGONEW:
		case AExpression.READ:
		case AExpression.SELF:
		case AExpression.NEW:
		{
			AIInLabel inlabel = new AIInLabel(expr,new AICall(ident("clone")));
			return inlabel;
		}
		case AExpression.INLABEL: {
			AInLabel iexpr = (AInLabel) expr;
			AIInLabel inlabel = new AIInLabel(iexpr.getScope(), clonePreExpr(iexpr.getSubExpression()));
			return inlabel;
		}
		case AExpression.CONVERTTYPE: {
			AConvertType cexpr = (AConvertType)expr;
			return new AIConvertType(cexpr.getNewType(),clonePreExpr(cexpr.getExprToConvert()));
		}
		case AExpression.NOT: {
			ANot not = (ANot)expr;
			return new AINot(clonePreExpr(not.getSubExpression()));
		}
		
		
		}
		return null;
	}

	@Override
	public Object visitInState(GInState giInState) throws TamagoCCException {
		return new AIInState(giInState.getInState());
	}

	@Override
	public Object visitIsBound(GIsBound giIsBound) throws TamagoCCException {
		return new  AIIsBound(giIsBound.getLabel());
	}
}
