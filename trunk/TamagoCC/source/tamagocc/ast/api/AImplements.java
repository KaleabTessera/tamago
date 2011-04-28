/**
 * 
 */
package tamagocc.ast.api;

import tamagocc.ast.TamagoCCAST;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AImplements extends TamagoCCAST{
	String getFullNameImplements();
	String getName();
	AModule getModule();
}
