/**
 * 
 */
package tamagocc.ast.api;

import tamagocc.ast.TamagoCCAST;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface ACatchBlock extends TamagoCCAST {
	AIdent getIdent();
	AType getExceptionType();
	AInstruction getBody();
}
