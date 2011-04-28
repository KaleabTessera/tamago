package tamagocc.generic.impl;

import java.util.Collection;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GNoContract;
import tamagocc.generic.api.GPreExpression;
import tamagocc.util.NilCollection;

public class GINoContract implements GNoContract {

	public static final GINoContract NOCONTRACT = new GINoContract();
	
	public GINoContract() {
		super();
	}

	public GExprType getCategory() {
		return GExprType.NOCONTRACT;
	}

	public Collection<GPreExpression> getPreExpression() {
		return new NilCollection<GPreExpression>();
	}
 
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitNoContract(this);
	}
	
	/**
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitNoContract(this);
	}
	
	public Object visitPreExpression(TamagoCCGPreExpressionVisitor visitor) throws TamagoCCException {
		return this.visitExpression(visitor);
	}

	public boolean equals(Object o) {
		return (o instanceof GNoContract);			
	}
}
