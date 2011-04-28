/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.ANoExpression;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AINoExpression implements ANoExpression {

	
	private static final ANoExpression vNoinst = new AINoExpression();
	public static final ANoExpression getNoExpression() { return vNoinst; }
	
	/**
	 * 
	 */
	public AINoExpression() {
		super();
	}

	/**
	 * @see tamagocc.ast.api.AExpression#getExpressionType()
	 */
	public int getExpressionType() {
		return AExpression.NOEXPRESSION;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitNoExpression(this);
	}
	
	public boolean equals(Object o) {
		return (o instanceof ANoExpression);
	}

}
