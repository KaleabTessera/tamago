/**
 * 
 */
package fr.lacl.tamago.aca.visitor;

import java.util.Set;
import java.util.TreeSet;

import fr.lacl.tamago.aca.xmlterm.ACATerm;
import fr.lacl.tamago.aca.xmlterm.Action;
import fr.lacl.tamago.aca.xmlterm.Atomic;
import fr.lacl.tamago.aca.xmlterm.Bans;
import fr.lacl.tamago.aca.xmlterm.Obl;
import fr.lacl.tamago.aca.xmlterm.Org;
import fr.lacl.tamago.aca.xmlterm.Parstrong;
import fr.lacl.tamago.aca.xmlterm.Parweak;
import fr.lacl.tamago.aca.xmlterm.Permissions;
import fr.lacl.tamago.aca.xmlterm.Processus;
import fr.lacl.tamago.aca.xmlterm.Role;
import fr.lacl.tamago.aca.xmlterm.Sod;
import fr.lacl.tamago.aca.xmlterm.Term;
import fr.lacl.tamago.aca.xmlterm.User;

/**
 * @author hakim
 *
 */
public class ACASearchActions implements ACAVisitor<Object, RuntimeException> {
	private TreeSet<String> actions = new TreeSet<String>();
	/**
	 * 
	 */
	public ACASearchActions() {
	}

	@Override
	public Object vACATerm(ACATerm term) throws RuntimeException {
		if(term instanceof Atomic)
			return vAtomic((Atomic)term);
		else if(term instanceof Obl)
			return vObl((Obl)term);
		else if(term instanceof Sod) 
			return vSod((Sod)term);
		else if(term instanceof Parstrong)
			return vParstrong((Parstrong)term);
		else if(term instanceof Parweak)
			return vParweak((Parweak)term);
		else {
			return null;
		}
	}

	@Override
	public Object vTerm(Term t) throws RuntimeException {
		if(t.getAtomic() != null)
			return vAtomic(t.getAtomic());
		else if(t.getObl() != null)
			return vObl(t.getObl());
		else if(t.getParstrong() != null)
			return vParstrong(t.getParstrong());
		else if(t.getParweak() != null)
			return vParweak(t.getParweak());
		else if(t.getSod() != null)
			return vSod(t.getSod());

		throw new RuntimeException("Empty term ?");
	}

	@Override
	public Object vAction(Action term) throws RuntimeException {
		actions.add(term.getName());
		return null;
	}

	@Override
	public Object vAtomic(Atomic term) throws RuntimeException {
		vAction(term.getAction());
		return null;
	}

	@Override
	public Object vBans(Bans term) throws RuntimeException {
		for (Atomic t : term.getAtomic()) {
			vAtomic(t);
		}
		return null;
	}

	@Override
	public Object vObl(Obl term) throws RuntimeException {
		vTerm(term.getLeft());
		vTerm(term.getRight());
		return null;
	}

	@Override
	public Object vOrg(Org term) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object vParstrong(Parstrong term) throws RuntimeException {
		for (Term t : term.getChild()) {
			vTerm(t);
		}
		return null;
	}

	@Override
	public Object vParweak(Parweak term) throws RuntimeException {
		for (Term t : term.getChild()) {
			vTerm(t);
		}
		return null;
	}

	@Override
	public Object vPermissions(Permissions term) throws RuntimeException {
		for (ACATerm t : term.getAtomic()) {
			vACATerm(t);
		}
		return null;
	}

	@Override
	public Object vProcessus(Processus term) throws RuntimeException {
		vPermissions(term.getPermissions());
		vBans(term.getBans());
		vTerm(term.getComplex());
		return null;
	}

	@Override
	public Object vRole(Role term) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object vSod(Sod term) throws RuntimeException {
		vTerm(term.getLeft());
		vTerm(term.getRight());
		return null;
	}

	@Override
	public Object vUser(User term) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<String> getFoundActions() {
		return actions;
	}
}
