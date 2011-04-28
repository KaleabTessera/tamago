/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.ADocComment;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIDocComment implements ADocComment {

	private String doc;
	
	/**
	 * 
	 */
	public AIDocComment(String doc) {
		super();
		this.doc = doc;
	}

	/**
	 * @see tamagocc.ast.api.ADocComment#getComment()
	 */
	public String getComment() {
		return doc;
	}

	/**
	 * @see tamagocc.ast.api.AComment#getCommentType()
	 */
	public int getCommentType() {
		return AComment.DOCCOMMENT;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitDocComment(this);
	}

}
