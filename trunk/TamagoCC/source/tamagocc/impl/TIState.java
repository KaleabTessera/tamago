package tamagocc.impl;

import java.util.Collection;
import java.util.Iterator;

import tamagocc.api.TAllow;
import tamagocc.api.TState;
/**
 * @author Hakim BELHAOUARI
 * 10 oct. 2005 TIState.java
 */
/**
 */
public class TIState implements TState {

    private String name;
    private Collection<TAllow> allowed;
    
    /**
     * @param n : nom de l'etat
     * @param c : collection contenant les methodes appelables
     */
    public TIState(String n,Collection<TAllow> c) {
        super();
        name = n;
        allowed = c;
    }

    /**
     * @see tamagocc.api.TState#getState()
     */
    public String getState() {
        return name;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof TState) {
			TState p = (TState) o;
			return (getState().equals(p.getState()));
		}
    	return false;
    }

    /**
     * @see tamagocc.api.TState#getAllow()
     */
    public Iterator<TAllow> getAllow() {
        return allowed.iterator();
    }
    
    public boolean addAllow(TAllow allow) {
    	if(!allowed.contains(allow))
    		return allowed.add(allow);
    	else
    		return false;
    }

}
