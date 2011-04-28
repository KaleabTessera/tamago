/**
 * 
 */
package tamagocc.api;

/**
 * @author belhaouari
 *
 */
public interface TForallSet extends TExpression {
	
	TVariable getVariable();
	TType getType();
	TExpression getBody();
	
	TSet getSet();
}
