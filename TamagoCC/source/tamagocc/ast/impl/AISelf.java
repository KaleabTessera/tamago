/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.ASelf;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AISelf implements ASelf {

	/**
	 * 
	 */
	public AISelf() {
		super();
	}

	/**
	 * @see tamagocc.ast.api.AExpression#getExpressionType()
	 */
	public int getExpressionType() {
		return AExpression.SELF;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitSelf(this);
	}

}
