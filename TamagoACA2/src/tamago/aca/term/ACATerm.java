package tamago.aca.term;

import tamago.aca.visitor.ACAVisitor;

public interface ACATerm {
	public <R,E extends Exception> R visit(ACAVisitor<R,E> visitor) throws E;
}
