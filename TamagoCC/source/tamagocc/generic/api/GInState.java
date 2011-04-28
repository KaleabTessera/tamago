/**
 * 
 */
package tamagocc.generic.api;

import java.util.Collection;

/**
 * @author Hakim Belhaouari
 *
 */
public interface GInState extends GExpression {
	Collection<String> getInState();
	int size();
}
