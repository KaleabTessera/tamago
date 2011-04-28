package tamagocc.generic.impl;

import java.util.Collection;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GInteger;
import tamagocc.generic.api.GPreExpression;
import tamagocc.util.NilCollection;

public class GIInteger implements GInteger {
	private int value;
	
	public GIInteger(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public GExprType getCategory() {
		return GExprType.INT;
	}

	public Collection<GPreExpression> getPreExpression() {
		return new NilCollection<GPreExpression>();
	}
 
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitInteger(this);
	}
	
	/**
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitInteger(this);
	}
	
	public Object visitPreExpression(TamagoCCGPreExpressionVisitor visitor) throws TamagoCCException {
		return this.visitExpression(visitor);
	}
	
	public boolean equals(Object o) {
		if (o instanceof GInteger) {
			GInteger nb = (GInteger) o;
			return (getValue() == nb.getValue());
		}
		else
			return false;
	}

}
