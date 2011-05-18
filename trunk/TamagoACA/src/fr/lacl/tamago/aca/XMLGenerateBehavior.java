package fr.lacl.tamago.aca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tamagocc.api.TAllow;
import tamagocc.api.TState;
import tamagocc.api.TTransition;
import tamagocc.impl.TIAllow;
import tamagocc.impl.TIState;
import tamagocc.impl.TITransition;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.Pair;
import fr.lacl.tamago.aca.convert.BridgeState;
import fr.lacl.tamago.aca.convert.SetMultiplicity;
import fr.lacl.tamago.aca.exception.ConvertACACDLException;
import fr.lacl.tamago.aca.exception.TermMalFormedException;
import fr.lacl.tamago.aca.term.mine.IAtomic;
import fr.lacl.tamago.aca.term.mine.IParstrong;
import fr.lacl.tamago.aca.term.mine.IParweak;
import fr.lacl.tamago.aca.visitor.ACAVAdapter;
import fr.lacl.tamago.aca.xmlterm.Action;
import fr.lacl.tamago.aca.xmlterm.Atomic;
import fr.lacl.tamago.aca.xmlterm.Obl;
import fr.lacl.tamago.aca.xmlterm.Parstrong;
import fr.lacl.tamago.aca.xmlterm.Parweak;
import fr.lacl.tamago.aca.xmlterm.Sod;
import fr.lacl.tamago.aca.xmlterm.Term;

public class XMLGenerateBehavior extends ACAVAdapter<Pair<BridgeState, Term>, ConvertACACDLException>  {
	public ArrayList<BridgeState> mapstate;
	public TIState init;
	public ArrayList<TIState> states;
	public ArrayList<TITransition> transitions;
	
	private Pair<BridgeState, Term> current;
	private int numstate;
	private String genStateName() {
		return ""+(numstate++);
	}
	public XMLGenerateBehavior(Term t) {
		mapstate = new ArrayList<BridgeState>();
		states = new ArrayList<TIState>();
		transitions = new ArrayList<TITransition>();
		// --
		init = new TIState(genStateName(), new ArrayList<TAllow>());
		states.add(init);
		BridgeState binit = new BridgeState(new SetMultiplicity(), init);
		mapstate.add(binit);
		current = new Pair<BridgeState, Term>(binit, t);
	}
	@Override
	public Pair<BridgeState, Term> vAtomic(Atomic term)
			throws ConvertACACDLException {
		String act = term.getAction().getName();
		SetMultiplicity sm = new SetMultiplicity(current.l().getListActions());
		sm.put(act);
		BridgeState next = getOrCreatState(sm);
		current.l().getState().addAllow(new TIAllow(act));
		getOrCreatTransition(current.l().getState().getState(), 
											  next.getState().getState(), act);
		
		Pair<BridgeState, Term> future = new Pair<BridgeState, Term>(next, null);
		current = future;
		return future;
	}
	private TITransition getOrCreatTransition(String state, String state2,
			String act) {
		int i = 0;
		while(i < transitions.size()) {
			TITransition t = transitions.get(i);
			if(t.getFrom().equals(state) && t.getTo().equals(state2) 
						&& t.getMethod().equals(act))
			{
				TamagoCCLogger.println(2,"Transition already exists: "+t.toString());
				return t;
			}
			i++;
		}
		TITransition t = new TITransition(state, state2, act);
		transitions.add(t);
		return t;
	}
	private BridgeState getOrCreatState(SetMultiplicity set) {
		for (BridgeState bs : mapstate) {
			if(bs.isSame(set)) {
				TamagoCCLogger.println(2, "State found for actions: "+bs);
				return bs;
			}
		}
		TamagoCCLogger.println(2, "State not found for actions: "+set);
		TIState renvoie = new TIState(genStateName(), new ArrayList<TAllow>());
		states.add(renvoie);
		SetMultiplicity sm = new SetMultiplicity(set);
		BridgeState bs = new BridgeState(sm, renvoie);
		mapstate.add(bs);
		return bs;
	}
	
	@Override
	public Pair<BridgeState, Term> vObl(Obl term)
			throws ConvertACACDLException {
		current = vTerm(term.getLeft());
		Pair<BridgeState, Term> res = vTerm(term.getRight());
		current = res;
		return res;
	}
	
	@Override
	public Pair<BridgeState, Term> vParstrong(Parstrong term)
			throws ConvertACACDLException {
		List<Term> list =  term.getChild();
		Pair<BridgeState, Term> backup = current;
		for(int i= 0;i < list.size();i++) {
			current = backup;
			Pair<List<Term>, List<Atomic>> divide;
			try {
				divide = divideSync(list,i);
				
			} catch (TermMalFormedException e) {
				throw new ConvertACACDLException(e);
			}
			if(divide.getR().size() == 0)
				throw new ConvertACACDLException("None atomic term in parstrong?");
			Atomic unified = unifyAtomic(divide.r());
			Parstrong newparstrong = new IParstrong(divide.l());
			Term t = new Term();
			t.setParstrong(newparstrong);
			Pair<BridgeState, Term> future = vAtomic(unified);
			future.setR(t);
			current = future;
			vParstrong(newparstrong);
		}	
		return null;
	}
	private Atomic unifyAtomic(List<Atomic> r) {
		Atomic at = new IAtomic(r.get(0).getAction().getName(), r);
		return at;
	}
	
