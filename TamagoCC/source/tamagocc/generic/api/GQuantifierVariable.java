/**
 * 
 */
package tamagocc.generic.api;

/**
 * @author Hakim Belhaouari
 *
 */
public interface GQuantifierVariable extends GVariable {
	GQuantifier getQuantifier();
	
	/**
	 * This function a simple variable to avoid redundancy of the stack
	 * @return
	 */
	GVariable getSimpleVariable();
}
