/**
 * 
 */
package tamagocc.ast.api;

import tamagocc.ast.TamagoCCAST;

/**
 * @author Hakim Belhaouari
 *
 */
public interface AProperty extends TamagoCCAST {
	
	String getName();
	AType getType();

	boolean canRead();
	boolean canWrite();
	
	boolean emptyBody();
}
