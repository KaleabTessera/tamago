/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AInstExpression;
import tamagocc.ast.api.AInstruction;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIInstExpression implements AInstExpression {

	private AExpression expr;
	private AComment comment;
	
	/**
	 * 
	 */
	public AIInstExpression(AExpression expr) {
		super();
		this.expr = expr;
		comment = new AINoComment();
	}

	/**
	 * @see tamagocc.ast.api.AInstExpression#getExpression()
	 */
	public AExpression getExpression() {
		return expr;
	}

	/**
	 * @see tamagocc.ast.api.AInstruction#getInstructionType()
	 */
	public int getInstructionType() {
		return AInstruction.EXPRESSION;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitInstExpression(this);
	}

	public AComment getComment() {
		return comment;
	}
	
	public void setComment(AComment comment) {
		this.comment = comment;
	}
}
