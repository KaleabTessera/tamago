package tamagocc.impl.test;

import tamagocc.impl.TIAllow;
import junit.framework.TestCase;

public class TestTIAllow extends TestCase {

	public static TIAllow allow;
	
	/*
	 * Test method for 'tamagocc.impl.TIAllow.TIAllow(String)'
	 */
	public void testTIAllow() {
		allow = new TIAllow("id");
		assertNotNull("Test fail : TIAllow constructor with 'id'",allow.getMethod());
	}

	/*
	 * Test method for 'tamagocc.impl.TIAllow.getMethod()'
	 */
	public void testGetMethod() {
		assertEquals("Test fail : TIAllow getMethod with 'id'","id",allow.getMethod());
	}
	
	public void testEquals() {
		TIAllow a1 = new TIAllow("toto");
		TIAllow a2 = new TIAllow("ttiti");
		TIAllow a3 = new TIAllow("toto");
		
		assertEquals(a1,a3);
		
		assertFalse(a2.equals(a1));
		assertFalse(a2.equals(a3));
		assertFalse(a1.equals(a2));
		assertFalse(a3.equals(a2));
	}

}
