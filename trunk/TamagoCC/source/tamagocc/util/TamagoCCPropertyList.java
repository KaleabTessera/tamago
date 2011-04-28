package tamagocc.util;

import java.util.ArrayList;
import java.util.Iterator;

import tamagocc.generic.api.GProperty;

/**
 * 
 * 
 * @author Hakim Belhaouari
 */
public class TamagoCCPropertyList {
	private ArrayList<GProperty> liste;

	/**
	 * 
	 */
	public TamagoCCPropertyList() {
		super();
		liste = new ArrayList<GProperty>();
	}
	
	public boolean add(GProperty property) {
		if(!contains(property))
			return liste.add(property);
		else
			return false;
	}

	public boolean contains(GProperty property) {
		return liste.contains(property);
	}
	
	public GProperty get(int i) {
		return (GProperty)liste.get(i);
	}
	
	public int indexOf(GProperty property) {
		return liste.indexOf(property);
	}

	public Iterator<GProperty> iterator() {
		return liste.iterator();
	}
}