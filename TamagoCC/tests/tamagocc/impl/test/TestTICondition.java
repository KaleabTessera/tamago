package tamagocc.impl.test;

import tamagocc.impl.TIBool;
import tamagocc.impl.TICategory;
import tamagocc.impl.TICondition;
import junit.framework.TestCase;

public class TestTICondition extends TestCase {

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
		
		TICondition c1 = new TICondition(bt,cat1,m1);
		TICondition c2 = new TICondition(bf,cat2,m2);
		
		assertNotNull(c1);
		assertNotNull(c1.getExpression());
		assertEquals(c1.getExpression(),bt);
		
		assertNotNull(c2);
		assertNotNull(c2.getExpression());
		assertEquals(c2.getExpression(),bf);
	}

	/*
	 * Test method for 'tamagocc.impl.TICondition.getCategory()'
	 */
	public void testGetCategory() {
		TICategory cat1 = new TICategory("toto");
		String m1 = "none";
		TIBool bt = new TIBool(true);
		
		TICategory cat2 = new TICategory("titi");
		String m2 ="";
		TIBool bf = new TIBool(false);
		
		TICondition c1 = new TICondition(bt,cat1,m1);
		TICondition c2 = new TICondition(bf,cat2,m2);
		
		assertNotNull(c1.getCategory());
		assertEquals(c1.getCategory(),cat1);
		
		assertNotNull(c2.getCategory());
		assertEquals(c2.getCategory(),cat2);
		
	}

	/*
	 * Test method for 'tamagocc.impl.TICondition.getMessage()'
	 */
	public void testGetMessage() {
		TICategory cat1 = new TICategory("toto");
		String m1 = "none";
		TIBool bt = new TIBool(true);
		
		TICategory cat2 = new TICategory("titi");
		String m2 ="";
		TIBool bf = new TIBool(false);
		
		TICondition c1 = new TICondition(bt,cat1,m1);
		TICondition c2 = new TICondition(bf,cat2,m2);
		
		assertNotNull(c1.getMessage());
		assertEquals(c1.getMessage(),m1);
		
		assertNotNull(c2.getMessage());
		assertEquals(c2.getMessage(),m2);
	}

	/*
	 * Test method for 'tamagocc.impl.TICondition.equals(Object)'
	 */
	public void testEqualsObject() {
		TICategory cat1 = new TICategory("toto");
		String m1 = "none";
		TIBool bt = new TIBool(true);
		
		TICategory cat2 = new TICategory("titi");
		String m2 ="";
		TIBool bf = new TIBool(false);
		
		TICategory cat = new TICategory("tata");
		
		TICondition c1 = new TICondition(bt,cat1,m1);
		TICondition c3 = new TICondition(bt,cat1,m1);
		TICondition c2 = new TICondition(bf,cat2,m2);
		TICondition c4 = new TICondition(bf,cat2,m2);
		TICondition c5 = new TICondition(bf,cat,m1);
		
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
