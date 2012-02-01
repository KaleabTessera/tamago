package tamago.aca.term;

import java.util.ArrayList;
import java.util.Collection;

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
}
