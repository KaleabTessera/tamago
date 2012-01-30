package tamago.aca.term;

import tamago.aca.visitor.ACAVisitor;

public class Obl implements ACATerm{
	private Quad left;
	private Quad right;
	private OnCA target;

	public Obl(OnCA ca, Quad second, Quad third) {
		target = ca;
		left = second;
		right = third;
	}


	/**
	 * @see tamago.aca.term.ACATerm#visit(tamago.aca.visitor.ACAVisitor)
	 */
	@Override
	public <R, E extends Exception> R visit(ACAVisitor<R, E> visitor) throws E {
		return visitor.visitOBL(this);
	}

	
	public Quad getLeft() {
		return left;
	}

	public void setLeft(Quad left) {
		this.left = left;
	}

	public Quad getRight() {
		return right;
	}

	public void setRight(Quad right) {
		this.right = right;
	}

	public OnCA getTarget() {
		return target;
	}

	public void setTarget(OnCA target) {
		this.target = target;
	}

}
