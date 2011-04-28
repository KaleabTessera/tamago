/**
 * 
 */
package tamagocc.util;

import java.util.Hashtable;

/**
 * Provide fresh variable for a system (tamago abuse of it and need a manager to avoid redundancy variables
 * ) but i dont have time to retake all the code and make modification for using this class
 * 
 * @author Hakim Belhaouari
 *
 */
public class TamagoFreshVar {
	private Hashtable<String, Integer> mem;
	private int unknown;
	
	public static final TamagoFreshVar Default = new TamagoFreshVar();
	
	/**
	 * 
	 */
	public TamagoFreshVar() {
		mem = new Hashtable<String, Integer>();
		unknown = 0;
	}

	public void flush() {
		mem.clear();
		unknown = 0;
	}
	
	public String getName(String prefix) {
		if(prefix == null || prefix.length() == 0 || prefix.trim().length() == 0)
			return getName();
		if(mem.containsKey(prefix)) {
			int val = mem.get(prefix).intValue();
			val++;
			mem.put(prefix, new Integer(val));
			return prefix+val;
		}
		else {
			mem.put(prefix, new Integer(0));
			return prefix+"0";
		}
	}

	public String getName() {
		return "__tamago_fresh_var"+(unknown++);
	}
}
