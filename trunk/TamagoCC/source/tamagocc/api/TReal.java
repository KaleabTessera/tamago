package tamagocc.api;


/**
 * This interface is used in assertion to represents a float literal.  
 * 
 * @author Hakim BELHAOUARI
 */
public interface TReal extends TExpression {
    /**
     * 
     * @return Return the literal value
     */
    double getValue();
}
