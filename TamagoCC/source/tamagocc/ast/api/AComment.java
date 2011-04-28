/**
 * 
 */
package tamagocc.ast.api;

import tamagocc.ast.TamagoCCAST;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AComment extends TamagoCCAST {
	public static final int INLINECOMMENT = 0;
	public static final int LONGCOMMENT = 1;
	public static final int CODECOMMENT = 2;
	public static final int DOCCOMMENT = 3;
	public static final int NOCOMMENT = 4;
	
	int getCommentType();
}
