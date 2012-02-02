package tamago.aca.term;

import java.util.ArrayList;
import java.util.Collection;

import tamago.aca.visitor.ACAVisitor;

public class Sods extends ArrayList<Sod>{

	private static final long serialVersionUID = 372501491864078463L;

	public Sods(Collection<Sod> content) {
		super(content);
	}

	public Sods() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "sods := "+super.toString();
	}
	
	public <R,E extends Exception> R visitTerm(ACAVisitor<R,E> visitor) throws E {
		return visitor.visitSods(this);
	}
}
