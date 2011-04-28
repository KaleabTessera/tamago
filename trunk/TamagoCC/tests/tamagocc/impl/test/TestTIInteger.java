package tamagocc.impl.test;

import tamagocc.api.TExpression;
import tamagocc.impl.TIInteger;
import junit.framework.TestCase;

public class TestTIInteger extends TestCase {
	/*
	 * Test method for 'tamagocc.impl.TIInteger.getValue()'
	 */
	public void testGetValue() {
		int v = 5;
		TIInteger i = new TIInteger(v);
		
		assertEquals(i.getValue(),v);
	}

	/*
	 * Test method for 'tamagocc.impl.TIInteger.getCat()'
	 */
	public void testGetCat() {
		int v = 5;
		TIInteger i = new TIInteger(v);
		
		assertEquals(i.getCat(),TExpression.ExprType.INT);
	}

	/*
	 * Test method for 'tamagocc.impl.TIInteger.equals(Object)'
	 */
	public void testEqualsObject() {
		int a = 4;
		int b = 5;
		TIInteger i1 = new TIInteger(a);
		TIInteger i2 = new TIInteger(a);
		TIInteger i3 = new TIInteger(b);
		TIInteger i4 = new TIInteger(b);
		
		assertEquals(i1,i2);
		assertFalse(i1.equals(i3));
		
		assertEquals(i3,i4);
		assertFalse(i3.equals(i2));
	}

}
