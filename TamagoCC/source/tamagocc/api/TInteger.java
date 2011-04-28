package tamagocc.api;


/**
 * 
 * This interface represents an integer in the CDL specification.
 * It is used in expression.
 * 
 * @author Hakim BELHAOUARI
 */
public interface TInteger extends TExpression {
    /**
     * 
     * @return Return the value of the literal.
     */
    public int getValue();
    
}
