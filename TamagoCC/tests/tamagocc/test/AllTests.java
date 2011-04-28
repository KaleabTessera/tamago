package tamagocc.test;

import tamagocc.impl.test.TestTIAccess;
import tamagocc.impl.test.TestTIAllow;
import tamagocc.impl.test.TestTIAtPre;
import tamagocc.impl.test.TestTIBind;
import tamagocc.impl.test.TestTIBool;
import tamagocc.impl.test.TestTICall;
import tamagocc.impl.test.TestTICategory;
import tamagocc.impl.test.TestTICondition;
import tamagocc.impl.test.TestTIDefinition;
import tamagocc.impl.test.TestTIExport;
import tamagocc.impl.test.TestTIExtendService;
import tamagocc.impl.test.TestTIInteger;
import tamagocc.impl.test.TestTIInvariant;
import tamagocc.impl.test.TestTIMethod;
import tamagocc.impl.test.TestTINoContract;
import tamagocc.impl.test.TestTINot;
import tamagocc.impl.test.TestTIOperator;
import tamagocc.util.test.TestNilCollection;
import tamagocc.util.test.TestNilIterator;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for tamagocc.test");
		//$JUnit-BEGIN$
		
		suite.addTestSuite(TestNilCollection.class);
		suite.addTestSuite(TestNilIterator.class);
		
		
		suite.addTestSuite(TestTIAccess.class);
		suite.addTestSuite(TestTIAllow.class);
		suite.addTestSuite(TestTIAtPre.class);
		suite.addTestSuite(TestTIBind.class);
		suite.addTestSuite(TestTIBool.class);
		suite.addTestSuite(TestTICall.class);
		suite.addTestSuite(TestTICategory.class);
		suite.addTestSuite(TestTICondition.class);
		suite.addTestSuite(TestTIDefinition.class);
		suite.addTestSuite(TestTIExport.class);
		suite.addTestSuite(TestTIExtendService.class);
		suite.addTestSuite(TestTIInteger.class);
		suite.addTestSuite(TestTIInvariant.class);
		suite.addTestSuite(TestTIMethod.class);
		suite.addTestSuite(TestTINoContract.class);
		suite.addTestSuite(TestTINot.class);
		suite.addTestSuite(TestTIOperator.class);
		
		
		suite.addTestSuite(TestTxtParser.class);
		
		//$JUnit-END$
		return suite;
	}

}
