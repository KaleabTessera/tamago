/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AInlineComment;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AINoComment implements AInlineComment{

	/**
	 * 
	 */
	public AINoComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see tamagocc.ast.api.AComment#getCommentType()
	 */
	public int getCommentType() {
		return NOCOMMENT;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitInlineComment(this);
	}

	public String getComment() {
		return "";
	}

}
