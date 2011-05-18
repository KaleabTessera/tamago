/**
 * 
 */
package fr.lacl.tamago.aca.convert;

import java.util.HashMap;
import java.util.Set;

/**
 * @author hakim
 *
 */
public class SetMultiplicity {

	private HashMap<String, Integer> set;
	/**
	 * 
	 */
	public SetMultiplicity(HashMap<String, Integer> set) {
		this.set = new HashMap<String, Integer>(set);
	}
	
	public SetMultiplicity(SetMultiplicity sm) {
		this.set = new HashMap<String, Integer>(sm.set);
	}
	
	public SetMultiplicity() {
		this.set = new HashMap<String, Integer>();
	}
	
	
	public int getMulti(String name) {
		if(set.containsKey(name))
			return set.get(name);
		else
			return 0;
	}
	
	public void put(String action) {
		if(set.containsKey(action)) {
			int val = set.get(action)+ 1;
			set.put(action,  val);
		}
		else {
			set.put(action,0);
		}	
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SetMultiplicity) {
			SetMultiplicity nobj = (SetMultiplicity) obj;
			Set<String> myset = set.keySet();
			Set<String> yourset = nobj.set.keySet();
			if(myset.containsAll(yourset) 
					&& yourset.containsAll(myset)) {
				for (String string : yourset) {
					if(getMulti(string) != nobj.getMulti(string))
						return false;
				}
				return true;
			}
		}
		return false;
	}

}
