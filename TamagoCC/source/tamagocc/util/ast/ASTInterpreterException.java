/**
 * 
 */
package tamagocc.util.ast;

/**
 * @author Hakim Belhaouari
 *
 */
public class ASTInterpreterException extends RuntimeException {

	private static final long serialVersionUID = 7684642510801795129L;

	/**
	 * 
	 */
	public ASTInterpreterException() {
	}

	/**
	 * @param message
	 */
	public ASTInterpreterException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ASTInterpreterException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ASTInterpreterException(String message, Throwable cause) {
		super(message, cause);
	}

}
