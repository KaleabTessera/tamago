/**
 * 
 */
package tamagotest;

import java.util.Collection;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTransition;

/**
 * This interface represents the abstract detection of fix point for testing a component.
 * If the fix depends of an inner state, the object which implements this interface MUST 
 * manage it itself.
 * 
 * @author Hakim Belhaouari
 *
 */
public interface TamagoTestTransSelector {
/*
 * le choix de la transition permet ici de verifier
 * et/ou renvoie la transition amenant a trouver le fix point.
 * Dans le cas ou on a atteint le fix point la fonction doit renvoie null
 */ 
	/*/**
	 * This method select a possible transition from the current context and of the 
	 * current state of the contract. If the fix point has been reached, the method must return 
	 * <b>null</b> 
	 * @return a possible transition if the fix point is not attempt or else return null.
	 * @param state : the current state of the contract
	 * @param ctx : current context of the test tool
	 * @throws TamagoTestException 
	 */
	//GTransition selectTransition(GState state, TamagoTestContext ctx) throws TamagoCCException;

	// DANS ASE + ICLP + HASE
	//Collection<GTransition> getTransition(GState state, TamagoTestContext ctx) throws TamagoCCException;
	
	// nouvelle algo
	GTransition getTransition(GState state, TamagoTestContext ctx) throws TamagoCCException; 
	
	/**
	 * This method must be used after the getTransition, when the solver
	 * find a solution and will choose another transition
	 * @return
	 */
	boolean fetch();
	
	GTransition lastTransition();
	
	
	//void undo();
	
	// demande au valideur de point fixe de tout oublie. pour remettre a zero ces parametres
	// et reprendre la meme ou un nouveau scenario
	void reset();
}
