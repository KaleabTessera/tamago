/**
 * 
 */
package tamagocc.exception;

/**
 * @author hbelhaou
 *
 */
public final class ParseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3134463574642398228L;

	/**
	 * 
	 */
	public ParseException() {
	}

	/**
	 * @param arg0
	 */
	public ParseException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public ParseException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ParseException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
