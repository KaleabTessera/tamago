/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCAST;
import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.ACodeComment;
import tamagocc.ast.api.AComment;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AICodeComment implements ACodeComment {

	private TamagoCCAST instruction;
	
	/**
	 * 
	 */
	public AICodeComment(TamagoCCAST instruction) {
		super();
		this.instruction = instruction;
	}

	/**
	 * @see tamagocc.ast.api.ACodeComment#getCode()
	 */
	public TamagoCCAST getCode() {
		return instruction;
	}

	/**
	 * @see tamagocc.ast.api.AComment#getCommentType()
	 */
	public int getCommentType() {
		return AComment.CODECOMMENT;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitCodeComment(this);
	}

}
