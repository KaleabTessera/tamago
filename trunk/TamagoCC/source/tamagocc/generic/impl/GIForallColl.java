/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GForallColl;
import tamagocc.generic.api.GQuantifierType;
import tamagocc.generic.api.GType;
import tamagocc.generic.api.GVariable;

/**
 * @author Hakim Belhaouari
 *
 */
public class GIForallColl extends GIQuantifier implements GForallColl {

	private GExpression collection;

	public GIForallColl(GType t, GVariable v, GExpression collection , GExpression body) {
		super(t, v, body);
		this.collection = collection;
	}
	

	/**
	 * @see tamagocc.generic.api.GForallColl#getCollection()
	 */
	public GExpression getCollection() {
		return collection;
	}

	/**
	 * @see tamagocc.generic.api.GQuantifier#getQuantifierType()
	 */
	public GQuantifierType getQuantifierType() {
		return GQuantifierType.FORALLCOLL;
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitForallColl(this);
	}


	/**
	 * @see tamagocc.generic.api.GQuantifier#visitGQuantifier(tamagocc.generic.TamagoCCGPreExpressionVisitor)
	 */
	public Object visitGQuantifier(TamagoCCGPreExpressionVisitor visitor)
			throws TamagoCCException {
		return visitor.visitForallColl(this);
	}

	/**
	 * @see tamagocc.generic.api.GPreExpression#visitPreExpression(tamagocc.generic.TamagoCCGPreExpressionVisitor)
	 */
	public Object visitPreExpression(TamagoCCGPreExpressionVisitor visitor)
			throws TamagoCCException {
		return visitor.visitForallColl(this);
	}
}
