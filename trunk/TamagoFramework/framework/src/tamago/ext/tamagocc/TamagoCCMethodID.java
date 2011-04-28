/**
 * 
 */
package tamago.ext.tamagocc;

import java.util.Comparator;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCMethodID implements Comparable<TamagoCCMethodID>, Comparator<TamagoCCMethodID>{

	private String id;
	/**
	 * 
	 */
	public TamagoCCMethodID(String id) {
		this.id = id;
	}
	
	public String getID() {
		return id;
	}

	public int compareTo(TamagoCCMethodID o) {
		return id.compareTo(o.getID());
	}

	public int compare(TamagoCCMethodID o1, TamagoCCMethodID o2) {
		return o1.getID().compareTo(o2.getID());
	}

	public boolean equals(Object obj) {
		if (obj instanceof TamagoCCMethodID) {
			TamagoCCMethodID new_name = (TamagoCCMethodID) obj;
			return id.equals(new_name.getID());
		}
		return false;
	}
}
