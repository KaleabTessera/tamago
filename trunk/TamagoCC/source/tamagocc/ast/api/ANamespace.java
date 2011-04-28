/**
 * 
 */
package tamagocc.ast.api;

import tamagocc.ast.TamagoCCAST;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface ANamespace extends TamagoCCAST {
	public String getNamespace();
	public String getType();
	public boolean isPackage();
	public boolean isStatic();
}
