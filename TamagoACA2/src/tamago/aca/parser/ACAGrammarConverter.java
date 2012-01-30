package tamago.aca.parser;

import javapop.framework.parser.MaybeParse;
import javapop.utils.Pair;

public class ACAGrammarConverter {
	// -- Methods for default method values

	// -- Methods for default method values
	public String convNegQuadIdent(Object content) {
		Pair<MaybeParse<String>, String> r = ((Pair<MaybeParse<String>, String>)content);
		if(r.getFirst().hasResult())
			return r.getFirst().getResult().getResult() + r.getSecond();
		else
			return r.getSecond();
	}
	 public java.lang.String convQuadIdent(Object content) {
		return (String)content;
	}
	 public tamago.aca.term.Users convUsers(Object content) {
		
		 return null;
	}
	 public tamago.aca.term.Roles convRoles(Object content) {
		// TODO complete this method
		return null;
	}
	 public tamago.aca.term.Orgs convOrgs(Object content) {
		// TODO complete this method
		return null;
	}
	 public tamago.aca.term.Actions convActions(Object content) {
		// TODO complete this method
		return null;
	}
	 public tamago.aca.term.PlayItem convPlayItem(Object content) {
		// TODO complete this method
		return null;
	}
	 public tamago.aca.term.Play convPlay(Object content) {
		// TODO complete this method
		return null;
	}
	 public tamago.aca.term.Quad convQuad(Object content) {
		// TODO complete this method
		return null;
	}
	 public tamago.aca.term.Perms convPerms(Object content) {
		// TODO complete this method
		return null;
	}
	 public tamago.aca.term.Bans convBans(Object content) {
		// TODO complete this method
		return null;
	}
	 public tamago.aca.term.Obls convObls(Object content) {
		// TODO complete this method
		return null;
	}
	 public tamago.aca.term.Obls convSods(Object content) {
		// TODO complete this method
		return null;
	}
	 public tamago.aca.term.Info convInfo(Object content) {
		// TODO complete this method
		return null;
	}
	 public tamago.aca.term.ACA convertAcaTerm(Object content) {
		// TODO complete this method
		return null;
	}

}

