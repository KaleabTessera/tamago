/**
 * 
 */
package fr.lacl.tamago.aca.term;

import java.util.ArrayList;

import tamagocc.util.Pair;
import fr.lacl.tamago.aca.term.util.ACATermVisitor;

/**
 * @author Hakim Belhaouari
 *
 */
public class Atomic implements Term {

	protected ArrayList<Pair<String,Boolean>> user;
	protected ArrayList<Pair<String,Boolean>> role;
	protected ArrayList<Pair<String,Boolean>> org;
	protected String action;
	protected ArrayList<Logic> variables;
	
	/**
	 * @param action 
	 * 
	 */
	public Atomic(String action) {
		this.user = new ArrayList<Pair<String,Boolean>>();
		this.role = new ArrayList<Pair<String,Boolean>>();
		this.org = new ArrayList<Pair<String,Boolean>>();
		variables = new ArrayList<Logic>();
		this.action = action;
	}
	
	public Atomic(ArrayList<Atomic> r) {
		this.user = new ArrayList<Pair<String,Boolean>>();
		this.role = new ArrayList<Pair<String,Boolean>>();
		this.org = new ArrayList<Pair<String,Boolean>>();
		for (Atomic atomic : r) {
			this.action = atomic.getAction();
			for(int i = 0;i < atomic.sizeUser();i++) {
				Pair<String,Boolean> pair = atomic.getUser(i);
				addUser(pair.l(), pair.r());
			}
			
			for(int i = 0;i < atomic.sizeRole();i++) {
				Pair<String,Boolean> pair = atomic.getRole(i);
				addRole(pair.l(), pair.r());
			}
			
			for(int i = 0;i < atomic.sizeOrg();i++) {
				Pair<String,Boolean> pair = atomic.getOrg(i);
				addOrg(pair.l(), pair.r());
			}
			
			variables = new ArrayList<Logic>(atomic.variables);
		}
	}
	
	
	public void addVariables(Logic l) {
		variables.add(l);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Atomic) {
			Atomic no = (Atomic) obj;
			return (user.equals(no.user) && role.equals(no.role) && org.equals(no.org));
		}
		return false;
	}

	
	public void addUser(String user,boolean forbidden) {
		this.user.add(new Pair<String, Boolean>(user, forbidden));
	}
	
	public void addRole(String role,boolean forbidden) {
		this.role.add(new Pair<String, Boolean>(role, forbidden));
	}
	
	public void addOrg(String org,boolean forbidden) {
		this.org.add(new Pair<String, Boolean>(org, forbidden));
	}
	
		
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getAction() {
		return action;
	}
	
	public int sizeUser() {	return user.size(); }	
	public int sizeRole() { return role.size();}
	public int sizeOrg() { return org.size(); }

	public Pair<String, Boolean> getUser(int i) {
		return user.get(i);
	}
	public Pair<String, Boolean> getRole(int i) {
		return role.get(i);
	}
	public Pair<String, Boolean> getOrg(int i) {
		return org.get(i);
	}
	
	@Override
	public <R, E extends Throwable> R visit(ACATermVisitor<R,E> visitor) throws E {
		return visitor.visitAtomic(this);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Atomic [");
		if (action != null) {
			builder.append("action=");
			builder.append(action);
			builder.append(", ");
		}
		if (user.size() != 0) {
			builder.append("user=");
			builder.append(user);
			builder.append(", ");
		}
		if (role.size() != 0) {
			builder.append("role=");
			builder.append(role);
			builder.append(", ");
		}
		if (org.size() != 0) {
			builder.append("org=");
			builder.append(org);
		}
		for (Logic log : variables) {
			builder.append(" logic: ").append(log.toString());
		}
		builder.append("]");
		return builder.toString();
	}

	public ArrayList<Logic> getLogics() {
		return variables;
	}
	
	
}
