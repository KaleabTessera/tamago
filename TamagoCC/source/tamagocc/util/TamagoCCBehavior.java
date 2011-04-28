/**
 * 
 */
package tamagocc.util;

import java.util.Hashtable;
import java.util.Iterator;

import tamagocc.api.TOpeName;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVariable;
import tamagocc.ast.impl.AIAffectation;
import tamagocc.ast.impl.AICall;
import tamagocc.ast.impl.AIIdent;
import tamagocc.ast.impl.AIIfThenElse;
import tamagocc.ast.impl.AIInLabel;
import tamagocc.ast.impl.AIInitialisation;
import tamagocc.ast.impl.AIInstExpression;
import tamagocc.ast.impl.AIInteger;
import tamagocc.ast.impl.AIMethod;
import tamagocc.ast.impl.AINil;
import tamagocc.ast.impl.AINoInstruction;
import tamagocc.ast.impl.AIOperator;
import tamagocc.ast.impl.AIParameter;
import tamagocc.ast.impl.AIReturn;
import tamagocc.ast.impl.AISequence;
import tamagocc.ast.impl.AIString;
import tamagocc.ast.impl.AIThrowsException;
import tamagocc.ast.impl.AIType;
import tamagocc.ast.impl.AIVariable;
import tamagocc.ast.impl.AIVisibility;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GAllow;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GMergeState;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GTransition;
import tamagocc.generic.api.GExpression.GExprType;

/**
 * 
 * @author Hakim Belhaouari
 *
 */
public final class TamagoCCBehavior {

	
	private TamagoCCGVisitor visitor;
	private GTamago contract;
	private GBehavior synchro;
	private Hashtable<GState, Integer> hstates;
	private Hashtable<Integer, GState> hrevstates;
	
	/**
	 * 
	 */
	public TamagoCCBehavior(TamagoCCGVisitor visitor,GTamago tamago,GBehavior fusionne) throws TamagoCCException {
		this.visitor = visitor;
		contract = tamago;
		synchro = fusionne;
		hstates = new Hashtable<GState, Integer>();
		hrevstates = new Hashtable<Integer, GState>();
	}
	
	private AIdent ident(String id) {
		return new AIIdent(id);
	}
	private AType type(String type) {
		return AIType.generateType(type);
	}
	
	public void generateConstructor(AISequence body) {
		// tout d'abord on recherche les ID principaux
		Iterable<GMethod> methods = contract.getUniqueMethods();
		body.addInstruction(new AINoInstruction(" main IDs"));
		
		for (GMethod method : methods) {
			AICall registerID = new AICall(ident("registerID"));
			AIString str = new AIString(method.getID());
			registerID.addArgument(str);
			registerID.addArgument(str);
			body.addInstruction(new AIInstExpression(registerID));
		}
		
		body.addInstruction(new AINoInstruction(" secondary IDs"));
		
		methods = contract.getUniqueMethods();
		for (GMethod method : methods) {
			Iterable<GMethod> secs = contract.getAllMethods(method);
			for (GMethod method2 : secs) {
				AICall registerID = new AICall(ident("registerID"));
				registerID.addArgument(new AIString(method2.getID()));
				registerID.addArgument(new AIString(method.getID()));
				body.addInstruction(new AIInstExpression(registerID));
			}
		}
				
		// -------------------------------------------------------------
		
		body.addInstruction(new AINoInstruction(" states generations"));
		Iterable<? extends GState> states = synchro.getStates();
		AIInitialisation init = new AIInitialisation(ident("state"),type("TamagoCCState"),new AINil());
		body.addInstruction(init);
		int position = 0;
		for (GState state : states) {
			// on doit initialiser la variable
			AIAffectation affect = new AIAffectation(var("state"),new AICall(ident("newstate")));
			body.addInstruction(affect);
			hstates.put(state, new Integer(position));
			hrevstates.put(new Integer(position), state);
			position++;
			
			// on ajoute tous les sous-etats
			searchStateName(body, state);
			
			// on ajoute les methodes allowed
			Iterator<GAllow> allows = state.getAllowsMethod();
			while(allows.hasNext()) {
				String mid = allows.next().getID();
				AICall callmethodID = new AICall(ident("methodID"));
				callmethodID.addArgument(new AIString(mid));
				
				AICall addallow = new AICall(ident("allow"));
				addallow.addArgument(new AIVariable(ident("state")));
				addallow.addArgument(callmethodID);
				body.addInstruction(new AIInstExpression(addallow));
			}
		}
		
		
		// ------------------------------------------------------
		// derniere etape on met a jour l'etat par defaut
		body.addInstruction(new AINoInstruction(" Initialize the current default state"));
		
		for (GState state : synchro.getDefaultStates()) {
			AICall callget = new AICall(ident("get"));
			callget.addArgument(new AIInteger(hstates.get(state).intValue()));
			AIInLabel inlabel = new AIInLabel(var("states"),callget);
			
			AIAffectation afcurrent = new AIAffectation(var("current"),inlabel);
			body.addInstruction(afcurrent);
		}
	}
	
