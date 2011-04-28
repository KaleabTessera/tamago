/**
 * 
 */
package tamagocc.api;

import java.util.ArrayList;

/**
 * @author hakim
 *
 */
public interface TInState extends TExpression {
	ArrayList<String> getInState();
	int size();
}
