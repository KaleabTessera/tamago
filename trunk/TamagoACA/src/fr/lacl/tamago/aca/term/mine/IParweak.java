/**
 * 
 */
package fr.lacl.tamago.aca.term.mine;

import java.util.List;

import fr.lacl.tamago.aca.xmlterm.Parweak;
import fr.lacl.tamago.aca.xmlterm.Term;

/**
 * @author hakim
 *
 */
public class IParweak extends Parweak {

	
	public IParweak(List<Term> dup) {
		super();
		this.child = dup;
	}

	public IParweak(Term right) {
		super();
		this.child.add(right);
	}

}
