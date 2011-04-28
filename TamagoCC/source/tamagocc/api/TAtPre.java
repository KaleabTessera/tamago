package tamagocc.api;


/**
 * This class is used in expression of contracts (postcondition). Represents the 
 * value of an expression at the precondition of the functionality.
 * @author Hakim BELHAOUARI
 */
public interface TAtPre extends TExpression {
    /**
     * 
     * @return Return the expression to be evaluate at the precondition.
     */
    TExpression getExpression();
}
