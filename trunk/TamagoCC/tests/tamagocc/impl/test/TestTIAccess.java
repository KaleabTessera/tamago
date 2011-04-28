package tamagocc.impl.test;

import tamagocc.impl.TIAccess;
import junit.framework.TestCase;

public class TestTIAccess extends TestCase {
	
	/*
	 * Test method for 'tamagocc.impl.TIAccess.TIAccess(String)'
	 */
	public void testTIAccessString() {
		TIAccess access = new TIAccess("r");
		assertTrue("Test fail : Access 'r' -> canread",access.canRead());
		assertFalse("Test fail : Access 'r' -> canwrite",access.canWrite());
		
		access = new TIAccess("w");
		assertFalse("Test fail : Access 'w' -> canread",access.canRead());
		assertTrue("Test fail : Access 'w' -> canwrite",access.canWrite());
		
		access = new TIAccess("rw");
		assertTrue("Test fail : Access 'rw' -> canread",access.canRead());
		assertTrue("Test fail : Access 'rw' -> canwrite",access.canWrite());
		
		
		access = new TIAccess("toto");
		assertFalse("Test fail : Access '?' -> canread",access.canRead());
		assertFalse("Test fail : Access '?' -> canwrite",access.canWrite());
	}

	/*
	 * Test method for 'tamagocc.impl.TIAccess.TIAccess()'
	 */
	public void testTIAccess() {
		TIAccess access = new TIAccess();
		assertTrue("Test fail : Access default -> canread",access.canRead());
		assertFalse("Test fail : Access default -> canwrite",access.canWrite());
	}
	
	public void testEquals() {
		TIAccess a1 = new TIAccess();
		TIAccess a2 = new TIAccess("r");
		TIAccess a3 = new TIAccess("rw");
		TIAccess a4 = new TIAccess("rw");
		TIAccess a5 = new TIAccess("toto");
		
		assertEquals(a1,a2);
		assertEquals(a3,a4);
		
		assertFalse(a1.equals(a3));
		assertFalse(a1.equals(a4));
		assertFalse(a1.equals(a5));
		
		assertFalse(a2.equals(a3));
		assertFalse(a2.equals(a4));
		assertFalse(a2.equals(a5));
		
		assertFalse(a5.equals(a4));
		assertFalse(a5.equals(a3));
		
		assertFalse(a3.equals(a5));
		
	}
}
