/**
 * 
 */
package tamago.ext.tamagocc;

import tamago.TamagoException;

/**
 * This class represents the exception throws by user or container when an error during the changing
 * of the underlying implementation.
 * 
 * @author Hakim Belhaouari
 */
public class TamagoCCHotSwappingException extends TamagoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8005092786689060683L;

	/**
	 * @param message
	 */
	public TamagoCCHotSwappingException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	public TamagoCCHotSwappingException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public TamagoCCHotSwappingException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
