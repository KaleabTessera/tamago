package tamagocc.util.test;

import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCFailCondition;
import junit.framework.TestCase;

public class TestTamagoCCFailCondition extends TestCase {

	/*
	 * Test method for 'tamagocc.util.TamagoCCFailCondition.getTypeCondition()'
	 */
	public void testGetTypeCondition() {
		TamagoCCFailCondition invcond = new TamagoCCFailCondition(TamagoCCFailCondition.INVARIANT,null);
		assertEquals(TamagoCCFailCondition.INVARIANT,invcond.getTypeCondition());
		
		
		TamagoCCFailCondition precond = new TamagoCCFailCondition(TamagoCCFailCondition.PRECONDITION,null);
		assertEquals(TamagoCCFailCondition.PRECONDITION,precond.getTypeCondition());
		
		TamagoCCFailCondition postcond = new TamagoCCFailCondition(TamagoCCFailCondition.POSTCONDITION,null);
		assertEquals(TamagoCCFailCondition.POSTCONDITION,postcond.getTypeCondition());
	}

	/*
	 * Test method for 'tamagocc.util.TamagoCCFailCondition.addFailedCondition(GCondition, AVariable)'
	 */
	public void testAddFailedCondition() {
		
	}

	/*
	 * Test method for 'tamagocc.util.TamagoCCFailCondition.generateThrowException()'
	 */
	public void testGenerateThrowException() {
		
		try {
			TamagoCCFailCondition invcond = new TamagoCCFailCondition(TamagoCCFailCondition.INVARIANT,null);
			assertNotNull(invcond.generateThrowException());
		}
		catch(TamagoCCException tc) {
			assertTrue(tc.getMessage(),false);
		}	
		
		try {
			TamagoCCFailCondition precond = new TamagoCCFailCondition(TamagoCCFailCondition.PRECONDITION,null);
			assertNotNull(precond.generateThrowException());
		}
		catch(TamagoCCException tc) {
			assertTrue(tc.getMessage(),false);
		}
		
		try {
			TamagoCCFailCondition postcond = new TamagoCCFailCondition(TamagoCCFailCondition.POSTCONDITION,null);
			assertNotNull(postcond.generateThrowException());
		}
		catch(TamagoCCException tc) {
			assertTrue(tc.getMessage(),false);
		}
		
	}

}
