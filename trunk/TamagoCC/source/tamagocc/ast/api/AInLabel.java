/**
 * 
 */
package tamagocc.ast.api;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AInLabel extends AExpression {
	AExpression getScope();
	AExpression getSubExpression();
}
