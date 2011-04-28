/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.ALongComment;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AILongComment implements ALongComment {

	private StringBuffer sb;
	
	
	/**
	 * 
	 */
	public AILongComment(String msg) {
		super();
		sb = new StringBuffer();
		sb.append(msg);
	}

	/**
	 * @see tamagocc.ast.api.ALongComment#getComment()
	 */
	public String getComment() {
		return sb.toString();
	}

	/**
	 * @see tamagocc.ast.api.ALongComment#getLineNumbers()
	 */
	public int getLineNumbers() {
		String s = sb.toString();
		String ts[] = s.split("\n");
		if(ts == null)
			return 0;
		else
			return ts.length;
	}

	/**
	 * @see tamagocc.ast.api.AComment#getCommentType()
	 */
	public int getCommentType() {
		return AComment.LONGCOMMENT;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitLongComment(this);
	}

}
