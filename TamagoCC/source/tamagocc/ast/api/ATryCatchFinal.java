/**
 * 
 */
package tamagocc.ast.api;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface ATryCatchFinal extends AInstruction {
	AInstruction getTryBody();
	Iterable<ACatchBlock> getCatchBlock();	
	AInstruction getFinalBody();
}
