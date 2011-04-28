/**
 * 
 */
package org.tamago.eclipse.cdl;

/**
 * @author aliquando
 *
 */
public class CDLEditorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6824781064211035167L;

	/**
	 * 
	 */
	public CDLEditorException() {
	}

	/**
	 * @param message
	 */
	public CDLEditorException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CDLEditorException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CDLEditorException(String message, Throwable cause) {
		super(message, cause);
	}

}
