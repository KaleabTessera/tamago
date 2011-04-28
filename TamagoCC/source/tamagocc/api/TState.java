package tamagocc.api;

import java.util.Iterator;

/**
 * This interface represents a state in the service behavior
 * @author Hakim BELHAOUARI
 */
public interface TState {
    /**
     * @return Return the name of the state
     */
    String getState();
    
    /**
     * @return Return an iterator with all allowed functionality 
     */
    Iterator<TAllow> getAllow();
    
}
