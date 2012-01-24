/**
 * 
 */
package tamagotest.report;

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
import tamagocc.ast.impl.AIProperty;
import tamagocc.ast.impl.AIRead;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCIndentator;

/**
 * @author Hakim Belhaouari
 *
 */
public class ASTtoXml implements TamagoCCASTVisitor {

	private TamagoCCIndentator indent;
	
	/**
	 * 
	 */
	public ASTtoXml(TamagoCCIndentator indent) {
		this.indent = indent;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitAffectation(tamagocc.ast.api.AAffectation)
	 */
	public Object visitAffectation(AAffectation affectation)
			throws TamagoCCException {
		indent.println("<affect>");
		indent.shiftright();
		indent.println("<variable>");
		indent.shiftright();
		affectation.getVariable().visit(this);
		indent.shiftleft();
		indent.println("</variable>");
		
		indent.println("<value>");
		indent.shiftright();
		affectation.getInitValeur().visit(this);
		indent.shiftleft();
		indent.println("</value>");
		
		indent.shiftleft();
		indent.println("</affect>");
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitBool(tamagocc.ast.api.ABool)
	 */
	public Object visitBool(ABool bool) throws TamagoCCException {
		indent.print("<bool value=\"");
		indent.print(""+bool.getValue());
		indent.println("\" />");
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitCall(tamagocc.ast.api.ACall)
	 */
	public Object visitCall(ACall call) throws TamagoCCException {
		indent.print("<call name=\"");
		indent.print(call.getIdent().getName());
		indent.println("\" >");
		indent.shiftright();
		indent.println("<arguments>");
		indent.shiftright();
		Iterator<AExpression> exprs = call.getArguments();
		while(exprs.hasNext()) {
			exprs.next().visit(this);
		}
		indent.shiftleft();
		indent.println("</arguments>");
		indent.shiftleft();
		indent.println("</call>");
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitCatchBlock(tamagocc.ast.api.ACatchBlock)
	 */
	public Object visitCatchBlock(ACatchBlock catchblock)
			throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitCodeComment(tamagocc.ast.api.ACodeComment)
	 */
	public Object visitCodeComment(ACodeComment codecomment)
			throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitConstructor(tamagocc.ast.impl.AConstructor)
	 */
	public Object visitConstructor(AConstructor constructor)
			throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitConvertType(tamagocc.ast.api.AConvertType)
	 */
	public Object visitConvertType(AConvertType ct) throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitDocComment(tamagocc.ast.api.ADocComment)
	 */
	public Object visitDocComment(ADocComment doccomment)
			throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitEntity(tamagocc.ast.api.AEntity)
	 */
	public Object visitEntity(AEntity entity) throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitFor(tamagocc.ast.api.AFor)
	 */
	public Object visitFor(AFor f) throws TamagoCCException {
		indent.println("<for>");
		indent.println("<initialisation>");
		f.getAffectation().visit(this);
		indent.println("</initialisation>");
		indent.println("<condition>");
		f.getCondition().visit(this);
		indent.println("</condition>");
		indent.println("<increment>");
		f.getIncrement().visit(this);
		indent.println("</increment>");
		indent.println("<body>");
		f.getBody().visit(this);
		indent.println("</body>");
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitForeach(tamagocc.ast.api.AForeach)
	 */
	public Object visitForeach(AForeach f) throws TamagoCCException {
		indent.print("<foreach variable=\"");
		indent.print(f.getVariable().getIdent().getName());
		indent.print("\" type=\"");
		indent.print(f.getVariableType().getType());
		indent.println("\" >");
		indent.shiftright();
		indent.println("<collection>");
		indent.shiftright();
		f.getCollection().visit(this);
		indent.shiftleft();
		indent.println("</collection>");
		indent.println("<body>");
		indent.shiftright();
		f.getBody().visit(this);
		indent.shiftleft();
		indent.println("</body>");
		indent.shiftleft();
		indent.println("</foreach>");
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitIdent(tamagocc.ast.api.AIdent)
	 */
	public Object visitIdent(AIdent ident) throws TamagoCCException {
		indent.print(ident.getName());
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitIfThenElse(tamagocc.ast.api.AIfThenElse)
	 */
	public Object visitIfThenElse(AIfThenElse ifthenelse)
			throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitImplement(tamagocc.ast.api.AImplements)
	 */
	public Object visitImplement(AImplements impl) throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitInLabel(tamagocc.ast.api.AInLabel)
	 */
	public Object visitInLabel(AInLabel inlabel) throws TamagoCCException {
		indent.println("<in>");
		indent.shiftright();
		indent.println("<scope>");
		indent.shiftright();
		inlabel.getScope().visit(this);
		indent.shiftleft();
		indent.println("</scope>");
		indent.println("<target>");
		indent.shiftright();
		inlabel.getSubExpression().visit(this);
		indent.shiftleft();
		indent.println("</target>");
		indent.shiftleft();
		indent.println("</in>");
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitInherit(tamagocc.ast.api.AInherit)
	 */
	public Object visitInherit(AInherit inherit) throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitInitialisation(tamagocc.ast.api.AInitialisation)
	 */
	public Object visitInitialisation(AInitialisation init)
			throws TamagoCCException {
		
		indent.println("<initialisation>");
		indent.shiftright();
		indent.println("<variable type=\""+init.getType().visit(this)+"\">");
		indent.shiftright();
		init.getIdent().visit(this);
		indent.shiftleft();
		indent.println("</variable>");
		
		if(init.getInitial() != null && init.getInitial().getExpressionType() != AExpression.NOEXPRESSION) {
			indent.println("<value>");
			indent.shiftright();
			init.getInitial().visit(this);
			indent.shiftleft();
			indent.println("</value>");
		}

		indent.shiftleft();
		indent.println("</initialisation>");
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitInlineComment(tamagocc.ast.api.AInlineComment)
	 */
	public Object visitInlineComment(AInlineComment inlinecomment)
			throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitInstExpression(tamagocc.ast.api.AInstExpression)
	 */
	public Object visitInstExpression(AInstExpression instexpr)
			throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitInteger(tamagocc.ast.api.AInteger)
	 */
	public Object visitInteger(AInteger integer) throws TamagoCCException {
		indent.print("<integer value=\"");
		indent.print(""+integer.getValue());
		indent.println("\" />");
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitLongComment(tamagocc.ast.api.ALongComment)
	 */
	public Object visitLongComment(ALongComment longcomment)
			throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitMemberVariable(tamagocc.ast.api.AMemberVariable)
	 */
	public Object visitMemberVariable(AMemberVariable varmem)
			throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitMethod(tamagocc.ast.api.AMethod)
	 */
	public Object visitMethod(AMethod method) throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitModule(tamagocc.ast.api.AModule)
	 */
	public Object visitModule(AModule module) throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitNamespace(tamagocc.ast.api.ANamespace)
	 */
	public Object visitNamespace(ANamespace namespace) throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitNew(tamagocc.ast.api.ANew)
	 */
	public Object visitNew(ANew newobjet) throws TamagoCCException {
		indent.print("<new type=\"");
		indent.print(newobjet.getType().getType());
		if(newobjet.size() > 0) {
			indent.println("\"> ");
			indent.shiftright();
			Iterator<AExpression> args = newobjet.getArguments();
			while(args.hasNext()) {
				indent.println("<argument>");
				indent.shiftright();
				args.next().visit(this);
				indent.shiftleft();
				indent.println("</argument>");
			}
			indent.shiftleft();
			indent.println("</new>");
		}
		else 
			indent.println("\" />");
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitNewArray(tamagocc.ast.api.ANewArray)
	 */
	public Object visitNewArray(ANewArray newarray) throws TamagoCCException {
		indent.print("<array size=\"");
		indent.print(""+newarray.getTaille());
		indent.print("\" type=\"");
		indent.print(newarray.getType().getType());
		indent.println("\" />");
		//throw new TamagoCCException("ASTtoXML : Not yet supported");
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitNil(tamagocc.ast.api.ANil)
	 */
	public Object visitNil(ANil nil) throws TamagoCCException {
		indent.println("<nil />");
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitNoExpression(tamagocc.ast.api.ANoExpression)
	 */
	public Object visitNoExpression(ANoExpression noexpr)
			throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitNoInstruction(tamagocc.ast.api.ANoInstruction)
	 */
	public Object visitNoInstruction(ANoInstruction noinst)
			throws TamagoCCException {
		indent.println("<no-instruction/>");
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitNot(tamagocc.ast.api.ANot)
	 */
	public Object visitNot(ANot not) throws TamagoCCException {
		indent.println("<not>");
		indent.shiftright();
		not.getSubExpression().visit(this);
		indent.shiftleft();
		indent.println("</not>");
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitOperator(tamagocc.ast.api.AOperator)
	 */
	public Object visitOperator(AOperator operator) throws TamagoCCException {
		indent.print("<operator name=\"");
		indent.print(operator.getOperator().getOperator());
		indent.println("\" >");
		indent.shiftright();
		Iterator<AExpression> exprs = operator.getOperands();
		while(exprs.hasNext()) {
			AExpression expr = exprs.next();
			expr.visit(this);
			indent.newline();
		}
		indent.shiftleft();
		indent.println("</operator>");
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitParameter(tamagocc.ast.api.AParameter)
	 */
	public Object visitParameter(AParameter parameter) throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitReal(tamagocc.ast.api.AReal)
	 */
	public Object visitReal(AReal real) throws TamagoCCException {
		indent.print("<real value=\"");
		indent.print(""+real.getValue());
		indent.println("\" />");
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitReturn(tamagocc.ast.api.AReturn)
	 */
	public Object visitReturn(AReturn r) throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitSelf(tamagocc.ast.api.ASelf)
	 */
	public Object visitSelf(ASelf self) throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitSequence(tamagocc.ast.api.ASequence)
	 */
	public Object visitSequence(ASequence sequence) throws TamagoCCException {
		indent.println("<sequence>");
		indent.shiftright();
		Iterator<AInstruction> insts = sequence.getInstructions();
		while(insts.hasNext()) {
			insts.next().visit(this);
		}
		indent.shiftleft();
		indent.println("</sequence>");
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitSet(tamagocc.ast.api.ASet)
	 */
	public Object visitSet(ASet set) throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitString(tamagocc.ast.api.AString)
	 */
	public Object visitString(AString string) throws TamagoCCException {
		indent.print("<string>");
		indent.print(string.getValue());
		indent.println("</string>");
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitTamagoNew(tamagocc.ast.api.ATamagoNew)
	 */
	public Object visitTamagoNew(ATamagoNew tamagonew) throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitThrowException(tamagocc.ast.api.AThrowException)
	 */
	public Object visitThrowException(AThrowException throwexception)
			throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitThrowsException(tamagocc.ast.api.AThrowsException)
	 */
	public Object visitThrowsException(AThrowsException throwsexception)
			throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitTryCatchFinal(tamagocc.ast.api.ATryCatchFinal)
	 */
	public Object visitTryCatchFinal(ATryCatchFinal trycatchfinal)
			throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitType(tamagocc.ast.api.AType)
	 */
	public Object visitType(AType type) throws TamagoCCException {
		return type.getType();
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitVariable(tamagocc.ast.api.AVariable)
	 */
	public Object visitVariable(AVariable variable) throws TamagoCCException {
		indent.print("<variable name=\"");
		indent.print(variable.getIdent().getName());
		indent.print("\" ");
		if(variable.hasIndex()) {
			indent.println(">");
			indent.shiftright();
			indent.println("<index>");
			indent.shiftright();
			variable.getIndex().visit(this);
			indent.shiftleft();
			indent.println("</index>");
			indent.shiftleft();
			indent.println("</variable>");
		}
		else {
			indent.println("/>");
		}
		return null;
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitVisibility(tamagocc.ast.api.AVisibility)
	 */
	public Object visitVisibility(AVisibility visibility)
			throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	/**
	 * @see tamagocc.ast.TamagoCCASTVisitor#visitWhile(tamagocc.ast.api.AWhile)
	 */
	public Object visitWhile(AWhile w) throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	public Object visitLanguageExpr(ALanguageExpr languageExpr)
			throws TamagoCCException {
		indent.print("<lang-expr>");
		indent.print(languageExpr.getExpression());
		indent.println("</lang-expr>");
		return null;
	}

	public Object visitProperty(AIProperty property) throws TamagoCCException {
		throw new TamagoCCException("ASTtoXML : Not yet supported");
	}

	public Object visitRead(AIRead read) throws TamagoCCException {
		indent.print("<read property=\"");
		indent.print(read.getName());
		indent.println("\" />");
		return null;
	}

	@Override
	public Object visitInState(AInState instate) throws TamagoCCException {
		indent.open("instate");
		for (String st : instate.getStates()) {
			indent.openclose("state", "name",st);
		}
		indent.close("instate");
		return null;
	}

	@Override
	public Object visitIsBound(AIsBound aiIsBound) throws TamagoCCException {
		indent.openclose("isbound", "label",aiIsBound.getLabel());
		return null;
	}

}
