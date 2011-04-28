/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GInLabel;

/**
 * @author hakim
 *
 */
public class GIInLabel extends GIExpression implements GInLabel {

	private GExpression target;
	private GExpression subexpr;
	
	/**
	 * 
	 */
	public GIInLabel(GExpression target,GExpression subexpr) {
		super();
		this.target = target;
		this.subexpr = subexpr;
		addAllPreExpression(target.getPreExpression());
		addAllPreExpression(subexpr.getPreExpression());
	}

	/**
	 * @see tamagocc.generic.api.GInLabel#getTarget()
	 */
	public GExpression getTarget() {
		return target;
	}
	
	public void setTarget(GExpression target) {
		this.target = target;
	}

	/**
	 * @see tamagocc.generic.api.GInLabel#getSubExpression()
	 */
	public GExpression getSubExpression() {
		return subexpr;
	}
		
	public void setSubExpression(GExpression subexpr) {
		this.subexpr = subexpr;
	}

	public GExprType getCategory() {
		return GExprType.INLABEL;
	}
 
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitInLabel(this);
	}
	
	/**
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitInLabel(this);
	}
	
	public boolean equals(Object o) {
		if (o instanceof GInLabel) {
			GInLabel ni = (GInLabel) o;
			return ni.getTarget().equals(ni.getTarget())&&getSubExpression().equals(ni.getSubExpression());
		}
		else
			return false;
	}
}
