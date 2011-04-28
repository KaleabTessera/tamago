
/**
 * 
 */
package tamagocc.generic.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import tamagocc.exception.TamagoCCException;
import tamagocc.exception.TamagoCCNotFoundState;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GAllow;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTransition;
import tamagocc.logger.TamagoCCLogger;

/**
 * @author Hakim Belhaouari
 *
 */
public class GIBehavior implements GBehavior {

	private Hashtable<String, GIState> states;
	private ArrayList<GTransition> transitions;
	private ArrayList<GState> defaultstates;
	
	public static GIBehavior getEmptyBehavior() {
		return new GIBehavior();
	}
	
	/**
	 * 
	 */
	public GIBehavior() {
		states = new Hashtable<String, GIState>();
		transitions = new ArrayList<GTransition>();
		defaultstates = new ArrayList<GState>();
	}
	
	public GIBehavior(GState defaultState,Collection<? extends GState> etats,Collection<GTransition> transitions)
		throws TamagoCCNotFoundState
	{
		this();
		for (GState state : etats) {
			addDeclaredState(state);
		}
		
		addDefaultState(defaultState);
		
		for (GTransition transition : transitions) {
			addTransition(transition);
		}
	}
		
	public GState addDeclaredState(GState state) {
		if(states.containsKey(state.getName())) {
			Iterator<GAllow> allows = state.getAllowsMethod();
			GIState istate = states.get(state.getName());
			while(allows.hasNext()) {
				istate.addAllow(allows.next());				
			}
			istate.setIsImplicit(state.isImplicit() && istate.isImplicit());
			return istate;
		}
		else {
			GIState gistate = new GIState(state);
			states.put(state.getName(), gistate);
			return gistate;
		}
	}
	
	public GState addInheritedState(GState state) {
		if(states.containsKey(state.getName())) {
			Iterator<GAllow> allows = state.getAllowsMethod();
			GIState istate = states.get(state.getName());
			while(allows.hasNext()) {
				istate.addAllow(allows.next());				
			}
			istate.setIsImplicit(state.isImplicit() && istate.isImplicit());
			return istate;
		}
		else {
			GIState gistate = new GIState(state);
			states.put(state.getName(), gistate);
			return gistate;
		}
	}
	
	public GState addIncludedState(GState state) {
		if(states.containsKey(state.getName())) {
			GIState gistate = states.get(state.getName());
			//if(gistate.isImplicit()) {
				Iterator<GAllow> allows = state.getAllowsMethod();
				while(allows.hasNext()) {
					gistate.addAllow(allows.next());				
				}
				gistate.setIsImplicit(state.isImplicit() && gistate.isImplicit());
				//TamagoCCLogger.println(3,"*Warning* an state already exist for the inclusion of the state "+state.getName());
				//TamagoCCLogger.println(3,"  However this state is implicit, we can not guess its origin.");
				return gistate;
			/*}
			else {
				TamagoCCLogger.println(3,"*Warning* an state already exist for the inclusion of the state "+state.getName());
				TamagoCCLogger.println(3,"  Rename with the prefix _inc_");
				gistate = new GIState(state,"_inc_"+state.getName());
				return addIncludedState(gistate);
			}*/
		}
		else {
			GIState gistate = new GIState(state);
			states.put(state.getName(), gistate);
			return gistate;
		}
	}
	
	public GState addDefaultState(GState state) throws TamagoCCNotFoundState {
		if(states.containsKey(state.getName())) {
			if(!defaultstates.contains(state))
				defaultstates.add(states.get(state.getName()));
			return states.get(state.getName());
		}
		else {
			TamagoCCLogger.println(3,"*Warning* : Unfound default state : "+state.getName());
			throw new TamagoCCNotFoundState("*Warning* : Unfound default state : "+state.getName());
		}
	}
	
	public void addTransition(GTransition transition) throws TamagoCCNotFoundState {
		GState ori = transition.getOrigin();
		GState dest = transition.getFinal();
		
		if(!states.containsKey(ori.getName()))
			throw new TamagoCCNotFoundState("Unfound state : "+ori.getName());
		if(!states.containsKey(dest.getName()))
			throw new TamagoCCNotFoundState("Unfound state : "+dest.getName());
		
		ori = states.get(ori.getName());
		dest = states.get(dest.getName());
		
		GTransition trans = new GITransition(transition,ori,dest);
		if(!transitions.contains(trans))
			transitions.add(trans);
	}
	
	public GState getState(GState s) throws TamagoCCNotFoundState {
		if(!states.containsKey(s.getName()))
			throw new TamagoCCNotFoundState("Unfound state : "+s.getName());
		else
			return states.get(s.getName());
	}
	
	public GState getState(String s) throws TamagoCCNotFoundState {
		if(!states.containsKey(s))
			throw new TamagoCCNotFoundState("Unfound state : "+s);
		else
			return states.get(s);
	}
	
	public Iterable<? extends GState> getStates() {
		return states.values();
	}
	
	public Iterable<? extends GState> getDefaultStates() {
		return defaultstates;
	}
	
	public Iterable<GTransition> getTransitions() {
		return transitions;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitBehavior(this);
	}

	public int countStates() {
		return states.values().size();
	}

	public int countTransitions() {
		return transitions.size();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("behavior {\n");
		sb.append(getDefaultStates().toString());
		sb.append("states:\n"+getStates().toString());
		sb.append("transitions:\n"+getTransitions().toString());
		sb.append("}");
		return sb.toString();
	}
}