/**
 * 
 */
package tamago.aca.exceptions;

/**
 * @author hbelhaou
 *
 */
public class ConvertionACAtoCDLException extends Exception {

		private static final long serialVersionUID = 6258008158937935169L;

	/**
	 * 
	 */
	public ConvertionACAtoCDLException() {
	}

	/**
	 * @param arg0
	 */
	public ConvertionACAtoCDLException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public ConvertionACAtoCDLException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ConvertionACAtoCDLException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
