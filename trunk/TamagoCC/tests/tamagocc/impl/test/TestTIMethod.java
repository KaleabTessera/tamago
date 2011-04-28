package tamagocc.impl.test;


import java.util.ArrayList;

import junit.framework.TestCase;
import tamagocc.api.TParameter;
import tamagocc.impl.TIBool;
import tamagocc.impl.TICategory;
import tamagocc.impl.TICondition;
import tamagocc.impl.TIMethod;
import tamagocc.impl.TIParameter;
import tamagocc.impl.TIType;
import tamagocc.util.NilIterator;

public class TestTIMethod extends TestCase {
	
	public void testGetID() {
		String name ="method";
		String id="methodID";
		TIType type= (TIType)TIType.generateType("void");
		ArrayList<TParameter> args = new ArrayList<TParameter>();

		TICategory cat1 = new TICategory("toto");
		String m1 = "none";
		TIBool bt = new TIBool(true);

		TICategory cat2 = new TICategory("titi");
		String m2 ="";
		TIBool bf = new TIBool(false);

		TICondition c1 = new TICondition(bt,cat1,m1);
		TICondition c2 = new TICondition(bf,cat2,m2);

		TIMethod meth1 = new TIMethod(name,id,type,args,c1,c2);

		assertNotNull(meth1.getID());
		assertEquals(meth1.getID(),id);

	}

	public void testGetName() {
		String name ="method";
		String id="methodID";
		TIType type= (TIType)TIType.generateType("void");
		ArrayList<TParameter> args = new ArrayList<TParameter>();

		TICategory cat1 = new TICategory("toto");
		String m1 = "none";
		TIBool bt = new TIBool(true);

		TICategory cat2 = new TICategory("titi");
		String m2 ="";
		TIBool bf = new TIBool(false);

		TICondition c1 = new TICondition(bt,cat1,m1);
		TICondition c2 = new TICondition(bf,cat2,m2);

		TIMethod meth1 = new TIMethod(name,id,type,args,c1,c2);

		assertNotNull(meth1.getName());
		assertEquals(meth1.getName(),name);

	}


	public void testGetType() {
		String name ="method";
		String id="methodID";
		TIType type= (TIType)TIType.generateType("void");
		ArrayList<TParameter> args = new ArrayList<TParameter>();

		TICategory cat1 = new TICategory("toto");
		String m1 = "none";
		TIBool bt = new TIBool(true);

		TICategory cat2 = new TICategory("titi");
		String m2 ="";
		TIBool bf = new TIBool(false);

		TICondition c1 = new TICondition(bt,cat1,m1);
		TICondition c2 = new TICondition(bf,cat2,m2);

		TIMethod meth1 = new TIMethod(name,id,type,args,c1,c2);

		assertNotNull(meth1.getType());
		assertEquals(meth1.getType(),type);

	}


	public void testGetParameters() {
		String name ="method";
		String id="methodID";
		TIType type= (TIType)TIType.generateType("int");
		ArrayList<TParameter> args = new ArrayList<TParameter>();
		TIParameter param = new TIParameter("p1",type);
		args.add(param);

		TICategory cat1 = new TICategory("toto");
		String m1 = "none";
		TIBool bt = new TIBool(true);

		TICategory cat2 = new TICategory("titi");
		String m2 ="";
		TIBool bf = new TIBool(false);

		TICondition c1 = new TICondition(bt,cat1,m1);
		TICondition c2 = new TICondition(bf,cat2,m2);

		TIMethod meth1 = new TIMethod(name,id,type,args,c1,c2);

		assertNotNull(meth1.getParameters());
		assertTrue(NilIterator.areEqual(args.iterator(),meth1.getParameters()));
	}


}
