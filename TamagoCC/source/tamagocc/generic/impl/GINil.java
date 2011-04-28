/**
 * 
 */
package tamagocc.generic.impl;

import java.util.Collection;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GNil;
import tamagocc.generic.api.GPreExpression;
import tamagocc.util.NilCollection;

/**
 * @author Hakim Belhaouari
 *
 */
public class GINil implements GNil {

	/**
	 * 
	 */
	public GINil() {
		
	}

	/**
	 * @see tamagocc.generic.api.GExpression#getCategory()
	 */
	public GExprType getCategory() {
		return GExprType.NIL;
	}

	/**
	 * @see tamagocc.generic.api.GExpression#getPreExpression()
	 */
	public Collection<GPreExpression> getPreExpression() {
		return new NilCollection<GPreExpression>();
	}

	/**
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor)
			throws TamagoCCException {
		return exprvisitor.visitNil(this);
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitNil(this);
	}

	public Object visitPreExpression(TamagoCCGPreExpressionVisitor visitor) throws TamagoCCException {
		return this.visitExpression(visitor);
	}
}
