/**
 * 
 */
package tamagotest.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
import tamagocc.ast.api.AIsBound;
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
import tamagocc.ast.impl.AIAffectation;
import tamagocc.ast.impl.AICall;
import tamagocc.ast.impl.AICatchBlock;
import tamagocc.ast.impl.AIFor;
import tamagocc.ast.impl.AIForeach;
import tamagocc.ast.impl.AIIfThenElse;
import tamagocc.ast.impl.AIInLabel;
import tamagocc.ast.impl.AIInitialisation;
import tamagocc.ast.impl.AIInstExpression;
import tamagocc.ast.impl.AINew;
import tamagocc.ast.impl.AINewArray;
import tamagocc.ast.impl.AINot;
import tamagocc.ast.impl.AIOperator;
import tamagocc.ast.impl.AIProperty;
import tamagocc.ast.impl.AIRead;
import tamagocc.ast.impl.AISequence;
import tamagocc.ast.impl.AITryCatchFinal;
import tamagocc.ast.impl.AIVariable;
import tamagocc.ast.impl.AIWhile;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GTamago;

/**
 * @author Hakim Belhaouari
 *
 */
public class AConverterScope implements TamagoCCASTVisitor {

	private AIVariable newscope;
	@SuppressWarnings("unused")
	private GTamago contract;
	
	
	public AConverterScope(AIVariable variable, GTamago contract) {
		this.newscope = variable;
		this.contract = contract;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitAffectation(tamagocc.ast.api.AAffectation)
	 */
	public Object visitAffectation(AAffectation affectation)
			throws TamagoCCException {
		AIAffectation affect = new AIAffectation(affectation.getVariable(),(AExpression)affectation.getInitValeur().visit(this));
		return affect;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitBool(tamagocc.ast.api.ABool)
	 */
	public Object visitBool(ABool bool) throws TamagoCCException {
		return bool;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitCall(tamagocc.ast.api.ACall)
	 */
	public Object visitCall(ACall call) throws TamagoCCException {
		Collection<AExpression> list = new ArrayList<AExpression>();
		Iterator<AExpression> expr = call.getArguments();
		while(expr.hasNext()) {
			list.add((AExpression) expr.next().visit(this));
		}
		return new AICall(call.getIdent(),list);
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitCatchBlock(tamagocc.ast.api.ACatchBlock)
	 */
	public Object visitCatchBlock(ACatchBlock catchblock)
			throws TamagoCCException {
		AInstruction body = (AInstruction)catchblock.getBody().visit(this);
		return new AICatchBlock(catchblock.getExceptionType(),catchblock.getIdent(),body);
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitCodeComment(tamagocc.ast.api.ACodeComment)
	 */
	public Object visitCodeComment(ACodeComment codecomment)
			throws TamagoCCException {
		return codecomment;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitConstructor(tamagocc.ast.impl.AConstructor)
	 */
	public Object visitConstructor(AConstructor constructor)
			throws TamagoCCException {
		AInstruction body = (AInstruction)constructor.getBody().visit(this);
		AConstructor c = new AConstructor(constructor.getIdent(),constructor.getVisibility());
		c.setBody(body);
		return c;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitConvertType(tamagocc.ast.api.AConvertType)
	 */
	public Object visitConvertType(AConvertType ct) throws TamagoCCException {
		return ct;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitDocComment(tamagocc.ast.api.ADocComment)
	 */
	public Object visitDocComment(ADocComment doccomment)
			throws TamagoCCException {
		return doccomment;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitEntity(tamagocc.ast.api.AEntity)
	 */
	public Object visitEntity(AEntity entity) throws TamagoCCException {
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitFor(tamagocc.ast.api.AFor)
	 */
	public Object visitFor(AFor f) throws TamagoCCException {
		AInstruction init = (AInstruction) f.getAffectation().visit(this);
		AExpression expr =(AExpression) f.getCondition().visit(this);
		AInstruction incr = (AInstruction) f.getIncrement().visit(this);
		AInstruction body = (AInstruction) f.getBody().visit(this);
		
		AIFor fd = new AIFor(init,expr,incr,body);
		return fd;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitForeach(tamagocc.ast.api.AForeach)
	 */
	public Object visitForeach(AForeach f) throws TamagoCCException {
		AExpression coll =  (AExpression)f.getCollection().visit(this);
		AInstruction body = (AInstruction) f.getBody().visit(this);
		return new AIForeach(f.getVariable(),f.getVariableType(),coll,body);
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitIdent(tamagocc.ast.api.AIdent)
	 */
	public Object visitIdent(AIdent ident) throws TamagoCCException {
		return ident;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitIfThenElse(tamagocc.ast.api.AIfThenElse)
	 */
	public Object visitIfThenElse(AIfThenElse ifthenelse)
			throws TamagoCCException {
		AExpression cond = (AExpression) ifthenelse.getCondition().visit(this);
		AInstruction cons = (AInstruction) ifthenelse.getCons().visit(this);
		AInstruction alt = (AInstruction) ifthenelse.getAlt().visit(this);
		return new AIIfThenElse(cond,cons,alt);
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitImplement(tamagocc.ast.api.AImplements)
	 */
	public Object visitImplement(AImplements impl) throws TamagoCCException {
		return impl;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitInLabel(tamagocc.ast.api.AInLabel)
	 */
	public Object visitInLabel(AInLabel inlabel) throws TamagoCCException {
		AExpression scope = (AExpression)inlabel.getScope().visit(this);
		AExpression target = (AExpression)inlabel.getSubExpression().visit(this);
		return new AIInLabel(scope,target);
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitInherit(tamagocc.ast.api.AInherit)
	 */
	public Object visitInherit(AInherit inherit) throws TamagoCCException {
		return inherit;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitInitialisation(tamagocc.ast.api.AInitialisation)
	 */
	public Object visitInitialisation(AInitialisation init)
			throws TamagoCCException {
		AExpression val = (AExpression)init.getInitial().visit(this);
		return new AIInitialisation(init.getIdent(),init.getType(),val);
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitInlineComment(tamagocc.ast.api.AInlineComment)
	 */
	public Object visitInlineComment(AInlineComment inlinecomment)
			throws TamagoCCException {
		return inlinecomment;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitInstExpression(tamagocc.ast.api.AInstExpression)
	 */
	public Object visitInstExpression(AInstExpression instexpr)
			throws TamagoCCException {
		AExpression expr = (AExpression)instexpr.visit(this);
		return new AIInstExpression(expr);
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitInteger(tamagocc.ast.api.AInteger)
	 */
	public Object visitInteger(AInteger integer) throws TamagoCCException {
		return integer;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitLanguageExpr(tamagocc.ast.api.ALanguageExpr)
	 */
	public Object visitLanguageExpr(ALanguageExpr languageExpr)
			throws TamagoCCException {
		return languageExpr;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitLongComment(tamagocc.ast.api.ALongComment)
	 */
	public Object visitLongComment(ALongComment longcomment)
			throws TamagoCCException {
		return longcomment;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitMemberVariable(tamagocc.ast.api.AMemberVariable)
	 */
	public Object visitMemberVariable(AMemberVariable varmem)
			throws TamagoCCException {
		return varmem;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitMethod(tamagocc.ast.api.AMethod)
	 */
	public Object visitMethod(AMethod method) throws TamagoCCException {
		return method;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitModule(tamagocc.ast.api.AModule)
	 */
	public Object visitModule(AModule module) throws TamagoCCException {
		return module;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitNamespace(tamagocc.ast.api.ANamespace)
	 */
	public Object visitNamespace(ANamespace namespace) throws TamagoCCException {
		return namespace;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitNew(tamagocc.ast.api.ANew)
	 */
	public Object visitNew(ANew newobjet) throws TamagoCCException {
		Iterator<AExpression> args = newobjet.getArguments();
		AINew inew = new AINew(newobjet.getType());
		while(args.hasNext()) {
			inew.addArguments((AExpression)args.next().visit(this));
		}
		return inew;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitNewArray(tamagocc.ast.api.ANewArray)
	 */
	public Object visitNewArray(ANewArray newarray) throws TamagoCCException {
		return new AINewArray(newarray.getType(),newarray.getTaille());
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitNil(tamagocc.ast.api.ANil)
	 */
	public Object visitNil(ANil nil) throws TamagoCCException {
		return nil;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitNoExpression(tamagocc.ast.api.ANoExpression)
	 */
	public Object visitNoExpression(ANoExpression noexpr)
			throws TamagoCCException {
		return noexpr;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitNoInstruction(tamagocc.ast.api.ANoInstruction)
	 */
	public Object visitNoInstruction(ANoInstruction noinst)
			throws TamagoCCException {
		return noinst;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitNot(tamagocc.ast.api.ANot)
	 */
	public Object visitNot(ANot not) throws TamagoCCException {
		return new AINot((AExpression)not.getSubExpression().visit(this));
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitOperator(tamagocc.ast.api.AOperator)
	 */
	public Object visitOperator(AOperator operator) throws TamagoCCException {
		Iterator<AExpression> exprs =  operator.getOperands();
		ArrayList<AExpression> list = new ArrayList<AExpression>();
		while(exprs.hasNext()) {
			list.add((AExpression)exprs.next().visit(this));
		}
		return new AIOperator(operator.getOperator(),list);
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitParameter(tamagocc.ast.api.AParameter)
	 */
	public Object visitParameter(AParameter parameter) throws TamagoCCException {
		return parameter;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitProperty(tamagocc.ast.impl.AIProperty)
	 */
	public Object visitProperty(AIProperty property) throws TamagoCCException {
		return property;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitRead(tamagocc.ast.impl.AIRead)
	 */
	public Object visitRead(AIRead read) throws TamagoCCException {
		if(read.hasIndex()) {
			AExpression idx = (AExpression)read.getIndex().visit(this);
			return new AIInLabel(newscope, new AIRead(read.getName(),idx));
		}
		
		return new AIInLabel(newscope,read);
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitReal(tamagocc.ast.api.AReal)
	 */
	public Object visitReal(AReal real) throws TamagoCCException {
		return real;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitReturn(tamagocc.ast.api.AReturn)
	 */
	public Object visitReturn(AReturn r) throws TamagoCCException {
		return r;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitSelf(tamagocc.ast.api.ASelf)
	 */
	public Object visitSelf(ASelf self) throws TamagoCCException {
		return self;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitSequence(tamagocc.ast.api.ASequence)
	 */
	public Object visitSequence(ASequence sequence) throws TamagoCCException {
		AISequence seq = new AISequence();
		Iterator<AInstruction> inst = sequence.getInstructions();
		while(inst.hasNext()) {
			seq.addInstruction((AInstruction) inst.next().visit(this));
		}
		return seq;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitSet(tamagocc.ast.api.ASet)
	 */
	public Object visitSet(ASet set) throws TamagoCCException {
		return set;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitString(tamagocc.ast.api.AString)
	 */
	public Object visitString(AString string) throws TamagoCCException {
		return string;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitTamagoNew(tamagocc.ast.api.ATamagoNew)
	 */
	public Object visitTamagoNew(ATamagoNew tamagonew) throws TamagoCCException {
		return tamagonew;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitThrowException(tamagocc.ast.api.AThrowException)
	 */
	public Object visitThrowException(AThrowException throwexception)
			throws TamagoCCException {
		return throwexception;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitThrowsException(tamagocc.ast.api.AThrowsException)
	 */
	public Object visitThrowsException(AThrowsException throwsexception)
			throws TamagoCCException {
		return throwsexception;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitTryCatchFinal(tamagocc.ast.api.ATryCatchFinal)
	 */
	public Object visitTryCatchFinal(ATryCatchFinal trycatchfinal)
			throws TamagoCCException {
		AInstruction body = (AInstruction)trycatchfinal.getTryBody().visit(this);
		AInstruction fin = (AInstruction) trycatchfinal.getFinalBody().visit(this);
		ArrayList<ACatchBlock> list = new ArrayList<ACatchBlock>();
		
		for(ACatchBlock c : trycatchfinal.getCatchBlock()) {
			list.add((ACatchBlock) c.visit(this));
		}
		AITryCatchFinal res = new AITryCatchFinal(body,list,fin);
		
		return res;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitType(tamagocc.ast.api.AType)
	 */
	public Object visitType(AType type) throws TamagoCCException {
		return type;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitVariable(tamagocc.ast.api.AVariable)
	 */
	public Object visitVariable(AVariable variable) throws TamagoCCException {
		if(variable.hasIndex())
			return new AIVariable(variable.getIdent(),(AExpression) variable.getIndex().visit(this));
		else
			return variable;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitVisibility(tamagocc.ast.api.AVisibility)
	 */
	public Object visitVisibility(AVisibility visibility)
			throws TamagoCCException {
		return visibility;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitWhile(tamagocc.ast.api.AWhile)
	 */
	public Object visitWhile(AWhile w) throws TamagoCCException {
		AInstruction body = (AInstruction)w.getBody().visit(this);
		AExpression cond = (AExpression) w.getCondition().visit(this);
		return new AIWhile(cond,body);
	}

	@Override
	public Object visitInState(AInState instate) throws TamagoCCException {
		return instate;
	}

	@Override
	public Object visitIsBound(AIsBound aiIsBound) throws TamagoCCException {
		return aiIsBound;
	}

}