	private Action searchLefterAction(Term t) throws TermMalFormedException {
		if(t.getObl() != null) {
			return searchLefterAction(t.getObl().getLeft());
		}
		else if(t.getParstrong() != null) {
			return searchLefterAction(t.getParstrong().getChild().get(0));
		}
		else if(t.getParweak() != null) {
			return searchLefterAction(t.getParweak().getChild().get(0));
		}
		else if(t.getSod() != null) {
			return searchLefterAction(t.getSod().getLeft());
		}
		else if(t.getAtomic() != null) {
			return t.getAtomic().getAction();
		}
		else
			throw new TermMalFormedException();
	}
	
	private void removeDuplicate(Term t, Action action,ArrayList<Term> terms,ArrayList<Atomic> atomics) throws TermMalFormedException {
		if(t.getObl() != null) {
			Atomic at = t.getObl().getLeft().getAtomic();
			if(at != null) {
				if(at.getAction().getName().equals(action.getName())) {
					terms.add(t.getObl().getRight());
					atomics.add(at);
				}
				else
					terms.add(t);
			}
			else
				throw new TermMalFormedException("NOT YET SUPPORTED (Left complex term on a OBL)");
		}
		else if(t.getParstrong() != null) {
			throw new TermMalFormedException("NOT YET SUPPORTED (PARSTRONG IN PARSTRONG)");
		}
		else if(t.getParweak() != null) {
			List<Term> list =  t.getParweak().getChild();
			throw new TermMalFormedException("NOT YET SUPPORTED (PARWEAK)");
		}
		else if(t.getSod() != null) {
			Atomic at = t.getSod().getLeft().getAtomic();
			if(at != null) {
				if(at.getAction().getName().equals(action.getName())) {
					terms.add(t.getSod().getRight());
					atomics.add(at);
				}
				else
					terms.add(t);
			}
			else
				throw new TermMalFormedException("NOT YET SUPPORTED (Left complex term on a SOD)");
		}
		else if(t.getAtomic() != null) {
			if(t.getAtomic().getAction().getName().equals(action.getName())) {
				atomics.add(t.getAtomic());
			}
		}
		else
			throw new TermMalFormedException();
	}
	
	private Pair<List<Term>, List<Atomic>> divideSync(List<Term> list, int i) throws TermMalFormedException {
		Term t = list.get(i);
		Action target = searchLefterAction(t);
		ArrayList<Term> res = new ArrayList<Term>();
		ArrayList<Atomic> ats = new ArrayList<Atomic>();
		for (Term term : list) {
			removeDuplicate(term, target, res, ats);
		}
		return new Pair<List<Term>, List<Atomic>>(res, ats);
	}
	@Override
	public Pair<BridgeState, Term> vParweak(Parweak term)
			throws ConvertACACDLException {
		List<Term> list =  term.getChild();
		Pair<BridgeState, Term> backup = current;
		for (int i=0; i < list.size();i++) {
			current = backup;
			List<Term> dup = new ArrayList<Term>(list);
			Term t = dup.remove(i);
			if(t.getAtomic() != null) {
				Pair<BridgeState, Term> future = vAtomic(t.getAtomic());
				Parweak newtermweak = new IParweak(dup);
				Term newterm = new Term();
				newterm.setParweak(newtermweak);
				future.setR(newterm);
				// deja pusher dans le vAtomic
				current = future;
				vParweak(newtermweak);
			}
			else if(t.getObl() != null) {
				Pair<BridgeState, Term> future = vTerm(t.getObl().getLeft());
				dup.add(t.getObl().getRight());
				Parweak newtermweak = new IParweak(dup);
				Term newterm = new Term();
				newterm.setParweak(newtermweak);
				future.setR(newterm);
				current = future;
				vParweak(newtermweak);
			}
			else if(t.getSod() != null) {
				Pair<BridgeState, Term> future = vTerm(t.getSod().getLeft());
				dup.add(t.getSod().getRight());
				Parweak newtermweak = new IParweak(dup);
				Term newterm = new Term();
				newterm.setParweak(newtermweak);
				future.setR(newterm);
				current = future;
				vParweak(newtermweak);
			}
			else if(t.getParstrong() != null) {
				
			}
			else if(t.getParweak() != null) {
				throw new ConvertACACDLException("not flattern expression");
			}
		}
		return null;
	}
	@Override
	public Pair<BridgeState, Term> vSod(Sod term)
			throws ConvertACACDLException {
		current = vTerm(term.getLeft());
		Pair<BridgeState, Term> res = vTerm(term.getRight());
		current = res;
		return res;
	}
	public TIState getInit() {
		return init;
	}
	public Collection<TState> getStates() {
		Collection<TState> res = new ArrayList<TState>(states);
		return res;
	}
	public Collection<TTransition> getTransitions() {
		Collection<TTransition> res = new ArrayList<TTransition>(transitions);
		return res;
	}

	
}