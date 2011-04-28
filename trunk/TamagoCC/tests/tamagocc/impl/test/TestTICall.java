package tamagocc.impl.test;

import java.util.ArrayList;
import java.util.Collection;

import tamagocc.api.TExpression;
import tamagocc.impl.TIBool;
import tamagocc.impl.TICall;
import tamagocc.util.NilIterator;
import junit.framework.TestCase;

public class TestTICall extends TestCase {

	/*
	 * Test method for 'tamagocc.impl.TICall.getName()'
	 */
	public void testGetName() {
		String n1 = "foo";
		String n2 = "bar";
		
		Collection<TExpression> a1 = new ArrayList<TExpression>();
		Collection<TExpression> a2 = new ArrayList<TExpression>();
		a2.add(new TIBool(false));
		
		TICall c1 = new TICall(n1,a1);
		
		assertNotNull(c1);
		assertNotNull(c1.getName());
		assertEquals(n1,c1.getName());
		
		TICall c2 = new TICall(n2,a2);
		assertNotNull(c2);
		assertNotNull(c2.getName());
		assertEquals(n2,c2.getName());
		
	}

	/*
	 * Test method for 'tamagocc.impl.TICall.getArguments()'
	 */
	public void testGetArguments() {
		String n1 = "foo";
		String n2 = "bar";
		
		Collection<TExpression> a1 = new ArrayList<TExpression>();
		Collection<TExpression> a2 = new ArrayList<TExpression>();
		a2.add(new TIBool(false));
		
		TICall c1 = new TICall(n1,a1);
		
		assertNotNull(c1);
		assertNotNull(c1.getArguments());
		
		assertTrue(NilIterator.areEqual(c1.getArguments(),a1.iterator()));
		
		TICall c2 = new TICall(n2,a2);
		assertNotNull(c2);
		assertNotNull(c2.getArguments());
		
		assertTrue(NilIterator.areEqual(c2.getArguments(),a2.iterator()));
	}

	/*
	 * Test method for 'tamagocc.impl.TICall.getCat()'
	 */
	public void testGetCat() {
		String n1 = "foo";
		String n2 = "bar";
		
		Collection<TExpression> a1 = new ArrayList<TExpression>();
		Collection<TExpression> a2 = new ArrayList<TExpression>();
		a2.add(new TIBool(false));
		
		TICall c1 = new TICall(n1,a1);
		TICall c2 = new TICall(n2,a2);
		
		assertEquals(TExpression.ExprType.CALL,c1.getCat());
		assertEquals(TExpression.ExprType.CALL,c2.getCat());
	}

	/*
	 * Test method for 'tamagocc.impl.TICall.equals(Object)'
	 */
	public void testEqualsObject() {
		String n1 = "foo";
		String n2 = "bar";
		
		Collection<TExpression> a1 = new ArrayList<TExpression>();
		Collection<TExpression> a2 = new ArrayList<TExpression>();
		a2.add(new TIBool(false));
		
		TICall c1 = new TICall(n1,a1);
		TICall c3 = new TICall(n1,a1);
		TICall c2 = new TICall(n2,a2);
		TICall c4 = new TICall(n2,a2);
		TICall c5 = new TICall(n1,a2);
		
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
