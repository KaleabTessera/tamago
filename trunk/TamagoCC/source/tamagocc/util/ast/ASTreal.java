/**
 * 
 */
package tamagocc.util.ast;

/**
 * @author Hakim Belhaouari
 *
 */
public class ASTreal implements ASTValue {

	private double real;
	
	public ASTreal(double real) {
		this.real = real;
	}
	
	public double getValue() {
		return real;
	}
	
	/**
	 * @see tamagocc.util.ast.ASTValue#getType()
	 */
	public ASTValueType getType() {
		return ASTValueType.REAL;
	}

	public boolean boolValue() {
		return (real != 0.0);
	}

	public int intValue() {
		return (int)real;
	}

	public Object objectValue() {
		return new Double(real);
	}

	public double realValue() {
		return real;
	}

	public String stringValue() {
		return String.valueOf(real);
	}

	public Class<?> innerClass() {
		return double.class;
	}

}
