/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AWhile;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIWhile implements AWhile {

	private AExpression condition;
	private AInstruction body;
	private AComment comment;
	
	/**
	 * 
	 */
	public AIWhile(AExpression condition,AInstruction body) {
		super();
		this.condition = condition;
		this.body = body;
		comment = new AINoComment();
	}

	/**
	 * @see tamagocc.ast.api.AWhile#getCondition()
	 */
	public AExpression getCondition() {
		return condition;
	}

	/**
	 * @see tamagocc.ast.api.AWhile#getBody()
	 */
	public AInstruction getBody() {
		return body;
	}

	/**
	 * @see tamagocc.ast.api.AInstruction#getInstructionType()
	 */
	public int getInstructionType() {
		return AInstruction.WHILE;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitWhile(this);
	}
	
	public AComment getComment() {
		return comment;
	}
	
	public void setComment(AComment comment) {
		this.comment = comment;
	}

}
