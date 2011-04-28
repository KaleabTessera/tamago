/**
 * 
 */
package tamagocc.ast.api;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface ANewArray extends ANew {
	AType getType();
	int getTaille();
}
