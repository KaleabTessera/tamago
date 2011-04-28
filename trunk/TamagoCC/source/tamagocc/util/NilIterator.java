package tamagocc.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NilIterator<T> implements Iterator<T> {

	public void remove() {
		throw new NoSuchElementException("NilIterator cannot contain elements");
	}

	public boolean hasNext() {
		return false;
	}

	public T next() {
		throw new NoSuchElementException("NilIterator cannot contain elements");
	}

	public static boolean areEqual(Iterator<?> i1,Iterator<?> i2) {
		boolean result = true;
		
		if((!i1.hasNext())&& (!i2.hasNext()))
			return true;
		
		while((i1.hasNext()&&i2.hasNext())) {
			if(!(i1.next().equals(i2.next())))
				return false;
		}
		
		if(i1.hasNext()||i2.hasNext())
			return false;
		else
			return result;
	}
	
}
