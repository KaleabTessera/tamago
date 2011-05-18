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
public class Obl implements Term {

	private Atomic left;
	private Atomic right;
	private OnEnum on;
	/**
	 * 
	 */
	public Obl(OnEnum on, Atomic left, Atomic right) {
		this.left = left;
		this.right = right;
		this.on = on;
		String var = TamagoFreshVar.Default.getName("__obl_");
		left.addVariables(new Logic(AccesMode.WRITE_OBL, var, on));
		right.addVariables(new Logic(AccesMode.READ_OBL, var, on));
		
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Obl) {
			Obl no = (Obl) obj;
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

	@Override
	public <R, E extends Throwable> R visit(ACATermVisitor<R,E> visitor) throws E {
		return visitor.visitObl(this);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Obl [left=");
		builder.append(left);
		builder.append(", on=");
		builder.append(on);
		builder.append(", right=");
		builder.append(right);
		builder.append("]");
		return builder.toString();
	}
}
