/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AInteger;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIInteger implements AInteger {

	private int value;
	
	/**
	 * 
	 */
	public AIInteger(int value) {
		super();
		this.value = value;
	}

	/**
	 * @see tamagocc.ast.api.AInteger#getValue()
	 */
	public int getValue() {
		return value;
	}

	/**
	 * 
	 * @see tamagocc.ast.api.AExpression#getExpressionType()
	 */
	public int getExpressionType() {
		return AExpression.INT;
	}

	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitInteger(this);
	}

	public String toString() {
		return ""+value;
	}


}
