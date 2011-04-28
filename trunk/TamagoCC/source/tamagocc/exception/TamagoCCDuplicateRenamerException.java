package tamagocc.exception;

/**
 * This Exception is used when TamagoCC, rename a variable if needed
 * with a name of an existing variable.
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCDuplicateRenamerException extends TamagoCCException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7619090164103595735L;

	public TamagoCCDuplicateRenamerException() {
		super();
	}

	public TamagoCCDuplicateRenamerException(String arg0) {
		super(arg0);
	}

	public TamagoCCDuplicateRenamerException(Throwable arg0) {
		super(arg0);
	}

	public TamagoCCDuplicateRenamerException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
