/**
 * 
 */
package fr.lacl.tamago.aca.term.mine;

import java.util.ArrayList;
import java.util.List;

import fr.lacl.tamago.aca.xmlterm.Action;
import fr.lacl.tamago.aca.xmlterm.Atomic;

/**
 * @author aliquando
 *
 */
public class IAtomic extends Atomic {

	List<Atomic> atomics;
	
	public IAtomic(String act,List<Atomic> r) {
		Action action = new Action();
		action.setName(act);
		setAction(action);
		atomics = new ArrayList<Atomic>(r);
		for (Atomic atomic : r) {
			if(atomic.getUser() != null) {
				this.setUser(atomic.getUser());
			}
			if(atomic.getOrg() != null) {
				this.setOrg(atomic.getOrg());
			}
			if(atomic.getRole() != null) {
				this.setRole(atomic.getRole());
			}
		}
	}
	
	

}
