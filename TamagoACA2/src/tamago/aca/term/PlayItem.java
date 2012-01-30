package tamago.aca.term;

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
	
}
