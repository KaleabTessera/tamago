/**
 * 
 */
package tamagocc.generic.impl;

import java.util.ArrayList;
import java.util.Collection;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GLanguageExpr;
import tamagocc.generic.api.GPreExpression;

/**
 * @author Hakim Belhaouari
 *
 */
public class GILanguageExpr implements GLanguageExpr {
	private String expr;
	
	/**
	 * 
	 */
	public GILanguageExpr(String expr) {
		this.expr = expr;
	}

	/**
	 * @see tamagocc.generic.api.GLanguageExpr#getExpression()
	 */
	public String getExpression() {
		return expr;
	}

	/**
	 * @see tamagocc.generic.api.GExpression#getCategory()
	 */
	public GExprType getCategory() {
		return GExprType.LANGEXPR;
	}

	/**
	 * @see tamagocc.generic.api.GExpression#getPreExpression()
	 */
	public Collection<GPreExpression> getPreExpression() {
		return new ArrayList<GPreExpression>(0);
	}

	/**
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor)
			throws TamagoCCException {
		return exprvisitor.visitLanguageExpr(this);
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitLanguageExpr(this);
	}
	
	public Object visitPreExpression(TamagoCCGPreExpressionVisitor visitor) throws TamagoCCException {
		return this.visitExpression(visitor);
	}

}
