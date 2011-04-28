package tamagocc.api;

/**
 * This interface is used in assertion to represent in postcondition the result of the
 * functionality.  
 * 
 * @author Hakim BELHAOUARI
 */
public interface TReturn extends TExpression {
	boolean hasIndex();
	
	TExpression getIndex();
}
