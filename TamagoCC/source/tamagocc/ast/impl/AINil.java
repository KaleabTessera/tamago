/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.ANil;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AINil implements ANil {

	/**
	 * 
	 */
	public AINil() {
		super();
	}

	/**
	 * @see tamagocc.ast.api.AExpression#getExpressionType()
	 */
	public int getExpressionType() {
		return AExpression.NIL;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitNil(this);
	}

	public String toString() {
		return "null";
	}
}
