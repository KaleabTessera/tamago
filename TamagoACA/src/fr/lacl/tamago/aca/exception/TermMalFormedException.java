/**
 * 
 */
package fr.lacl.tamago.aca.exception;

/**
 * @author aliquando
 *
 */
public class TermMalFormedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4914526998628400082L;

	/**
	 * 
	 */
	public TermMalFormedException() {
	}

	/**
	 * @param arg0
	 */
	public TermMalFormedException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public TermMalFormedException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TermMalFormedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
