package tamago.aca.term;

import java.util.ArrayList;
import java.util.Collection;

public class Play extends ArrayList<PlayItem>{

	public Play(Collection<PlayItem> content) {
		super(content);
	}

	public Play() {
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 5692256431452891183L;

	@Override
	public String toString() {
		return "play := " +super.toString();
	}
}
