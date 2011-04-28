package tamagocc.exception;

/**
 * this exception is thrown when the contract compiler can not find a correct type, when
 * a type is wrong or when the inference failed.
 * @author Hakim Belhaouari 
 *
 */
public class TamagoCCTypeException extends TamagoCCException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7589657190914186884L;

	/**
	 * 
	 */
	public TamagoCCTypeException() {
		super();
	}

	/**
	 * @param arg0
	 */
	public TamagoCCTypeException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public TamagoCCTypeException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TamagoCCTypeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
