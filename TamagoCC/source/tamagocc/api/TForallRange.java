/**
 * 
 */
package tamagocc.api;

/**
 * @author belhaouari
 *
 */
public interface TForallRange extends TExpression {
	TType getType();
	TVariable getVariable();
	
	TExpression getBody();
	
	TExpression getMin();
	TExpression getMax();
}
