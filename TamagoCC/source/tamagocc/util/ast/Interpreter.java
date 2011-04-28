/**
 * 
 */
package tamagocc.util.ast;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;

import tamagocc.api.TOpeName;
import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AAffectation;
import tamagocc.ast.api.ABool;
import tamagocc.ast.api.ACall;
import tamagocc.ast.api.ACatchBlock;
import tamagocc.ast.api.ACodeComment;
import tamagocc.ast.api.AConvertType;
import tamagocc.ast.api.ADocComment;
import tamagocc.ast.api.AEntity;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AFor;
import tamagocc.ast.api.AForeach;
import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AIfThenElse;
import tamagocc.ast.api.AImplements;
import tamagocc.ast.api.AInLabel;
import tamagocc.ast.api.AInState;
import tamagocc.ast.api.AInherit;
import tamagocc.ast.api.AInitialisation;
import tamagocc.ast.api.AInlineComment;
import tamagocc.ast.api.AInstExpression;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AInteger;
import tamagocc.ast.api.ALanguageExpr;
import tamagocc.ast.api.ALongComment;
import tamagocc.ast.api.AMemberVariable;
import tamagocc.ast.api.AMethod;
import tamagocc.ast.api.AModule;
import tamagocc.ast.api.ANamespace;
import tamagocc.ast.api.ANew;
import tamagocc.ast.api.ANewArray;
import tamagocc.ast.api.ANil;
import tamagocc.ast.api.ANoExpression;
import tamagocc.ast.api.ANoInstruction;
import tamagocc.ast.api.ANot;
import tamagocc.ast.api.AOperator;
import tamagocc.ast.api.AParameter;
import tamagocc.ast.api.AReal;
import tamagocc.ast.api.AReturn;
import tamagocc.ast.api.ASelf;
import tamagocc.ast.api.ASequence;
import tamagocc.ast.api.ASet;
import tamagocc.ast.api.AString;
import tamagocc.ast.api.ATamagoNew;
import tamagocc.ast.api.AThrowException;
import tamagocc.ast.api.AThrowsException;
import tamagocc.ast.api.ATryCatchFinal;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVariable;
import tamagocc.ast.api.AVisibility;
import tamagocc.ast.api.AWhile;
import tamagocc.ast.impl.AConstructor;
import tamagocc.ast.impl.AIProperty;
import tamagocc.ast.impl.AIRead;
import tamagocc.exception.TamagoCCException;
import tamagocc.logger.TamagoCCLogger;

/**
 * @author Hakim Belhaouari
 *
 */
public class Interpreter implements TamagoCCASTVisitor {

	private ASTContext ctx;

	/**
	 * 
	 */
	public Interpreter() {
		ctx = new ASTContext();
	}
	
	public Interpreter(ASTContext ctx) {
		this.ctx = ctx;
	}

	public Interpreter(ASTValue self) {
		ctx = new ASTContext(self);
	}
	
	
	public Object visitAffectation(AAffectation affectation)
	throws TamagoCCException {
		ASTValue val = (ASTValue) affectation.getInitValeur().visit(this);
		ctx.getMemory().affect(affectation.getVariable(), val);
		return ASTvoid.single();
	}

	public Object visitBool(ABool bool) throws TamagoCCException {
		return ASTboolean.bool(bool.getValue());
	}

	public Object visitCall(ACall call) throws TamagoCCException {
		ASTObject obj = (ASTObject) ctx.scope();
		String name = call.getIdent().getName();
		ArrayList<ASTValue> al = new ArrayList<ASTValue>();
		Iterator<AExpression> args = call.getArguments();
		while(args.hasNext()) {
			AExpression expr = args.next();
			al.add((ASTValue) expr.visit(this));
		}
		ASTValue value = obj.call(name,al);
		return value;
	}

	public Object visitCatchBlock(ACatchBlock catchblock)
	throws TamagoCCException {
		// inutile
		catchblock.getBody().visit(this);
		return ASTvoid.single();
	}

	public Object visitCodeComment(ACodeComment codecomment)
	throws TamagoCCException {
		return ASTvoid.single();
	}

	public Object visitConstructor(AConstructor constructor)
	throws TamagoCCException {
		constructor.getBody().visit(this);
		return ASTvoid.single();
	}

