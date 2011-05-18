/**
 * 
 */
package fr.lacl.tamago.aca;

import java.util.ArrayList;
import java.util.HashMap;

import tamagocc.api.TComponent;
import tamagocc.api.TMethod;
import tamagocc.api.TOpeName;
import tamagocc.api.TParameter;
import tamagocc.api.TService;
import tamagocc.exception.TamagoCCException;
import tamagocc.impl.TIBehavior;
import tamagocc.impl.TICall;
import tamagocc.impl.TICategory;
import tamagocc.impl.TIComposant;
import tamagocc.impl.TICondition;
import tamagocc.impl.TIInLabel;
import tamagocc.impl.TIMethod;
import tamagocc.impl.TINoContract;
import tamagocc.impl.TIOperator;
import tamagocc.impl.TIProvide;
import tamagocc.impl.TIString;
import tamagocc.impl.TIType;
import tamagocc.impl.TIVariable;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.TamagoCCPool;
import fr.lacl.tamago.aca.exception.ConvertACACDLException;
import fr.lacl.tamago.aca.visitor.ACASearchActions;
import fr.lacl.tamago.aca.xmlterm.Atomic;
import fr.lacl.tamago.aca.xmlterm.Bans;
import fr.lacl.tamago.aca.xmlterm.Permissions;
import fr.lacl.tamago.aca.xmlterm.Processus;
import fr.lacl.tamago.aca.xmlterm.Term;

/**
 * Construit l'automate de Tamago-CDL a partir d'une spécification ACA (pouvant donner de l'EB3)
 * 
 */
public class ConvertACACDL {

	public static final String TYPE_QUAD_ACCESS_CONTROL = "tamago.ext.aca.Quad";
	/*
	 *  algorithme de conversion:
	 *  on prend un terme (par exemple un parallele fort/faible)
	 *  et on fait un automate � partir du tout
	 */
	
	private TIComposant component;
	private Processus processus;
	private TComponent ref;
	
	private String n;
	private String module;
	/**
	 * 
	 */
	public ConvertACACDL(String name, String module,Processus prop) {
		this.processus = prop;
		n = name;
		this.module = module;
		
		try {
			ref = (TComponent)TamagoCCPool.getDefaultPool().getTreeAbstractSyntax(n, module);
		} catch (TamagoCCException e) { 
			e.printStackTrace();
			ref = null;
		}
		
		component = null;
	}
	
	public TComponent getContainer() throws ConvertACACDLException {
		if(component == null)
			convert();
		return component;
	}
	
	public void convert() throws ConvertACACDLException {
		TamagoCCLogger.println(3, "Creation of a new service "+processus.getName()+"...");
		component = new TIComposant(n+"ACA", module);
		/*if(ref != null) {
			
			component.addProvide(new TIProvide(n, module, ref));
		}*/
		
		TamagoCCLogger.println(3, "Search all actions...");
		fillActions();
		TamagoCCLogger.println(3, "Treatment of permissions...");
		affectPermissions();
		TamagoCCLogger.println(3, "Treatment of bans...");
		affectBans();
		TamagoCCLogger.println(3, "Generation of the behavioral automaton...");
		generateBehavior();
		TamagoCCLogger.println(3, "FINISH");
		
	}
	
	private void fillActions() {
		ACASearchActions sa = new ACASearchActions();
		sa.vProcessus(processus);
		
		for (String action : sa.getFoundActions()) {
			ArrayList<TParameter> params = new ArrayList<TParameter>();
			TICondition precond = new TICondition(new TINoContract(), TICategory.NoCategory, "");
			TICondition postcond = new TICondition(new TINoContract(), TICategory.NoCategory, "");
			TIMethod meth = new TIMethod(action, action, TIType.generateType("void"), 
											params, precond,postcond);
			TamagoCCLogger.println(3, "Find action: "+action);
			meth.addParameter(TIType.generateType(TYPE_QUAD_ACCESS_CONTROL),"quad");
		}
	}
	
	private void affectPermissions() {
		Permissions perms = processus.getPermissions();
		for(Atomic at : perms.getAtomic()) {
			productConditionAut(at);			
		}
	}
	
	private void productConditionAut(Atomic at) {
		productConditionBans(at);
		
	}

	private void affectBans() {
		Bans bans = processus.getBans();
		for(Atomic at : bans.getAtomic()) {
			productConditionBans(at);
		}
	}
	
	private void productConditionBans(Atomic at) {
		String action = at.getAction().getName();
		TIMethod meth = searchMethod(action);
		
		TIOperator opand = new TIOperator(TOpeName.opAnd);
		if(at.getOrg() != null) {
			TICall call;
			if(at.isForbidden())
				 call = new TICall("org_IsNot");
			else
				call = new TICall("org_Is");
			call.addArgument(new TIString(at.getOrg().getValue()));
			TIInLabel inlabel = new TIInLabel(new TIVariable("quad",true,TYPE_QUAD_ACCESS_CONTROL), call);
			opand.addOperand(inlabel);
		}
		if(at.getRole() != null) {
			TICall call;
			if(at.isForbidden())
				 call = new TICall("role_IsNot");
			else
				call = new TICall("role_Is");
			call.addArgument(new TIString(at.getRole().getValue()));
			TIInLabel inlabel = new TIInLabel(new TIVariable("quad",true,TYPE_QUAD_ACCESS_CONTROL), call);
			opand.addOperand(inlabel);
		}
		if(at.getUser() != null) {
			TICall call;
			if(at.isForbidden())
				 call = new TICall("user_IsNot");
			else
				call = new TICall("user_Is");
			call.addArgument(new TIString(at.getUser().getValue()));
			TIInLabel inlabel = new TIInLabel(new TIVariable("quad",true,TYPE_QUAD_ACCESS_CONTROL), call);
			opand.addOperand(inlabel);
		}
		
			
		
		// fouiller le terme atomic
		// TODO: demander a pierre si on doit faire un et a l'interieur du meme terme 
		// 			atomique pour traiter analyser les wildcardss
		TICondition precond = (TICondition)meth.getPrecondition();
		if(opand.size() > 0) {
			TIOperator opor = new TIOperator(TOpeName.opOr);
			opor.addOperand((precond.getExpression()));
			if(opand.size() == 1)
				opor.addOperand(opand.getOperand(0));
			else
				opor.addOperand(opand);
			precond.setExpression(opor);
		}
	}

	private TIMethod searchMethod(String action) {
		return null;
	}

	private void generateBehavior() throws ConvertACACDLException {
		Term term = processus.getComplex();
		XMLGenerateBehavior gb = new XMLGenerateBehavior(term);
		gb.vTerm(term);
		TIBehavior beh = new TIBehavior(gb.getStates(), gb.getTransitions(), gb.getInit().getState());
		component.setBehavior(beh);
	}

	public String getName() {
		return n;
	}

	public String getModule() {
		return module;
	}

}
