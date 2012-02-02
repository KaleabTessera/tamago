package tamago.aca.term;

import tamago.aca.visitor.ACAVisitor;

public class Quad {
	private String user;
	private String role;
	private String org;
	private String action;
	
	public Quad(String user,String role,String org,String action) {
		this.user = user;
		this.role = role;
		this.org = org;
		this.action = action;
	}
	
	public String getUser() {
		if(isNegUser())
			return user.substring(1);
		else
			return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRole() {
		if(isNegRole())
			return role.substring(1);
		else					
			return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOrg() {
		if(isNegOrg())
			return org.substring(1);
		else
			return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public boolean isNegUser() {
		return user.startsWith("!");
	}
	
	public boolean isNegRole() {
		return role.startsWith("!");
	}
	
	public boolean isNegOrg() {
		return org.startsWith("!");
	}

	public boolean hasNegField() {
		return isNegOrg() || isNegRole() || isNegUser();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("<");
		sb.append(user);
		sb.append(",");
		sb.append(role);
		sb.append(",");
		sb.append(org);
		sb.append(",");
		sb.append(action);
		sb.append(">");
		return sb.toString();
	}
	
	public <R,E extends Exception> R visitTerm(ACAVisitor<R,E> visitor) throws E {
		return visitor.visitQuad(this);
	}
}


