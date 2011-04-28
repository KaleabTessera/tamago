/**
 * 
 */
package tamagocc.impl;

import tamagocc.api.TExpression;
import tamagocc.api.TForallSet;
import tamagocc.api.TSet;
import tamagocc.api.TType;
import tamagocc.api.TVariable;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author belhaouari
 *
 */
public class TIForallSet implements TForallSet {

	private TVariable variable;
	private TSet set;
	private TType type;
	private TExpression body;
	
	/**
	 * 
	 */
	public TIForallSet(TType t,TVariable var, TSet set,TExpression body) {
		this.type = t;
		this.variable = var;		
		this.set = set;
		this.body = body;
	}

	/**
	 * Return the body of the \\forall expression 
	 * @see tamagocc.api.TForallSet#getExpression()
	 */
	public TExpression getBody() {
		return body;
	}

	/**
	 * Return the used set (it could be a range of an enumerative type).
	 * @see tamagocc.api.TForallSet#getSet()
	 */
	public TSet getSet() {
		return set;
	}

	/**
	 * Return the type of an element.
	 * @see tamagocc.api.TForallSet#getType()
	 */
	public TType getType() {
		return type;
	}

	/**
	 * @see tamagocc.api.TForallSet#getVariable()
	 */
	public TVariable getVariable() {
		return variable;
	}

	/**
	 * @see tamagocc.api.TExpression#getCat()
	 */
	public ExprType getCat() {
		return ExprType.FORALLSET;
	}

	/**
	 * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor)
			throws TamagoCCException {
		return exprvisitor.visitForallSet(this);
	}

	/**
	 * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
	 */
	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitForallSet(this);
	}

}
