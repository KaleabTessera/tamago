package tamagocc.impl.test;

import tamagocc.impl.TIDefinition;
import junit.framework.TestCase;

public class TestTIDefinition extends TestCase {
	/*
	 * Test method for 'tamagocc.impl.TIDefinition.getComponentName()'
	 */
	public void testGetComponentName() {
		String type = "type";
		String label = "label";
		String module = "module";
		String percolator = "percolator";
		
		TIDefinition d1 = new TIDefinition(type,module,label);
		TIDefinition d2 = new TIDefinition(type,module,label,percolator);
		
		assertNotNull(d1.getComponentName());
		assertEquals(d1.getComponentName(),type);
		
		assertNotNull(d2.getComponentName());
		assertEquals(d2.getComponentName(),type);
	}

	/*
	 * Test method for 'tamagocc.impl.TIDefinition.getComponentModule()'
	 */
	public void testGetComponentModule() {
		String type = "type";
		String label = "label";
		String module = "module";
		String percolator = "percolator";
		
		TIDefinition d1 = new TIDefinition(type,module,label);
		TIDefinition d2 = new TIDefinition(type,module,label,percolator);
		
		assertNotNull(d1.getComponentModule());
		assertEquals(d1.getComponentModule(),module);
		
		assertNotNull(d2.getComponentModule());
		assertEquals(d2.getComponentModule(),module);
	}

	/*
	 * Test method for 'tamagocc.impl.TIDefinition.equals(Object)'
	 */
	public void testEqualsObject() {
		String type = "type";
		String label = "label";
		String module = "module";
		String percolator = "percolator";
		
		TIDefinition c1 = new TIDefinition(type,module,label);
		TIDefinition c3 = new TIDefinition(type,module,label);
		TIDefinition c2 = new TIDefinition(type,module,label,percolator);
		TIDefinition c4 = new TIDefinition(type,module,label,percolator);
		
		TIDefinition c5 = new TIDefinition("commposant",module,label,percolator);
		
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

	/*
	 * Test method for 'tamagocc.impl.TIDefinition.getComponentLabel()'
	 */
	public void testGetComponentLabel() {
		String type = "type";
		String label = "label";
		String module = "module";
		String percolator = "percolator";
		
		TIDefinition d1 = new TIDefinition(type,module,label);
		TIDefinition d2 = new TIDefinition(type,module,label,percolator);
		
		assertNotNull(d1.getComponentLabel());
		assertEquals(d1.getComponentLabel(),label);
		
		assertNotNull(d2.getComponentLabel());
		assertEquals(d2.getComponentLabel(),label);
	}

	/*
	 * Test method for 'tamagocc.impl.TIDefinition.getPercolator()'
	 */
	public void testGetPercolator() {
		String type = "type";
		String label = "label";
		String module = "module";
		String percolator = "percolator";
		String empty = "";
		
		TIDefinition d1 = new TIDefinition(type,module,label);
		TIDefinition d2 = new TIDefinition(type,module,label,percolator);
		
		assertNotNull(d1.getPercolator());
		assertEquals(d1.getPercolator(),empty);
		
		assertNotNull(d2.getPercolator());
		assertEquals(d2.getPercolator(),percolator);
	}

}
