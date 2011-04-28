package tamagocc.util.test;

import java.util.ArrayList;
import java.util.Iterator;

import tamagocc.util.NilCollection;
import junit.framework.TestCase;

public class TestNilCollection extends TestCase {

	public static NilCollection<String> nc;
	
	/*
	 * Test method for 'tamagocc.util.NilCollection.NilCollection()'
	 */
	public void testNilCollection() {
		nc = new NilCollection<String>();
		assertEquals(0,nc.size());
	}

	/*
	 * Test method for 'tamagocc.util.NilCollection.add(Object)'
	 */
	public void testAdd() {
		assertFalse(nc.add("toto"));
		assertEquals(0,nc.size());
	}

	/*
	 * Test method for 'tamagocc.util.NilCollection.addAll(Collection)'
	 */
	public void testAddAll() {
		ArrayList<String> al = new ArrayList<String>();
		al.add("toto");
		al.add("tutu");
		
		assertFalse(nc.addAll(al));
		assertEquals(0,nc.size());
	}

	/*
	 * Test method for 'tamagocc.util.NilCollection.size()'
	 */
	public void testSize() {
		assertEquals(0,nc.size());
	}

	/*
	 * Test method for 'tamagocc.util.NilCollection.isEmpty()'
	 */
	public void testIsEmpty() {
		assertTrue(nc.isEmpty());
	}

	/*
	 * Test method for 'tamagocc.util.NilCollection.contains(Object)'
	 */
	public void testContains() {
		assertFalse(nc.contains("toto"));
		assertFalse(nc.contains("tutu"));
	}

	/*
	 * Test method for 'tamagocc.util.NilCollection.iterator()'
	 */
	public void testIterator() {
		Iterator r = nc.iterator();
		assertNotNull(r);
		assertFalse(r.hasNext());
	}

	/*
	 * Test method for 'tamagocc.util.NilCollection.toArray()'
	 */
	public void testToArray() {
		Object[] r = nc.toArray();
		assertNotNull(r);
		assertEquals(0,r.length);
	}

	/*
	 * Test method for 'tamagocc.util.NilCollection.toArray(Object[])'
	 */
	public void testToArrayObjectArray() {
		Object[] p = new Object[] { "tata", "tete" , "titi" };
		Object[] r = nc.toArray(p);
		
		assertNotNull(r);
		assertEquals(p.length,r.length);
	}

	/*
	 * Test method for 'tamagocc.util.NilCollection.remove(Object)'
	 */
	public void testRemove() {
		ArrayList<String> al = new ArrayList<String>();
		al.add("toto");
		al.add("tutu");
		
		assertFalse(nc.remove("toto"));
		assertFalse(nc.remove("tutu"));
	}

	/*
	 * Test method for 'tamagocc.util.NilCollection.containsAll(Collection)'
	 */
	public void testContainsAll() {
		String toto = "toto";
		String tutu = "tutu";
		
		ArrayList<String> al = new ArrayList<String>();
		al.add(toto);
		al.add(tutu);
		
		nc.add(toto);
		nc.add(tutu);
		
		assertFalse(nc.containsAll(al));
	}

	/*
	 * Test method for 'tamagocc.util.NilCollection.removeAll(Collection)'
	 */
	public void testRemoveAll() {
		String toto = "toto";
		String tutu = "tutu";
		
		ArrayList<String> al = new ArrayList<String>();
		al.add(toto);
		al.add(tutu);
		
		nc.add(toto);
		nc.add(tutu);
		
		assertFalse(nc.removeAll(al));
	}

	/*
	 * Test method for 'tamagocc.util.NilCollection.retainAll(Collection)'
	 */
	public void testRetainAll() {
		String toto = "toto";
		String tutu = "tutu";
		
		ArrayList<String> al = new ArrayList<String>();
		al.add(toto);
		al.add(tutu);
		
		nc.add(toto);
		nc.add(tutu);
		
		assertFalse(nc.retainAll(al));
	}

	/*
	 * Test method for 'tamagocc.util.NilCollection.clear()'
	 */
	public void testClear() {
		nc.clear();
		assertEquals(0,nc.size());
	}

}
