/**
 * 
 */
package tamagocc.parser.cdlast;

import tamagocc.api.TExpression;
import javapop.framework.parser.expr.OperandNode;

/**
 * @author Hakim Belhaouari
 *
 */
public abstract class CDLValue<T> extends CDLExpression implements OperandNode {

	T value;
	
	/**
	 * 
	 */
	public CDLValue(T val) {
		this.value = val;
	}

	public String getDescription() {
		return value.toString();
	}

	@Override
	public boolean isOperand() {
		return true;
	}

	@Override
	public TExpression toTExpression() {
		throw new RuntimeException("strange error");
	}
}
