package tamago.aca.term;

import java.util.ArrayList;
import java.util.Collection;

import tamago.aca.visitor.ACAVisitor;

public class Orgs extends ArrayList<String>{
	public Orgs(Collection<String> content) {
		super(content);
	}

	public Orgs() {
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 6218866703622950304L;

	@Override
	public String toString() {
		return "orgs := " +super.toString();
	}
	
	public <R,E extends Exception> R visitTerm(ACAVisitor<R,E> visitor) throws E {
		return visitor.visitOrgs(this);
	}
}
