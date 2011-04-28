/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AReal;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIReal implements AReal {

	private double value;
	
	/**
	 * 
	 */
	public AIReal(double value) {
		super();
		this.value = value;
	}

	/**
	 * @see tamagocc.ast.api.AReal#getValue()
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @see tamagocc.ast.api.AExpression#getExpressionType()
	 */
	public int getExpressionType() {
		return AExpression.REAL;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitReal(this);
	}
	
	public String toString() {
		return ""+value;
	}

}
