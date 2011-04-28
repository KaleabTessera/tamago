/**
 * 
 */
package tamagocc.util;

import java.util.ArrayList;
import java.util.Iterator;

import tamagocc.generic.api.GMethod;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class TamagoCCMethodList{

	private ArrayList<GMethod> liste;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4861881668873666582L;

	/**
	 * 
	 */
	public TamagoCCMethodList() {
		super();
		liste = new ArrayList<GMethod>();
	}
	
	public boolean add(GMethod method) {
		if(!contains(method))
			return liste.add(method);
		else
			return false;
	}

	public boolean contains(GMethod method) {
		Iterator<GMethod> ite = liste.iterator();
		while(ite.hasNext()) {
			GMethod m = (GMethod)ite.next();
			if(m.sameSignature(method))
				return true;
		}
		return false;
	}
	
	public GMethod get(int i) {
		return (GMethod)liste.get(i);
	}
	
	public int indexOf(GMethod method) {
		int i=0;
		Iterator<GMethod> ite = liste.iterator();
		while(ite.hasNext()) {
			GMethod m = (GMethod)ite.next();
			if(m.sameSignature(method))
				return i;
			i++;
		}
		return -1;
	}
	
	public int size() {
		return liste.size();
	}

	public Iterator<GMethod> iterator() {
		return liste.iterator();
	}
}
