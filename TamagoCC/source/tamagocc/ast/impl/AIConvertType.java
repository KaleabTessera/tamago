/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AConvertType;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AType;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIConvertType implements AConvertType {
	private AType newtype;
	private AExpression expression;
	/**
	 * 
	 */
	public AIConvertType(AType newtype,AExpression expression) {
		super();
		this.newtype = newtype;
		this.expression = expression;
	}

	/**
	 * @see tamagocc.ast.api.AConvertType#getNewType()
	 */
	public AType getNewType() {
		return newtype;
	}

	/**
	 * @see tamagocc.ast.api.AConvertType#getExprToConvert()
	 */
	public AExpression getExprToConvert() {
		return expression;
	}
	
	/**
	 * @see tamagocc.ast.api.AExpression#getExpressionType()
	 */
	public int getExpressionType() {
		return AExpression.CONVERTTYPE;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitConvertType(this);
	}

}
