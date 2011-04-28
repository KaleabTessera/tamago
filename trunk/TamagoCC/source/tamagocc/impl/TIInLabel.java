/**
 * 
 */
package tamagocc.impl;

import tamagocc.api.TExpression;
import tamagocc.api.TInLabel;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author hakim
 *
 */
public class TIInLabel implements TInLabel {

	private TExpression target;
	private TExpression subexpr;
	
	/**
	 * 
	 */
	public TIInLabel(TExpression target,TExpression subexpr) {
		super();
		this.target = target;
		this.subexpr = subexpr;
	}

	/**
	 * @see tamagocc.api.TInLabel#getTarget()
	 */
	public TExpression getTarget() {
		return target;
	}

	/**
	 * @see tamagocc.api.TInLabel#getSubTerm()
	 */
	public TExpression getSubTerm() {
		return subexpr; 
	}

	public boolean equals(Object o) {
		if (o instanceof TInLabel) {
			TInLabel p = (TInLabel) o;
			return (getTarget().equals(p.getTarget())
					&& getSubTerm().equals(p.getSubTerm()));
		}
		return false;
	}
	
	/**
	 * @see tamagocc.api.TExpression#getCat()
	 */
	public ExprType getCat() {
		return ExprType.INLABEL;
	}

	/**
	 * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
	 */
	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitInLabel(this);
	}

    /**
     * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
     */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitInLabel(this);
	}
}
