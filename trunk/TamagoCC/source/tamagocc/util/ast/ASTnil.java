/**
 * 
 */
package tamagocc.util.ast;

/**
 * @author Hakim Belhaouari
 *
 */
public class ASTnil implements ASTValue {

	/**
	 * 
	 */
	public ASTnil() {
	}

	/**
	 * @see tamagocc.util.ast.ASTValue#getType()
	 */
	public ASTValueType getType() {
		return ASTValueType.NIL;
	}
	
	private static ASTnil def = new ASTnil();
	
	public static ASTnil nil() {
		return def;
	}

	public boolean boolValue() {
		return false;
	}

	public int intValue() {
		return 0;
	}

	public Object objectValue() {
		return null;
	}

	public double realValue() {
		return 0.0;
	}

	public String stringValue() {
		return null;
	}

	public Class<?> innerClass() {
		throw new ASTInterpreterException("NULL pointer does not have class");
	}
}
