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
public class Parweak implements Term {

	private ArrayList<Term> terms;
	
	/**
	 * 
	 */
	public Parweak() {
		terms = new ArrayList<Term>();
	}
	
	public Parweak(Collection<Term> terms) {
		this.terms = new ArrayList<Term>(terms);
	}

	public void addTerm(Term term) {
		terms.add(term);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Parweak) {
			Parweak no = (Parweak) obj;
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
	
	public void setTerms(ArrayList<Term> terms) {
		this.terms = terms;
	}
	
	
	public Pair<Atomic, Term> fetch(int i) throws TermMalFormedException {
		if(terms.size() == 0)
			throw new TermMalFormedException("Parweak with empty branch");
		ArrayList<Term> dup = new ArrayList<Term>(terms);
		Term left = dup.remove(i);
		if(left instanceof Atomic) {
			if(dup.size() == 0)
				return new Pair<Atomic, Term>((Atomic) left, null );
			else
				return new Pair<Atomic, Term>((Atomic) left, new Parweak(dup));
		}
		
		if(left instanceof Sod) {
			Sod sod = (Sod)left;
			Atomic rleft = sod.getLeft();
			Atomic rright = sod.getRight();
			dup.add(rright);
			return new Pair<Atomic, Term>(rleft, new Parweak(dup));
		}
		
		if(left instanceof Obl) {
			Obl sod = (Obl)left;
			Atomic rleft = sod.getLeft();
			Atomic rright = sod.getRight();
			dup.add(rright);
			return new Pair<Atomic, Term>(rleft, new Parweak(dup));
		}
		
		if(left instanceof Parstrong) {
			Pair<Atomic, Term> res = ((Parstrong)left).fetch(0);
			if(res.getR() != null)
				dup.add(res.getR());
			return new Pair<Atomic, Term>(res.getL(), new Parweak(dup));
		}
		if(left instanceof Parweak)
			throw new TermMalFormedException("Not flattern parweak expression (maybe a bug contact me if you doubt)");
		// le bug ci dessus peut venir dans le cas ou on a un parweak qui inclut un parstrong et dedans un parweak
		// normalement ca devrait le faire 
		
		throw new TermMalFormedException("Term not supported in fetch of a parweak");
	}
	
	/**
	 * @see fr.lacl.tamago.aca.term.Term#visit(fr.lacl.tamago.aca.term.util.ACATermVisitor)
	 */
	@Override
	public <R, E extends Throwable> R visit(ACATermVisitor<R,E> visitor) throws E {
		return visitor.visitParweak(this);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Parweak [terms=");
		builder.append(terms);
		builder.append("]");
		return builder.toString();
	}

	public int size() {
		return terms.size();
	}

	protected Pair<ArrayList<Term>,ArrayList<Atomic>> regroupAtomic( String action) throws TermMalFormedException {
		ArrayList<Term> remain = new ArrayList<Term>(terms);
		ArrayList<Atomic> atomics = new ArrayList<Atomic>();
		
		for (Term left : terms) {
			if(left instanceof Atomic) {
				Atomic a = (Atomic)left;
				if(a.getAction().equals(action)) {
					remain.remove(left);
					atomics.add(a);
				}
			}
			
			if(left instanceof Sod) {
				Sod sod = (Sod)left;
				Atomic rleft = sod.getLeft();
				Atomic rright = sod.getRight();
				if(rleft.getAction().equals(action)) {
					remain.remove(left);
					remain.add(rright);
					atomics.add(rleft);
				}
			}
			
			if(left instanceof Obl) {
				Obl sod = (Obl)left;
				Atomic rleft = sod.getLeft();
				Atomic rright = sod.getRight();
				if(rleft.getAction().equals(action)) {
					remain.remove(left);
					remain.add(rright);
					atomics.add(rleft);
				}
			}
			
			if(left instanceof Parweak) {
				throw new TermMalFormedException("Not flattern parweak expression (maybe a bug contact me if you doubt)");
			}
			if(left instanceof Parstrong) {
				throw new TermMalFormedException("parstrong in parweak => not yet supported");
			}
				
			
		}
		return new Pair<ArrayList<Term>, ArrayList<Atomic>>(remain, atomics);
	}
}
