package tamagocc.impl.test;

import tamagocc.api.TExpression;
import tamagocc.impl.TIBool;
import junit.framework.TestCase;

public class TestTIBool extends TestCase {

	/*
	 * Test method for 'tamagocc.impl.TIBool.equals(Object)'
	 */
	public void testEqualsObject() {
		TIBool b2 = new TIBool(false);
		TIBool b1 = new TIBool(true);
		TIBool b4 = new TIBool(false);
		TIBool b3 = new TIBool(true);
		
		assertEquals(b1,b3);
		assertEquals(b2,b4);
		
		assertEquals(b2,b2);
		
		assertFalse(b2.equals(b1));
		assertFalse(b3.equals(b4));
	}

	/*
	 * Test method for 'tamagocc.impl.TIBool.getValue()'
	 */
	public void testGetValue() {
		TIBool b1 = new TIBool(true);
		assertNotNull(b1);
		assertTrue(b1.getValue());
		
		TIBool b2 = new TIBool(false);
		assertNotNull(b2);
		assertFalse(b2.getValue());
	}

	/*
	 * Test method for 'tamagocc.impl.TIBool.getCat()'
	 */
	public void testGetCat() {
		TIBool b2 = new TIBool(false);
		TIBool b1 = new TIBool(true);
		
		assertEquals(TExpression.ExprType.BOOL,b2.getCat());
		assertEquals(TExpression.ExprType.BOOL,b1.getCat());
	}

}
