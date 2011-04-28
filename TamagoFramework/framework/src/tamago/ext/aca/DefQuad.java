/**
 * 
 */
package tamago.ext.aca;

/**
 * @author hakim
 *
 */
public class DefQuad implements Quad {

	private String role;
	private String user;
	private String org;
	
	/**
	 * 
	 */
	public DefQuad() {
	}

	public DefQuad(String user, String role, String org) {
		this.role = role;
		this.user = user;
		this.org = org;
	}
	/**
	 * @see tamago.ext.aca.Quad#role_Is(java.lang.String)
	 */
	@Override
	public boolean role_Is(String role) {
		return role.equals(this.role);
	}

	/**
	 * @see tamago.ext.aca.Quad#role_IsNot(java.lang.String)
	 */
	@Override
	public boolean role_IsNot(String role) {
		return !role.equals(this.role);
	}

	/**
	 * @see tamago.ext.aca.Quad#user_Is(java.lang.String)
	 */
	@Override
	public boolean user_Is(String user) {
		return user.equals(this.user);
	}

	/**
	 * @see tamago.ext.aca.Quad#user_IsNot(java.lang.String)
	 */
	@Override
	public boolean user_IsNot(String user) {
		return !user.equals(this.user);
	}

	/**
	 * @see tamago.ext.aca.Quad#org_Is(java.lang.String)
	 */
	@Override
	public boolean org_Is(String org) {
		return org.equals(org);
	}

	/**
	 * @see tamago.ext.aca.Quad#org_IsNot(java.lang.String)
	 */
	@Override
	public boolean org_IsNot(String org) {
		return !org.equals(org);
	}

}
