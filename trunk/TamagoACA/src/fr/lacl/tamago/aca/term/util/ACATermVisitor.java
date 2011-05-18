/**
 * 
 */
package fr.lacl.tamago.aca.term.util;

import fr.lacl.tamago.aca.term.Atomic;
import fr.lacl.tamago.aca.term.Obl;
import fr.lacl.tamago.aca.term.Parstrong;
import fr.lacl.tamago.aca.term.Parweak;
import fr.lacl.tamago.aca.term.Sod;

/**
 * @author Hakim Belhaouari
 *
 */
public interface ACATermVisitor<R, E extends Throwable> {
	R visitAtomic(Atomic term) throws E;
	R visitObl(Obl term) throws E;
	R visitSod(Sod term) throws E;
	R visitParweak(Parweak term) throws E;
	R  visitParstrong(Parstrong term) throws E;
}
