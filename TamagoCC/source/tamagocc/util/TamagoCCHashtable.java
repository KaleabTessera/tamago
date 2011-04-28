package tamagocc.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

/**
 * This class represents an alternative to the class Hashtable provided in the JDK.
 * Indeed, the provided Hashtable does not allow multi value for a specific key, whereas our
 * implementation allow it.
 * 
 * We use this class in particular to represent or/and make (optimised) computation
 * on the service behavior, in the inheritance.
 *  
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCHashtable<K,V> {

	private Hashtable<K, ArrayList<V>> table;
	private boolean allowduplicatevalues;
	
	/**
	 * Default constructor of our hashtable. 
	 */
	public TamagoCCHashtable() {
		table = new Hashtable<K, ArrayList<V>>();
		allowduplicatevalues = false;
	}

	/**
	 * Constructor which allow an initial capacity.
	 * @param initialCapacity
	 */
	public TamagoCCHashtable(int initialCapacity) {
		table = new Hashtable<K, ArrayList<V>>(initialCapacity);
		allowduplicatevalues = false;
	}
	
	/**
	 * Return an enumeration of all keys
	 * @return Return an enumeration of all keys 
	 */
	public Enumeration<K> keys() {
		return table.keys();
	}
	
	/**
	 * Return a set of all keys of the inner structure
	 * @return Return a set of all keys of the inner structure
	 */
	public Set<K> keySet() {
		return table.keySet();
	}
	
	/**
	 * This function allow to the user to input an association in the inner structure.
	 * As usual, the key should implement the equals method.
	 * 
	 * @param key : represent the key of the association 
	 * @param value : correspond to the value to be associated to the key
	 * @return Return the <b>true</b> if the association has been added, else <b>false</b>
	 */
	public boolean put(K key,V value) {
		if(table.containsKey(key)) {
			ArrayList<V> al = table.get(key);
			if(allowduplicatevalues)
				return al.add(value);
			else if(!al.contains(value)) {
				return al.add(value);
			}
		}
		else {
			ArrayList<V> al = new ArrayList<V>(2);
			table.put(key, al);
			return al.add(value);
		}
		return false;
	}
	
	/**
	 * This function returns an array of all values contained in the inner structure
	 * @return Return an object which contains all values of the inner structure
	 */	
	public Iterable<V> values() {
		ArrayList<V> values = new ArrayList<V>();
		Collection<ArrayList<V>> av =  table.values();
		for (ArrayList<V> object : av) {
			values.addAll(object);
		}
		return values;
	}
	
	
	/**
	 * Return all associates values for a specific keys. In the case of the key does not exist
	 * in the structure, the function returns an empty Iterable.
	 * @param key : indicate the key of all associate value
	 * @return Return an iterable object which contains all associate values (potentially empty).
	 */
	public Iterable<V> get(K key) {
		if(table.containsKey(key))
			return table.get(key);
		else
			return new ArrayList<V>(0);
	}

	/**
	 * This function indicates if the key already exists in the structure
	 * @param key : correspond to the key which we would test
	 * @return Return <b>true</b> if the key is defined in the inner structure, else <b>false</b>.
	 */
	public boolean containsKey(K key) {
		return table.containsKey(key);
	}
	
	/**
	 * Indicates if the value exists somewhere in the inner structure.
	 * @param value : value to search in the structure
	 * @return Return <b>true</b> if the value exists in the structure, else <b>false</b> 
	 */
	public boolean containsValue(V value) {
		for (ArrayList<V> v : table.values()) {
			if(v.contains(value))
				return true;
		}
		return false;
	}
	
	/**
	 * Indicates if the value already exists for the specific key. 
	 * @param key : the key scope
	 * @param value : the value to search
	 * @return Return <b>true</b> if the value exists for the specific key in the structure, else <b>false</b>
	 */
	public boolean containsValue(K key,V value) {
		if(table.containsKey(key))
			return table.get(key).contains(value);
		else
			return false;
	}
	
	/**
	 * Indicates if the inner structure should test if duplicate values for a same key can be specified
	 * in the case of redundancy.
	 * @return Return true if the structure allow duplicates values, else false.
	 */
	public boolean getAllowDuplicateValues() {
		return allowduplicatevalues;
	}
	
	/**
	 * Use this method to force the behavior of this object to allow or forbidden duplicates values for a
	 * specific key.
	 * @param b : corresponds to the future value of the property.
	 */
	public void setAllowDuplicateValues(boolean b) {
		allowduplicatevalues = b;
	}
	
	public V getLast(K key) {
		if(table.containsKey(key))
			return table.get(key).get(table.get(key).size()-1);
		else
			return null;
	}
	
	public V removeLast(K key) {
		if(table.containsKey(key)) {
			ArrayList<V> al = table.get(key);
			V v = al.remove(al.size()-1);
			if(al.size() == 0) {
				table.remove(key);
			}
			return v;
		}
		else
			return null;
	}
}
