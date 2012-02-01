package tamago.aca.term;

import java.util.ArrayList;
import java.util.Collection;

public class Roles extends ArrayList<String>{

	public Roles(Collection<String> content) {
		super(content);
	}

	public Roles() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3624318738453003540L;

	
	@Override
	public String toString() {
		return "roles := "+super.toString();
	}
}
