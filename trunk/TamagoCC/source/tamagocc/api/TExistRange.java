/**
 * 
 */
package tamagocc.api;

/**
 * @author belhaouari
 *
 */
public interface TExistRange extends TExpression {
	TType getType();
	TVariable getVariable();
	
	TExpression getBody();
	
	TExpression getMin();
	TExpression getMax();
}
