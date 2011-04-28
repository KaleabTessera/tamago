/**
 * 
 */
package tamagocc.ast.api;

import java.util.Iterator;


/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AThrowException extends AInstruction {
	AType getType();
	
	// Iterator<AExpression>
	Iterator<AExpression> getArguments();
	int size();
}
