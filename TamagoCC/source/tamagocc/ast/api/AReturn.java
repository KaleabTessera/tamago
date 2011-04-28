/**
 * 
 */
package tamagocc.ast.api;


/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AReturn extends AInstruction {
	AExpression getExpression();
}
