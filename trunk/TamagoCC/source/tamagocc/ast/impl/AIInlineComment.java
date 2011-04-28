/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.AInlineComment;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIInlineComment implements AInlineComment {

	private String comment;
	
	/**
	 * 
	 */
	public AIInlineComment(String comment) {
		super();
		this.comment = comment;
	}

	/**
	 * @see tamagocc.ast.api.AInlineComment#getComment()
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @see tamagocc.ast.api.AComment#getCommentType()
	 */
	public int getCommentType() {
		return AComment.INLINECOMMENT;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitInlineComment(this);
	}

}
