/**
 * 
 */
package tamagocc.impl;

import tamagocc.api.TCast;
import tamagocc.api.TExpression;
import tamagocc.api.TType;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author Hakim Belhaouari
 *
 */
public class TICast implements TCast {

	private TType type;
	private TExpression expr;
	
	/**
	 * 
	 */
	public TICast(TType type, TExpression expr) {
		this.type = type;
		this.expr = expr;
	}

	/**
	 * @see tamagocc.api.TCast#getExpression()
	 */
	public TExpression getExpression() {
		return expr;
	}

	/**
	 * @see tamagocc.api.TCast#getType()
	 */
	public TType getType() {
		return type;
	}

	/**
	 * @see tamagocc.api.TExpression#getCat()
	 */
	public ExprType getCat() {
		return ExprType.CAST;
	}

	/**
	 * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor)
			throws TamagoCCException {
		return exprvisitor.visitCast(this);
	}

	/**
	 * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
	 */
	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitCast(this);
	}

}
