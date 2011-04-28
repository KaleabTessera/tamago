package tamagocc.test;

import java.io.File;

import tamagocc.TamagoCC;
import tamagocc.TamagoCCParser;
import tamagocc.api.TTamagoEntity;
import junit.framework.TestCase;

public class TestTxtParser extends TestCase {

	/*
	 * Test method for 'tamagocc.TamagoCCParser.getDefaultParser()'
	 */
	public void testGetDefaultParser() {
		try {
			File wd = new File(System.getProperty("user.dir")+"/TamagoNS.xsd");
			System.out.println(wd.toURI().toString());
			TamagoCC.initialize(new String[] { "-setxsd", wd.toURI().toString()});
			
			TamagoCCParser parser = TamagoCCParser.getDefaultParser();
			assertNotNull(parser);
		}
		catch(Exception e) {
			assertTrue(e.getMessage(),false);
		}
		
		
	}

	/*
	 * Test method for 'tamagocc.TamagoCCParser.parse(String)'
	 */
	public void testParse() {
		try {
			
			File wd = new File(System.getProperty("user.dir")+"/TamagoNS.xsd");
			System.out.println(wd.toURI().toString());
			TamagoCC.initialize(new String[] { "-setxsd", wd.toURI().toString()});
			
			TamagoCCParser parser = TamagoCCParser.getDefaultParser();
			
			TTamagoEntity entity = parser.parse("<?xml version=\"1.0\" ?> <service name=\"Sercice\" module=\"module\" />");
			assertNotNull(entity);
		}
		catch(Exception excp) {
			assertTrue("Exception test parseeur : "+excp.getMessage(),false);
		}
	}

}
