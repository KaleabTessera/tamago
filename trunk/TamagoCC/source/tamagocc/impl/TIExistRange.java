/**
 * 
 */
package tamagocc.impl;

import tamagocc.api.TExistRange;
import tamagocc.api.TExpression;
import tamagocc.api.TType;
import tamagocc.api.TVariable;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author belhaouari
 *
 */
public class TIExistRange implements TExistRange {

	private TVariable variable;
	private TExpression body;
	private TType type;
	private TExpression min;
	private TExpression max;
	
	/**
	 * 
	 */
	public TIExistRange(TType t,TVariable var,TExpression min,TExpression max,TExpression body) {
		this.variable = var;
		this.type = t;
		this.min = min;
		this.max = max;
		this.body = body;
	}
	/**
	 * @see tamagocc.api.TExistRange#getBody()
	 */
	public TExpression getBody() {
		return body;
	}

	/**
	 * @see tamagocc.api.TExistRange#getMax()
	 */
	public TExpression getMax() {
		return max;
	}

	/**
	 * @see tamagocc.api.TExistRange#getMin()
	 */
	public TExpression getMin() {
		return min;
	}

	/**
	 * @see tamagocc.api.TExistRange#getType()
	 */
	public TType getType() {
		return type;
	}

	/**
	 * @see tamagocc.api.TExistRange#getVariable()
	 */
	public TVariable getVariable() {
		return variable;
	}

	/**
	 * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
	 */
	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitExistRange(this);
	}

	public ExprType getCat() {
		return ExprType.EXISTRANGE;
	}

	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitExistRange(this);
	}
}
