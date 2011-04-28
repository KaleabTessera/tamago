/**
 * 
 */
package tamagocc.impl;

import tamagocc.api.TExistColl;
import tamagocc.api.TExpression;
import tamagocc.api.TType;
import tamagocc.api.TVariable;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author Hakim Belhaouari
 *
 */
public class TIExistColl implements TExistColl {
	private TVariable variable;
	private TExpression collection;
	private TType type;
	private TExpression body;
	
	/**
	 * 
	 */
	public TIExistColl(TType type, TVariable variable, TExpression collection, TExpression body) {
		this.type =type;
		this.variable = variable;
		this.collection = collection;
		this.body = body;
	}

	/**
	 * @see tamagocc.api.TExistColl#getBody()
	 */
	public TExpression getBody() {
		return body;
	}

	/**
	 * @see tamagocc.api.TExistColl#getCollection()
	 */
	public TExpression getCollection() {
		return collection;
	}

	/**
	 * @see tamagocc.api.TExistColl#getType()
	 */
	public TType getType() {
		return type;
	}

	/**
	 * @see tamagocc.api.TExistColl#getVariable()
	 */
	public TVariable getVariable() {
		return variable;
	}

	/**
	 * @see tamagocc.api.TExpression#getCat()
	 */
	public ExprType getCat() {
		return ExprType.EXISTCOLL;
	}

	/**
	 * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor)
			throws TamagoCCException {
		return exprvisitor.visitExistColl(this);
	}

	/**
	 * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
	 */
	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitExistColl(this);
	}

}
