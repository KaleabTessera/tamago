package tamago.aca.term;

import java.util.ArrayList;
import java.util.Collection;

import tamago.aca.visitor.ACAVisitor;

public class Roles extends ArrayList<String>{

	public Roles(Collection<String> content) {
		super(content);
	}

	public Roles() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3624318738453003540L;

	
	@Override
	public String toString() {
		return "roles := "+super.toString();
	}
	
	public <R,E extends Exception> R visitTerm(ACAVisitor<R,E> visitor) throws E {
		return visitor.visitRoles(this);
	}
}
