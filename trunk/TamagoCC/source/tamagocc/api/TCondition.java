package tamagocc.api;


/**
 * this interface is the root of all condition (precondition, invariant,postcondition).
 * @author Hakim Belhaouari
 */

public interface TCondition extends TObject {
	/**
	 * 
	 * @return Return the expression of an assertion
	 */
	TExpression getExpression();
	
	/**
	 * 
	 * @return Return the category of the assertion
	 */
	TCategory getCategory();
	
	/**
	 * 
	 * @return Return the fail message of the assertion
	 */
	String getMessage();
}
