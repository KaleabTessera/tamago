/**
 * 
 */
package tamagocc.api;

/**
 * @author belhaouari
 *
 */
public interface TExistSet extends TExpression {
	TVariable getVariable();
	TType getType();
	TExpression getBody();
	
	TSet getSet();
}
