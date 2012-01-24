/**
 * 
 */
package tamago.ext.tamagocc;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import tamago.TamagoException;

/**
 * This class represent a state in the service behavior. In the abstract form or in the concret form.
 * In other word, a state can be a simple state or a set of some states.
 * 
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCState {
	private ArrayList<String> names;
	private ArrayList<TamagoCCMethodID> allows;
	private Hashtable<TamagoCCMethodID, ArrayList<Integer>> transitions;
	private int id;
	
	TamagoCCState(int id) {
		this.id = id;
		names = new ArrayList<String>();
		allows = new ArrayList<TamagoCCMethodID>();
		transitions = new Hashtable<TamagoCCMethodID, ArrayList<Integer>>();
	}
	
	public int id() {
		return id;
	}
	
	public String getName() {
		StringBuffer sb = new StringBuffer();
		Iterator<String> ns = names.iterator();
		while(ns.hasNext()) {
			sb.append(ns.next());
			if(ns.hasNext())
				sb.append("/");
		}
		if(sb.length() == 0)
			throw new TamagoException("Unamed state");
		return sb.toString();
	}
	
	public boolean isMergedState() {
		return names.size() >1;
	}
	
	public boolean canCallMethod(TamagoCCMethodID mid) {
		return allows.contains(mid);
	}
	
	
	// ---------------- beginning of the limited part
	void include(String name) {
		if(!names.contains(name))
			names.add(name);
	}
	
	void allow(TamagoCCMethodID mid) {
		if(!allows.contains(mid))
			allows.add(mid);
	}
	
	void transition(TamagoCCMethodID mid,int tid) {
		if(transitions.containsKey(mid)) {
			ArrayList<Integer> core = transitions.get(mid);
			Integer integer = new Integer(tid);
			if(!core.contains(integer))
				core.add(integer);
		}
		else {
			ArrayList<Integer> core = new ArrayList<Integer>();
			core.add(new Integer(tid));
			transitions.put(mid, core);
		}
	}

	public boolean isInState(String name) {
		String[] splitted = name.split("/");
		boolean res = true;
		for (String n : splitted) {
			names.contains(n);
		}
		return res;
	}
}
