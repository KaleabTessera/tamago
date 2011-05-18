/**
 * 
 */
package fr.lacl.tamago.aca.term.util;

import java.util.ArrayList;
import java.util.List;

import fr.lacl.tamago.aca.exception.XmlTermToTermException;
import fr.lacl.tamago.aca.term.Obl;
import fr.lacl.tamago.aca.term.OnEnum;
import fr.lacl.tamago.aca.xmlterm.Atomic;
import fr.lacl.tamago.aca.xmlterm.Onenum;
import fr.lacl.tamago.aca.xmlterm.Parstrong;
import fr.lacl.tamago.aca.xmlterm.Parweak;
import fr.lacl.tamago.aca.xmlterm.Processus;
import fr.lacl.tamago.aca.xmlterm.Sod;
import fr.lacl.tamago.aca.xmlterm.Term;

/**
 * @author Hakim Belhaouari
 *
 */
public final class XmlTermToTerm {
	
	
	public static fr.lacl.tamago.aca.term.Processus convert(Processus p) throws XmlTermToTermException {
		ArrayList<fr.lacl.tamago.aca.term.Atomic> perms =  convertPermissions(p.getPermissions().getAtomic());
		ArrayList<fr.lacl.tamago.aca.term.Atomic> bans =  convertBans(p.getBans().getAtomic());
		fr.lacl.tamago.aca.term.Term term = convertTerm(p.getComplex());
		int pos = p.getName().lastIndexOf(".");
		String name = p.getName().substring(pos+1);
		String module = p.getName().substring(0, pos);
		return new fr.lacl.tamago.aca.term.Processus(name,module,perms,bans,term);
	}

	private static fr.lacl.tamago.aca.term.Term convertTerm(Term complex) throws XmlTermToTermException {
		if(complex.getAtomic() != null)
			return convertAtomic(complex.getAtomic());
		else if(complex.getObl() != null)
			return convertObl(complex.getObl());
		else if(complex.getSod() != null)
			return convertSod(complex.getSod());
		else if(complex.getParstrong() != null)
			return convertParstrong(complex.getParstrong());
		else if(complex.getParweak() != null)
			return convertParweak(complex.getParweak());
		throw new XmlTermToTermException("unknow XML term");
	}

	private static fr.lacl.tamago.aca.term.Term convertParweak(
			Parweak parweak) throws XmlTermToTermException {
		ArrayList<fr.lacl.tamago.aca.term.Term> terms = new ArrayList<fr.lacl.tamago.aca.term.Term>();
		for (Term t : parweak.getChild()) {
			terms.add(convertTerm(t));
		}
		return new fr.lacl.tamago.aca.term.Parweak(terms);
	}

	private static fr.lacl.tamago.aca.term.Term convertParstrong(
			Parstrong parstrong) throws XmlTermToTermException {
		ArrayList<fr.lacl.tamago.aca.term.Term> terms = new ArrayList<fr.lacl.tamago.aca.term.Term>();
		for (Term t : parstrong.getChild()) {
			terms.add(convertTerm(t));
		}
		return new fr.lacl.tamago.aca.term.Parstrong(terms);
	}

	private static fr.lacl.tamago.aca.term.Term convertSod(Sod sod) {
		return new fr.lacl.tamago.aca.term.Sod(convertOn(sod.getOn()), 
				convertAtomic(sod.getLeft().getAtomic()),
				convertAtomic(sod.getRight().getAtomic()));
	}

	private static fr.lacl.tamago.aca.term.Term convertObl(
			fr.lacl.tamago.aca.xmlterm.Obl obl) {
		return new Obl(convertOn(obl.getOn()), 
				convertAtomic(obl.getLeft().getAtomic()),
				convertAtomic(obl.getRight().getAtomic()));
	}

	private static OnEnum convertOn(Onenum on) {
		return OnEnum.values()[on.ordinal()];
	}

	private static fr.lacl.tamago.aca.term.Atomic convertAtomic(Atomic atomic) {
		String action = atomic.getAction().getName();
		fr.lacl.tamago.aca.term.Atomic res = new fr.lacl.tamago.aca.term.Atomic(action);
		
		if(atomic.getUser() != null)
			res.addUser(atomic.getUser().getValue(), atomic.isForbidden());
		if(atomic.getRole() != null)
			res.addRole(atomic.getRole().getValue(), atomic.isForbidden());
		if(atomic.getOrg() != null)
			res.addOrg(atomic.getOrg().getValue(), atomic.isForbidden());
		
		return res;
	}

	private static ArrayList<fr.lacl.tamago.aca.term.Atomic> convertBans(
			List<Atomic> atomic) {
		ArrayList<fr.lacl.tamago.aca.term.Atomic> at = new ArrayList<fr.lacl.tamago.aca.term.Atomic>();
		for (Atomic a : atomic) {
			at.add(convertAtomic(a));
		}
		return at;
	}

	private static ArrayList<fr.lacl.tamago.aca.term.Atomic> convertPermissions(
			List<Atomic> atomic) {
		ArrayList<fr.lacl.tamago.aca.term.Atomic> at = new ArrayList<fr.lacl.tamago.aca.term.Atomic>();
		for (Atomic a : atomic) {
			at.add(convertAtomic(a));
		}
		return at;
	}

}
