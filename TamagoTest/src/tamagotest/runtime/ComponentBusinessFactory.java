/**
 * 
 */
package tamagotest.runtime;

import tamago.ext.tamagocc.TamagoCCComponent;

/**
 * @author Hakim Belhaouari
 *
 */
public interface ComponentBusinessFactory {
	TamagoCCComponent provide();
	void prepare();
}
