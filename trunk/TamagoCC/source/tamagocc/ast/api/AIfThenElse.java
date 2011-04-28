/**
 * 
 */
package tamagocc.ast.api;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AIfThenElse extends AInstruction {
	AExpression getCondition();
	AInstruction getCons();
	AInstruction getAlt();
}
