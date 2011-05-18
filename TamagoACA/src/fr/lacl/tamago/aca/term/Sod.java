/**
 * 
 */
package fr.lacl.tamago.aca.term;

import tamagocc.util.TamagoFreshVar;
import fr.lacl.tamago.aca.term.util.ACATermVisitor;

/**
 * @author Hakim Belhaouari
 *
 */
public class Sod implements Term {
	private Atomic left;
	private Atomic right;
	private OnEnum on;
	/**
	 * 
	 */
	public Sod(OnEnum on,Atomic left, Atomic right) {
		this.left = left;
		this.right = right;
		this.on = on;
		String var = TamagoFreshVar.Default.getName("__sod_");
		left.addVariables(new Logic(AccesMode.WRITE_SOD, var, on));
		right.addVariables(new Logic(AccesMode.READ_SOD, var, on));
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Sod) {
			Sod no = (Sod) obj;
			return left.equals(no.left) && right.equals(no.right);
		}
		return false;
	}

	public Atomic getLeft() {
		return left;
	}

	public OnEnum getOn() {
		return on;
	}

	public Atomic getRight() {
		return right;
	}


	public void setLeft(Atomic left) {
		this.left = left;
	}

	public void setOn(OnEnum on) {
		this.on = on;
	}

	public void setRight(Atomic right) {
		this.right = right;
	}
	
	/**
	 * @see fr.lacl.tamago.aca.term.Term#visit(fr.lacl.tamago.aca.term.util.ACATermVisitor)
	 */
	@Override
	public <R, E extends Throwable> R visit(ACATermVisitor<R,E> visitor) throws E {
		return visitor.visitSod(this);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sod [on=");
		builder.append(on);
		builder.append(", left=");
		builder.append(left);
		builder.append(", right=");
		builder.append(right);
		builder.append("]");
		return builder.toString();
	}

}
