package tamagocc.exception;

/**
 * this exception is thrown when the synchronized product can not determine the default state.
 *  
 * @author Hakim Belhaouari
 */
public class TamagoCCExceptionZeroDefaultState extends TamagoCCException {

	private static final long serialVersionUID = -4327457105617384739L;

	/**
	 * 
	 */
	public TamagoCCExceptionZeroDefaultState() {
		super();
	}

	/**
	 * @param arg0
	 */
	public TamagoCCExceptionZeroDefaultState(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public TamagoCCExceptionZeroDefaultState(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TamagoCCExceptionZeroDefaultState(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
