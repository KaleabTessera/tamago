package tamagocc.impl.test;

import tamagocc.impl.TIExport;
import tamagocc.impl.TIVariable;
import junit.framework.TestCase;

public class TestTIExport extends TestCase {
	/*
	 * Test method for 'tamagocc.impl.TIExport.getService()'
	 */
	public void testGetService() {
		String service = "service";
		String module = "module";
		TIVariable var = new TIVariable("toto");
		
		TIExport c1 = new TIExport(service,module,var);
		
		assertNotNull(c1.getService());
		assertEquals(c1.getService(),service);
	}

	/*
	 * Test method for 'tamagocc.impl.TIExport.getProvider()'
	 */
	public void testGetProvider() {
		String service = "service";
		String module = "module";
		TIVariable var = new TIVariable("toto");
		
		TIExport c1 = new TIExport(service,module,var);
		
		assertNotNull(c1.getProvider());
		assertEquals(c1.getProvider(),var);
	}

	/*
	 * Test method for 'tamagocc.impl.TIExport.getServiceModule()'
	 */
	public void testGetServiceModule() {
		String service = "service";
		String module = "module";
		TIVariable var = new TIVariable("toto");
		
		TIExport c1 = new TIExport(service,module,var);
		
		assertNotNull(c1.getServiceModule());
		assertEquals(c1.getServiceModule(),module);
	}

	/*
	 * Test method for 'tamagocc.impl.TIExport.equals(Object)'
	 */
	public void testEqualsObject() {
		String service = "service";
		String module = "module";
		TIVariable var = new TIVariable("toto");
		
		String s2= "s2";
		TIVariable v2 = new TIVariable("t");
		
		
		TIExport c1 = new TIExport(service,module,var);
		TIExport c3 = new TIExport(service,module,var);
		
		TIExport c2 = new TIExport(s2,module,v2);
		TIExport c4 = new TIExport(s2,module,v2);
		
		TIExport c5 = new TIExport(s2,module,var);
		
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