	public Object visitConvertType(AConvertType ct) throws TamagoCCException {
		TamagoCCLogger.println(3, "*Warning* interpreter of convert type expression is not fully supported");
		return ct.getExprToConvert().visit(this);
	}

	public Object visitDocComment(ADocComment doccomment)
	throws TamagoCCException {
		return ASTvoid.single();
	}

	public Object visitEntity(AEntity entity) throws TamagoCCException {
		// appeler la methode main de l'entite
		/*Iterator<AMemberVariable> amv = entity.getVariablesMembers();
		while(amv.hasNext()) {
			AMemberVariable memvar = amv.next();
			switch(memvar.getType().getCategoryType()) {
			case BOOL:
				ctx.getMemory().put(memvar.getIdent().getName(), new ASTboolean(false));
				break;
			case INTEGER:
				ctx.getMemory().put(memvar.getIdent().getName(), new ASTinteger(0));
				break;
			case REAL:
				ctx.getMemory().put(memvar.getIdent().getName(), new ASTreal(0.0));
				break;
			case VOID:
				throw new ASTInterpreterException("no default value for void type");
			default:
				ctx.getMemory().put(memvar.getIdent().getName(), new ASTnil());
			break;
			}
		}*/

		throw new TamagoCCException("Interpreter of entity not supported");
	}

	public Object visitFor(AFor f) throws TamagoCCException {
		f.getAffectation().visit(this);
		while(((ASTboolean)f.getCondition().visit(this)).boolValue()) {
			f.getBody().visit(this);
			f.getIncrement().visit(this);
		}
		return ASTvoid.single();
	}

	public Object visitForeach(AForeach f) throws TamagoCCException {
		throw new TamagoCCException("Not yet implemented instrument foreach");
	}

	public Object visitIdent(AIdent ident) throws TamagoCCException {
		// rien a faire
		throw new TamagoCCException("Not yet implemented ident");
	}

	public Object visitIfThenElse(AIfThenElse ifthenelse)
	throws TamagoCCException {
		if(((ASTboolean)ifthenelse.getCondition()).boolValue()) {
			ifthenelse.getCons().visit(this);
		}
		else {
			ifthenelse.getAlt().visit(this);
		}
		return ASTvoid.single();
	}

	public Object visitImplement(AImplements impl) throws TamagoCCException {
		// inutile
		throw new TamagoCCException("Not yet implemented implements");
	}

	public Object visitInLabel(AInLabel inlabel) throws TamagoCCException {
		// change le scope
		ctx.pushScope((ASTValue) inlabel.getScope().visit(this));
		ASTValue obj = (ASTValue) inlabel.getSubExpression().visit(this);
		ctx.popScope();
		return obj;
	}

	public Object visitInherit(AInherit inherit) throws TamagoCCException {
		// inutile
		throw new TamagoCCException("Not yet implemented inherit");
	}

	public Object visitInitialisation(AInitialisation init)
	throws TamagoCCException {
		ctx.getMemory().initialize(init.getIdent().getName(), (ASTValue) init.getInitial().visit(this));
		return ASTvoid.single();
	}

	public Object visitInlineComment(AInlineComment inlinecomment)
	throws TamagoCCException {
		// inutile
		throw new TamagoCCException("Not yet implemented inline");
	}

	public Object visitInstExpression(AInstExpression instexpr)
	throws TamagoCCException {
		instexpr.getExpression().visit(this);
		throw new TamagoCCException("Not yet implemented instexpression");
	}

	public Object visitInteger(AInteger integer) throws TamagoCCException {
		return new ASTinteger(integer.getValue());
	}

	public Object visitLanguageExpr(ALanguageExpr languageExpr)
	throws TamagoCCException {
		throw new TamagoCCException("Unsupported instruction in the interpreter: "+languageExpr.getExpression());
	}

	public Object visitLongComment(ALongComment longcomment)
	throws TamagoCCException {
		throw new TamagoCCException("Not yet implemented longcomment");
	}

	public Object visitMemberVariable(AMemberVariable varmem)
	throws TamagoCCException {
		// chercher la valeur du membre
		throw new TamagoCCException("Not yet implemented member variable");
	}

