/**
 * 
 */
package tamagocc.api;

/**
 * @author Hakim Belhaouari
 *
 */
public interface TForallColl extends TExpression {
	TType getType();
	TVariable getVariable();
	
	TExpression getCollection();
	
	TExpression getBody();
}
