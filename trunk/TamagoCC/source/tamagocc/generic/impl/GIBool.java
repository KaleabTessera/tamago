package tamagocc.generic.impl;

import java.util.Collection;

import tamagocc.api.TBool;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GBool;
import tamagocc.generic.api.GPreExpression;
import tamagocc.util.NilCollection;

public class GIBool implements GBool {
	private boolean value;
	public GIBool(boolean v) {
		value = v;
	}

	public GIBool(TBool b) {
		value = b.getValue();
	}
	
	public boolean getValue() {
		return value;
	}


	public GExprType getCategory() {
		return GExprType.BOOL;
	}

	public Collection<GPreExpression> getPreExpression() {
		return new NilCollection<GPreExpression>();
	}
	
	public void setValue(boolean bool) {
		value = bool;
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitBool(this);
	}
	
	/**
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitBool(this);
	}

	public boolean equals(Object o) {
		if (o instanceof GBool) {
			GBool nb = (GBool) o;
			return (getValue() == nb.getValue());
		}
		else
			return false;
	}
	
	public Object visitPreExpression(TamagoCCGPreExpressionVisitor visitor) throws TamagoCCException {
		return this.visitExpression(visitor);
	}
}
