package tamago.aca.term;

import tamago.aca.visitor.ACAVisitor;

public class PlayItem {

	private String user;
	private String role;
	private String org;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public PlayItem(String user, String role,String org) {
		this.user = user;
		this.role = role;
		this.org = org;
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("<");
		sb.append(user);
		sb.append(",");
		sb.append(role);
		sb.append(",");
		sb.append(org);
		sb.append(">");
		return sb.toString();
	}
	
	public <R,E extends Exception> R visitTerm(ACAVisitor<R,E> visitor) throws E {
		return visitor.visitPlayItem(this);
	}
}
