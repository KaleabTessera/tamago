/**
 * 
 */
package tamagocc.generic.api;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface GObject {
	Object visit(TamagoCCGVisitor visitor) throws TamagoCCException;
}
