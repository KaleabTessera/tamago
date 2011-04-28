package tamagocc.exception;

/**
 * 
 * this exception is thrown when the parser fails its file searching
 * 
 * @author Hakim BELHAOUARI
 */
public class TamagoCCNotFoundFile extends TamagoCCException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6060169072936124196L;

	/**
     * 
     */
    public TamagoCCNotFoundFile() {
        super();
    }

    /**
     * @param arg0
     */
    public TamagoCCNotFoundFile(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public TamagoCCNotFoundFile(Throwable arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public TamagoCCNotFoundFile(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

}
