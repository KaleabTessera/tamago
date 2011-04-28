package tamagocc.api;

/**
 * 
 * This class is used in the service behavior, to 
 * represent an allowed functionality in a specific state.
 * 
 * @author Hakim BELHAOUARI
 */
public interface TAllow extends TObject {
    /**
     * @return Return the ID of a functionality.
     */
    String getMethod();
}
