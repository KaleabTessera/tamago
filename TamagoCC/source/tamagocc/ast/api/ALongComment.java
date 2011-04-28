/**
 * 
 */
package tamagocc.ast.api;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface ALongComment extends AComment {
	String getComment();
	int getLineNumbers();
}
