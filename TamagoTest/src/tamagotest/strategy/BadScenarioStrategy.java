/**
 * 
 */
package tamagotest.strategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import tamago.csp.convert.TamagoCSPFlatten;
import tamagocc.api.TOpeName;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GAllow;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GInvariant;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTransition;
import tamagocc.generic.api.GExpression.GExprType;
import tamagocc.generic.impl.GEmptyTransition;
import tamagocc.generic.impl.GIAtPre;
import tamagocc.generic.impl.GIBool;
import tamagocc.generic.impl.GINoContract;
import tamagocc.generic.impl.GIOperator;
import tamagocc.generic.impl.GIRead;
import tamagocc.generic.impl.GITransition;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.TamagoCCMakeReadableGExpression;
import tamagotest.TamagoTestContext;
import tamagotest.TamagoTestTransSelector;

/**
 * @author Hakim Belhaouari
 *
 */
public class BadScenarioStrategy extends NominalStrategy implements TamagoTestStrategy,TamagoTestTransSelector {
	private int depth;
	private GTransition lasttrans;
	private ArrayList<GTransition> visited;
	private Random rand;
	private boolean send;
	
	/**
	 * 
	 */
	public BadScenarioStrategy() {
		lasttrans = null;
		depth = 0;
		rand = new Random();
		visited = new ArrayList<GTransition>();
		send = false;
	}

	/**
	 * @see tamagotest.strategy.TamagoTestStrategy#getFixPoint()
	 */
	public TamagoTestTransSelector getFixPoint() {
		return this;
	}

	
	/**
	 * @see tamagotest.strategy.TamagoTestStrategy#getName()
	 */
	public String getName() {
		return "BadScenarioStrategy";
	}
	

	/**
	 * @see tamagotest.strategy.TamagoTestStrategy#setFixPoint(tamagotest.TamagoTestTransSelector)
	 */
	public void setFixPoint(TamagoTestTransSelector fixpoint) {
		TamagoCCLogger.println(2, " * BadScenarioStrategy: set transition selector is not available for me");
	}

	/**
	 * @see tamagotest.strategy.TamagoTestStrategy#strategyForOracle(tamagocc.generic.api.GExpression)
	 */
	public GExpression strategyForOracle(GExpression expr) throws TamagoCCException {
		if(send) {
			GIOperator ope = new GIOperator(TOpeName.opAnd);
			for (GInvariant inv : ctx.getContract().getInvariants()) {
				if(inv.getExpression() != null && inv.getExpression().getCategory() != GExprType.NOCONTRACT) {
					ope.addOperand(inv.getExpression());
				}
			}
			for (GProperty property : ctx.getContract().getProperties()) {
				GIRead apres = new GIRead(property.getName());
				GIAtPre atpre = new GIAtPre(new GIRead(property.getName()), property.getType());
				GIOperator egalite = new GIOperator(TOpeName.opEg);
				egalite.addOperand(apres);
				egalite.addOperand(atpre);
				
				ope.addOperand(egalite);
			}
			TamagoCCLogger.println(3, "BadScenarioStrategy oracle : "+TamagoCCMakeReadableGExpression.toString(ope));
			TamagoCSPFlatten flatten = new TamagoCSPFlatten(ope);
			return flatten.flatten();	
		}
		else
			return super.strategyForOracle(expr);
		
	}

	/**
	 * @see tamagotest.strategy.TamagoTestStrategy#strategyForPrerequisite(tamagocc.generic.api.GExpression)
	 */
	public GExpression strategyForPrerequisite(GExpression expr)throws TamagoCCException {
	    if(send) {
	    	
	    	return new GIBool(true);  // on force le comportement a etre valide
	    }
	    else
	    	return super.strategyForPrerequisite(expr);
		
	}
	
	public GExpression strategyForPrecondition(GExpression expr) throws TamagoCCException {
	    if(send) {
	    	return new GIBool(true); // on force le comportement a etre valide
	    }
	    else
		return super.strategyForPrecondition(expr);
	}
	
	
	// ---
	public boolean fetch() {
		if(depth < getMaxLengthScenario()) {
			depth++;
			visited.clear();
			return true;
		}
		else
			return false;
	}

	public GTransition getTransition(GState state, TamagoTestContext ctx)
			throws TamagoCCException {
		if(depth > getMaxLengthScenario())
			return null;
		
		if(depth == getMaxLengthScenario()) {
		    ArrayList<GMethod> forbidden = new ArrayList<GMethod>( searchForbiddenMethod(state, ctx));
		    if(forbidden.size() == 0) {
		    	TamagoCCLogger.println(2, "BADSCENARIO STRATEGY: Unfound an impossible transition");
		    	return null; 
		    }
 			
		    int pos = rand.nextInt(forbidden.size());
		    GMethod meth  = forbidden.get(pos);
			forbidden.remove(pos);
			send = true;
			return new GITransition(state, state, meth.getID(), new GINoContract());
		}
		else {
			ArrayList<GTransition> transs = searchAllTransitions(state, ctx);
			if(transs.size() <= 0) 
				return null;
			else {
				do {
					int pos = rand.nextInt(transs.size());
					lasttrans = transs.get(pos);
					transs.remove(pos);
				} while(visited.contains(lasttrans));
				visited.add(lasttrans);

				//if(checkIflastTransition(lasttrans))
				//	send = true;

				return lasttrans;
			}
		}
	}

    private ArrayList<GMethod> searchForbiddenMethod(GState state, TamagoTestContext ctx) throws TamagoCCException {
    	Iterator<GAllow> allows = state.getAllowsMethod();
    	ArrayList<GMethod> auth = new ArrayList<GMethod>();
    	while(allows.hasNext()) {
    		GAllow allow = allows.next();
    		auth.add(ctx.getContract().getMethod(allow.getID()));
    	}
    	
    	ArrayList<GMethod> notauth = new ArrayList<GMethod>();
    	for (GMethod meth : ctx.getContract().getUniqueMethods()) {
    		if(!auth.contains(meth)) {
    			TamagoCCLogger.println(3, "BAD Strategy: find a impossible method -> "+meth.toString());
    			notauth.add(meth);
    		}
		}
    	
		return notauth;
    }
	
	private boolean checkIflastTransition(GTransition lasttrans2) {
		if(depth == (getMaxLengthScenario() -1))
			return true;
		GState fin = lasttrans2.getFinal();
		if(fin.sizeAllowedMethods() == 0)
			return true;
		return false;
	}

	public ArrayList<GTransition> searchAllTransitions(GState state,
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
		currentScenario++;
	}

	public GTransition lastTransition() {
		return lasttrans;
	}	

}
