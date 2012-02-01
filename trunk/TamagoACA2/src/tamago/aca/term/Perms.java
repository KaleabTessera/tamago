package tamago.aca.term;

import java.util.ArrayList;
import java.util.Collection;

public class Perms extends ArrayList<Quad>{

	public Perms(Collection<Quad> content) {
		super(content);
	}

	public Perms() {
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -1339571679827442551L;

	@Override
	public String toString() {
		return "perms := "+super.toString();
	}
}
