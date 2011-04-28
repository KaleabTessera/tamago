package tamagocc.impl.test;

import tamagocc.impl.TIBool;
import tamagocc.impl.TICategory;
import tamagocc.impl.TIInvariant;
import junit.framework.TestCase;

public class TestTIInvariant extends TestCase {
	/*
	 * Test method for 'tamagocc.impl.TICondition.getExpression()'
	 */
	public void testGetExpression() {
		TICategory cat1 = new TICategory("toto");
		String m1 = "none";
		TIBool bt = new TIBool(true);
		
		TICategory cat2 = new TICategory("titi");
		String m2 ="";
		TIBool bf = new TIBool(false);
		
		TIInvariant c1 = new TIInvariant(bt,cat1,m1);
		TIInvariant c2 = new TIInvariant(bf,cat2,m2);
		
		assertNotNull(c1);
		assertNotNull(c1.getExpression());
		assertEquals(c1.getExpression(),bt);
		
		assertNotNull(c2);
		assertNotNull(c2.getExpression());
		assertEquals(c2.getExpression(),bf);
	}

	/*
	 * Test method for 'tamagocc.impl.TIInvariant.getCategory()'
	 */
	public void testGetCategory() {
		TICategory cat1 = new TICategory("toto");
		String m1 = "none";
		TIBool bt = new TIBool(true);
		
		TICategory cat2 = new TICategory("titi");
		String m2 ="";
		TIBool bf = new TIBool(false);
		
		TIInvariant c1 = new TIInvariant(bt,cat1,m1);
		TIInvariant c2 = new TIInvariant(bf,cat2,m2);
		
		assertNotNull(c1.getCategory());
		assertEquals(c1.getCategory(),cat1);
		
		assertNotNull(c2.getCategory());
		assertEquals(c2.getCategory(),cat2);
		
	}

	/*
	 * Test method for 'tamagocc.impl.TIInvariant.getMessage()'
	 */
	public void testGetMessage() {
		TICategory cat1 = new TICategory("toto");
		String m1 = "none";
		TIBool bt = new TIBool(true);
		
		TICategory cat2 = new TICategory("titi");
		String m2 ="";
		TIBool bf = new TIBool(false);
		
		TIInvariant c1 = new TIInvariant(bt,cat1,m1);
		TIInvariant c2 = new TIInvariant(bf,cat2,m2);
		
		assertNotNull(c1.getMessage());
		assertEquals(c1.getMessage(),m1);
		
		assertNotNull(c2.getMessage());
		assertEquals(c2.getMessage(),m2);
	}

	/*
	 * Test method for 'tamagocc.impl.TIInvariant.equals(Object)'
	 */
	public void testEqualsObject() {
		TICategory cat1 = new TICategory("toto");
		String m1 = "none";
		TIBool bt = new TIBool(true);
		
		TICategory cat2 = new TICategory("titi");
		String m2 ="";
		TIBool bf = new TIBool(false);
		
		TICategory cat = new TICategory("tata");
		
		TIInvariant c1 = new TIInvariant(bt,cat1,m1);
		TIInvariant c3 = new TIInvariant(bt,cat1,m1);
		TIInvariant c2 = new TIInvariant(bf,cat2,m2);
		TIInvariant c4 = new TIInvariant(bf,cat2,m2);
		TIInvariant c5 = new TIInvariant(bf,cat,m1);
		
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
