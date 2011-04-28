package tamagocc.exception;

/**
 * This exception is the root of all exception presented in TamagoCC 
 * 
 * @author Hakim BELHAOUARI
 */
public class TamagoCCException extends Exception {

    private static final long serialVersionUID = -3262209882609475210L;

	/**
     * Default constructor with no information
     */
    public TamagoCCException() {
        super();
     }

    /**
     * A constructor with a message as information
     * @param arg0 : a message
     */
    public TamagoCCException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public TamagoCCException(Throwable arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public TamagoCCException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

}
