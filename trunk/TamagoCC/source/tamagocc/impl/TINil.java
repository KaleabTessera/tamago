/**
 * 
 */
package tamagocc.impl;

import tamagocc.api.TNil;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author Hakim Belhaouari
 *
 */
public class TINil implements TNil {

	/**
	 * 
	 */
	public TINil() {
	}

	/**
	 * @see tamagocc.api.TExpression#getCat()
	 */
	public ExprType getCat() {
		return ExprType.NIL;
	}

	/**
	 * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor)
			throws TamagoCCException {
		return exprvisitor.visitNil(this);
	}

	/**
	 * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
	 */
	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitNil(this);
	}

}
