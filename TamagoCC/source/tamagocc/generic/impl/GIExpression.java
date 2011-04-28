/**
 * 
 */
package tamagocc.generic.impl;

import java.util.ArrayList;
import java.util.Collection;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GPreExpression;
import tamagocc.util.TamagoCCMakeReadableGExpression;

/**
 * @author Hakim Belhaouari
 *
 */
public abstract class GIExpression implements GExpression {
	protected ArrayList<GPreExpression> preexprs;
	
	/**
	 * 
	 */
	public GIExpression() {
		preexprs = new ArrayList<GPreExpression>();
	}
	
	public GIExpression(Collection<GPreExpression> exprs) {
		preexprs = new ArrayList<GPreExpression>(exprs);
	}


	/**
	 * @see tamagocc.generic.api.GExpression#getPreExpression()
	 */
	public Collection<GPreExpression> getPreExpression() {
		return preexprs;
	}

	public void addPreExpression(GPreExpression expr) {
		preexprs.add(expr);
	}
	
	public void addAllPreExpression(Collection<GPreExpression> exprs) {
		preexprs.addAll(exprs);
	}
	
	public void setPreExpression(Collection<GPreExpression> exprs) {
		preexprs = new ArrayList<GPreExpression>(exprs);
	}
	
	public Object visitPreExpression(TamagoCCGPreExpressionVisitor visitor) throws TamagoCCException {
		return this.visitExpression(visitor);
	}
	
	public String toString() {
		return TamagoCCMakeReadableGExpression.toString(this);
	}
}
