/**
 * 
 */
package fr.lacl.tamago.aca.term;

/**
 * @author Hakim Belhaouari
 *
 */
public class Logic {
	private AccesMode mode;
	private String variable;
	private OnEnum on;
	
	/**
	 * 
	 */
	public Logic(AccesMode mode, String variable, OnEnum on) {
		this.mode = mode;
		this.variable = variable;
		this.on = on;
	}

	public AccesMode getMode() {
		return mode;
	}

	public void setMode(AccesMode mode) {
		this.mode = mode;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public OnEnum getOn() {
		return on;
	}

	public void setOn(OnEnum on) {
		this.on = on;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Logic [");
		if (mode != null) {
			builder.append("mode=");
			builder.append(mode);
			builder.append(", ");
		}
		if (on != null) {
			builder.append("on=");
			builder.append(on);
			builder.append(", ");
		}
		if (variable != null) {
			builder.append("variable=");
			builder.append(variable);
		}
		builder.append("]");
		return builder.toString();
	}

}
