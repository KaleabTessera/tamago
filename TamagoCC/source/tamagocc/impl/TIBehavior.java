package tamagocc.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import tamagocc.api.TBehavior;
import tamagocc.api.TState;
import tamagocc.api.TTransition;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.NilCollection;
import tamagocc.util.TamagoCCVisitor;
/**
 * @author Hakim BELHAOUARI
 * 10 oct. 2005 TIBehavior.java
 */
/**
 */
public class TIBehavior implements TBehavior {

    private Collection<TState> states;
    private Collection<TTransition> transitions;
    private String defaultState;
    
    
    public static final TBehavior NoBehavior = new TIBehavior(new NilCollection<TState>(), new NilCollection<TTransition>(), "");
    /**
     * @param s : collection des etats
     * @param t : collection des transitions possible
     */
    public TIBehavior(Collection<TState> s,Collection<TTransition> t,String defaultState) {
        super();
        states = s;
        transitions = t;
        this.defaultState = defaultState; 
    }

    public TIBehavior() {
    	this.states = new ArrayList<TState>();
    	this.transitions = new ArrayList<TTransition>();
    	this.defaultState = "";
	}

	/**
     * @see tamagocc.api.TBehavior#getStates()
     */
    public Iterator<TState> getStates() {
        return states.iterator();
    }

    /**
     * @see tamagocc.api.TBehavior#getTransitions()
     */
    public Iterator<TTransition> getTransitions() {
        return transitions.iterator();
    }
    
    /**
     * @see tamagocc.api.TBehavior#getDefaultState()
     */
    public String getDefaultState() {
    	return defaultState;
    }

    /**
     * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitBehavior(this);
    }

}
