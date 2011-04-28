package tamagocc.impl.test;

import tamagocc.api.TExpression;
import tamagocc.impl.TIBool;
import tamagocc.impl.TINot;
import tamagocc.impl.TIVariable;
import junit.framework.TestCase;

public class TestTINot extends TestCase {

	public void testGetTerm() {
		TIBool b = new TIBool(true);
		
		TINot not = new TINot(b);
		
		assertNotNull(not.getTerm());
		assertEquals(not.getTerm(), b);
	}

	public void testGetCat() {
		TIBool b = new TIBool(true);
		TINot not = new TINot(b);
		
		assertEquals(not.getCat(),TExpression.ExprType.NOT);
	}

	public void testEqualsObject() {
		TIBool b = new TIBool(true);
		TIBool bf = new TIBool(false);
		TIVariable var = new TIVariable("var");
		
		TINot n1 = new TINot(b);
		TINot n2 = new TINot(b);
		TINot n3 = new TINot(bf);
		TINot n4 = new TINot(bf);
		TINot n5 = new TINot(var);
		
		assertEquals(n1, n2);
		assertEquals(n2, n1);
		
		assertEquals(n3, n4);
		assertEquals(n4, n3);
		
		assertFalse(n1.equals(n3));
		assertFalse(n1.equals(n5));
		
		assertFalse(n3.equals(n1));
		assertFalse(n3.equals(n5));
		
		assertFalse(n5.equals(n1));
		assertFalse(n5.equals(n3));
	}

}
