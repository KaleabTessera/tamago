package tamagocc.impl.test;

import tamagocc.impl.TICategory;
import junit.framework.TestCase;

public class TestTICategory extends TestCase {

	/*
	 * Test method for 'tamagocc.impl.TICategory.getName()'
	 */
	public void testGetName() {
		String n1 = "toto";
		String n2 = "titi";
		
		TICategory c1 = new TICategory(n1);
		TICategory c2 = new TICategory(n2);
		
		assertNotNull(c1);
		assertNotNull(c1.getName());
		assertEquals(c1.getName(),n1);
		
		assertNotNull(c2);
		assertNotNull(c2.getName());
		assertEquals(c2.getName(),n2);
	}

	/*
	 * Test method for 'tamagocc.impl.TICategory.equals(Object)'
	 */
	public void testEqualsObject() {
		String n1 = "toto";
		String n2 = "titi";
		String n5 = "foo";
		
		TICategory c1 = new TICategory(n1);
		TICategory c3 = new TICategory(n1);
		TICategory c2 = new TICategory(n2);
		TICategory c4 = new TICategory(n2);
		TICategory c5 = new TICategory(n5);
		
		assertEquals(c1,c3);
		assertEquals(c2,c4);
		
		assertFalse(c1.equals(c2));
		assertFalse(c1.equals(c5));
		
		assertFalse(c2.equals(c1));
		assertFalse(c2.equals(c5));
		
		assertFalse(c5.equals(c3));
		assertFalse(c5.equals(c4));
		assertEquals(c5,c5);
		
	}

}
