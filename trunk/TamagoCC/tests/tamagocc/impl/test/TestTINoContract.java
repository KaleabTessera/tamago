package tamagocc.impl.test;

import tamagocc.api.TExpression;
import tamagocc.impl.TINoContract;
import junit.framework.TestCase;

public class TestTINoContract extends TestCase {

	public void testGetCat() {
		TINoContract nocontract = new TINoContract();
		
		assertNotNull(nocontract);
		assertEquals(nocontract.getCat(),TExpression.ExprType.NOCONTRACT);
	}

	public void testEqualsObject() {
		TINoContract n1 = new TINoContract();
		TINoContract n2 = new TINoContract();
		
		assertNotNull(n1);
		assertNotNull(n2);
		
		assertEquals(n1, n2);
		assertNotSame(n1, n2);
	}

}
