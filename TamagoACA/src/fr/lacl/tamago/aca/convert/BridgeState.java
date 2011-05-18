/**
 * 
 */
package fr.lacl.tamago.aca.convert;

import java.util.ArrayList;

import fr.lacl.tamago.aca.term.Logic;

import tamagocc.impl.TIState;

/**
 * @author hakim
 *
 */
public class BridgeState {
	/**
	 * List of action and its multiplicity that describe the current list
	 */
	private SetMultiplicity listActions;
	private TIState state;
	private ArrayList<Logic> logics;
	
	/**
	 * 
	 */
	public BridgeState(SetMultiplicity listActions, TIState state) {
		this.listActions = new SetMultiplicity(listActions);
		this.state = state;
		this.logics = new ArrayList<Logic>();
	}

	public ArrayList<Logic> getLogics() {
		return logics;
	}
	
	public void addLogics(ArrayList<Logic> logics) {
		this.logics.addAll(logics);
	}
	
	public SetMultiplicity getListActions() {
		return listActions;
	}

	public TIState getState() {
		return state;
	}

	public void setListActions(SetMultiplicity listActions) {
		this.listActions = listActions;
	}

	public void setState(TIState state) {
		this.state = state;
	}

	public boolean isSame(SetMultiplicity actions) {
		return listActions.equals(actions);
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(listActions.toString());
		sb.append("\n\t\t");
		sb.append(state.toString());
		sb.append("\n");
		return sb.toString();
	}

}
