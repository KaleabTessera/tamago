/**
 * 
 */
package tamago.aca.visitor;

import tamago.aca.term.ACA;
import tamago.aca.term.Obl;
import tamago.aca.term.Sod;

/**
 * @author hakim
 *
 */
public interface ACAVisitor<R, E extends Exception> {
	R visitACA(ACA e) throws E;

	R visitSOD(Sod sod) throws E;

	R visitOBL(Obl obl) throws E;
	
}
