/**
 * 
 */
package tamagotest.fixpoint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GAllow;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTransition;
import tamagocc.generic.api.GExpression.GExprType;
import tamagocc.generic.impl.GEmptyTransition;
import tamagotest.TamagoTestContext;
import tamagotest.TamagoTestTransSelector;

/**
 * @author Hakim Belhaouari
 *
 */
public class MaxDepth implements TamagoTestTransSelector {
	public static int MAX_DEPTH = 100;
	private int depth;
	private GTransition lasttrans;
	private ArrayList<GTransition> visited;
	private Random rand;

	public MaxDepth() {
		depth = 0;
		lasttrans = null;
		rand = new Random();
		visited = new ArrayList<GTransition>();
	}

	public boolean fetch() {
		if(depth < MAX_DEPTH) {
			depth++;
			visited.clear();
			return true;
		}
		else
			return false;
	}

	public GTransition getTransition(GState state, TamagoTestContext ctx)
	throws TamagoCCException {
		if(depth >= MAX_DEPTH)
			return null;
		ArrayList<GTransition> transs = searchAllTransitions(state, ctx);
		if(transs.size() <= 0) 
			return null;
		else {
			visited.clear();
			do {
				if(transs.size() == 0)
					return null; // tous visite
				int pos = rand.nextInt(transs.size());
				lasttrans = transs.get(pos);
				transs.remove(pos);
			} while(visited.contains(lasttrans));
			visited.add(lasttrans);
			return lasttrans;
		}
	}

	private ArrayList<GTransition> searchAllTransitions(GState state,
			TamagoTestContext ctx) throws TamagoCCException {

		GAllow meth = searchAllowedMethod(state,ctx);
		ArrayList<GTransition> transitions = new ArrayList<GTransition>();
		if(meth == null)
			return transitions;

		enrichDeclaredTransitions(state,meth,transitions,ctx);

		needImplicitTransitions(state,meth, transitions,ctx);

		return transitions;
	}

	private void needImplicitTransitions(GState state, GAllow allow,
			ArrayList<GTransition> transitions,TamagoTestContext ctx) {
		boolean findimplicit = false;
		for (GTransition gTransition : transitions) {
			if((gTransition.getCondition() == null || gTransition.getCondition().getCategory() == GExprType.NOCONTRACT) && gTransition.getMethodID().equals(allow.getID())) {
				findimplicit = true;
				break;
			}
		}
		if(!findimplicit) {
			transitions.add(new GEmptyTransition(state,ctx.getContract().getMethod(allow.getID()),state));
		}
	}

	private void enrichDeclaredTransitions(GState state, GAllow allow,
			ArrayList<GTransition> transitions,TamagoTestContext ctx) {
		Iterator<GTransition> transs = state.getOutgoingTransitions();
		while(transs.hasNext()) {
			GTransition trans = transs.next();
			GMethod meth = ctx.getContract().getMethod(trans.getMethodID());
			for (String id : ctx.getContract().getMethodID().get(meth)) {
				if(allow.getID().equals(id)) {
					transitions.add(trans);
				}
			}
		}
	}

	private GAllow searchAllowedMethod(GState state,TamagoTestContext ctx) {
			if(state.sizeAllowedMethods() <= 0)
				return null;
			int wanted = rand.nextInt(state.sizeAllowedMethods());
			Iterator<GAllow> meths = state.getAllowsMethod();
			while(meths.hasNext() && (wanted>0)) {
				meths.next();
				wanted--;
			}
			return meths.next();
		
	}

	public void reset() {
		depth = 0;
	}

	public GTransition lastTransition() {
		return lasttrans;
	}

	public void setDepth(int v) {
		depth = v;
	}

	public int getDepth() {
		return depth;
	}

