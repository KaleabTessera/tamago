/**
 * 
 */
package fr.lacl.tamago.aca.exception;

/**
 * @author hakim
 *
 */
public class ACAException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2151946399473236423L;

	/**
	 * 
	 */
	public ACAException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ACAException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ACAException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ACAException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
