/**
 * 
 */
package tamagocc.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GMergeState;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GTransition;
import tamagocc.generic.impl.GIBehavior;
import tamagocc.generic.impl.GIMergeState;
import tamagocc.generic.impl.GIState;
import tamagocc.generic.impl.GITransition;

/**
 * This class implements the algorithm of the synchronized product of automaton.
 * The current algorithm is based on travel of all transitions.
 *  
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCSynchronizeProduct {

	private GIBehavior behavior;
	private GTamago tamago;
	private ArrayList<GState> visited;
	private Hashtable<GState, GState> mirrors;
	
	/**
	 * Unique constructor which take the entity as argument.
	 *  The entity should contain a behavior
	 */
	public TamagoCCSynchronizeProduct(GTamago tamago) {
		this.tamago = tamago;
		behavior = null;
		visited = new ArrayList<GState>();
		mirrors = new Hashtable<GState, GState>();
	}
	
	/**
	 * Construct the product of behavior provided in the current contract.
	 * @return the resulted product behavior.
	 * @throws TamagoCCException
	 */
	public GBehavior getProduct() throws TamagoCCException {
		if(behavior == null) {
			merge();
		}
		return behavior;
	}


	private void merge() throws TamagoCCException {
		behavior = GIBehavior.getEmptyBehavior();
		
		GState initState = mergeState(tamago.getBehavior().getDefaultStates());
		GState mirror = cloneState(initState);
		behavior.addDeclaredState(mirror);
		traversal(initState);
		behavior.addDefaultState(mirror);
	}

	private void traversal(GState state) throws TamagoCCException {
		if(visited.contains(state))
			return;
		else {
			visited.add(state);
		}
		
		
		Iterator<GTransition> transitions = state.getOutgoingTransitions();
		while(transitions.hasNext()) {
			GTransition transition = transitions.next();
			
			GState nstate = subState(state,transition);
			GState mirror_ori = mirrors.get(state);
			GState mirror_dst = cloneState(nstate);
			behavior.addDeclaredState(mirror_dst);
			
			//GTransition trans = newTrans(transition,state,nstate);
			GTransition trans = newTrans(transition,mirror_ori,mirror_dst);
			
			behavior.addTransition(trans);
			traversal(nstate);
		}
	}
	
	private GTransition newTrans(GTransition transition, GState state, GState nstate) {
		GITransition trans = new GITransition(state,nstate,
				transition.getMethodID(),
				transition.getCondition());
		return trans;
	}


	private GState subState(GState state, GTransition transition) {
		if(state instanceof GMergeState) {
			GMergeState ms = (GMergeState)state;
			
			GIMergeState m = new GIMergeState(tamago,new ArrayList<GState>());
			
			Iterator<GState> states = ms.getSubStates();
			while(states.hasNext()) {
				GState s = states.next();
				if(s.equals(transition.getOrigin())) {
					m.addState(transition.getFinal());
				}
				else
					m.addState(s);
			}
			return m;
		}
		else
			return transition.getFinal();
	}


	private GState mergeState(Iterable<? extends GState> states) throws TamagoCCException{
		GIMergeState m  = new GIMergeState(tamago,new ArrayList<GState>());
		for (GState state : states) {
			m.addState(state);
		}
		
		if(m.getCountSubStates() == 1) {
			return m.getSubStates().next();
		}
		else if (m.getCountSubStates() == 0)
			throw new TamagoCCException("No initial states for the behavior");
		else {
			return m;
		}
	}


	private GState cloneState(GState state) {
		if(state instanceof GMergeState) {
			GMergeState m = (GMergeState) state;
			GIMergeState renvoie = new GIMergeState(m.getParent(),new ArrayList<GState>());
			Iterator<GState> states = m.getSubStates();
			while(states.hasNext()) {
				GState s1 = states.next();
				GState s2 = cloneState(s1);
				mirrors.put(s1, s2);
				renvoie.addState(s2);
			}
			mirrors.put(state, renvoie);
			return renvoie;
		}
		else {
			if(mirrors.containsKey(state))
				return mirrors.get(state);
			else {
				GState renvoie = new GIState(state);
				mirrors.put(state, renvoie);
				return renvoie;
			}
		}
	}
}
