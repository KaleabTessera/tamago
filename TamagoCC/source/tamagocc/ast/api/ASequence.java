/**
 * 
 */
package tamagocc.ast.api;

import java.util.Iterator;


/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface ASequence extends AInstruction {
	Iterator<AInstruction> getInstructions();
}
