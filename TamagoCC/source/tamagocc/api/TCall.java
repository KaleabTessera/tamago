package tamagocc.api;

import java.util.Iterator;;

/**
 * This class is used in contract expression.
 * It represents the call of an method (without side-effect), in order to exploit the issues.
 * 
 * @author Hakim BELHAOUARI
 */
public interface TCall extends TExpression {
    
    /**
     * 
     * @return Returns the name of the method.
     */    
    public String getName();
    
        
    /**
     * 
     * @return Returns the iterator of all arguments.
     * Arguments are specified left-to-right order.
     */
    public Iterator<TExpression> getArguments();
}
