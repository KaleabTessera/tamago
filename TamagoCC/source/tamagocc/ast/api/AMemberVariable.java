/**
 * 
 */
package tamagocc.ast.api;

import tamagocc.ast.TamagoCCAST;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AMemberVariable extends TamagoCCAST {
	AIdent getIdent();
	AType getType();
	AVisibility getVisibility();
	AComment getComment();
	
	AExpression getCallMe();
}
