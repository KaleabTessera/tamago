/**
 * 
 */
package tamagocc.ast.api;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AWhile extends AInstruction {
	AExpression getCondition();
	AInstruction getBody();
}
