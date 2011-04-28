package tamagocc.impl.test;

import tamagocc.api.TExpression;
import tamagocc.impl.TIAtPre;
import tamagocc.impl.TIBool;
import junit.framework.TestCase;

public class TestTIAtPre extends TestCase {

	/*
	 * Test method for 'tamagocc.impl.TIAtPre.getExpression()'
	 */
	public void testGetExpression() {
		TIAtPre pre = new TIAtPre(null);
		assertNotNull(pre);
		
		assertNull(pre.getExpression());
		
	}

	/*
	 * Test method for 'tamagocc.impl.TIAtPre.getCat()'
	 */
	public void testGetCat() {
		TIAtPre pre = new TIAtPre(null);
		assertEquals(TExpression.ExprType.ATPRE,pre.getCat());
		
		TIAtPre pre2 = new TIAtPre(new TIBool(false));
		assertEquals(TExpression.ExprType.ATPRE,pre2.getCat());
	}

	/*
	 * Test method for 'tamagocc.impl.TIAtPre.equals(Object)'
	 */
	public void testEqualsObject() {
		TIAtPre p1 = new TIAtPre(null);
		TIAtPre p2 = new TIAtPre(null);
		TIAtPre p3 = new TIAtPre(new TIBool(false));
		TIAtPre p4 = new TIAtPre(new TIBool(false));
		TIAtPre p5 = new TIAtPre(new TIBool(true));
		TIAtPre p6 = new TIAtPre(new TIBool(true));
		
		
		assertEquals(p3,p4);
		assertEquals(p5,p6);
		
		assertFalse(p3.equals(p1));
		assertFalse(p3.equals(p5));
		
		assertFalse(p5.equals(p2));
		assertFalse(p5.equals(p4));
	}

}