	public Object visitMethod(AMethod method) throws TamagoCCException {
		// inutile
		throw new TamagoCCException("Not yet implemented method");
	}

	public Object visitModule(AModule module) throws TamagoCCException {
		// inutile
		throw new TamagoCCException("Not yet implemented module");
	}

	public Object visitNamespace(ANamespace namespace) throws TamagoCCException {
		// inutile
		throw new TamagoCCException("Not yet implemented namespace");
	}

	public Object visitNew(ANew n) throws TamagoCCException {
		// renvoie un objet ASTobject
		try {
			Class<?> c = Class.forName(n.getType().getType());
			Object[] obj = new Object[n.size()];
			Class<?>[] cobj = new Class[n.size()];
			Iterator<AExpression> expr = n.getArguments();
			int i = 0;
			while(expr.hasNext()) {
				ASTObject ast =(ASTObject) expr.next().visit(this); 
				obj[i] = ast.objectValue();
				cobj[i] = obj[i].getClass();
				i++;
			}
			Constructor<?> cons = c.getConstructor(cobj);
			Object o = cons.newInstance(obj);
			return new ASTObject(o);
		} catch (Exception e) {
			throw new TamagoCCException(e);
		}
	}

	public Object visitNewArray(ANewArray newarray) throws TamagoCCException {
		// renvoie un objet ASTarray
		
		
		throw new TamagoCCException("Not yet implemented newarray");
	}

	public Object visitNil(ANil nil) throws TamagoCCException {
		// renvoie l'objet ASTnil
		return ASTnil.nil();
	}

	public Object visitNoExpression(ANoExpression noexpr)
	throws TamagoCCException {
		// inutile
		return ASTvoid.single();
	}

	public Object visitNoInstruction(ANoInstruction noinst)
	throws TamagoCCException {
		// inutile
		return ASTvoid.single();
	}

	public Object visitNot(ANot not) throws TamagoCCException {
		// renvoie le bool not de la subexpre
		return new ASTboolean(! ((ASTboolean)not.getSubExpression().visit(this)).boolValue());
	}

	public Object visitOperator(AOperator operator) throws TamagoCCException {
		switch(operator.getOperator().getID()) {
		case TOpeName.AND: {
			ASTboolean cur = new ASTboolean(true);
			Iterator<AExpression> exprs = operator.getOperands();
			while(exprs.hasNext()) {
				AExpression expr = exprs.next();
				ASTValue val = (ASTValue) expr.visit(this);
				cur = new ASTboolean(cur.boolValue() && val.boolValue());
			}
			return cur;
		}
		case TOpeName.OR:
		{
			ASTboolean cur = new ASTboolean(false);
			Iterator<AExpression> exprs = operator.getOperands();
			while(exprs.hasNext()) {
				AExpression expr = exprs.next();
				ASTValue val = (ASTValue) expr.visit(this);
				cur = new ASTboolean(cur.boolValue() || val.boolValue());
			}
			return cur;
		}
		case TOpeName.XOR:
		{
			ASTValue arg0 = (ASTValue) operator.getOperand(0).visit(this);
			ASTValue arg1 = (ASTValue) operator.getOperand(1).visit(this);
			ASTboolean cur = new ASTboolean(arg0.boolValue() ^ arg1.boolValue());
			return cur;
		}
		case TOpeName.INF:
		case TOpeName.INFEG:
		case TOpeName.SUP:
		case TOpeName.SUPEG:
		case TOpeName.EG:
		case TOpeName.NE:
		{
			ASTValue arg0 = (ASTValue) operator.getOperand(0).visit(this);
			ASTValue arg1 = (ASTValue) operator.getOperand(1).visit(this);
			ASTboolean cur = new ASTboolean(operRel(operator.getOperator(), arg0,arg1));
			return cur;
		}
		case TOpeName.PLUS:
		case TOpeName.TIMES:
		case TOpeName.MINUS:
		case TOpeName.QUO:
		case TOpeName.MOD:
		{
			ASTValue cur = new ASTinteger(0);
			boolean first = true;
			Iterator<AExpression> exprs = operator.getOperands();
			while(exprs.hasNext()) {
				AExpression expr = exprs.next();
				ASTValue val = (ASTValue) expr.visit(this);
				cur = operArith(first,operator.getOperator(), cur,val);
				first = false;
			}
			return cur;
		}
		default:
			throw new ASTInterpreterException("Interpreter unknows the operator: "+ operator.getOperator());
		}
	}

