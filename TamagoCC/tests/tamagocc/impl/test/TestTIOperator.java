package tamagocc.impl.test;

import java.util.ArrayList;

import tamagocc.api.TExpression;
import tamagocc.api.TOpeName;
import tamagocc.impl.TIBool;
import tamagocc.impl.TIInteger;
import tamagocc.impl.TIOperator;
import tamagocc.util.NilIterator;
import junit.framework.TestCase;

public class TestTIOperator extends TestCase {

	public void testGetOperator() {
		ArrayList<TExpression> ope = new ArrayList<TExpression>();
		TIOperator op = new TIOperator(TOpeName.opAnd,ope);
		
		TIBool t = new TIBool(true);
		TIBool f = new TIBool(false);
		
		ope.add(t);
		ope.add(f);
		
		assertNotNull(op.getOperator());
		assertTrue(op.getOperator().equals(TOpeName.opAnd));
		assertFalse(op.getOperator().equals(TOpeName.opOr));
	}

	public void testGetOperands() {
		ArrayList<TExpression> ope = new ArrayList<TExpression>();
		TIBool t = new TIBool(true);
		TIBool f = new TIBool(false);
		ope.add(t);
		ope.add(f);
		TIOperator op = new TIOperator(TOpeName.opAnd,ope);
		
		assertNotNull(op.getOperands());
		assertTrue(NilIterator.areEqual(op.getOperands(), ope.iterator()));
	}

	public void testEqualsObject() {
		
		ArrayList<TExpression> ope = new ArrayList<TExpression>();
		TIBool t = new TIBool(true);
		TIBool f = new TIBool(false);
		ope.add(t);
		ope.add(f);
		
		TIOperator o1 = new TIOperator(TOpeName.opAnd,ope);
		TIOperator o2 = new TIOperator(TOpeName.opAnd,ope);
		
		assertEquals(o1, o2);
		
		ArrayList<TExpression> ope2 = new ArrayList<TExpression>();
		TIInteger i1 = new TIInteger(2);
		TIInteger i2 = new TIInteger(4);
		ope2.add(i1);
		ope2.add(i2);
		
		TIOperator o3 = new TIOperator(TOpeName.opPlus,ope2);
		assertFalse(o3.equals(o1));
	}

	public void testGetCat() {
		ArrayList<TExpression> ope = new ArrayList<TExpression>();
		TIBool t = new TIBool(true);
		TIBool f = new TIBool(false);
		ope.add(t);
		ope.add(f);
		
		TIOperator o1 = new TIOperator(TOpeName.opAnd,ope);
		assertEquals(o1.getCat(), TExpression.ExprType.OPERATOR);
	}

}
