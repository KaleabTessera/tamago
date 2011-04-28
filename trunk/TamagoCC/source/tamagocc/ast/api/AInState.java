/**
 * 
 */
package tamagocc.ast.api;

import java.util.Collection;

/**
 * @author Hakim Belhaouari
 *
 */
public interface AInState extends AExpression {
	Collection<String> getStates();
	int size();
}
