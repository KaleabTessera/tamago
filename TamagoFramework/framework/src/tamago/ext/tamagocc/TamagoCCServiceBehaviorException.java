/**
 * 
 */
package tamago.ext.tamagocc;

import tamago.TamagoException;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCServiceBehaviorException extends TamagoException {

	private static final long serialVersionUID = -8635529323888368866L;
	
	/**
	 * @param message
	 */
	public TamagoCCServiceBehaviorException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public TamagoCCServiceBehaviorException(Throwable cause) {
		super(cause);
	}

}
