/**
 * 
 */
package tamagocc.util.ast;

/**
 * @author Hakim Belhaouari
 *
 */
public class ASTboolean implements ASTValue {

	private boolean b;
	
	/**
	 * 
	 */
	public ASTboolean(boolean b) {
		this.b = b;
	}
	
	public boolean getValue() {
		return b;
	}

	public ASTValueType getType() {
		return ASTValueType.BOOLEAN;
	}

	private static ASTboolean[] bufs = null;
		
	public static ASTboolean bool(boolean bool) {
		if(bufs == null) {
			bufs = new ASTboolean[2];
			bufs[0] = new ASTboolean(false);
			bufs[1] = new ASTboolean(true);
		}
		
		if(bool)
			return bufs[1];
		else
			return bufs[0];
	}

	public boolean boolValue() {
		return b;
	}

	public int intValue() {
		if(b)
			return 1;
		else
			return 0;
	}

	public Object objectValue() {
		return new Boolean(b);
	}

	public double realValue() {
		if(b)
			return 1.0;
		else
			return 0.0;
	}

	public String stringValue() {
		if(b)
			return "true";
		else 
			return "false";
	}

	public Class<?> innerClass() {
		return boolean.class;
	}

	
}
