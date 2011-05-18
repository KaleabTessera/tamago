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
public interface ACAVisitor<T, E extends Throwable> {

	public T vACATerm(ACATerm term) throws E;
	
	public T vTerm(Term term) throws E;

	public T vAction(Action term) throws E;

	public T vAtomic(Atomic term) throws E;

	public T vBans(Bans term) throws E;

	public T vObl(Obl term) throws E;

	public T vOrg(Org term) throws E;

	public T vParstrong(Parstrong term) throws E;

	public T vParweak(Parweak term) throws E;

	public T vPermissions(Permissions term) throws E;

	public T vProcessus(Processus term) throws E;

	public T vRole(Role term) throws E;

	public T vSod(Sod term) throws E;

	public T vUser(User term) throws E;
}
