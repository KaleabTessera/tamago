/**
 * 
 */
package tamagocc.generic.impl;

import java.util.ArrayList;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GExistRange;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GPreExpression;
import tamagocc.generic.api.GQuantifierType;
import tamagocc.generic.api.GType;
import tamagocc.generic.api.GVariable;

/**
 * @author Hakim Belhaouari
 *
 */
public class GIExistRange extends GIQuantifier implements GExistRange {


	private GExpression min;
	private GExpression max;
	
	/**
	 * 
	 */
	public GIExistRange(GType t,GVariable v,GExpression min,GExpression max,GExpression body) {
		super(t,v,body);
		this.min = min;
		this.max = max;
		
		preexpr = new ArrayList<GPreExpression>();
		preexpr.addAll(min.getPreExpression());
		preexpr.addAll(max.getPreExpression());
		
		for (GPreExpression e : preexpr) {
			resvar.addPreExpression(e);
		}
	}

	/**
	 * @see tamagocc.generic.api.GExistRange#getMax()
	 */
	public GExpression getMax() {
		return max;
	}

	/**
	 * @see tamagocc.generic.api.GExistRange#getMin()
	 */
	public GExpression getMin() {
		return min;
	}

	/**
	 * @see tamagocc.generic.api.GExistRange#getType()
	 */
	public GType getType() {
		return type;
	}

	/**
	 * @see tamagocc.generic.api.GExistRange#getVariable()
	 */
	public GVariable getVariable() {
		return variable;
	}


	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitExistRange(this);
	}

	public GQuantifierType getQuantifierType() {
		return GQuantifierType.EXISTRANGE;
	}

	/**
	 * @see tamagocc.generic.api.GQuantifier#visitGQuantifier(tamagocc.generic.TamagoCCGPreExpressionVisitor)
	 */
	public Object visitGQuantifier(TamagoCCGPreExpressionVisitor visitor)
			throws TamagoCCException {
		return visitor.visitExistRange(this);
	}
	
	/**
	 * @see tamagocc.generic.api.GPreExpression#visitPreExpression(tamagocc.generic.TamagoCCGPreExpressionVisitor)
	 */
	public Object visitPreExpression(TamagoCCGPreExpressionVisitor visitor)
			throws TamagoCCException {
		return visitor.visitExistRange(this);
	}

}
