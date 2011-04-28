/**
 * 
 */
package tamagocc.util.ast;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author Hakim Belhaouari
 *
 */
public class ASTObject implements ASTValue {

	private Object o;
	
	/**
	 * 
	 */
	public ASTObject(Object o) {
		this.o = o;
	}

	/**
	 * @see tamagocc.util.ast.ASTValue#boolValue()
	 */
	public boolean boolValue() {
		throw new ASTInterpreterException("Object pointer can not be a boolean value");
	}

	/**
	 * @see tamagocc.util.ast.ASTValue#getType()
	 */
	public ASTValueType getType() {
		return ASTValueType.OBJECT;
	}

	/**
	 * @see tamagocc.util.ast.ASTValue#intValue()
	 */
	public int intValue() {
		throw new ASTInterpreterException("Object pointer can not be a int value");
	}

	/**
	 * @see tamagocc.util.ast.ASTValue#objectValue()
	 */
	public Object objectValue() {
		return o;
	}

	/**
	 * @see tamagocc.util.ast.ASTValue#realValue()
	 */
	public double realValue() {
		throw new ASTInterpreterException("Object pointer can not be a real value");
	}

	/**
	 * @see tamagocc.util.ast.ASTValue#stringValue()
	 */
	public String stringValue() {
		return o.toString();
	}

	public Class<?> innerClass() {
		return o.getClass();
	}

	public ASTValue call(String name, ArrayList<ASTValue> al) {
		Class<?> obj = o.getClass();
		try {
			Method method = obj.getMethod("m",extractClasses(al));
			Object res = method.invoke(obj,al.toArray());
			if(method.getReturnType() == void.class) {
				return new ASTvoid();
			}
			else if(method.getReturnType() == int.class) {
				return new ASTinteger(((Integer)res).intValue());
			}
			else if(method.getReturnType() == double.class) {
				return new ASTreal(((Double)res).doubleValue());
			}
			else if(method.getReturnType() == float.class) {
				return new ASTreal(((Float)res).doubleValue());
			}
			else if(method.getReturnType() == String.class) {
				return new ASTstring((String)res);
			}
			else {
				return new ASTObject(res);
			}
		}
		catch (Exception e) {
			throw new ASTInterpreterException(e);
		}
	}

	private Class<?>[] extractClasses(ArrayList<ASTValue> al) {
		Class<?>[] tabs = new Class[al.size()];
		for(int i=0; i< al.size();i++) {
			tabs[i] = al.get(i).innerClass();
		}
		return tabs;
	}
}
