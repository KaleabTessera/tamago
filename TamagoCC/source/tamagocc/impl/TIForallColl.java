/**
 * 
 */
package tamagocc.impl;

import tamagocc.api.TExpression;
import tamagocc.api.TForallColl;
import tamagocc.api.TType;
import tamagocc.api.TVariable;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author Hakim Belhaouari
 *
 */
public class TIForallColl implements TForallColl {

	private TVariable variable;
	private TExpression collection;
	private TType type;
	private TExpression body;
	
	/**
	 * 
	 */
	public TIForallColl(TType type, TVariable variable, TExpression collection, TExpression body) {
		this.type =type;
		this.variable = variable;
		this.collection = collection;
		this.body = body;
	}

	/**
	 * @see tamagocc.api.TForallColl#getBody()
	 */
	public TExpression getBody() {
		return body;
	}

	/**
	 * @see tamagocc.api.TForallColl#getCollection()
	 */
	public TExpression getCollection() {
		return collection;
	}

	/**
	 * @see tamagocc.api.TForallColl#getType()
	 */
	public TType getType() {
		return type;
	}

	/**
	 * @see tamagocc.api.TForallColl#getVariable()
	 */
	public TVariable getVariable() {
		return variable;
	}

	/**
	 * @see tamagocc.api.TExpression#getCat()
	 */
	public ExprType getCat() {
		return ExprType.FORALLCOLL;
	}

	/**
	 * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor)
			throws TamagoCCException {
		return exprvisitor.visitForallColl(this);
	}

	/**
	 * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
	 */
	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitForallColl(this);
	}

}
