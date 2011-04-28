package tamagocc.util.test;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Vector;

import tamagocc.util.NilIterator;
import junit.framework.TestCase;

public class TestNilIterator extends TestCase {

	public static NilIterator ni = new NilIterator();
	
	/*
	 * Test method for 'tamagocc.util.NilIterator.remove()'
	 */
	public void testRemove() {
		try {
			ni.remove();
			assertFalse(true);
		}
		catch(NoSuchElementException nsee) {
			assertTrue(true);
		}
		catch(Exception e) {
			assertFalse(true);
		}
	}

	/*
	 * Test method for 'tamagocc.util.NilIterator.hasNext()'
	 */
	public void testHasNext() {
		assertFalse(ni.hasNext());
	}

	/*
	 * Test method for 'tamagocc.util.NilIterator.next()'
	 */
	public void testNext() {
		try {
			ni.next();
			assertFalse(true);
		}
		catch(NoSuchElementException nsee) {
			assertTrue(true);
		}
		catch(Exception e) {
			assertFalse(true);
		}
	}

	/*
	 * Test method for 'tamagocc.util.NilIterator.areEqual(Iterator, Iterator)'
	 */
	public void testAreEqual() {
		NilIterator ni2 = new NilIterator();
		assertTrue(NilIterator.areEqual(ni,ni2));
		
		ArrayList<String> al = new ArrayList<String>();
		al.add("toto");
		al.add("titi");
		
		assertFalse(NilIterator.areEqual(ni,al.iterator()));
		
		Vector<String> v = new Vector<String>(2);
		v.add("toto");
		v.add("titi");
		
		assertTrue(NilIterator.areEqual(al.iterator(),v.iterator()));
		
		v.add("tutu");
		
		assertFalse(NilIterator.areEqual(al.iterator(),v.iterator()));
		
		al.add("titi");
		assertFalse(NilIterator.areEqual(al.iterator(),v.iterator()));
		
		al.remove(al.size()-1);
		al.add("tutu");
		assertTrue(NilIterator.areEqual(al.iterator(),v.iterator()));
	}

}
