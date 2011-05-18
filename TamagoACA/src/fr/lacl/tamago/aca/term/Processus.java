/**
 * 
 */
package fr.lacl.tamago.aca.term;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Hakim Belhaouari
 *
 */
public class Processus {

	private String name;
	private String module;
	private ArrayList<Atomic> permissions;
	private ArrayList<Atomic> bans;
	private Term term;
	
	/**
	 * 
	 */
	public Processus(String name, String module,Collection<Atomic> permissions, Collection<Atomic> bans, Term term) {
		this.name = name;
		this.module = module;
		this.permissions = new ArrayList<Atomic>(permissions);
		this.bans = new ArrayList<Atomic>(bans);
		this.term = term;
	}

	/**
	 * @return the permissions
	 */
	public ArrayList<Atomic> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(ArrayList<Atomic> permissions) {
		this.permissions = permissions;
	}

	/**
	 * @return the bans
	 */
	public ArrayList<Atomic> getBans() {
		return bans;
	}

	/**
	 * @param bans the bans to set
	 */
	public void setBans(ArrayList<Atomic> bans) {
		this.bans = bans;
	}

	/**
	 * @return the term
	 */
	public Term getTerm() {
		return term;
	}

	/**
	 * @param term the term to set
	 */
	public void setTerm(Term term) {
		this.term = term;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Processus [");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (module != null) {
			builder.append("module=");
			builder.append(module);
			builder.append(", ");
		}
		if (permissions != null && permissions.size()>0) {
			builder.append("permissions=");
			builder.append(permissions);
			builder.append(", ");
		}
		if (bans != null && bans.size() > 0) {
			builder.append("bans=");
			builder.append(bans);
			builder.append(", ");
		}
		if (term != null) {
			builder.append("term=");
			builder.append(term);
		}
		builder.append("]");
		return builder.toString();
	}

}
