package tamago.aca.term;

import java.util.ArrayList;
import java.util.Collection;

public class Obls extends ArrayList<Obl>{
	public Obls(Collection<Obl> content) {
		super(content);
	}

	public Obls() {
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 3926833777425982898L;

	@Override
	public String toString() {
		return "obls := "+super.toString();
	}
}
