/**
 * 
 */
package tamagocc.ast.api;


/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface ANot extends AExpression {
	AExpression getSubExpression();
}
