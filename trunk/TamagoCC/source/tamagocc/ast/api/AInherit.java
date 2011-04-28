/**
 * 
 */
package tamagocc.ast.api;

import tamagocc.ast.TamagoCCAST;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AInherit extends TamagoCCAST {
	String getFullNameInherit();
	AModule getModule();
	String getName();
}
