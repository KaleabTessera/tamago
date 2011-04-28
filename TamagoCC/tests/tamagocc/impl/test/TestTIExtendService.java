package tamagocc.impl.test;

import java.util.ArrayList;

import tamagocc.api.TExtendService;
import tamagocc.api.TImplements;
import tamagocc.api.TInvariant;
import tamagocc.api.TMethod;
import tamagocc.api.TNamespace;
import tamagocc.api.TProperty;
import tamagocc.api.TService;
import tamagocc.api.TState;
import tamagocc.api.TTransition;
import tamagocc.api.TType;
import tamagocc.impl.TIBehavior;
import tamagocc.impl.TIIncludeService;
import tamagocc.impl.TIRefineService;
import tamagocc.impl.TIService;
import junit.framework.TestCase;

public class TestTIExtendService extends TestCase {

	/*
	 * Test method for 'tamagocc.impl.TIExtendService.getServiceName()'
	 */
	public void testGetServiceName() {
		String s1 = "service";
		String m1 = "module";
		
		TIRefineService refine = new TIRefineService(s1,m1,null);
		TIIncludeService include = new TIIncludeService(s1,m1,null);
		
		assertNotNull(refine.getServiceName());
		assertNotNull(include.getServiceName());
		
		assertEquals(refine.getServiceName(),s1);
		assertEquals(include.getServiceName(),s1);

	}

	/*
	 * Test method for 'tamagocc.impl.TIExtendService.getModuleService()'
	 */
	public void testGetModuleService() {
		String s1 = "service";
		String m1 = "module";
		
		TIRefineService refine = new TIRefineService(s1,m1,null);
		TIIncludeService include = new TIIncludeService(s1,m1,null);
		
		assertNotNull(refine.getModuleService());
		assertNotNull(include.getModuleService());
		
		assertEquals(refine.getModuleService(),m1);
		assertEquals(include.getModuleService(),m1);
	}

	/*
	 * Test method for 'tamagocc.impl.TIExtendService.getService()'
	 */
	public void testGetService() {
		String s1 = "service";
		String m1 = "module";
		
		TService service = new TIService(s1,m1,"",
				new ArrayList<TMethod>(),
				new ArrayList<TProperty>(),
				new ArrayList<TInvariant>(),
				new TIBehavior(new ArrayList<TState>(),new ArrayList<TTransition>(),""),
				new ArrayList<TExtendService>(),
				new ArrayList<TImplements>(),
				new ArrayList<TNamespace>(),
				new ArrayList<TType>());
		
		TIRefineService refine = new TIRefineService(s1,m1,service);
		TIRefineService r2 = new TIRefineService(s1,m1,null);
		
		TIIncludeService include = new TIIncludeService(s1,m1,service);
		TIIncludeService i2 = new TIIncludeService(s1,m1,null);
		
		assertNotNull(refine.getService());
		assertNotNull(include.getService());
		
		assertNull(r2.getService());
		assertNull(i2.getService());
		
		assertEquals(refine.getService(),service);
		assertEquals(include.getService(),service);
	}

	/*
	 * Test method for 'tamagocc.impl.TIExtendService.equals(Object)'
	 */
	public void testEqualsObject() {
		String s1 = "service";
		String m1 = "module";
		
		TService service = new TIService(s1,m1,"",new ArrayList<TMethod>(),
				new ArrayList<TProperty>(),new ArrayList<TInvariant>(),
				new TIBehavior(new ArrayList<TState>(),new ArrayList<TTransition>(),""),
				new ArrayList<TExtendService>(),new ArrayList<TImplements>(),
				new ArrayList<TNamespace>(), new ArrayList<TType>());
		
		TIRefineService refine1 = new TIRefineService(s1,m1,service);
		TIRefineService refine2 = new TIRefineService(s1,m1,service);
		TIRefineService r1 = new TIRefineService(s1,m1,null);
		TIRefineService r2 = new TIRefineService(s1,m1,null);
		
		TIIncludeService include1 = new TIIncludeService(s1,m1,service);
		TIIncludeService include2 = new TIIncludeService(s1,m1,service);
		TIIncludeService i1 = new TIIncludeService(s1,m1,null);
		TIIncludeService i2 = new TIIncludeService(s1,m1,null);
		
		
		assertEquals(refine1,refine2);
		assertTrue(refine1.equals(r1));
		assertFalse(refine1.equals(include1));
		assertFalse(refine1.equals(i1));
		
		assertEquals(r1,r2);
		assertFalse(r1.equals(include1));
		assertFalse(r1.equals(i1));
		
		assertEquals(include1,include2);
		assertTrue(include1.equals(i1));
		assertFalse(include1.equals(refine2));
		assertFalse(include1.equals(r2));
		
		assertEquals(i1,i2);
		assertTrue(i1.equals(include1));
		assertFalse(i1.equals(refine2));
		assertFalse(i1.equals(r2));
	}

}
