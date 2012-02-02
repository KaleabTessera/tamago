/**
 * 
 */
package tamago.aca.parser;

import javapop.framework.parser.expr.ExprAssoc;
import javapop.framework.parser.expr.ExprNode;
import javapop.framework.parser.expr.InfixNode;
import javapop.framework.parser.expr.OperandNode;
import javapop.framework.parser.expr.PostfixNode;
import javapop.framework.parser.expr.PrefixNode;

/**
 * @author hbelhaou
 *
 */
public class ProcessOperator implements InfixNode,Process {
	private String op;
	private int prio;
	private ExprAssoc assoc;
	private ExprNode right;
	private ExprNode left;
	
	/**
	 * 
	 */
	public ProcessOperator(String op) {
		this.op = op;
	}

	/**
	 * @see javapop.framework.parser.expr.OperatorNode#getPrio()
	 */
	@Override
	public int getPrio() {
		return prio;
	}

	/**
	 * @see javapop.framework.parser.expr.OperatorNode#setPrio(int)
	 */
	@Override
	public void setPrio(int prio) {
		this.prio = prio;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#isInfix()
	 */
	@Override
	public boolean isInfix() {
		return true;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#asInfix()
	 */
	@Override
	public InfixNode asInfix() {
		return this;
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
		return false;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#asOperand()
	 */
	@Override
	public OperandNode asOperand() {
		return null;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Operator: "+op;
	}

	/**
	 * @see javapop.framework.parser.expr.InfixNode#getLeftOperand()
	 */
	@Override
	public ExprNode getLeftOperand() {
		return left;
	}

	/**
	 * @see javapop.framework.parser.expr.InfixNode#setLeftOperand(javapop.framework.parser.expr.ExprNode)
	 */
	@Override
	public void setLeftOperand(ExprNode operand) {
		this.left = operand;
	}

	/**
	 * @see javapop.framework.parser.expr.InfixNode#getRightOperand()
	 */
	@Override
	public ExprNode getRightOperand() {
		return right;
	}

	/**
	 * @see javapop.framework.parser.expr.InfixNode#setRightOperand(javapop.framework.parser.expr.ExprNode)
	 */
	@Override
	public void setRightOperand(ExprNode operand) {
		this.right = operand;
	}

	/**
	 * @see javapop.framework.parser.expr.InfixNode#getAssoc()
	 */
	@Override
	public ExprAssoc getAssoc() {
		return assoc;
	}

	/**
	 * @see javapop.framework.parser.expr.InfixNode#setAssoc(javapop.framework.parser.expr.ExprAssoc)
	 */
	@Override
	public void setAssoc(ExprAssoc assoc) {
		this.assoc = assoc; 
	}

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		sb.append(left.toString());
		sb.append(" ");
		sb.append(op);
		sb.append(" ");
		sb.append(right.toString());
		sb.append(")");
		return sb.toString();
	}
}
