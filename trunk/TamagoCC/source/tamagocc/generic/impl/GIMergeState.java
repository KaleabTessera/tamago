/**
 * 
 */
package tamagocc.generic.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GAllow;
import tamagocc.generic.api.GMergeState;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.generic.api.GTransition;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class GIMergeState implements GMergeState {

	private ArrayList<GState> states;
	protected GTamagoEntity parent;
	protected boolean implicit;
	
	/**
	 * 
	 * @param states
	 */
	public GIMergeState(GTamagoEntity parent,Collection<GState> states) {
		this.states = new ArrayList<GState>();
		this.parent = parent;
				
		Iterator<GState> istates = states.iterator();
		while(istates.hasNext()) {
			addState((GState) istates.next());			
		}
		
	}
	
	public GIMergeState(GMergeState s) {
		this.states = new ArrayList<GState>();
		this.parent = s.getParent();
		
		Iterator<GState> oldstates = s.getSubStates();
		while(oldstates.hasNext()) {
			GState state = (GState)oldstates.next();
			if(!this.states.contains(state))
				this.states.add(state);
		}
		
	}
	
	private GIMergeState(GMergeState s,GState s1,GState s2) {
		this.states = new ArrayList<GState>();
		this.parent = s.getParent();
		
		Iterator<GState> oldstates = s.getSubStates();
		while(oldstates.hasNext()) {
			GState state = (GState)oldstates.next();
			if(state.equals(s1)) 
				state = s2;
			if(!this.states.contains(state)) {
				this.states.add(state);
			}
		}
		
	}
	
	/**
	 * @see tamagocc.generic.api.GMergeState#getSubStates()
	 */
	public Iterator<GState> getSubStates() {
		return states.iterator();
	}

	/**
	 * @see tamagocc.generic.api.GMergeState#containsState(tamagocc.generic.api.GState)
	 */
	public boolean containsState(GState state) {
		Iterator<GState> states = this.states.iterator();
		while(states.hasNext()) {
			GState s =(GState)states.next();
			if(s.equals(state))
				return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @see tamagocc.generic.api.GState#getName()
	 */
	public String getName() {
		String name = "";
		Iterator<GState> states = this.states.iterator();
		while(states.hasNext()) {
			GState s =(GState)states.next();
			name += s.getName();
			if(states.hasNext())
				name += "/";
		}
		return name;
	}
	
	/**
	 * 
	 * @param state
	 */
	public boolean addState(GState state) {
		if(containsState(state))
			return false;
		
		if(state instanceof GMergeState) {
			// Si c''est un etat fusionne on le met a plat
			Iterator<?> ite = ((GMergeState)state).getSubStates();
			boolean renv = true;
			while(ite.hasNext())
				renv = addState((GState)ite.next()) && renv;
			return renv;
		}
		else {
			// sinon on l'ajoute car il n'est pas dans la liste
			return states.add(state);
		}
	}
	
	/**
	 * @see tamagocc.generic.api.GState#getAllowsMethod()
	 */
	public Iterator<GAllow> getAllowsMethod() {
		ArrayList<GAllow> allowed = new ArrayList<GAllow>();
		Iterator<GState> states = this.states.iterator();
		while(states.hasNext()) {
			GState s = states.next();
			Iterator<GAllow> allows = s.getAllowsMethod();
			while(allows.hasNext()) {
				GAllow allow = allows.next();
				allowed.add(allow);
			}
		}
		return allowed.iterator();
	}
	
	public boolean equals(Object o) {
		if(o == this)
			return true;
		if(o instanceof GMergeState) {
			GMergeState ms = (GMergeState)o;
			Iterator<GState> states = ms.getSubStates();
			while(states.hasNext()) {
				GState state = (GState)states.next();
				if(!containsState(state))
					return false;
			}
			return (ms.getCountSubStates() == getCountSubStates());
		}
		else if(o instanceof GState) {
			GState s = (GState)o;
			return (getName().equals(s.getName()));
		}
		return false;
	}

	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitState(this);
	}

	public Iterator<GTransition> getIncomingTransitions() {
		ArrayList<GTransition> trans = new ArrayList<GTransition>();
		for (GState state : this.states) {
			Iterator<GTransition> ts = state.getIncomingTransitions();
			while(ts.hasNext()) {
				trans.add(ts.next());
			}
		}
		return trans.iterator();
		//return incomingtransitions.iterator();
	}

	public Iterator<GTransition> getOutgoingTransitions() {
		ArrayList<GTransition> trans = new ArrayList<GTransition>();
		for (GState state : this.states) {
			Iterator<GTransition> ts = state.getOutgoingTransitions();
			while(ts.hasNext()) {
				trans.add(ts.next());
			}
		}
		return trans.iterator();
		//return outgoingtransitions.iterator();
	}

	public boolean addIncomingTransition(GTransition transition) {
		return false;
	}
	
	public boolean addOutgoingTransition(GTransition transition) {
		return false;
	}

	public GTamagoEntity getParent() {
		return parent;
	}

	public String getFullName() {
		return parent.getModule().getFullModule()+"."+parent.getName()+"#"+getName();
	}
	
	public void setParent(GTamagoEntity parent) {
		this.parent = parent;
	}

	public int getCountSubStates() {
		return states.size();
	}
	
	
	public static GState substitueSubState(GState s,GState s1,GState s2) {
		if(s instanceof GMergeState) {
			GIMergeState ms = new GIMergeState((GMergeState)s,s1,s2);
			return ms;
		}
		else if(s instanceof GState) {
			if(s.equals(s1))
				return s2;
			else
				return s;
		} 
		else
			return s;
	}

	public boolean isImplicit() {
		return implicit;
	}
	
	public void setImplicit(boolean b) {
		implicit = b;
	}

	public int sizeOutgoingTransitions() {
		ArrayList<GTransition> trans = new ArrayList<GTransition>();
		for (GState state : this.states) {
			Iterator<GTransition> ts = state.getOutgoingTransitions();
			while(ts.hasNext()) {
				trans.add(ts.next());
			}
		}
		return trans.size();
	}

	public int sizeAllowedMethods() {
		int res = 0;
		for (GState state : this.states) {
			res += state.sizeAllowedMethods();
		}
		return res;
	}
}
