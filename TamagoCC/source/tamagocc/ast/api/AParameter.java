/**
 * 
 */
package tamagocc.ast.api;

import tamagocc.ast.TamagoCCAST;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AParameter extends TamagoCCAST {
	AIdent getIdent();
	AType getType();
}
