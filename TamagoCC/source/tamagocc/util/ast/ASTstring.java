/**
 * 
 */
package tamagocc.util.ast;

/**
 * @author Hakim Belhaouari
 *
 */
public class ASTstring extends ASTObject implements ASTValue {

	private String string;
	
	/**
	 * 
	 */
	public ASTstring(String string) {
		super(string);
		this.string = string;
	}

	public String getValue() {
		return string;
	}
	
	/**
	 * @see tamagocc.util.ast.ASTValue#getType()
	 */
	public ASTValueType getType() {
		return ASTValueType.STRING;
	}

	public boolean boolValue() {
		return Boolean.parseBoolean(string);
	}

	public int intValue() {
		return Integer.parseInt(string);
	}

	public Object objectValue() {
		return string;
	}

	public double realValue() {
		return Double.parseDouble(string);
	}

	public String stringValue() {
		return string;
	}

}
