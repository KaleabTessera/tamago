/**
 * 
 */
package tamagocc.ast.api;

import java.util.Iterator;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface ACall extends AExpression {
	AIdent getIdent();
	Iterator<AExpression> getArguments();
	int getArgCount();
}
