/**
 * 
 */
package tamagocc.util.ast;

/**
 * @author Hakim Belhaouari
 *
 */
public class ASTvoid implements ASTValue {

	private static ASTvoid single  = new ASTvoid();

	/**
	 * 
	 */
	public ASTvoid() {
		
	}

	/**
	 * @see tamagocc.util.ast.ASTValue#boolValue()
	 */
	public boolean boolValue() {
		throw new RuntimeException("void type conversion into boolean");
	}

	/**
	 * @see tamagocc.util.ast.ASTValue#getType()
	 */
	public ASTValueType getType() {
		return ASTValueType.VOID;
	}

	/**
	 * @see tamagocc.util.ast.ASTValue#intValue()
	 */
	public int intValue() {
		throw new RuntimeException("void type conversion into int");
	}

	/**
	 * @see tamagocc.util.ast.ASTValue#objectValue()
	 */
	public Object objectValue() {
		throw new RuntimeException("void type conversion into object");
	}

	/**
	 * @see tamagocc.util.ast.ASTValue#realValue()
	 */
	public double realValue() {
		throw new RuntimeException("void type conversion into real");
	}

	/**
	 * @see tamagocc.util.ast.ASTValue#stringValue()
	 */
	public String stringValue() {
		throw new RuntimeException("void type conversion into string");
	}

	public Class<?> innerClass() {
		return void.class;
	}

	public static ASTvoid single() {
		return single  = new ASTvoid();
	}

}
