package tamagocc.api;

import java.util.Iterator;

/**
 * 
 * This interface is the root functionality definition.
 * 
 * @author Hakim BELHAOUARI
 */
public interface TMethod extends TObject {
    
    /**
     * 
     * @return Return the name of the method
     */
    String getName();
    
    /**
     * 
     * @return Return the type of the method 
     */
    TType getType();
    
    /**
     * To specify an ID to the method to avoid the overload of method. By default the ID is 
     * formed by the name of the included entity and the name of the method.
     * @return Return the ID of the method
     */
    String getID();
    
    /**
     * 
     * @return the iterator of all parameters of the method
     */
    Iterator<TParameter> getParameters();
    
    
    /**
     * 
     * @return Return the count of parameters
     */
    int getParameterNumber();
    
    /**
     * 
     * @return Return the local precondition associate to the functionality
     */
    TCondition getPrecondition();
    
    /**
     * 
     * @return Return the local postcondition associate to the functionality
     */
    TCondition getPostcondition();
}
