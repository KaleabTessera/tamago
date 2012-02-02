package tamago.aca.term;

import java.util.ArrayList;
import java.util.Collection;

import tamago.aca.visitor.ACAVisitor;

public class Users extends ArrayList<String>{

	private static final long serialVersionUID = 7139214004101253530L;

	public Users(Collection<String> content) {
		super(content);
	}
	
	
	
	public Users() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "users := " +super.toString();
	}
	
	public <R,E extends Exception> R visitTerm(ACAVisitor<R,E> visitor) throws E {
		return visitor.visitUsers(this);
	}

}