	private ASTValue operArith(boolean first, TOpeName op, ASTValue cur, ASTValue val) {
		if(first)
			return val;
		switch(op.getID()) {
		case TOpeName.PLUS:
			return somme(cur,val);
		case TOpeName.TIMES:
			return multiplication(cur, val);
		case TOpeName.MINUS:
			return moins(cur, val);
		case TOpeName.QUO:
			return division(cur, val);
		case TOpeName.MOD:
			return modulo(cur, val);
		}
		throw new ASTInterpreterException("Unsupported arith operator");
	}
	
	private ASTValue modulo(ASTValue cur, ASTValue val) {
		if(cur instanceof ASTreal || val instanceof ASTreal) {
			return new ASTreal(cur.realValue() % val.realValue());
		}
		else
			return new ASTinteger(cur.intValue() % val.intValue());
	}
	
	private ASTValue division(ASTValue cur, ASTValue val) {
		if(cur instanceof ASTreal || val instanceof ASTreal) {
			return new ASTreal(cur.realValue() / val.realValue());
		}
		else
			return new ASTinteger(cur.intValue() / val.intValue());
	}
	
	private ASTValue moins(ASTValue cur, ASTValue val) {
		if(cur instanceof ASTreal || val instanceof ASTreal) {
			return new ASTreal(cur.realValue() - val.realValue());
		}
		else
			return new ASTinteger(cur.intValue() - val.intValue());
	}

	private ASTValue multiplication(ASTValue cur, ASTValue val) {
		if(cur instanceof ASTreal || val instanceof ASTreal) {
			return new ASTreal(cur.realValue() * val.realValue());
		}
		else
			return new ASTinteger(cur.intValue() * val.intValue());
	}
	
	private ASTValue somme(ASTValue cur, ASTValue val) {
		if(cur instanceof ASTreal || val instanceof ASTreal) {
			return new ASTreal(cur.realValue() + val.realValue());
		}
		else
			return new ASTinteger(cur.intValue() + val.intValue());
	}

	private boolean operRel(TOpeName operator, ASTValue arg0, ASTValue arg1) {
		switch(operator.getID()) {
		case TOpeName.INF:
			if(arg0 instanceof ASTreal || arg1 instanceof ASTreal)
				return arg0.realValue() < arg1.realValue();
			else
				return arg0.intValue() < arg1.intValue();
		case TOpeName.INFEG:
			if(arg0 instanceof ASTreal || arg1 instanceof ASTreal)
				return arg0.realValue() <= arg1.realValue();
			else
				return arg0.intValue() <= arg1.intValue();
		case TOpeName.SUP:
			if(arg0 instanceof ASTreal || arg1 instanceof ASTreal)
				return arg0.realValue() > arg1.realValue();
			else
				return arg0.intValue() > arg1.intValue();
		case TOpeName.SUPEG:
			if(arg0 instanceof ASTreal || arg1 instanceof ASTreal)
				return arg0.realValue() >= arg1.realValue();
			else
				return arg0.intValue() >= arg1.intValue();
		case TOpeName.EG:
			if(arg0 instanceof ASTreal || arg1 instanceof ASTreal)
				return arg0.realValue()== arg1.realValue();
			else
				return arg0.intValue() == arg1.intValue();
		case TOpeName.NE:
			if(arg0 instanceof ASTreal || arg1 instanceof ASTreal)
				return arg0.realValue() != arg1.realValue();
			else
				return arg0.intValue() != arg1.intValue();
		}
		throw new ASTInterpreterException("Unsupported rel operator");
	}

	public Object visitParameter(AParameter parameter) throws TamagoCCException {
		// inutile a faire dans le 
		ctx.getMemory().initialize(parameter.getIdent().getName(), new ASTnil());
		return ASTvoid.single();
	}

	public Object visitReal(AReal real) throws TamagoCCException {
		return new ASTreal(real.getValue());
	}

	public Object visitReturn(AReturn r) throws TamagoCCException {
		// valeur de retour d'une fonction
		throw new ASTInterpreterException("Not yet supported return");
	}

