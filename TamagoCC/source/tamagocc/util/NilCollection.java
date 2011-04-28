package tamagocc.util;

import java.util.Collection;
import java.util.Iterator;

public class NilCollection<T> implements Collection<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 160296714192539672L;

	public NilCollection() {
		super();
	}

	public boolean add(T o) {
		return false;
	}
		
	public boolean addAll(Collection<? extends T> c) {
		return false;
	}

	public int size() {
		return 0;
	}

	public boolean isEmpty() {
		return true;
	}

	public boolean contains(Object arg0) {
		return false;
	}

	public Iterator<T> iterator() {
		return new NilIterator<T>();
	}

	public Object[] toArray() {
		return new Object[] { };
	}

	public <R> R[] toArray(R[] arg0) {
		return arg0;
	}

	public boolean remove(Object arg0) {
		return false;
	}

	public boolean containsAll(Collection<?> arg0) {
		return false;
	}

	public boolean removeAll(Collection<?> arg0) {
		return false;
	}

	public boolean retainAll(Collection<?> arg0) {
		return false;
	}

	public void clear() {
		
	}
	
}
