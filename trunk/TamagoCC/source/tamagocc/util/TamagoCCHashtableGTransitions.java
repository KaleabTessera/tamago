/**
 * 
 */
package tamagocc.util;

import tamagocc.generic.api.GTransition;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCHashtableGTransitions {

	private TamagoCCHashtable<String, GTransition> transitions;
	
	/**
	 * 
	 */
	public TamagoCCHashtableGTransitions() {
		transitions = new TamagoCCHashtable<String, GTransition>();
	}

	
	public void put(GTransition t) {
		String from = t.getOrigin().getName();
		transitions.put(from, t);			
	}
	
	public Iterable<GTransition> getTransitionsFrom(String from) {
		return transitions.get(from);
	}
		
}
