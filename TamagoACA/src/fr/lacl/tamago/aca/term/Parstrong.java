/**
 * 
 */
package fr.lacl.tamago.aca.term;

import java.util.ArrayList;
import java.util.Collection;

import tamagocc.util.Pair;

import fr.lacl.tamago.aca.exception.TermMalFormedException;
import fr.lacl.tamago.aca.term.util.ACATermVisitor;

/**
 * @author Hakim Belhaouari
 *
 */
public class Parstrong implements Term {

	private ArrayList<Term> terms;
	
	/**
	 * 
	 */
	public Parstrong() {
		terms = new ArrayList<Term>();
	}
	
	public Parstrong(Collection<Term> terms) {
		this.terms = new ArrayList<Term>(terms);
	}

	public void addTerm(Term term) {
		terms.add(term);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Parstrong) {
			Parstrong no = (Parstrong) obj;
			return terms.containsAll(no.terms) && no.terms.containsAll(terms);
		}
		return false;
	}

	public ArrayList<Term> getTerms() {
		return terms;
	}
	
	public void removeTerm(Term term) {
		terms.remove(term);
	}
	
	/**
	 * @see fr.lacl.tamago.aca.term.Term#visit(fr.lacl.tamago.aca.term.util.ACATermVisitor)
	 */
	@Override
	public <R, E extends Throwable> R visit(ACATermVisitor<R,E> visitor) throws E {
		return visitor.visitParstrong(this);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Parstrong [terms=");
		builder.append(terms);
		builder.append("]");
		return builder.toString();
	}

	private void removeReferences(ArrayList<Term> remain, Term ref) {
		int i = 0;
		while(i < remain.size()) {
			if(remain.get(i) == ref)
				remain.remove(i);
			else
				i++;
		}
	}
	
	protected Pair<ArrayList<Term>,ArrayList<Atomic>> regroupAtomic(String action) throws TermMalFormedException {
		ArrayList<Term> remain = new ArrayList<Term>(terms);
		ArrayList<Atomic> atomics = new ArrayList<Atomic>();
		
		for (Term left : terms) {
			if(left instanceof Atomic) {
				Atomic a = (Atomic)left;
				if(a.getAction().equals(action)) {
					removeReferences(remain, left);
					atomics.add(a);
				}
			}
			
			if(left instanceof Sod) {
				Sod sod = (Sod)left;
				Atomic rleft = sod.getLeft();
				Atomic rright = sod.getRight();
				if(rleft.getAction().equals(action)) {
					removeReferences(remain, left);
					remain.add(rright);
					atomics.add(rleft);
				}
			}
			
			if(left instanceof Obl) {
				Obl sod = (Obl)left;
				Atomic rleft = sod.getLeft();
				Atomic rright = sod.getRight();
				if(rleft.getAction().equals(action)) {
					removeReferences(remain, left);
					remain.add(rright);
					atomics.add(rleft);
				}
			}
			
			if(left instanceof Parweak) {
				//Parweak parweak = (Parweak)left;
				//Pair<ArrayList<Term>,ArrayList<Atomic>> pair = parweak.regroupAtomic(action);
				throw new TermMalFormedException("parweak in parstrong => not yet supported");
			}
			if(left instanceof Parstrong)
				throw new TermMalFormedException("Not flattern parstrong expression (maybe a bug contact me if you doubt)");
			
		}
		return new Pair<ArrayList<Term>, ArrayList<Atomic>>(remain, atomics);
	}
	
	private Pair<Atomic, Term> cutAtomic(Atomic a) throws TermMalFormedException {
		Pair<ArrayList<Term>,ArrayList<Atomic>> pair = regroupAtomic(a.action);
		Atomic b = new Atomic(pair.r());
		if(pair.l().size() == 0)
			return new Pair<Atomic, Term>(b, null );
		else
			return new Pair<Atomic, Term>(b, new Parstrong(pair.l()));
	}
	
	// TODO: a revoir il n'y a pas encore la synchro dans cette implem
	public Pair<Atomic, Term> fetch(int i) throws TermMalFormedException {
		if(terms.size() == 0)
			throw new TermMalFormedException("Parstrong with empty branch");
		Term left = terms.get(i);
		
		if(left instanceof Atomic) {
			Atomic a = (Atomic)left;
			Pair<ArrayList<Term>,ArrayList<Atomic>> pair = regroupAtomic(a.action);
			Atomic b = new Atomic(pair.r());
			if(pair.l().size() == 0)
				return new Pair<Atomic, Term>(b, null );
			else
				return new Pair<Atomic, Term>(b, new Parstrong(pair.l()));
		}
		
		if(left instanceof Sod) {
			Sod sod = (Sod)left;
			Atomic rleft = sod.getLeft();
			return cutAtomic(rleft);
		}
		
		if(left instanceof Obl) {
			Obl sod = (Obl)left;
			Atomic rleft = sod.getLeft();
			return cutAtomic(rleft);
		}
		
		if(left instanceof Parweak) {
			throw new TermMalFormedException("Not yet supported (parweak in parstrong)");
			/*Pair<Atomic, Term> res = ((Parweak)left).fetch(0);
			if(res.getR() != null)
				dup.add(res.getR());
			return new Pair<Atomic, Term>(res.getL(), new Parweak(dup));*/
		}
		if(left instanceof Parstrong)
			throw new TermMalFormedException("Not flattern parstrong expression (maybe a bug contact me if you doubt)");
		// le bug ci dessus peut venir dans le cas ou on a un parweak qui inclut un parstrong et dedans un parweak
		// normalement ca devrait le faire  (ici le verso)
		
		throw new TermMalFormedException("Term not supported in fetch of a parweak");
	}
	
	public int size() {
		return terms.size();
	}
}