	private AVariable var(String v) {
		return new AIVariable(ident(v));
	}
	
	private void searchStateName(AISequence seq, GState state) {
		if(state instanceof GMergeState) {
			GMergeState m = (GMergeState)state;
			Iterator<GState> ss = m.getSubStates();
			while(ss.hasNext()) {
				searchStateName(seq, ss.next());
			}
		}
		else {
			AICall call = new AICall(ident("include"));
			call.addArgument(new AIVariable(ident("state")));
			call.addArgument(new AIString(state.getName()));
			seq.addInstruction(new AIInstExpression(call));
		}
	}
	
	public AIMethod makeFunctionFetch() throws TamagoCCException {
		AIMethod method = new AIMethod(AIMethod.IMPLEMENTED,ident("fetchServiceBehavior"),AIType.typeVOID,AIVisibility.PROTECTED_VISIBILITY);
		method.addThrowsException(new AIThrowsException(type("TamagoCCServiceBehaviorException")));
		
		AISequence body = new AISequence();
		method.setBody(body);
		
		method.addParameters(new AIParameter(ident("mid"),type("TamagoCCMethodID")));
		
		AICall getid = new AICall(ident("id"));
		AIInLabel incurrent = new AIInLabel(var("current"),getid);
		
		// --------------- aiguilleur
		Iterator<Integer> ints = hrevstates.keySet().iterator();
		while(ints.hasNext()) {
			int id = ints.next().intValue();
			AIOperator ope = new AIOperator(TOpeName.opEg);
			ope.addOperand(incurrent);
			ope.addOperand(new AIInteger(id));
									
			AIIfThenElse aiguille = new AIIfThenElse(ope,generateConsequence(id),AINoInstruction.getNoInstruction());
			body.addInstruction(aiguille);
		}
		
		return method;
	}


	private AInstruction generateConsequence(int id) throws TamagoCCException {
		AISequence seq = new AISequence();
		GState state = hrevstates.get(new Integer(id));
		Iterator<GTransition> transitions = state.getOutgoingTransitions();
		while(transitions.hasNext()) {
			GTransition transition = transitions.next();
			AICall callmethod = new AICall(ident("methodID"));
			callmethod.addArgument(new AIString(transition.getMethodID()));
			
			AICall callequals = new AICall(ident("equals"));
			callequals.addArgument(var("mid"));
			
			AIInLabel inlabel = new AIInLabel(callmethod,callequals);
			
			AExpression condition = null;
			if(transition.getCondition().getCategory() != GExprType.NOCONTRACT) {
				AIOperator ope = new AIOperator(TOpeName.opAnd);
				ope.addOperand(inlabel);
				ope.addOperand((AExpression) transition.getCondition().visit(visitor) );
				condition = ope;
			}
			else {
				condition = inlabel;
			}
				
			
			// maintenant on s'occupe de l'affectation
			AICall callget = new AICall(ident("get"));
			callget.addArgument(new AIInteger(hstates.get(transition.getFinal()).intValue()));
			AIInLabel getposition = new AIInLabel(var("states"),callget);
			AIAffectation affect = new AIAffectation(var("current"),getposition);
			
			AISequence cons = new AISequence();
			cons.addInstruction(affect);
			cons.addInstruction(new AIReturn());
			
			// Maintenant on fait
			AIIfThenElse ifthen = new AIIfThenElse(condition,cons,AINoInstruction.getNoInstruction());
			seq.addInstruction(ifthen);
		}
		return seq;
	}
	
	

}
