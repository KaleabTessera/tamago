/**
 * 
 */
package tamagotest.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

import tamago.builder.TamagoEnvironment;
import tamago.csp.convert.TamagoCSPDNF;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GAllow;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GTransition;
import tamagocc.generic.api.GExpression.GExprType;
import tamagocc.generic.impl.GEmptyTransition;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.Pair;
import tamagotest.TamagoTest;

/**
 * @author Hakim Belhaouari
 *
 */
public class AllSequencesScenario {

	protected GTamago tamago;
	protected GBehavior beh;
	private int maxlength;
	private Random rand = new Random();
	
	
	private ArrayList<ArrayList<Pair<GTransition, Integer>>> scenarios;
	private Stack<Pair<GState, ArrayList<Pair<GTransition, Integer>>>> stack;
	
	public AllSequencesScenario(int maxlength, GTamago tamago) {
		this.tamago = tamago;
		this.beh = tamago.getBehavior();
		this.maxlength = maxlength;
		if(beh.countStates() == 0)
			beh = TamagoTest.buildDefaultBehavior(tamago);
		scenarios = new ArrayList<ArrayList<Pair<GTransition,Integer>>>();
		run();
	}
	
	public void run() {
		scenarios = new ArrayList<ArrayList<Pair<GTransition,Integer>>>();
		stack = new Stack<Pair<GState,ArrayList<Pair<GTransition,Integer>>>>();
		
		stack.push(new Pair<GState, ArrayList<Pair<GTransition,Integer>>>(beh.getDefaultStates().iterator().next(), new ArrayList<Pair<GTransition,Integer>>() ));
		
		while(!stack.isEmpty()) {
			Pair<GState, ArrayList<Pair<GTransition, Integer>>> elt = stack.pop();
			GState state = elt.l();
			ArrayList<Pair<GTransition,Integer>> scenario = elt.r();
			if(scenario.size() == maxlength) {
				scenarios.add(scenario);
			}
			else {
				try {
					ArrayList<GTransition> trans = searchAllTransitions(state);
					if(trans.size() == 0)
						scenarios.add(new ArrayList<Pair<GTransition,Integer>>(elt.r()));
					else {
						boolean extended = false;
						for (GTransition t : trans) {
							ArrayList<Pair<GTransition, Integer>> al = elt.r();
							extended = follow(al,t) || extended;
						}
						if(!extended)
							scenarios.add(new ArrayList<Pair<GTransition,Integer>>(elt.r()));
					}
				} catch (TamagoCCException e) {
					TamagoCCLogger.println(3, "*WARNING*: error during search of all possibles transitions included implicit transitions");
					e.printStackTrace();
				}
			}
		}
		TamagoCCLogger.println(3, "==========================================================================================");
		TamagoCCLogger.println(3, "==========================================================================================");
		TamagoCCLogger.println(3, "==========================================================================================");
		TamagoCCLogger.println(3, "   ALL TRANSITIONS/ALL PARTITION STRATEGY:");
		TamagoCCLogger.println(3, "       found scenario: "+scenarios.size());
		TamagoCCLogger.println(3, "==========================================================================================");
		if(TamagoCCLogger.getLevel() >= 4) {
			for (int i = 0; i < scenarios.size();i++) {
				ArrayList<Pair<GTransition, Integer>> scenario = scenarios.get(i);
				TamagoCCLogger.println(4, "Scenario "+i+" -> "+scenario.toString());
			}
		}
		TamagoCCLogger.println(3, "==========================================================================================");
		TamagoCCLogger.println(3, "==========================================================================================");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public GTransition getTransition(int posscenario,int step) {
		TamagoCCLogger.println(3, "getTransition: "+(posscenario-1)+" - "+step);
		if(posscenario-1 >= size() || step >= scenarios.get(posscenario-1).size())
			return null;
		return scenarios.get(posscenario-1).get(step).l();
	}
	
	public ArrayList<Pair<GTransition, Integer>> getScenario(int nbscen) {
		return scenarios.get(nbscen-1);
	}

	private boolean follow(ArrayList<Pair<GTransition, Integer>> scenario, GTransition t) {
		if(scenarioContains(scenario,t)) {
			//scenarios.add(scenario);
			return false;
		}
		
		
		TamagoEnvironment env = new TamagoEnvironment();
		GMethod meth = tamago.getMethod(t.getMethodID());
		try {
			GExpression expr = TamagoTest.genPrerequisiteExpression(tamago, meth, t, env);
			TamagoCSPDNF dnf = new TamagoCSPDNF(expr);
			int count = dnf.getCollection().size();
			dnf = null;
			for(int i=0;i < count; i++) {
				ArrayList<Pair<GTransition, Integer>> newscen = new ArrayList<Pair<GTransition,Integer>>(scenario);
				newscen.add(new Pair<GTransition, Integer>(t,i));
				stack.push(new Pair<GState, ArrayList<Pair<GTransition,Integer>>>(t.getFinal(), newscen));
			}
			return true;
		} catch (TamagoCCException e) {
			
			e.printStackTrace();
		}
		return false;
	}

	private boolean scenarioContains(ArrayList<Pair<GTransition, Integer>> scenario, GTransition t) {
		for (Pair<GTransition, Integer> pair : scenario) {
			if(t.toString().equals(pair.l().toString()))
				return true;
		}
		return false;
	}

	public int size() {
		return scenarios.size();
	}
	
	private ArrayList<GTransition> searchAllTransitions(GState state) throws TamagoCCException {

		ArrayList<GTransition> transitions = new ArrayList<GTransition>();
		/*GAllow meth = searchAllowedMethod(state);
		if(meth == null)
			return transitions;
		 */
		Iterator<GAllow> allowed = state.getAllowsMethod();
		while(allowed.hasNext()) {
			GAllow meth = allowed.next();
			enrichDeclaredTransitions(state,meth,transitions);

			needImplicitTransitions(state,meth, transitions);
		}

		return transitions;
	}

	private void needImplicitTransitions(GState state, GAllow allow,
			ArrayList<GTransition> transitions) {
		boolean findimplicit = false;
		for (GTransition gTransition : transitions) {
			if((gTransition.getCondition() == null || gTransition.getCondition().getCategory() == GExprType.NOCONTRACT) && gTransition.getMethodID().equals(allow.getID())) {
				findimplicit = true;
				break;
			}
		}
		if(!findimplicit) {
			transitions.add(new GEmptyTransition(state,tamago.getMethod(allow.getID()),state));
		}
	}

	private void enrichDeclaredTransitions(GState state, GAllow allow,
			ArrayList<GTransition> transitions) {
		Iterator<GTransition> transs = state.getOutgoingTransitions();
		while(transs.hasNext()) {
			GTransition trans = transs.next();
			GMethod meth = tamago.getMethod(trans.getMethodID());
			for (String id : tamago.getMethodID().get(meth)) {
				if(allow.getID().equals(id)) {
					transitions.add(trans);
				}
			}
		}
	}

	private GAllow searchAllowedMethod(GState state) {
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
