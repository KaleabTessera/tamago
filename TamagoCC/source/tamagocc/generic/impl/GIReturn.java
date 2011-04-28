/**
 * 
 */
package tamagocc.generic.impl;

import java.util.Collection;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GPreExpression;
import tamagocc.generic.api.GReturn;
import tamagocc.generic.api.GType;
import tamagocc.generic.api.GVariable;
import tamagocc.util.NilCollection;

/**
 * @author hakim
 *
 */
public class GIReturn implements GReturn {

	private GVariable variable;
	private GExpression idx;
	/**
	 * 
	 */
	public GIReturn(GVariable variable,GExpression idx) {
		super();
		this.idx = idx;
		this.variable = variable;
	}
	
	public GIReturn(GVariable variable) {
		super();
		this.idx = null;
		this.variable = variable;
	}
	

	/**
	 * @see tamagocc.generic.api.GReturn#getType()
	 */
	public GType getType() {
		return variable.getType();
	}

	/**
	 * @see tamagocc.generic.api.GReturn#getVariable()
	 */
	public GVariable getVariable() {
		return variable;
	}

	/**
	 * @see tamagocc.generic.api.GExpression#getCategory()
	 */
	public GExprType getCategory() {
		return GExprType.RETURN;
	}

	/**
	 * @see tamagocc.generic.api.GExpression#getPreExpression()
	 */
	public Collection<GPreExpression> getPreExpression() {
		return new NilCollection<GPreExpression>();
	}
 

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitReturn(this);
	}
	
	public Object visitPreExpression(TamagoCCGPreExpressionVisitor visitor) throws TamagoCCException {
		return this.visitExpression(visitor);
	}
	
	/**
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitReturn(this);
	}
	
	public boolean equals(Object o) {
		return (o instanceof GReturn);
	}

	public GExpression getIndex() {
		return idx;
	}

	public boolean hasIndex() {
		return idx != null;
	}
}
