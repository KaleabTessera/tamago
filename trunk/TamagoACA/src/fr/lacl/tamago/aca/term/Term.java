/**
 * 
 */
package fr.lacl.tamago.aca.term;

import fr.lacl.tamago.aca.term.util.ACATermVisitor;

/**
 * @author Hakim Belhaouari
 *
 */
public interface Term {
	<R, E extends Throwable> R visit(ACATermVisitor<R,E> visitor) throws E;
}
