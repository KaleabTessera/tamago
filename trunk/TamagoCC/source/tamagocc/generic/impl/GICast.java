/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GCast;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GType;

/**
 * @author Hakim Belhaouari
 *
 */
public class GICast extends GIExpression implements GCast {

	private GType type;
	private GExpression expr;
	
	/**
	 * 
	 */
	public GICast(GType type, GExpression expr) {
		this.type = type;
		this.expr = expr;
		setPreExpression(expr.getPreExpression());
	}

	/**
	 * @see tamagocc.generic.api.GCast#getExpression()
	 */
	public GExpression getExpression() {
		return expr;
	}

	/**
	 * @see tamagocc.generic.api.GCast#getType()
	 */
	public GType getType() {
		return type;
	}

	/**
	 * @see tamagocc.generic.api.GExpression#getCategory()
	 */
	public GExprType getCategory() {
		return GExprType.CAST;
	}

	/**
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor)
			throws TamagoCCException {
		return exprvisitor.visitCast(this);
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitCast(this);
	}

}
