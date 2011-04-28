/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.ALanguageExpr;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari
 *
 */
public class AILanguageExpr implements ALanguageExpr {

	private String expr;
	
	/**
	 * 
	 */
	public AILanguageExpr(String expr) {
		this.expr = expr;
	}

	/**
	 * @see tamagocc.ast.api.ALanguageExpr#getExpression()
	 */
	public String getExpression() {
		return expr;
	}

	/**
	 * @see tamagocc.ast.api.AExpression#getExpressionType()
	 */
	public int getExpressionType() {
		return LANGEXPR;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitLanguageExpr(this);
	}

}
