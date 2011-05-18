/**
 * 
 */
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

/**
 * @author hakim
 *
 */
public class ACAPrettyPrint implements ACAVisitor<StringBuilder, RuntimeException> {

	
	private static ACAPrettyPrint instance;
	public static ACAPrettyPrint getInstance() {
		if(instance == null) {
			instance = new ACAPrettyPrint();
		}
		return instance;
	}
	/**
	 * 
	 */
	private ACAPrettyPrint() {
	}

	/**
	 * @see fr.lacl.tamago.aca.visitor.ACAVisitor#vACATerm(fr.lacl.tamago.aca.xmlterm.ACATerm)
	 */
	@Override
	public StringBuilder vACATerm(ACATerm term) throws RuntimeException {
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
			StringBuilder sb = new StringBuilder("Unkown term type = ");
			sb.append(term.getClass().getName());
			return sb;
		}
	}
	

	/**
	 * @see fr.lacl.tamago.aca.visitor.ACAVisitor#vAction(fr.lacl.tamago.aca.xmlterm.Action)
	 */
	@Override
	public StringBuilder vAction(Action term) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append("Action "+term.getName());
		return sb;
	}

	/**
	 * @see fr.lacl.tamago.aca.visitor.ACAVisitor#vAtomic(fr.lacl.tamago.aca.xmlterm.Atomic)
	 */
	@Override
	public StringBuilder vAtomic(Atomic term) throws RuntimeException {
		StringBuilder sb = new StringBuilder(" ATOMIC ");
		if(term.getUser() != null)
			sb.append(vUser(term.getUser()));
		if(term.getRole() != null)
			sb.append(vRole(term.getRole()));
		if(term.getOrg() != null)
			sb.append(vOrg(term.getOrg()));
		sb.append(vAction(term.getAction()));
		return sb;
	}

	/**
	 * @see fr.lacl.tamago.aca.visitor.ACAVisitor#vBans(fr.lacl.tamago.aca.xmlterm.Bans)
	 */
	@Override
	public StringBuilder vBans(Bans term) throws RuntimeException {
		StringBuilder sb = new StringBuilder("BANS ");
		sb.append("SIZE :");
		sb.append(term.getAtomic().size());
		for (Atomic at : term.getAtomic()) {
			sb.append("\n\t");
			sb.append(vAtomic(at));
		}
		sb.append("\nEND BANS\n");
		return sb;
	}

	/**
	 * @see fr.lacl.tamago.aca.visitor.ACAVisitor#vObl(fr.lacl.tamago.aca.xmlterm.Obl)
	 */
	@Override
	public StringBuilder vObl(Obl term) throws RuntimeException {
		StringBuilder sb = new StringBuilder("OBL");
		sb.append("\n\tLEFT:");
		sb.append(vTerm(term.getLeft()));
		sb.append("\n\tRIGHT:");
		sb.append(vTerm(term.getRight()));
		return sb;
	}

	/**
	 * @see fr.lacl.tamago.aca.visitor.ACAVisitor#vOrg(fr.lacl.tamago.aca.xmlterm.Org)
	 */
	@Override
	public StringBuilder vOrg(Org term) throws RuntimeException {
		StringBuilder sb = new StringBuilder("ORG ");
		sb.append(term.getValue());
		return sb;
	}

	/**
	 * @see fr.lacl.tamago.aca.visitor.ACAVisitor#vParstrong(fr.lacl.tamago.aca.xmlterm.Parstrong)
	 */
	@Override
	public StringBuilder vParstrong(Parstrong term) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		for(Term at :  term.getChild()) {
			sb.append(vTerm(at));
			sb.append(" || ");
		}
		sb.append(" SIZE: ");
		sb.append(term.getChild().size());
		return sb;
	}

	/**
	 * @see fr.lacl.tamago.aca.visitor.ACAVisitor#vParweak(fr.lacl.tamago.aca.xmlterm.Parweak)
	 */
	@Override
	public StringBuilder vParweak(Parweak term) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		for(Term at :  term.getChild()) {
			sb.append("\n||| ");
			sb.append(vTerm(at));			
		}
		sb.append(" SIZE: ");
		sb.append(term.getChild().size());
		return sb;
	}

	/**
	 * @see fr.lacl.tamago.aca.visitor.ACAVisitor#vPermissions(fr.lacl.tamago.aca.xmlterm.Permissions)
	 */
	@Override
	public StringBuilder vPermissions(Permissions term) throws RuntimeException {
		StringBuilder sb = new StringBuilder("PERMS ");
		sb.append("SIZE :");
		sb.append(term.getAtomic().size());
		for (ACATerm at : term.getAtomic()) {
			sb.append("\n\t");
			sb.append(vACATerm(at));
		}
		sb.append("\nEND PERMS\n");
		return sb;
	}

	/**
	 * @see fr.lacl.tamago.aca.visitor.ACAVisitor#vProcessus(fr.lacl.tamago.aca.xmlterm.Processus)
	 */
	@Override
	public StringBuilder vProcessus(Processus term) throws RuntimeException {
		StringBuilder sb = new StringBuilder("PROCESSUS:");
		sb.append(term.getName());
		sb.append("\n");
		sb.append(vPermissions(term.getPermissions()));
		sb.append(vBans(term.getBans()));
		sb.append(vTerm(term.getComplex()));
		return sb;
	}

	/**
	 * @see fr.lacl.tamago.aca.visitor.ACAVisitor#vRole(fr.lacl.tamago.aca.xmlterm.Role)
	 */
	@Override
	public StringBuilder vRole(Role term) throws RuntimeException {
		StringBuilder sb = new StringBuilder("ROLE ");
		sb.append(term.getValue());
		return sb;
	}

	/**
	 * @see fr.lacl.tamago.aca.visitor.ACAVisitor#vSod(fr.lacl.tamago.aca.xmlterm.Sod)
	 */
	@Override
	public StringBuilder vSod(Sod term) throws RuntimeException {
		StringBuilder sb = new StringBuilder("SOD ");
		sb.append("\n\tLEFT:");
		sb.append(vTerm(term.getLeft()));
		sb.append("\n\tRIGHT:");
		sb.append(vTerm(term.getRight()));
		return sb;
	}

	public StringBuilder vTerm(Term t) throws RuntimeException {
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
	/**
	 * @see fr.lacl.tamago.aca.visitor.ACAVisitor#vUser(fr.lacl.tamago.aca.xmlterm.User)
	 */
	@Override
	public StringBuilder vUser(User term) throws RuntimeException {
		StringBuilder sb = new StringBuilder("USER ");
		sb.append(term.getValue());
		return sb;
	}
}
