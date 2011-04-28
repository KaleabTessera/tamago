/**
 * 
 */
package tamagocc.ast.api;

/**
 * @author Hakim Belhaouari
 *
 */
public interface AForeach extends AInstruction {
	AInstruction getBody();
	AVariable getVariable();
	AType getVariableType();
	
	AExpression getCollection();
}
