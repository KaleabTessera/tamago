package tamagocc.impl.test;

import tamagocc.impl.TIBind;
import junit.framework.TestCase;

public class TestTIBind extends TestCase {

	/*
	 * Test method for 'tamagocc.impl.TIBind.getProvider()'
	 */
	public void testGetProvider() {
		String provider = "provider";
		String requirer = "requirer";
		String label = "label";
		String service = "Service";
		String module ="module";
		
		TIBind b1 = new TIBind("provider","requirer","label");
		
		assertNotNull(b1);
		assertNotNull(b1.getProvider());	
		assertEquals(b1.getProvider(),provider);
		
		
		TIBind b2 = new TIBind(provider,requirer,label,service,module);
		
		assertNotNull(b2);
		assertNotNull(b2.getProvider());
		assertEquals(b2.getProvider(),provider);
		
		assertEquals(b2.getProvider(),b1.getProvider());
	}

	/*
	 * Test method for 'tamagocc.impl.TIBind.getRequirer()'
	 */
	public void testGetRequirer() {
		String provider = "provider";
		String requirer = "requirer";
		String label = "label";
		String service = "Service";
		String module ="module";
		
		TIBind b1 = new TIBind("provider","requirer","label");
		
		assertNotNull(b1);
		assertNotNull(b1.getRequirer());
		assertEquals(b1.getRequirer(),requirer);
				
		TIBind b2 = new TIBind(provider,requirer,label,service,module);
		
		assertNotNull(b2);
		assertNotNull(b2.getRequirer());
		assertEquals(b2.getRequirer(),requirer);
		
		assertEquals(b2.getRequirer(),b1.getRequirer());
	}

	/*
	 * Test method for 'tamagocc.impl.TIBind.getLabel()'
	 */
	public void testGetLabel() {
		String provider = "provider";
		String requirer = "requirer";
		String label = "label";
		String service = "Service";
		String module ="module";
		
		TIBind b1 = new TIBind("provider","requirer","label");
		
		assertNotNull(b1);
		assertNotNull(b1.getLabel());
		assertEquals(b1.getLabel(),label);
		
		TIBind b2 = new TIBind(provider,requirer,label,service,module);
		
		assertNotNull(b2);
		assertNotNull(b2.getLabel());
		assertEquals(b2.getLabel(),label);
		
		assertEquals(b2.getLabel(),b1.getLabel());
		
	}

	/*
	 * Test method for 'tamagocc.impl.TIBind.forcedService()'
	 */
	public void testForcedService() {
		String provider = "provider";
		String requirer = "requirer";
		String label = "label";
		String service = "Service";
		String module ="module";
		
		TIBind b1 = new TIBind(provider,requirer,label);
		assertNotNull(b1);
		TIBind b2 = new TIBind(provider,requirer,label,service,module);
		assertNotNull(b2);
		
		assertTrue(b2.forcedService());
		
		assertFalse(b1.forcedService());
	}

	/*
	 * Test method for 'tamagocc.impl.TIBind.getServiceName()'
	 */
	public void testGetServiceName() {
		String provider = "provider";
		String requirer = "requirer";
		String label = "label";
		String empty = "";
		String service = "Service";
		String module ="module";
		
		TIBind b1 = new TIBind("provider","requirer","label");
		
		assertNotNull(b1);
		assertNotNull(b1.getServiceName());
		
		assertEquals(b1.getServiceName(),empty);
		
		
		TIBind b2 = new TIBind(provider,requirer,label,service,module);
		
		assertNotNull(b2);
		assertNotNull(b2.getServiceName());
		assertEquals(b2.getServiceName(),service);
		
		assertFalse(b2.getServiceName().equals(b1.getServiceName()));
	}

	/*
	 * Test method for 'tamagocc.impl.TIBind.getServiceModule()'
	 */
	public void testGetServiceModule() {
		String provider = "provider";
		String requirer = "requirer";
		String label = "label";
		String empty = "";
		String service = "Service";
		String module ="module";
		
		TIBind b1 = new TIBind("provider","requirer","label");
		
		assertNotNull(b1);
		assertNotNull(b1.getServiceModule());
		assertEquals(b1.getServiceModule(),empty);
		
		
		TIBind b2 = new TIBind(provider,requirer,label,service,module);
		
		assertNotNull(b2);
		assertNotNull(b2.getServiceModule());
		assertEquals(b2.getServiceModule(),module);
		
		assertFalse(b2.getServiceModule().equals(b1.getServiceModule()));
	}

	/*
	 * Test method for 'tamagocc.impl.TIBind.equals(Object)'
	 */
	public void testEqualsObject() {
		String provider = "provider";
		String requirer = "requirer";
		String label = "label";
		String service = "Service";
		String module ="module";
		
		TIBind b1 = new TIBind(provider,requirer,label);
		TIBind b3 = new TIBind(provider,requirer,label);
		TIBind b2 = new TIBind(provider,requirer,label,service,module);
		TIBind b4 = new TIBind(provider,requirer,label,service,module);

		assertFalse(b1.equals(b2));
		assertFalse(b2.equals(b1));
		
		assertEquals(b1,b3);
		assertEquals(b2,b4);		
	}

}