	//
	//	@SuppressWarnings("unused")
	//	private GTamago last;
	//	@SuppressWarnings("unused")
	//	private GState origin;
	//	private int depth;
	//	
	//	private GTransition lastvisited;
	//	
	//	/**
	//	 * 
	//	 */
	//	public MaxDepth() {
	//		depth = 0;
	//		last = null;
	//		origin = null;
	//	}
	//	
	//	public void reset() {
	//		depth = 0;
	//		last = null;
	//		origin = null;
	//	}
	//
	//	
	//	
	//	/**
	//	 * @throws TamagoTestException 
	//	 * @see tamagotest.TamagoTestTransSelector#selectTransition(tamagocc.generic.api.GState, tamagotest.TamagoTestContext)
	//	 */
	//	public GTransition selectTransition(GState state, TamagoTestContext ctx) throws TamagoCCException {
	//		lastvisited = search(state, ctx);
	//		return lastvisited;
	//	}
	//	private GTransition search(GState state, TamagoTestContext ctx) throws TamagoCCException {
	//		
	//		// TODO faire les verifications
	//		if(depth < MAX_DEPTH) {
	//			depth++;
	//			Random random = new Random();
	//			
	//			if(state == null) {
	//				// choose random method
	//				int pos = random.nextInt(ctx.getContract().countAllMethods());
	//				Iterator<GMethod> meths = ctx.getContract().getAllMethods().iterator();
	//				GMethod s = null;
	//				while(pos >= 0) {
	//					s = meths.next();
	//					pos--;
	//				}
	//				return new GEmptyTransition(ctx.getContract().searchMethod(s));
	//			}
	//			else {
	//				if((state.sizeOutgoingTransitions() == 0) && (state.sizeAllowedMethods() == 0)) {
	//					TamagoCCLogger.println(3, "The scenario reach a final state");
	//					return null;
	//				}
	//				
	//				ArrayList<GTransition> transitions = new ArrayList<GTransition>();
	//				
	//				Iterator<GTransition> trans = state.getOutgoingTransitions();
	//				while(trans.hasNext()) {
	//					transitions.add(trans.next());
	//				}
	//				
	//				// maintenant on ajoute que les transitions qui n'ont pas de transition inconditionnelles
	//				Iterator<GAllow> allows = state.getAllowsMethod();
	//				while(allows.hasNext()) {
	//					GAllow allow = allows.next();
	//					if(!hasConditions(state,allow.getID(),transitions)) {
	//						GTransition t = new GITransition(state,state,allow.getID(), new GINoContract());
	//						TamagoCCLogger.println(2," * Implicite Transition: "+t);
	//						transitions.add(t);
	//					}
	//				}
	//				
	//				int pos = random.nextInt(transitions.size());
	//				return transitions.get(pos);
	//			}
	//		}
	//		else
	//			return null;
	//	}
	//
	//	private boolean hasConditions(GState state, String id, ArrayList<GTransition> transitions) {
	//		for (GTransition transition : transitions) {
	//			if(id.equals(transition.getMethodID())  && (transition.getOrigin().equals(state)))
	//				return true;
	//		}
	//		return false;
	//	}
	//
	//	
	//	// cette methode etait utile avant, mais en fait il est preferable de preciser toutes les transitions
	//	// dans le langage pour eviter les ambiguites des transitions
	//	@SuppressWarnings("unused")
	//	private boolean hasUnconditionalJump(GState state,String id, ArrayList<GTransition> transitions) {
	//		for (GTransition transition : transitions) {
	//			if(id.equals(transition.getMethodID())  && (transition.getOrigin().equals(state)) && (transition.getCondition().getCategory() == GExprType.NOCONTRACT))
	//				return true;
	//		}
	//		return false;
	//	}
	//
	//	public Collection<GTransition> getTransition(GState state,
	//			TamagoTestContext ctx) throws TamagoCCException {
	//		ArrayList<GTransition> transitions = new ArrayList<GTransition>();
	//		if(depth >= MAX_DEPTH)
	//			return transitions;
	//		
	//		if(state == null) {
	//			// choose random method
	//			Iterator<GMethod> meths = ctx.getContract().getAllMethods().iterator();
	//			GMethod s = null;
	//			while(meths.hasNext()) {
	//				s = meths.next();
	//				GTransition trans = new GEmptyTransition(ctx.getContract().searchMethod(s)); 
	//				transitions.add(trans);
	//			}
	//		}
	//		else {
	//			if((state.sizeOutgoingTransitions() == 0) && (state.sizeAllowedMethods() == 0)) {
	//				TamagoCCLogger.println(3, "The scenario reach a final state");
	//				return transitions;
	//			}
	//			
	//			
	//			Iterator<GTransition> trans = state.getOutgoingTransitions();
	//			while(trans.hasNext()) {
	//				transitions.add(trans.next());
	//			}
	//			
	//			// maintenant on ajoute que les transitions qui n'ont pas de transition inconditionnelles
	//			Iterator<GAllow> allows = state.getAllowsMethod();
	//			while(allows.hasNext()) {
	//				GAllow allow = allows.next();
	//				if(!hasConditions(state,allow.getID(),transitions))					
	//					transitions.add(new GITransition(state,state,allow.getID(), new GINoContract()));
	//			}
	//		}
	//		return transitions;
	//	}
	//
	//	public boolean fetch() {
	//		if(depth < MAX_DEPTH) {
	//			depth++;
	//			return true;
	//		}
	//		else
	//			return false;
	//	}
	//
	//	public void undo() {
	//		if(depth > 0)
	//			depth--;
	//	}
	//
	//	public GTransition getLastTransition() {
	//		return lastvisited;
	//	}


}
