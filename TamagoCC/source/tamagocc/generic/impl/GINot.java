package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GNot;

public class GINot extends GIExpression implements GNot {
	private GExpression term;
	
	public GINot(GExpression term) {
		this.term = term;
		addAllPreExpression(term.getPreExpression());
	}
	
	public GExpression getTerm() {
		return term;
	}

	public GExprType getCategory() {
		return GExprType.NOT;
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitNot(this);
	}
	
	/**
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitNot(this);
	}
	
	public boolean equals(Object o) {
		if (o instanceof GNot) {
			GNot nn = (GNot) o;
			return getTerm().equals(nn.getTerm());
		}
		else
			return false;
	}
 
}
