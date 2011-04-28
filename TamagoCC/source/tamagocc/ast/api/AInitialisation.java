/**
 * 
 */
package tamagocc.ast.api;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AInitialisation extends AInstruction {
	AIdent getIdent();
	AType getType();
	AExpression getInitial();
}
