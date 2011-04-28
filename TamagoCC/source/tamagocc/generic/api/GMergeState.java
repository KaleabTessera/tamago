/**
 * 
 */
package tamagocc.generic.api;

import java.util.Iterator;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface GMergeState extends GState {
	Iterator<GState> getSubStates();
	int getCountSubStates();
	boolean containsState(GState state);
	
}
