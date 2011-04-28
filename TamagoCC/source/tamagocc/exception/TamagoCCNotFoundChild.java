/*
 * Created on 10 nov. 2005
 */
package tamagocc.exception;

/**
 * This exception is thrown when the parser can not find a specific child
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCNotFoundChild extends TamagoCCException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7726166019494889883L;

	/**
	 * 
	 */
	public TamagoCCNotFoundChild() {
		super();
	}

	/**
	 * @param arg0
	 */
	public TamagoCCNotFoundChild(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public TamagoCCNotFoundChild(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TamagoCCNotFoundChild(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