	public Object visitSelf(ASelf self) throws TamagoCCException {
		// inutile
		return ctx.scope();
	}

	public Object visitSequence(ASequence sequence) throws TamagoCCException {
		// faire la sequence et c tout
		Iterator<AInstruction> ite = sequence.getInstructions();
		while(ite.hasNext()) {
			ite.next().visit(this);
		}
		return ASTvoid.single();
	}

	public Object visitSet(ASet set) throws TamagoCCException {
		// TODO Auto-generated method stub
		throw new ASTInterpreterException("Not yet supported set");
	}

	public Object visitString(AString string) throws TamagoCCException {
		return new ASTstring(string.getValue());
	}

	public Object visitTamagoNew(ATamagoNew tamagonew) throws TamagoCCException {
		throw new ASTInterpreterException("Not yet supported new tamagoNEW");
		/*Object compo = null;

		ASTObject obj = new ASTObject(compo); 
		return obj;*/
	}

	public Object visitThrowException(AThrowException n)
	throws TamagoCCException {
		try {
			Class<?> c = Class.forName(n.getType().getType());
			Object[] obj = new Object[n.size()];
			Class<?>[] cobj = new Class[n.size()];
			Iterator<AExpression> expr = n.getArguments();
			int i = 0;
			while(expr.hasNext()) {
				ASTObject ast =(ASTObject) expr.next().visit(this); 
				obj[i] = ast.objectValue();
				cobj[i] = obj[i].getClass();
				i++;
			}
			Constructor<?> cons = c.getConstructor(cobj);
			Object o = cons.newInstance(obj);
			throw (Exception) o;
		} catch (Exception e) {
			throw new TamagoCCException(e);
		}
	}

	public Object visitThrowsException(AThrowsException throwsexception)
	throws TamagoCCException {
		// inutile
		throw new ASTInterpreterException("Not yet supported throwsexception");
	}

	public Object visitTryCatchFinal(ATryCatchFinal trycatchfinal)
	throws TamagoCCException {
		try {
			trycatchfinal.getTryBody().visit(this);
		}
		catch(Exception e) {
			//Class.forName("Object").isInstance(e);
			for (ACatchBlock c : trycatchfinal.getCatchBlock()) {
				if(c.getExceptionType().getType().equals(e.getClass().getName())) {
					c.getBody().visit(this);
				}
			}
		}
		finally {
			trycatchfinal.getFinalBody().visit(this);
		}
		return ASTvoid.single();
	}

	public Object visitType(AType type) throws TamagoCCException {
		// inutile
		throw new ASTInterpreterException("Not yet supported type");
	}

	public Object visitVariable(AVariable variable) throws TamagoCCException {
		// renvoie ASTvalue de la variable
		try {
			return ctx.getMemory().values(variable);
		} catch (ASTInterpreterException e) {
			throw new TamagoCCException(e);
		}
	}

	public Object visitVisibility(AVisibility visibility)
	throws TamagoCCException {
		// inutile
		throw new ASTInterpreterException("Not yet supported visibility");
	}

	public Object visitWhile(AWhile w) throws TamagoCCException {
		while(((ASTboolean)w.getCondition().visit(this)).boolValue()) {
			w.getBody().visit(this);
		}
		return ASTvoid.single();
	}

	public Object visitProperty(AIProperty property) throws TamagoCCException {
		throw new ASTInterpreterException("Not yet supported property");
	}

	public Object visitRead(AIRead read) throws TamagoCCException {
		ASTValue val = ctx.getMemory().values(read.getName());
		return val;
	}

	public static ASTValue evalStatic(AExpression value) throws TamagoCCException {
		Interpreter inter = new Interpreter();
		return (ASTValue) value.visit(inter);
	}

	public ASTValue eval(AInstruction val) throws TamagoCCException {
		return (ASTValue) val.visit(this);
	}
	
	public ASTValue eval(AExpression val) throws TamagoCCException {
		return (ASTValue) val.visit(this);
	}

	public void put(String string, ASTObject astObject) {
		ctx.getMemory().initialize(string, astObject);
	}

	@Override
	public Object visitInState(AInState instate) throws TamagoCCException {
		throw new TamagoCCException("(InState) Not yet implemented instrument foreach");
	}
}
