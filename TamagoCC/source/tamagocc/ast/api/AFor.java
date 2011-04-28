/**
 * 
 */
package tamagocc.ast.api;

/**
 * This class represents the for loop, for enumerative type.
 * 
 * @author Hakim Belhaouari
 *
 */
public interface AFor extends AInstruction {
	AInstruction getAffectation();
	AExpression getCondition();
	AInstruction getIncrement();
	
	AInstruction getBody();
}
