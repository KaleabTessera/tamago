package tamagocc.ast;

import tamagocc.ast.api.AAffectation;
import tamagocc.ast.api.ABool;
import tamagocc.ast.api.ACall;
import tamagocc.ast.api.ACatchBlock;
import tamagocc.ast.api.ACodeComment;
import tamagocc.ast.api.AConvertType;
import tamagocc.ast.api.ADocComment;
import tamagocc.ast.api.AEntity;
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

/**
 * This interface is the interface for all Visitor for the third AST (similar to byte-code).
 * 
 * @author Hakim Belhaouari
 *
 */
public interface TamagoCCASTVisitor {
	/**
	 * This function is called for code comment
	 * @param codecomment
	 * @return depends of the implementation
	 * @throws TamagoCCException
	 */
	Object visitCodeComment(ACodeComment codecomment) throws TamagoCCException;
	
	/**
	 * This function is called for documentation comment
	 * @param doccomment
	 * @return depends of the implementation
	 * @throws TamagoCCException
	 */
	Object visitDocComment(ADocComment doccomment) throws TamagoCCException;
	
	/**
	 * This function is called by all unknown entity. (This method is usually not used)
	 * @param entity
	 * @return depends of the implementation
	 * @throws TamagoCCException
	 */
	Object visitEntity(AEntity entity) throws TamagoCCException;
	
	/**
	 * This function is called by an identifiant.
	 * @param ident
	 * @return depends of the implementation
	 * @throws TamagoCCException
	 */
	Object visitIdent(AIdent ident) throws TamagoCCException;
	
	/**
	 * This function is called by an inline comment
	 * @param inlinecomment
	 * @return depends of the implementation
	 * @throws TamagoCCException
	 */
	Object visitInlineComment(AInlineComment inlinecomment) throws TamagoCCException;
	
	/**
	 * 
	 * @param longcomment
	 * @return Depends of the implementation
	 * @throws TamagoCCException
	 */
	Object visitLongComment(ALongComment longcomment) throws TamagoCCException;
	
	/**
	 * 
	 * @param varmem
	 * @return depends of the implementation
	 * @throws TamagoCCException
	 */
	Object visitMemberVariable(AMemberVariable varmem) throws TamagoCCException;
	
	/**
	 * this function is called by a method.
	 * @param method
	 * @return depends of the implementation
	 * @throws TamagoCCException
	 */
	Object visitMethod(AMethod method) throws TamagoCCException;
	
	/**
	 * this function is called by a module
	 * @param module
	 * @return depends of the implementation
	 * @throws TamagoCCException
	 */
	Object visitModule(AModule module) throws TamagoCCException;
	
	/**
	 * this function is called by type 
	 * @param type
	 * @return depends of the implementation
	 * @throws TamagoCCException
	 */
	Object visitType(AType type) throws TamagoCCException;
	
	/**
	 * this function is called by the visibility.
	 * @param visibility
	 * @return depends of the implementation
	 * @throws TamagoCCException
	 */
	Object visitVisibility(AVisibility visibility) throws TamagoCCException;
	
	/**
	 * this function is called by an affectation
	 * @param affectation
	 * @return depends of the implementation
	 * @throws TamagoCCException
	 */
	Object visitAffectation(AAffectation affectation) throws TamagoCCException;
	
	/**
	 * this function is called by a boolean
	 * @param bool
	 * @return depends of the implementation
	 * @throws TamagoCCException
	 */
	Object visitBool(ABool bool)throws TamagoCCException;
	
	/**
	 * this function is called by a call 
	 * @param call
	 * @return depends of the implementation
	 * @throws TamagoCCException
	 */
	Object visitCall(ACall call) throws TamagoCCException;
	
	/**
	 * this function is called by a ifthenelse structure
	 * @param ifthenelse
	 * @return depends of the implementation
	 * @throws TamagoCCException
	 */
	Object visitIfThenElse(AIfThenElse ifthenelse) throws TamagoCCException;
	
	
	Object visitInLabel(AInLabel inlabel) throws TamagoCCException;
	Object visitInstExpression(AInstExpression instexpr) throws TamagoCCException;
	Object visitInteger(AInteger integer) throws TamagoCCException;
	Object visitNoExpression(ANoExpression noexpr) throws TamagoCCException;
	Object visitNot(ANot not) throws TamagoCCException;
	Object visitOperator(AOperator operator) throws TamagoCCException;
	Object visitReal(AReal real) throws TamagoCCException;
	Object visitSequence(ASequence sequence) throws TamagoCCException;
	Object visitString(AString string) throws TamagoCCException;
	Object visitVariable(AVariable variable) throws TamagoCCException;
	Object visitWhile(AWhile w) throws TamagoCCException;
	Object visitNoInstruction(ANoInstruction noinst) throws TamagoCCException;
	Object visitParameter(AParameter parameter) throws TamagoCCException;
	Object visitTryCatchFinal(ATryCatchFinal trycatchfinal) throws TamagoCCException;
	Object visitThrowException(AThrowException throwexception) throws TamagoCCException;
	Object visitCatchBlock(ACatchBlock catchblock) throws TamagoCCException;
	
	Object visitInherit(AInherit inherit) throws TamagoCCException;
	Object visitImplement(AImplements impl) throws TamagoCCException;
	Object visitThrowsException(AThrowsException throwsexception) throws TamagoCCException;
	
	Object visitNamespace(ANamespace namespace) throws TamagoCCException;
	Object visitReturn(AReturn r) throws TamagoCCException;
	Object visitConstructor(AConstructor constructor) throws TamagoCCException;
	Object visitNew(ANew newobjet) throws TamagoCCException;
	Object visitTamagoNew(ATamagoNew tamagonew) throws TamagoCCException;
	Object visitNewArray(ANewArray newarray) throws TamagoCCException;
	
	Object visitSelf(ASelf self) throws TamagoCCException;
	Object visitInitialisation(AInitialisation init) throws TamagoCCException;
	Object visitConvertType(AConvertType ct) throws TamagoCCException;
	
	Object visitNil(ANil nil) throws TamagoCCException;
	
	
	Object visitFor(AFor f) throws TamagoCCException;

	/**
	 * @deprecated use set.getInitialize()
	 * @param set
	 * @return
	 * @throws TamagoCCException
	 * @see {@link ASet}
	 */
	Object visitSet(ASet set) throws TamagoCCException;

	Object visitForeach(AForeach f) throws TamagoCCException;

	Object visitLanguageExpr(ALanguageExpr languageExpr) throws TamagoCCException;

	Object visitProperty(AIProperty property) throws TamagoCCException;

	Object visitRead(AIRead read) throws TamagoCCException;

	Object visitInState(AInState instate) throws TamagoCCException;
}


