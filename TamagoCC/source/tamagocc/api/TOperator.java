package tamagocc.api;

import java.util.Iterator;

/**
 * 
 * This interface represents all unary,binary,... operations used in contracts.
 * 
 * @author Hakim BELHAOUARI
 */
public interface TOperator extends TExpression {
    /**
     * 
     * @return Return the associated operator
     */
    TOpeName getOperator();
    
    /**
     * @return Return the iterator of all operands.
     */
    Iterator<TExpression> getOperands();
    
}
