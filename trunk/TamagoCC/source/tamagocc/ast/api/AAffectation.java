/**
 * 
 */
package tamagocc.ast.api;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AAffectation extends AInstruction {
	AVariable getVariable();
	AExpression getInitValeur();
	
}
