package tamago.aca.term;

import java.util.ArrayList;
import java.util.Collection;

import tamago.aca.visitor.ACAVisitor;

public class Bans extends ArrayList<Quad>{
	public Bans(Collection<Quad> content) {
		super(content);
	}

	public Bans() {
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 7217728412592062219L;

	@Override
	public String toString() {
		return "prohibitions := " +super.toString();
	}
	
	public <R,E extends Exception> R visitTerm(ACAVisitor<R,E> visitor) throws E {
		return visitor.visitBans(this);
	}
}
