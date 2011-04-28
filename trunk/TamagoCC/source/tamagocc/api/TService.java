package tamagocc.api;

import java.util.Iterator;


/**
 * This interface represents a service and all information
 * 
 * @author Hakim BELHAOUARI
 */
public interface TService extends TTamago {
   
    /**
     * 
     * @return Return true if the user defined a service behavior for this service, else false.
     */
    boolean hasBehavior();
    
    
    /**
     * 
     * @return Return true if the service contains information for a generic type 
     */
    boolean isGeneric();
    
    /**
     * 
     * @return Return the name of the generic type.
     */
    String getGenericType();
    
    /**
     * 
     * @return Return the iterator of all extented services.
     */
    Iterator<TExtendService> getExtends();
    
}
