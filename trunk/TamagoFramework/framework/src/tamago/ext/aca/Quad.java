package tamago.ext.aca;

public interface Quad {
	boolean role_Is(String role);
	boolean role_IsNot(String role);
	
	boolean user_Is(String user);
	boolean user_IsNot(String user);
	
	boolean org_Is(String org);
	boolean org_IsNot(String org);
	
	
}
