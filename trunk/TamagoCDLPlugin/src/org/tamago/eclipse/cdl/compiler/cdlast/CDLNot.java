/**
 * 
 */
package org.tamago.eclipse.cdl.compiler.cdlast;

import tamagocc.api.TExpression;
import tamagocc.impl.TINot;
import javapop.framework.parser.expr.ExprNode;
import javapop.framework.parser.expr.PrefixNode;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLNot extends CDLExpression implements PrefixNode {

	private CDLExpression expr;
	private String opname;
	private int prio;
	
	/**
	 * 
	 */
	public CDLNot(String name) {
		opname = name;
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
		expr = (CDLExpression)operand;
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
		return opname+expr.getDescription();
	}

	@Override
	public TExpression toTExpression() {
		return new TINot(expr.toTExpression());
	}
	
	@Override
	public boolean isPrefix() {
		return true;
	}
}
