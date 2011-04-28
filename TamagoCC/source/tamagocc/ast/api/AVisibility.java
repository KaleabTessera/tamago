/**
 * 
 */
package tamagocc.ast.api;

import tamagocc.ast.TamagoCCAST;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AVisibility extends TamagoCCAST {
	public static final int PUBLIC = 0;
	public static final int PRIVATE = 1;
	public static final int LIMITED = 2;
	public static final int PROTECTED = 3;
	
	int getVisibility();
}
