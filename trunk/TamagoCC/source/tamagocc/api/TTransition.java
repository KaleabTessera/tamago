package tamagocc.api;

/**
 * This interfaces is used in the service behavior to represent a conditioned transition 
 * @author Hakim BELHAOUARI
 */
public interface TTransition {
    /**
     * @return Return state name of the origin  
     */
    String getFrom();
    
    /**
     * @return Return state name of the destination
     */
    String getTo();
    
    /**
     * @return Return the condition associate to the transition
     */
    TExpression getCondition();
    
    /**
     * @return Return the ID of the method which trigger the transition
     */
    String getMethod();
}
