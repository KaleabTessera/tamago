/**
 * 
 */
package tamagocc.ast.api;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AVariable extends AExpression {
	AIdent getIdent();
	boolean hasIndex();
	AExpression getIndex();
}
