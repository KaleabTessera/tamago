package tamago.aca.term;

import java.util.ArrayList;
import java.util.Collection;

import tamago.aca.visitor.ACAVisitor;

public class Actions extends ArrayList<String>{

	public Actions(Collection<String> content) {
		super(content);
	}

	public Actions() {
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -2976039190090134402L;

	@Override
	public String toString() {
		return "actions := " +super.toString();
	}
	
	public <R,E extends Exception> R visitTerm(ACAVisitor<R,E> visitor) throws E {
		return visitor.visitActions(this);
	}
}
