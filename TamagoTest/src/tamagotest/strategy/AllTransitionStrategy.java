/**
 * 
 */
package tamagotest.strategy;

import java.util.ArrayList;
import java.util.Hashtable;
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
public class AllTransitionStrategy extends NominalStrategy implements TamagoTestTransSelector {

	protected Hashtable<GTransition,Integer> marked;
	protected GTransition lastvisited;
	protected boolean allvisited;
	private Random rand;
	
	/**
	 * 
	 */
	public AllTransitionStrategy() {
		marked = new Hashtable<GTransition,Integer>();
		allvisited = false;
		rand = new Random();
	}
	
	public TamagoTestTransSelector getFixPoint() {
		return this;
	}

	public boolean fetch() {
		// TODO Auto-generated method stub
		return false;
	}

	public GTransition getTransition(GState state, TamagoTestContext ctx)
			throws TamagoCCException {
		ArrayList<GTransition> trans = searchAllTransitions(state, ctx);
		for (GTransition g : trans) {
			if(!marked.containsKey(g)) {
				marked.put(g,1);
				lastvisited = g;
				return g;
			}
		}
		
		// il faudrait calculer le chemin adéquate pour savoir lequel il nous manque
		return null;
	}
	
	public void searchPath(GState state) {
		
	}

	public GTransition lastTransition() {
		return lastvisited;
	}
	
	public boolean makeNewScenario() {
		return allvisited;
	}
	
	public void reset() {
		super.reset();
		marked.clear();
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
			if(gTransition.getCondition() == null || gTransition.getCondition().getCategory() == GExprType.NOCONTRACT) {
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
}
