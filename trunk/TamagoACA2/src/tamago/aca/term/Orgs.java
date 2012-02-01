package tamago.aca.term;

import java.util.ArrayList;
import java.util.Collection;

public class Orgs extends ArrayList<String>{
	public Orgs(Collection<String> content) {
		super(content);
	}

	public Orgs() {
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 6218866703622950304L;

	@Override
	public String toString() {
		return "orgs := " +super.toString();
	}
}
