/**
 * 
 */
package fr.lacl.tamago.aca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

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
import fr.lacl.tamago.aca.exception.ACATermVisitorException;
import fr.lacl.tamago.aca.exception.TermMalFormedException;
import fr.lacl.tamago.aca.term.Atomic;
import fr.lacl.tamago.aca.term.Logic;
import fr.lacl.tamago.aca.term.Obl;
import fr.lacl.tamago.aca.term.Parstrong;
import fr.lacl.tamago.aca.term.Parweak;
import fr.lacl.tamago.aca.term.Sod;
import fr.lacl.tamago.aca.term.Term;
import fr.lacl.tamago.aca.term.util.ACATermVisitor;

/**
 * @author Hakim Belhaouari
 *
 */
public class GenerateBehavior implements ACATermVisitor<Pair<BridgeState, Term>,ACATermVisitorException> {

	public ArrayList<BridgeState> mapstate;
	public TIState init;
	public ArrayList<TIState> states;
	public ArrayList<TITransition> transitions;
	private boolean runned;
	private Term term;
	
	private HashMap<String, ArrayList<Pair<String, Logic>>> conditions;
	
	
	private Pair<BridgeState,Term> current;
	private int numstate;
	private String genStateName() {
		return ""+(numstate++);
	}
	
	/**
	 * 
	 */
	public GenerateBehavior(Term t) {
		mapstate = new ArrayList<BridgeState>();
		states = new ArrayList<TIState>();
		transitions = new ArrayList<TITransition>();
		term = t;
		// --
		init = new TIState(genStateName(), new ArrayList<TAllow>());
		states.add(init);
		BridgeState binit = new BridgeState(new SetMultiplicity(), init);
		mapstate.add(binit);
		current = new Pair<BridgeState, Term>(binit, t);
		conditions = new HashMap<String, ArrayList<Pair<String,Logic>>>();
	}

	/**
	 * @see fr.lacl.tamago.aca.term.util.ACATermVisitor#visitAtomic(fr.lacl.tamago.aca.term.Atomic)
	 */
	@Override
	public Pair<BridgeState, Term> visitAtomic(Atomic term) throws ACATermVisitorException {
		String act = term.getAction();
		SetMultiplicity sm = new SetMultiplicity(current.l().getListActions());
		sm.put(act);
		BridgeState next = getOrCreatState(sm);
		addLogicToMethod(act,next.getState().getState(), term.getLogics());
		next.addLogics(term.getLogics());
		current.l().getState().addAllow(new TIAllow(act));
		getOrCreatTransition(current.l().getState().getState(), 
											  next.getState().getState(), act);
		
		Pair<BridgeState, Term> future = new Pair<BridgeState, Term>(next, null);
		current = future;
		return future;
	}

	private void addLogicToMethod(String act, String statename, ArrayList<Logic> logics) {
		if(!conditions.containsKey(act))
			conditions.put(act, new ArrayList<Pair<String,Logic>>());
		for (Logic logic : logics) {
			conditions.get(act).add(new Pair<String,Logic>(statename,logic));
		}
		
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
	
	/**
	 * @see fr.lacl.tamago.aca.term.util.ACATermVisitor#visitObl(fr.lacl.tamago.aca.term.Obl)
	 */
	@Override
	public Pair<BridgeState, Term> visitObl(Obl term) throws ACATermVisitorException {
		current = term.getLeft().visit(this);
		Pair<BridgeState, Term> res = term.getRight().visit(this);
		current = res;
		return res;
	}

	/**
	 * @see fr.lacl.tamago.aca.term.util.ACATermVisitor#visitParstrong(fr.lacl.tamago.aca.term.Parstrong)
	 */
	@Override
	public Pair<BridgeState, Term> visitParstrong(Parstrong term) throws ACATermVisitorException {
		Pair<BridgeState, Term> backup = current;
		for(int i= 0; i < term.size();i++) {
			current = backup;
			try {
				Pair<Atomic, Term> res = term.fetch(i);
				Pair<BridgeState, Term> future = res.l().visit(this);
				future.setR(res.r());
				current = future;
				if(res.r() == null)
					return future;
				else
					future.r().visit(this);
				
			} catch (TermMalFormedException e) {
				throw new ACATermVisitorException(e);
			}
		}
		return current;
	}
	
	/**
	 * @see fr.lacl.tamago.aca.term.util.ACATermVisitor#visitParweak(fr.lacl.tamago.aca.term.Parweak)
	 */
	@Override
	public Pair<BridgeState, Term> visitParweak(Parweak term) throws ACATermVisitorException {
		Pair<BridgeState, Term> backup = current;
		for(int i= 0; i < term.size();i++) {
			current = backup;
			try {
				Pair<Atomic, Term> res = term.fetch(i);
				Pair<BridgeState, Term> future = res.l().visit(this);
				future.setR(res.r());
				current = future;
				if(res.r() == null)
					return future;
				else
					future.r().visit(this);
			} catch (TermMalFormedException e) {
				throw new ACATermVisitorException(e);
			}
		}
		return current;
	}

	/**
	 * @see fr.lacl.tamago.aca.term.util.ACATermVisitor#visitSod(fr.lacl.tamago.aca.term.Sod)
	 */
	@Override
	public Pair<BridgeState, Term> visitSod(Sod term) throws ACATermVisitorException {
		current = term.getLeft().visit(this);
		Pair<BridgeState, Term> res = term.getRight().visit(this);
		current = res;
		return res;
	}
	
	public void convert() throws ACATermVisitorException {
		if(!runned) {
			term.visit(this);
			runned = true;
		}
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

	public ArrayList<BridgeState> getBridgeStates() {
		return mapstate;
	}

	public HashMap<String, ArrayList<Pair<String, Logic>>> getConditions() {
		return conditions;
	}
}
