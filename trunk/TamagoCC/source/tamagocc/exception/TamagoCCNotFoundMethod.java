/**
 * 
 */
package tamagocc.exception;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCNotFoundMethod extends TamagoCCException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -808555316908843241L;

	/**
	 * 
	 */
	public TamagoCCNotFoundMethod() {
	}

	/**
	 * @param arg0
	 */
	public TamagoCCNotFoundMethod(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public TamagoCCNotFoundMethod(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TamagoCCNotFoundMethod(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
