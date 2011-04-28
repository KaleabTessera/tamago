/**
 * 
 */
package tamagocc.api;

/**
 * @author Hakim Belhaouari
 *
 */
public interface TCast extends TExpression {
	TType getType();
	TExpression getExpression();
}
