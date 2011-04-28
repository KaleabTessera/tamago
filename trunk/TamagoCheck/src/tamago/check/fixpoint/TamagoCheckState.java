/**
 * 
 */
package tamago.check.fixpoint;

import java.util.Hashtable;
import java.util.Set;
import java.util.Map.Entry;

import tamago.csp.domain.CSPAbstractDomain;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTransition;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCheckState {

	private Hashtable<String, CSPAbstractDomain> properties;
	private GState state;
	private GTransition transition;
	private TamagoCheckState previousstate;
	private long id;
	
	private static long COUNT = 0;
	
	/**
	 * @param state
	 * @param transition 
	 * 
	 */
	public TamagoCheckState(GState state, TamagoCheckState previousstate,GTransition transition) {
		this.state = state;
		this.transition = transition;
		this.previousstate = previousstate;
		properties = new Hashtable<String, CSPAbstractDomain>();
		id = (COUNT++);
	}
	
	public long getID() {return id; }
	
	public TamagoCheckState getPreviousState() {
		return previousstate;
	}
	
	public GTransition getTransition() {
		return transition;
	}
	
	public CSPAbstractDomain get(String property) {
		return properties.get(property);
	}
	
	public void register(String property, CSPAbstractDomain domain) {
		properties.put(property, domain);
	}
	
	public GState getState() { 
		return state;
	}
	
	public Set<Entry<String, CSPAbstractDomain>> getEntries() {
		return properties.entrySet();
	}
	
	public boolean equals(Object o) {
		if(o instanceof TamagoCheckState) {
			TamagoCheckState s = (TamagoCheckState)o;
			if(state.equals(s.getState()) && s.properties.size() == properties.size()) {
				for (Entry<String, CSPAbstractDomain> prop : properties.entrySet()) {
					if(!s.properties.containsKey(prop.getKey())) {
						return false;
					}
					CSPAbstractDomain sd = s.properties.get(prop.getKey());
					if(!prop.getValue().sameDomain(sd))
						return false;
				}
				return true;
			}
		}
		return false;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(state.getName());
		sb.append(" : [ ");
		for (Entry<String, CSPAbstractDomain> item : getEntries()) {
			sb.append(item.getKey());
			sb.append("=");
			sb.append(item.getValue().toString());
		}
		
		sb.append(" ] ID:"+id);
		return sb.toString();
	}
}
