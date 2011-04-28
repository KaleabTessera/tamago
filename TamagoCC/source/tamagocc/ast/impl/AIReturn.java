/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AReturn;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIReturn implements AReturn {

	private AExpression expression;
	private AComment comment;
	
	/**
	 * 
	 */
	public AIReturn() {
		super();
		expression = new AINoExpression();
		comment = new AINoComment();
	}
	
	public AIReturn(AExpression expr) {
		expression = expr;
		comment = new AINoComment();
	}

	/**
	 * @see tamagocc.ast.api.AReturn#getExpression()
	 */
	public AExpression getExpression() {
		return expression;
	}
	
	public void setExpression(AExpression e) {
		expression = e;
	}
	
	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitReturn(this);
	}

	public int getInstructionType() {
		return AInstruction.RETURN;
	}

	public AComment getComment() {
		return comment;
	}

	public void setComment(AComment comment) {
		this.comment = comment;
	}
}
