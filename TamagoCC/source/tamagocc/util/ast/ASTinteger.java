/**
 * 
 */
package tamagocc.util.ast;

/**
 * @author Hakim Belhaouari
 *
 */
public class ASTinteger implements ASTValue {

	private int core;
	/**
	 * 
	 */
	public ASTinteger(int i) {
		core = i;
	}
	
	public int getValue() {
		return core;
	}

	/**
	 * @see tamagocc.util.ast.ASTValue#getType()
	 */
	public ASTValueType getType() {
		return ASTValueType.INTEGER;
	}

	public boolean boolValue() {
		return (core != 0);
	}

	public int intValue() {
		return core;
	}

	public Object objectValue() {
		return new Integer(core);
	}

	public double realValue() {
		return (double)core;
	}

	public String stringValue() {
		return String.valueOf(core);
	}

	public Class<?> innerClass() {
		return int.class;
	}

}
