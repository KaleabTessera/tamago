/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AIsBound;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GExpression;

/**
 * @author hbelhaou
 *
 */
public class AIIsBound implements AIsBound {

	private String label;
	
	/**
	 * 
	 */
	public AIIsBound(String label) {
		this.label = label;
	}

	/**
	 * @see tamagocc.ast.api.AExpression#getExpressionType()
	 */
	@Override
	public int getExpressionType() {
		return AExpression.ISBOUND;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	@Override
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitIsBound(this);
	}

	/**
	 * @see tamagocc.ast.api.AIsBound#getLabel()
	 */
	@Override
	public String getLabel() {
		return label;
	}

}
