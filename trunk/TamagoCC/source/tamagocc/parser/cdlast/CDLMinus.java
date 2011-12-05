/**
 * 
 */
package tamagocc.parser.cdlast;

import javapop.framework.parser.expr.ExprNode;
import javapop.framework.parser.expr.PrefixNode;
import tamagocc.api.TExpression;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLMinus extends CDLExpression implements PrefixNode {

	private CDLExpression expr;
	private int prio;
	
	
	/**
	 * 
	 */
	public CDLMinus(String name) {
		prio = -1;
	}

	/**
	 * @see javapop.framework.parser.expr.UnaryNode#getOperand()
	 */
	public CDLExpression getOperand() {
		return expr;
	}

	/**
	 * @see javapop.framework.parser.expr.UnaryNode#setOperand(javapop.framework.parser.expr.ExprNode)
	 */
	public void setOperand(ExprNode operand) {
		this.expr = (CDLExpression) operand;
	}

	/**
	 * @see javapop.framework.parser.expr.OperatorNode#getPrio()
	 */
	public int getPrio() {
		return prio;
	}

	/**
	 * @see javapop.framework.parser.expr.OperatorNode#setPrio(int)
	 */
	public void setPrio(int prio) {
		this.prio = prio;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#getDescription()
	 */
	public String getDescription() {
		return "-"+expr.getDescription();
	}
	
	@Override
	public boolean isPrefix() {
		return true;
	}

	@Override
	public TExpression toTExpression() {
		throw new RuntimeException("not yet implemented");
	}

}
