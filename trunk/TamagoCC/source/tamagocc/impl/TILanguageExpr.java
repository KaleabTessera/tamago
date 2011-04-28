/**
 * 
 */
package tamagocc.impl;

import tamagocc.api.TLanguageExpr;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author Hakim Belhaouari
 *
 */
public class TILanguageExpr implements TLanguageExpr {

	private String expr;
	
	/**
	 * 
	 */
	public TILanguageExpr(String expr) {
		this.expr = expr;
	}

	/**
	 * @see tamagocc.api.TLanguageExpr#getExpression()
	 */
	public String getExpression() {
		return expr;
	}

	/**
	 * @see tamagocc.api.TExpression#getCat()
	 */
	public ExprType getCat() {
		return ExprType.LANGEXPR;
	}

	/**
	 * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor)
			throws TamagoCCException {
		return exprvisitor.visitLanguageExpr(this);
	}

	/**
	 * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
	 */
	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitLanguageExpr(this);
	}

}
