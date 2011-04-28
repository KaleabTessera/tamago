/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GExistSet;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GQuantifierType;
import tamagocc.generic.api.GSet;
import tamagocc.generic.api.GType;
import tamagocc.generic.api.GVariable;

/**
 * @author Hakim Belhaouari
 *
 */
public class GIExistSet extends GIQuantifier implements GExistSet {

	
	private GSet set;
	
	
	/**
	 * 
	 */
	public GIExistSet(GType t,GVariable v,GSet s,GExpression body) {
		super(t, v, body);
		this.type = t;
		this.set = s;
	}

	/**
	 * @see tamagocc.generic.api.GExistSet#getSet()
	 */
	public GSet getSet() {
		return set;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitExistSet(this);
	}
	
	public GQuantifierType getQuantifierType() {
		return GQuantifierType.EXISTSET;
	}

	/**
	 * @see tamagocc.generic.api.GQuantifier#visitGQuantifier(tamagocc.generic.TamagoCCGPreExpressionVisitor)
	 */
	public Object visitGQuantifier(TamagoCCGPreExpressionVisitor visitor)
			throws TamagoCCException {
		return visitor.visitExistSet(this);
	}
	
	/**
	 * @see tamagocc.generic.api.GPreExpression#visitPreExpression(tamagocc.generic.TamagoCCGPreExpressionVisitor)
	 */
	public Object visitPreExpression(TamagoCCGPreExpressionVisitor visitor)
			throws TamagoCCException {
		return visitor.visitExistSet(this);
	}

}
