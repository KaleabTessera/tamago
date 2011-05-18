/**
 * 
 */
package fr.lacl.tamago.aca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

import tamagocc.api.TAllow;
import tamagocc.api.TBehavior;
import tamagocc.api.TState;
import tamagocc.api.TTransition;
import tamagocc.impl.TIBehavior;
import tamagocc.impl.TIState;
import tamagocc.impl.TITransition;
import tamagocc.util.Pair;

/**
 * Construit l'automate de Tamago-CDL a partir d'une sp�cification ACA (pouvant donner de l'EB3)
 * 
 */
public class MergeACACDLAutomata {
	private TBehavior func;
	private TBehavior aca;
	
	private String def;
	private HashMap<Pair<String, String>, String> maps;
	private ArrayList<TTransition> transitions;
	private int numstate = 0;
	private ArrayList<String> visited;
	private ArrayList<TState> states;
	private TIBehavior finalBehavior;
	
	
	public MergeACACDLAutomata(TBehavior func, TBehavior aca) {
		this.func = func;
		this.aca = aca;
		
		maps = new HashMap<Pair<String,String>, String>();
		transitions = new ArrayList<TTransition>();
	}
	
	private String genState(String f, String a) {
		Pair<String, String> defPair = new Pair<String, String>(f, a);
		if(maps.containsKey(defPair)) {
			return maps.get(defPair);
		}
		else {
			String s = ""+(numstate++);
			maps.put(defPair, s);
			genTState(s,f,a);
			return s;
		}
	}
	
	private void genTState(String s, String f, String a) {
		ArrayList<TAllow> allows = new ArrayList<TAllow>();
		TState sf = getFState(f);
		TState sa = getAState(a);
		Iterator<TAllow> ff = sf.getAllow();
		while(ff.hasNext()) {
			TAllow allow = ff.next();
			allows.add(allow);
		}
		
		Iterator<TAllow> fa = sa.getAllow();
		while(fa.hasNext()) {
			TAllow allow = fa.next();
			allows.add(allow);
		}
		
		TIState z = new TIState(s, allows);
		states.add(z);
	}

	private boolean isExists(String f, String a) {
		Pair<String, String> defPair = new Pair<String, String>(f, a);
		return (maps.containsKey(defPair));
	}
	
	private TState getFState(String state) {
		Iterator<TState> ite =  func.getStates();
		while(ite.hasNext()) {
			TState s = ite.next();
			if(s.getState().equals(state))
				return s;
		}
		throw new RuntimeException("Unfound state in functional automaton: "+state);
	}

	private TState getAState(String state) {
		Iterator<TState> ite =  aca.getStates();
		while(ite.hasNext()) {
			TState s = ite.next();
			if(s.getState().equals(state))
				return s;
		}
		throw new RuntimeException("Unfound state in ACA automaton: "+state);
	}

	
	private ArrayList<TTransition> getFTransitions(String state) {
		ArrayList<TTransition> al = new ArrayList<TTransition>();
		Iterator<TTransition> ite = func.getTransitions();
		while(ite.hasNext()) {
			TTransition trans = ite.next();
			if(trans.getFrom().equals(state))
				al.add(trans);
		}
		return al;
	}
	
	private ArrayList<TTransition> getATransitions(String state) {
		ArrayList<TTransition> al = new ArrayList<TTransition>();
		Iterator<TTransition> ite = aca.getTransitions();
		while(ite.hasNext()) {
			TTransition trans = ite.next();
			if(trans.getFrom().equals(state))
				al.add(trans);
		}
		return al;
	}
	
	public void merge() {
		visited = new ArrayList<String>();
		states = new ArrayList<TState>();
		String defF = func.getDefaultState();
		String defA = aca.getDefaultState();
		
		String defO = genState(defF, defA);
		Stack<Pair<String, String>> pile = new Stack<Pair<String,String>>();
		
		pile.push(new Pair<String, String>(defF, defA));
		while(!pile.empty()) {
			Pair<String, String> pair = pile.pop();
			String s = genState(pair.l(), pair.r());
			if(!isVisited(s)) {
				visited.add(s);
				ArrayList<TTransition> atf = getFTransitions(defF);
				ArrayList<TTransition> ata = getATransitions(defA);
				for (TTransition ta : ata) {
					Pair<String, String> next =
						new Pair<String, String>(pair.l(), ta.getTo());
					String ns = genState(pair.l(), ta.getTo());
					TTransition tt = new TITransition(s,ns,ta.getMethod());
					transitions.add(tt);
					pile.push(next);
				}
				for( TTransition tf : atf) {
					Pair<String, String> next =
						new Pair<String, String>(tf.getTo(),pair.r());
					String ns = genState( tf.getTo(),pair.r());
					TTransition tt = new TITransition(s,ns,tf.getMethod());
					transitions.add(tt);
					pile.push(next);
				}
			}
		} // end while
		
		finalBehavior = new TIBehavior(states,transitions,defO);
	}

	public TBehavior getBehavior() {
		if(finalBehavior == null)
			merge();
		return finalBehavior;
	}
	
	private boolean isVisited(String s) {
		return visited.contains(s);
	}
	
}
