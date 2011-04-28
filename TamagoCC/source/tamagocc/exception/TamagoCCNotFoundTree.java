package tamagocc.exception;

/**
 * This exception is thrown when the tree of a file does not exist in the pool.
 * 
 * @author Hakim BELHAOUARI
 */
public class TamagoCCNotFoundTree extends TamagoCCException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1850102320308843569L;

	/**
     * 
     */
    public TamagoCCNotFoundTree() {
        super();
    }

    /**
     * @param arg0
     */
    public TamagoCCNotFoundTree(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public TamagoCCNotFoundTree(Throwable arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public TamagoCCNotFoundTree(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

}
