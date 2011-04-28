package tamagocc.api;


/**
 * 
 * This class is for contract expression.
 * This class represents a literal boolean.
 * @author Hakim BELHAOUARI
 */
public interface TBool extends TExpression {
    
    /**
     * 
     * @return Returns the value of the literal.
     */
    public boolean getValue();
}
