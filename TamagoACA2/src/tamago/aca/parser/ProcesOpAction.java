/**
 * 
 */
package tamago.aca.parser;

import javapop.framework.parser.expr.InfixNode;
import javapop.framework.parser.expr.OperandNode;
import javapop.framework.parser.expr.PostfixNode;
import javapop.framework.parser.expr.PrefixNode;

/**
 * @author hbelhaou
 *
 */
public class ProcesOpAction implements OperandNode,Process {

	private String ident;
	
	/**
	 * @param action 
	 * 
	 */
	public ProcesOpAction(String action) {
		this.ident = action;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#isInfix()
	 */
	@Override
	public boolean isInfix() {
		return false;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#asInfix()
	 */
	@Override
	public InfixNode asInfix() {
		return null;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#isPrefix()
	 */
	@Override
	public boolean isPrefix() {
		return false;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#asPrefix()
	 */
	@Override
	public PrefixNode asPrefix() {
		return null;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#isPostfix()
	 */
	@Override
	public boolean isPostfix() {
		return false;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#asPostfix()
	 */
	@Override
	public PostfixNode asPostfix() {
		return null;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#isOperand()
	 */
	@Override
	public boolean isOperand() {
		return true;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#asOperand()
	 */
	@Override
	public OperandNode asOperand() {
		return this;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Op Action: "+ident;
	}

	@Override
	public String toString() {
		return ident;
	}
}
