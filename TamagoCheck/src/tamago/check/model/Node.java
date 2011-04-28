/**
 * 
 */
package tamago.check.model;

import tamagocc.util.TamagoCCIndentator;

/**
 * @author Hakim Belhaouari
 *
 */
public interface Node {
	void toXML(TamagoCCIndentator indent);
	NodeKind category();
}
