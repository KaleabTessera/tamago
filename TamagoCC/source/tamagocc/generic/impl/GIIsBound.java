/**
 * 
 */
package tamagocc.generic.impl;

import java.util.Collection;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GIsBound;
import tamagocc.generic.api.GPreExpression;
import tamagocc.util.NilCollection;

/**
 * @author hbelhaou
 *
 */
public class GIIsBound implements GIsBound {

	private String label;
	
	/**
	 * 
	 */
	public GIIsBound(String label) {
		this.label = label;
	}

	/* (non-Javadoc)
	 * @see tamagocc.generic.api.GExpression#getCategory()
	 */
	@Override
	public GExprType getCategory() {
		return GExprType.ISBOUND;
	}

	/* (non-Javadoc)
	 * @see tamagocc.generic.api.GExpression#getPreExpression()
	 */
	@Override
	public Collection<GPreExpression> getPreExpression() {
		return new NilCollection<GPreExpression>();
	}

	/* (non-Javadoc)
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	@Override
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor)
			throws TamagoCCException {
		return exprvisitor.visitIsBound(this);
	}

	/* (non-Javadoc)
	 * @see tamagocc.generic.api.GPreExpression#visitPreExpression(tamagocc.generic.TamagoCCGPreExpressionVisitor)
	 */
	@Override
	public Object visitPreExpression(TamagoCCGPreExpressionVisitor visitor)
			throws TamagoCCException {
		return visitor.visitIsBound(this);
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	@Override
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitIsBound(this);
	}

	/**
	 * @see tamagocc.generic.api.GIsBound#getName()
	 */
	@Override
	public String getLabel() {
		return label;
	}
}
