/**
 * 
 */
package tamagocc.impl;

import tamagocc.api.TIsBound;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author hbelhaou
 *
 */
public class TIIsBound implements TIsBound {

	private String label;
	
	/**
	 * 
	 */
	public TIIsBound(String label) {
		this.label = label;
	}

	/**
	 * @see tamagocc.api.TExpression#getCat()
	 */
	@Override
	public ExprType getCat() {
		return ExprType.ISBOUND;
	}

	/* (non-Javadoc)
	 * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
	 */
	@Override
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor)
			throws TamagoCCException {
		return exprvisitor.visitIsBound(this);
	}

	/* (non-Javadoc)
	 * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
	 */
	@Override
	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitIsBound(this);
	}

	/* (non-Javadoc)
	 * @see tamagocc.api.TIsBound#getLabel()
	 */
	@Override
	public String getLabel() {
		return label;
	}

}
