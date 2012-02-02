package tamago.aca.parser;

import java.util.ArrayList;
import java.util.Collection;

import tamago.aca.term.ACA;
import tamago.aca.term.Actions;
import tamago.aca.term.Bans;
import tamago.aca.term.Info;
import tamago.aca.term.Obl;
import tamago.aca.term.Obls;
import tamago.aca.term.OnCA;
import tamago.aca.term.Orgs;
import tamago.aca.term.Perms;
import tamago.aca.term.Play;
import tamago.aca.term.PlayItem;
import tamago.aca.term.Quad;
import tamago.aca.term.Roles;
import tamago.aca.term.Sod;
import tamago.aca.term.Sods;
import tamago.aca.term.Users;
import javapop.framework.parser.MaybeParse;
import javapop.framework.parser.expr.InfixNode;
import javapop.framework.parser.expr.OperandNode;
import javapop.framework.parser.expr.generic.CInfixNode;
import javapop.utils.Decuple;
import javapop.utils.Pair;
import javapop.utils.Quadruple;
import javapop.utils.Triple;

@SuppressWarnings("unchecked")
public class ACAGrammarConverter {
	// -- Methods for default method values

	// -- Methods for default method values
	public String convNegQuadIdent(Object content) {
		/*Pair<MaybeParse<String>, String> r = ((Pair<MaybeParse<String>, String>)content);
		if(r.getFirst().hasResult())
			return r.getFirst().getResult().getResult() + r.getSecond();
		else
			return r.getSecond();
			*/
		return (String)content;
	}
	 public java.lang.String convQuadIdent(Object content) {
		return (String)content;
	}
	 public tamago.aca.term.Users convUsers(Object content) {
		return new Users((Collection<String>)content);
	}
	 public tamago.aca.term.Roles convRoles(Object content) {
		return new Roles((Collection<String>)content);
	}
	 public tamago.aca.term.Orgs convOrgs(Object content) {
		return new Orgs((Collection<String>)content);
	}
	 public tamago.aca.term.Actions convActions(Object content) {
		return new Actions((Collection<String>)content);
	}
	 public tamago.aca.term.PlayItem convPlayItem(Object content) {
		Triple<String, String, String> r = (Triple<String, String, String>)content;
		return new PlayItem(r.getFirst(), r.getSecond(), r.getThird());
	}
	 public tamago.aca.term.Play convPlay(Object content) {
		return new Play((Collection<PlayItem>)content);
	}
	 public tamago.aca.term.Quad convQuad(Object content) {
		Quadruple<String, String, String, String> r = (Quadruple<String, String, String, String>)content;
		return new Quad(r.getFirst(), r.getSecond(), r.getThird(), r.getFourth());
	}
	 public tamago.aca.term.Perms convPerms(Object content) {
		return new Perms((Collection<Quad>)content);
	}
	 public tamago.aca.term.Bans convBans(Object content) {
		return new Bans((Collection<Quad>)content);
	}
	 
	 public Obl convObl(Object content) {
		 Triple<String, Quad, Quad> r = (Triple<String, Quad, Quad>) content;
		 OnCA ca = OnCA.USER;
		 if("user".compareToIgnoreCase(r.getFirst()) == 0)
			 ca = OnCA.USER;
		 else if("role".compareToIgnoreCase(r.getFirst()) == 0)
			 ca = OnCA.ROLE;
		 else if("org".compareToIgnoreCase(r.getFirst()) == 0 || "organisation".compareToIgnoreCase(r.getFirst()) == 0)
			 ca = OnCA.ORG;
		 if(r.getSecond().hasNegField() || r.getThird().hasNegField())
			 throw new RuntimeException("The left and right branch in obl must not have a negation");
		 
		 Obl o = new Obl(ca,r.getSecond(),r.getThird());
		 return o;
	 }
	 public tamago.aca.term.Obls convObls(Object content) {
		return new Obls((Collection<Obl>)content);
	}
	 
	 public Sod convSod(Object content) {
		 Triple<String, Quad, Quad> r = (Triple<String, Quad, Quad>) content;
		 OnCA ca = OnCA.USER;
		 if("user".compareToIgnoreCase(r.getFirst()) == 0)
			 ca = OnCA.USER;
		 else if("role".compareToIgnoreCase(r.getFirst()) == 0)
			 ca = OnCA.ROLE;
		 else if("org".compareToIgnoreCase(r.getFirst()) == 0 || "organisation".compareToIgnoreCase(r.getFirst()) == 0)
			 ca = OnCA.ORG;
		 if(r.getSecond().hasNegField() || !r.getThird().hasNegField())
			 throw new RuntimeException("The left must not have a negation and the right branch must have a negation in SOD");
		 
		 return new Sod(ca,r.getSecond(),r.getThird());
	 } 
	 public tamago.aca.term.Sods convSods(Object content) {
		return new Sods((Collection<Sod>)content);
	}
	 public tamago.aca.term.Info convInfo(Object content) {
		Pair<String, String> r = (Pair<String, String>)content;
		return new Info(r.getFirst(), r.getSecond());
	}
	 public tamago.aca.term.ACA convertAcaTerm(Object content) {
		ArrayList r = (ArrayList) content;
		return new ACA(r);
	}
	 
	 public InfixNode convOperatorInfix(Object op) {
		 return new ProcessOperator((String) op);
	 }
	 
	 public OperandNode convOperandAction(Object action) {
		 return new ProcesOpAction((String)action);
	 }

}

