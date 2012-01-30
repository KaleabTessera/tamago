/**
 * 
 */
package tamago.aca.term;

import tamago.aca.visitor.ACAVisitor;

/**
 * @author hakim
 *
 */
public class Sod implements ACATerm {

	private Quad left;
	
	private Quad right;
	
	private OnCA target;
	
	/**
	 * 
	 */
	public Sod(OnCA target, Quad left, Quad right) {
		this.target = target;
		this.left = left;
		this.right = right;
	}

	/**
	 * @see tamago.aca.term.ACATerm#visit(tamago.aca.visitor.ACAVisitor)
	 */
	@Override
	public <R, E extends Exception> R visit(ACAVisitor<R, E> visitor) throws E {
		return visitor.visitSOD(this);
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
