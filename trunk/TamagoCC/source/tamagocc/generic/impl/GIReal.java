package tamagocc.generic.impl;

import java.util.Collection;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GPreExpression;
import tamagocc.generic.api.GReal;
import tamagocc.util.NilCollection;

public class GIReal implements GReal {
	private double value;
	
	public GIReal(double value) {
		super();
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public GExprType getCategory() {
		return GExprType.REAL;
	}

	public Collection<GPreExpression> getPreExpression() {
		return new NilCollection<GPreExpression>();
	}
 
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitReal(this);
	}
	
	public Object visitPreExpression(TamagoCCGPreExpressionVisitor visitor) throws TamagoCCException {
		return this.visitExpression(visitor);
	}
	
	/**
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitReal(this);
	}
	
	public boolean equals(Object o) {
		if (o instanceof GReal) {
			GReal nb = (GReal) o;
			return (getValue() == nb.getValue());
		}
		else
			return false;
	}
}
