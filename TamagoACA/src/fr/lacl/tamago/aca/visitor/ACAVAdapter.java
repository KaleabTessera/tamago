package fr.lacl.tamago.aca.visitor;

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

public abstract class ACAVAdapter<T,E extends Exception> implements ACAVisitor<T, E> {

	@Override
	public T vACATerm(ACATerm term) throws E {
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
	public T vTerm(Term t) throws E {
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
	public T vAction(Action term) throws E {
		return null;
	}

	
	@Override
	public T vBans(Bans term) throws E {
		return null;
	}

	@Override
	public T vOrg(Org term) throws E {
		return null;
	}

	@Override
	public T vPermissions(Permissions term) throws E {
		return null;
	}

	@Override
	public T vProcessus(Processus term) throws E {
		return null;
	}

	@Override
	public T vRole(Role term) throws E {
		return null;
	}
	
	@Override
	public T vUser(User term) throws E {
		return null;
	}

}
