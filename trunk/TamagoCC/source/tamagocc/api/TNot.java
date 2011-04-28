package tamagocc.api;

/**
 * 
 * This interface represents the not condition, used in contract CDL specification. 
 * 
 * @author Hakim BELHAOUARI
 */
public interface TNot extends TExpression {
	/**
	 * 
	 * @return Return the boolean subexpression
	 */
    TExpression getTerm();
}
