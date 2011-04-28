/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AInLabel;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIInLabel implements AInLabel {

	private AExpression scope;
	private AExpression expr;
	
	
	
	/**
	 * 
	 */
	public AIInLabel(AExpression scope,AExpression expr) {
		super();
		this.scope = scope;
		this.expr = expr;
	}

	/**
	 * @see tamagocc.ast.api.AInLabel#getIdent()
	 */
	public AExpression getScope() {
		return scope;
	}

	/**
	 * @see tamagocc.ast.api.AInLabel#getSubExpression()
	 */
	public AExpression getSubExpression() {
		return expr;
	}

	/**
	 * @see tamagocc.ast.api.AExpression#getExpressionType()
	 */
	public int getExpressionType() {
		return AExpression.INLABEL;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitInLabel(this);
	}

	public String toString() {
		return ""+getScope().toString()+"."+getSubExpression().toString();
	}
}
