/**
 * 
 */
package tamagocc.parser.cdlast;

import tamagocc.api.TExpression;
import tamagocc.impl.TINil;
import javapop.framework.parser.expr.OperandNode;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLNil extends CDLExpression implements OperandNode {

	/**
	 * 
	 */
	public CDLNil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#getDescription()
	 */
	public String getDescription() {
		return "null";
	}
	
	@Override
	public boolean isOperand() {
		return true;
	}

	@Override
	public TExpression toTExpression() {
		return new TINil();
	}

}
