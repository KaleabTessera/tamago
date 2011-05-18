/**
 * 
 */
package fr.lacl.tamago.aca.term.mine;

import java.util.ArrayList;
import java.util.List;

import fr.lacl.tamago.aca.xmlterm.Parstrong;
import fr.lacl.tamago.aca.xmlterm.Term;

/**
 * @author hakim
 *
 */
public class IParstrong extends Parstrong {

	/**
	 * 
	 */
	public IParstrong() {
		// TODO Auto-generated constructor stub
	}
	
	public IParstrong(List<Term> terms) {
		super();
		child = new ArrayList<Term>();
		child.addAll(terms);
	}
	
	public IParstrong(Term right) {
		super();
		this.child.add(right);
	}

}
