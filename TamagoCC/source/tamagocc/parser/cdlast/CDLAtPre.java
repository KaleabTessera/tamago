/**
 * 
 */
package tamagocc.parser.cdlast;

import tamagocc.api.TExpression;
import tamagocc.impl.TIAtPre;
import javapop.framework.parser.expr.ExprNode;
import javapop.framework.parser.expr.PostfixNode;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLAtPre extends CDLExpression implements PostfixNode {
	private CDLExpression expr;
	private int prio;
	
	/**
	 * 
	 */
	public CDLAtPre() {
		this.expr = null;
		prio = 0;
	}

	public String getDescription() {
		return expr.getDescription()+"@pre";
	}
	
	public CDLExpression getOperand() {
		return expr;
	}

	public void setOperand(ExprNode operand) {
		this.expr = (CDLExpression) operand;
	}

	public int getPrio() {
		return prio;
	}

	public void setPrio(int prio) {
		this.prio = prio;
	}
	
	@Override
	public boolean isPostfix() {
		return true;
	}

	@Override
	public TExpression toTExpression() {
		return new TIAtPre(expr.toTExpression());
	}

}
