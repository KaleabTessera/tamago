/**
 * 
 */
package tamagocc.api;

/**
 * @author Hakim Belhaouari
 *
 */
public interface TExistColl extends TExpression {
	TType getType();
	TVariable getVariable();
	
	TExpression getCollection();
	
	TExpression getBody();
}
