/**
 * 
 */
package tamago.check;

 import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;
import java.util.Map.Entry;

import tamago.builder.TamagoBuilder;
import tamago.builder.TamagoEnvironment;
import tamago.check.exception.TamagoCheckException;
import tamago.check.fixpoint.TamagoCheckFixPointSeeker;
import tamago.check.fixpoint.TamagoCheckState;
import tamago.check.lineparser.CheckMaxDepth;
import tamago.check.lineparser.TamagoCheckFixMaxInt;
import tamago.check.lineparser.TamagoCheckFixMinInt;
import tamago.check.lineparser.TamagoStaticDefaultContract;
import tamago.check.util.TamagoCheckContext;
import tamago.check.util.TamagoCheckRename;
import tamago.csp.Backtracking;
import tamago.csp.TamagoCSP;
import tamago.csp.convert.TamagoCSPDNF;
import tamago.csp.convert.TamagoCSPFlatten;
import tamago.csp.convert.TamagoCSPInferConstraint;
import tamago.csp.domain.CSPAbstractDomain;
import tamago.csp.exception.TamagoCSPException;
import tamago.csp.generic.CSPvar;
import tamagocc.TamagoCCParser;
import tamagocc.api.TOpeName;
import tamagocc.api.TTamago;
import tamagocc.exception.TamagoCCException;
import tamagocc.exception.TamagoCCNotFoundState;
import tamagocc.generic.TamagoCCGenericConverter;
import tamagocc.generic.api.GAllow;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GInvariant;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GOperator;
import tamagocc.generic.api.GParameter;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GTransition;
import tamagocc.generic.api.GExpression.GExprType;
import tamagocc.generic.impl.GEmptyTransition;
import tamagocc.generic.impl.GIAllow;
import tamagocc.generic.impl.GIBehavior;
import tamagocc.generic.impl.GINoContract;
import tamagocc.generic.impl.GINot;
import tamagocc.generic.impl.GIOperator;
import tamagocc.generic.impl.GIState;
import tamagocc.generic.impl.GITransition;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.percolation.TamagoCCPercolation;
import tamagocc.util.TamagoCCMakeReadableGExpression;
import tamagocc.util.TamagoCCPool;
import tamagocc.util.Triplet;
import tamagocc.util.lineparser.LineParser;
import tamagocc.util.lineparser.TamagoCCLogLevel;
import tamagocc.util.lineparser.TamagoCCPathCmd;
import tamagocc.util.lineparser.TamagoCCPercolator;
import tamagocc.util.lineparser.TamagoCCSetXSD;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCheck {

	private static TamagoCheckContext ctx = new TamagoCheckContext();
	private static Random rand;
	private static long solvercreated = 0;
	private static long statevisited = 0;
	private static long transitionsvisited = 0;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long start = 0,end = 0;
		
		TamagoCCLogger.println(1, "TamagoCheck - version 0.1");
		rand = new Random();
		LineParser lineparser = new LineParser("TamagoCheck","Detection of semantic errors in contracts written in the TamagoCDL language.\nAuthors: Hakim Belhaouari <hakim.belhaouari@lip6.fr>\n\t Frederic Peschanski <frederic.peschanski.lip6.fr>.");
		TamagoStaticDefaultContract filename = new TamagoStaticDefaultContract();
		try {
			TamagoCCParser.getDefaultParser();
			TamagoCCPool.getDefaultPool();
			TamagoCCPercolation.initialisation();
			TamagoCCLogger.setOut(System.err);

			lineparser.addSpec(new tamagocc.util.lineparser.TamagoCCLogFile());
			lineparser.addSpec(new TamagoCCLogLevel());
			lineparser.addSpec(new TamagoCCPathCmd());
			lineparser.addSpec(new TamagoCCPercolator());
			lineparser.addSpec(new TamagoCCSetXSD());
			lineparser.addSpec(new TamagoCheckFixMaxInt());
			lineparser.addSpec(new TamagoCheckFixMinInt());
			lineparser.addSpec(new CheckMaxDepth(ctx));
			lineparser.setDefaultSpec(filename);
			lineparser.parse(args);

		}
		catch(Exception e) {
			TamagoCCLogger.infoln(1,lineparser.toString());
			TamagoCCLogger.print(1,"Error in the command line: ");
			TamagoCCLogger.infoln(1,e.getMessage());
			return;
		}
		// end of initialisation
		
		// we search te contract and we parse and convert it
		try {
			if(filename.getFilename() != null) {
				TTamago contract = (TTamago) TamagoCCParser.getDefaultParser().parse(filename.getFilename());
				TamagoCCGenericConverter conv = new TamagoCCGenericConverter(contract);
				GTamago gcontract;
				gcontract = (GTamago)conv.getGTamagoEntity();
				ctx.setContract(gcontract);
				start = System.currentTimeMillis();
				if(gcontract.getBehavior().countStates() == 0) {
					TamagoCCLogger.println(3, "No behavior detected");
					GBehavior beh = buildDefaultBehavior(gcontract);
					followBehavior(gcontract,beh.getDefaultStates().iterator().next());
				}
				else {
					TamagoCCLogger.println(3, "Behavior detected");
					// we run from the first state
					// by default the behavior is the projected automaton (completed with the product)
					followBehavior(gcontract,gcontract.getBehavior().getDefaultStates().iterator().next());
				}
				end = System.currentTimeMillis();
			}
		}
		catch(Exception e) {
			TamagoCCLogger.print(1,"Error during the verification : ");
			TamagoCCLogger.infoln(1,e.getMessage());
		}
		
		long tps = (end - start);
		ctx.getReport().finish(tps,statevisited,transitionsvisited,solvercreated);
		TamagoCCLogger.println(2, "End of symbolic animation:");
		TamagoCCLogger.println(2, "\t Elapsed time: "+tps+"ms");
		TamagoCCLogger.println(2, "\t States visited: "+statevisited);
		TamagoCCLogger.println(2, "\t Transitions visited: "+transitionsvisited);
		TamagoCCLogger.println(2, "\t CSP solver created: "+solvercreated);
	}

	private static void extractProperties(GTamago contract, TamagoEnvironment env_props) {
		TamagoCCLogger.println(3, "Extraction of properties:");
		for (GProperty prop : contract.getProperties()) {
			try {
				TamagoCCLogger.print(3,"  Extraction of "+prop.getName());
				TamagoBuilder tb = ctx.getTypeBuilderFactory().searchBuilder(env_props, prop.getName(), prop.getType(), new Backtracking());
				env_props.put(prop.getName(), tb);
				TamagoCCLogger.println(3," [OK]");
			}
			catch(Exception e) {
				TamagoCCLogger.print(3," [KO]");
				TamagoCCLogger.print(4," Error during searching the builder of this type");
				TamagoCCLogger.println(3,"");
			}
		}
	}

	private static void minimizeProperties(GTamago contract, TamagoEnvironment env,
			TamagoCheckFixPointSeeker global, 
			Stack<Triplet<GState, Integer, TamagoCheckState>> pile, 
			GState state) throws TamagoCCException 
	{
		if(env.size() == 0) {
			TamagoCCLogger.println(3, "No property detected");
			return;
		}

		TamagoCCLogger.println(3, "Minimisation of extracted properties");
		GExpression constraints = new GIOperator(TOpeName.opAnd);
		for (GInvariant invariant : contract.getInvariants()) {
			((GIOperator) constraints).addOperand(invariant.getExpression());
		}

		TamagoCSPFlatten flat = new TamagoCSPFlatten(constraints);
		constraints = flat.flatten();

		TamagoCSPDNF dnf = new TamagoCSPDNF(constraints);
		TamagoCCLogger.println(3, "DNF :" +dnf.size());
		if(TamagoCCLogger.getLevel() >= 4) {
			TamagoCCLogger.println(4,"---------");
			for (GExpression expr : dnf) {
				TamagoCCLogger.println(4,"\t DNF expression: "+TamagoCCMakeReadableGExpression.toString(expr));
			}
			TamagoCCLogger.println(4,"---------");
		}

		

		ArrayList<GExpression> arraydnf = new ArrayList<GExpression>(dnf.getCollection());
		if(dnf.size() == 0) {
			TamagoCCLogger.println(3, "No invariant detected for minimization");
			TamagoCSP csp = new TamagoCSP();

			for (TamagoBuilder builder : env.values()) {
				builder.setBacktrack(csp.getBacktrack());
				CSPvar var = builder.getCSPvar(null,null);
				var.uninstallAllConstraints();
				csp.addVariable(var);
			}
			TamagoCCLogger.println(3, "Minimize properties...");
			try {
				csp.minimize();
				TamagoCCLogger.println(3, "Minimize [OK]");
				TamagoCCLogger.println(3, csp.toString());
				TamagoCheckState localstate = new TamagoCheckState(state,null,null);
				for (GProperty property : contract.getProperties()) {
					TamagoBuilder tb = env.get(property.getName());
					CSPAbstractDomain domain =  tb.getCSPvar().getAbstractDomain();
					localstate.register(property.getName(), domain);
				}
				global.register(localstate);
				ctx.getReport().registered(null,localstate);
				Triplet<GState, Integer, TamagoCheckState> firsttrip = new Triplet<GState, Integer, TamagoCheckState>(state, 0, localstate);
				pile.add(firsttrip);
			}
			catch(Exception e) {
				TamagoCCLogger.println(3, "Error during minimization:");
				TamagoCCLogger.infoln(3,e);
			}
			finally {
				Backtracking backtrack =  csp.getBacktrack();
				while(backtrack.size()>0) {
					tamago.csp.Triplet t = backtrack.pop();
					CSPvar v = t.getVariable();
					TamagoCCLogger.println(4, "    TRIPLET on "+v.getName());
					TamagoCCLogger.println(4, "            flag "+t.isForward());
					TamagoCCLogger.println(4, "            val "+t.getValue());
					try {
						v.retrieve(t);
					} catch (TamagoCSPException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} 
			}
			return;
		}

		for (GExpression texpr : arraydnf) {
			TamagoCCLogger.println(3,"-- Selected DNF : "+TamagoCCMakeReadableGExpression.toString(texpr));
			//ctx.setSelectedDNFInvariant(expr);
			ctx.setSelectedDNFInvariant(texpr);
			GExpression expr = TamagoCSPFlatten.flattern(texpr);
			
			TamagoCSP csp = new TamagoCSP();

			for (TamagoBuilder builder : env.values()) {
				CSPvar var = builder.getCSPvar();
				var.uninstallAllConstraints();
				builder.setBacktrack(csp.getBacktrack());
				csp.addVariable(var);
			}

			TamagoCSPInferConstraint infer = new TamagoCSPInferConstraint(csp,env);
			if((expr.getCategory() == GExprType.OPERATOR)
					&& (((GOperator)expr).getOperator().equals(TOpeName.opAnd))) {
				Iterator<GExpression> item = ((GOperator)expr).getOperands();
				while(item.hasNext()) {
					GExpression tmpexpr = item.next();
					TamagoCCMakeReadableGExpression tccmrg = new TamagoCCMakeReadableGExpression(tmpexpr);
					try {
						TamagoCCLogger.print(3," -- Conversion sub expr : ");
						TamagoCCLogger.println(3,tccmrg.getStrExpression());
					} catch (TamagoCCException e) {		}

					if(!infer.generate(tmpexpr)) {
						//TamagoCCMakeReadableGExpression tccmrg = new TamagoCCMakeReadableGExpression(tmpexpr);
						TamagoCCLogger.print(3,"  -- Problem to convert the sub expr ");
						try {
							TamagoCCLogger.print(3,": "+tccmrg.getStrExpression());
						} catch (TamagoCCException e) {		}
						TamagoCCLogger.println(3,"");
					} 

				}
			}
			else {
				if(!infer.generate(expr)) {
					TamagoCCMakeReadableGExpression tccmrg = new TamagoCCMakeReadableGExpression(expr);
					TamagoCCLogger.print(3," -- Problem to convert the expr ");
					try {
						TamagoCCLogger.print(3,": "+tccmrg.getStrExpression());
					} catch (TamagoCCException e) {		}
					TamagoCCLogger.println(3,"");
				}
			}


			TamagoCCLogger.println(3, "Minimize properties...");
			TamagoCCLogger.println(4, csp.toString());
				try {
					csp.minimize();
					TamagoCCLogger.println(3, "Minimize [OK]");
					TamagoCCLogger.println(3, csp.toString());
					// enregistrer l'etat et les props
					TamagoCheckState localstate = new TamagoCheckState(state,null,null);
					ctx.getReport().registered(null,localstate);
					for (GProperty property : contract.getProperties()) {
						TamagoBuilder tb = env.get(property.getName());
						CSPAbstractDomain domain =  tb.getCSPvar().getAbstractDomain();
						localstate.register(property.getName(), domain);
					}
					int old = global.findFixPoint(localstate); 
					//ICI
					if(old == -1) {
						global.register(localstate);
						Triplet<GState, Integer, TamagoCheckState> firsttrip = new Triplet<GState, Integer, TamagoCheckState>(state, 0, localstate);
						pile.add(firsttrip);
					}
					else
						ctx.getReport().findFixpoint(ctx,null,localstate,global.get(old));
				}
				catch (TamagoCSPException e) {
					TamagoCCLogger.println(3, "Minimize [KO]");
					TamagoCCLogger.println(3, csp.toString());
				}
				finally {
					Backtracking backtrack =  csp.getBacktrack();
					while(backtrack.size()>0) {
						tamago.csp.Triplet t = backtrack.pop();
						CSPvar v = t.getVariable();
						TamagoCCLogger.println(4, "    TRIPLET on "+v.getName());
						TamagoCCLogger.println(4, "            flag "+t.isForward());
						TamagoCCLogger.println(4, "            val "+t.getValue());
						try {
							v.retrieve(t);
						} catch (TamagoCSPException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} 
				}
			}
	}

	private static void followBehavior(GTamago contract, GState initstate)throws TamagoCCException {

		GState state = initstate;
		Stack<Triplet<GState, Integer, TamagoCheckState>> pile = new Stack<Triplet<GState,Integer,TamagoCheckState>>();
		TamagoCheckState localstate;
		TamagoEnvironment env_props = new TamagoEnvironment();
		TamagoCheckFixPointSeeker global = new TamagoCheckFixPointSeeker(contract, ctx); 

		ctx.getReport().initialize(ctx,contract);
		// extract properties
		extractProperties(contract,env_props);

		// minimize properties
		minimizeProperties(contract,env_props,global,pile,state);		
		
		// selectionner transition
		while(pile.size() > 0) {
			Triplet<GState, Integer, TamagoCheckState> triplet = pile.pop();
			statevisited++;
			int prof = triplet.c();
			state = triplet.l();
			localstate = triplet.r();
			TamagoCCLogger.infoln(2, "+++++++++++++++++++++++++++");
			TamagoCCLogger.infoln(2, "\tPOP: "+localstate.toString());
			TamagoCCLogger.infoln(2, "+++++++++++++++++++++++++++");
			ctx.getReport().openState(ctx,localstate);
			if(ctx.getMaxsearch() != -1 && prof >= ctx.getMaxsearch()) {
				TamagoCCLogger.infoln(2, "+++++++++++++++++++++++++++");
				TamagoCCLogger.infoln(2, "\tMAX DEPTH SEARCH: "+localstate.toString());
				TamagoCCLogger.infoln(2, "+++++++++++++++++++++++++++");
				ctx.getReport().reachMaxDepth(ctx,state,localstate);
				continue;
			}
			ArrayList<GTransition> transitions = searchAllTransitions(state);
			TamagoCCLogger.infoln(2, " * Nb transitions: "+ transitions.size());
			for (GTransition trans : transitions) {
				transitionsvisited++;
				ctx.getReport().openTransition(trans);
				// on remet l'etat comme au debut de la transition
				for (Entry<String, CSPAbstractDomain> prop : localstate.getEntries()) {
					env_props.get(prop.getKey()).getCSPvar().load(prop.getValue());
				}	
				TamagoCCLogger.infoln(2, "^^^^^^^^^^^^^^^^^^^^^^^^^^");
				TamagoCCLogger.infoln(2, "\t"+trans.toString());
				TamagoCCLogger.infoln(2, "^^^^^^^^^^^^^^^^^^^^^^^^^^");
				try {
					searchSolutions(prof,contract,trans,env_props,global,pile,state,localstate);
				}
				catch(TamagoCheckException ex) {
					ctx.getReport().addError(localstate,trans,ex);
				}
				catch(Exception cspex) {
					ctx.getReport().addError(localstate,trans,cspex);
				}
				finally {
					ctx.getReport().closeTransition();
				}

			}
			ctx.getReport().closeState(ctx,localstate);
		}
		
		
		ctx.getReport().close();
	}
	
	private static GExpression genPrerequisiteExpression(GTamago contract, GMethod meth, GTransition transition, TamagoEnvironment env) throws TamagoCCException {
		TamagoCCLogger.print(3, "Generation of prerequisite condition (percolator=");
		TamagoCCLogger.print(3,ctx.getPercolation().getName());
		TamagoCCLogger.println(3,")");
		GIOperator ope = new GIOperator(TOpeName.opAnd);


		TamagoCheckRename ttr = new TamagoCheckRename(env,ctx);
		// renommer les proprietes dans la post
		GExpression effpost = ctx.getPercolation().genEffPostExpr(meth);

		effpost = (GExpression) effpost.visitExpression(ttr); 
		ope.addOperand(effpost);

		GExpression guard = transition.getCondition();
		if(guard != null && transition.getCondition().getCategory() != GExprType.NOCONTRACT) {
			// renommer les proprietes dans la transition

			guard = (GExpression) guard.visitExpression(ttr);
			// renvoyer la concatenation
			ope.addOperand(guard);
		}


		for (GInvariant inv : contract.getInvariants()) {
			ope.addOperand(inv.getExpression());
			// TODO ajouter les expressions
			GExpression postinvariant = (GExpression) inv.getExpression().visitExpression(ttr);
			ope.addOperand(postinvariant);
		}
/*		
		if((ctx.selectedDNFInvariant() != null)	&& (ctx.selectedDNFInvariant().getCategory() != GExprType.NOCONTRACT))
		{
			TamagoCCLogger.println(3, " retrieve the specific DNF invariant");
			// DONE reflechir si cela etait interessant: ope.addOperand(ctx.selectedDNFInvariant());
			ope.addOperand(ctx.selectedDNFInvariant());
			// TODO ajouter les expressions
			GExpression postinvariant = (GExpression) ctx.selectedDNFInvariant().visitExpression(ttr);
			ope.addOperand(postinvariant);

		}
*/
		ope.addOperand(ctx.getPercolation().genEffPreExpr(meth));

		// nouvel algo on ajoute la negation de tous les autres gardes si elle existe (sans compte l'implicite)
		GState origine = transition.getOrigin();
		Iterator<GTransition> trans = origine.getOutgoingTransitions();
		while(trans.hasNext()) {
			GTransition tran = trans.next();
			if((tran != transition)
					&& tran.getMethodID().equals(transition.getMethodID())
					&& (tran.getCondition() != null)
					&& (!tran.getCondition().equals(transition.getCondition()))
					&& (tran.getCondition().getCategory() != GExprType.NOCONTRACT))
			{
				GExpression expr = new GINot((GExpression)tran.getCondition().visitExpression(ttr));
				TamagoCCLogger.println(4, " * Transition with an other guard has been found and added: "+TamagoCCMakeReadableGExpression.toString(expr));
				ope.addOperand(expr);
			}
		}

		// fin nouvel algo
		

		TamagoCSPFlatten flat = new TamagoCSPFlatten(ope);
		GExpression res = flat.flatten();

		TamagoCCLogger.println(3, " -- Prerequisite condition after strategy: "+TamagoCCMakeReadableGExpression.toString(res));

		return res;
	}
	
	private static void enrichEnvironment(TamagoEnvironment env, TamagoEnvironment env_props) {
		env.putAll(env_props);
		/*
		for (TamagoBuilder builder : env.values()) {
			try {
				TamagoCCLogger.println(3, "  -- Fix backtrack on property: "+builder.getCSPvar(null, null).getName());
			} catch (TamagoBuilderException e) { }
			builder.setBacktrack(csp.getBacktrack());
		}
		 */
	}
	
	private static void extractParameters(GMethod meth,TamagoEnvironment env) {
		TamagoCCLogger.println(3, "Extraction of parameters:");
		Iterator<GParameter> params =  meth.getParameters();

		while(params.hasNext()) {
			try {
				GParameter param = params.next();
				TamagoCCLogger.print(3,"  Extraction of "+param.getName());
				TamagoBuilder tb = ctx.getTypeBuilderFactory().searchBuilder(env,param.getName(), param.getType(), null);
				env.put(param.getName(), tb);
				TamagoCCLogger.println(3," [OK]");
			}
			catch(Exception e) {
				TamagoCCLogger.print(3," [KO]");
				TamagoCCLogger.print(4," Error during searching the builder of this type");
				TamagoCCLogger.println(3,"");
			}
		}

		TamagoCCLogger.println(3, "Analyze of the return type of the method:");
		switch(meth.getType().catType()) {
		case VOID:
			TamagoCCLogger.println(3," void type detected");
			break;
		default: {
			TamagoCCLogger.println(3," type detected linked to the variable: "+meth.getSavedResult().getName());
			try {
				TamagoBuilder tb = ctx.getTypeBuilderFactory().searchBuilder(env,meth.getSavedResult().getName(), meth.getType(), null);
				env.put(meth.getSavedResult().getName(), tb);
			}
			catch(Exception e) {
				TamagoCCLogger.println(3,"Error during searching the builder of this returned type");
			}
		}
		}

	}

	private static void searchSolutions(int prof, GTamago contract, GTransition transition,
			TamagoEnvironment env_props, TamagoCheckFixPointSeeker global, 
			Stack<Triplet<GState, Integer, TamagoCheckState>> pile, 
			GState state, TamagoCheckState localstate) throws TamagoCCException  {

		// 1 on prepare la precondition ainsi que l'invariant
		GMethod meth = contract.getMethod(transition.getMethodID());
		TamagoCCLogger.println(3, "Call of the method: "+transition.getMethodID());

		// on doit chercher l'oracle en premier pour renommer les prop necessaires

		// 2 regle de reecritures pour transforme en FND
		GExpression constraints = genPrerequisiteExpression(contract, meth,transition,env_props);
		try {
			TamagoCCMakeReadableGExpression make = new TamagoCCMakeReadableGExpression(constraints);
			TamagoCCLogger.println(3, "Expression : "+make.getStrExpression());
		} catch (TamagoCCException e1) {
			e1.printStackTrace();
		}
		
		TamagoEnvironment env = new TamagoEnvironment();

		enrichEnvironment(env,env_props);
		extractParameters(meth,env);

		// Choisir aleatoirement une FNC et detecter les variables
		TamagoCSPDNF dnf = new TamagoCSPDNF(constraints);
		TamagoCCLogger.println(3, "DNF :" +dnf.size());
		if(TamagoCCLogger.getLevel() >= 4) {
			TamagoCCLogger.println(4,"---------");
			for (GExpression expr : dnf) {
				TamagoCCLogger.println(4,"\t DNF expression: "+TamagoCCMakeReadableGExpression.toString(expr));
			}
			TamagoCCLogger.println(4,"---------");
		}

		ArrayList<GExpression> arraydnf = new ArrayList<GExpression>(dnf.getCollection());
		TamagoCSP csp_resolut = null;
		for (GExpression gexpr : arraydnf) {
			ctx.getReport().openDNF(ctx,transition,localstate,gexpr);
			
			for (Entry<String, CSPAbstractDomain> prop : localstate.getEntries()) {
				env_props.get(prop.getKey()).getCSPvar().load(prop.getValue());
			}	
			
			// TODO: ici peut etre un probleme d'initialisation du CSP.
			// une solution serait de sauvegarder le CSP avant de lui ajouter les contraintes.
			// DONE: la solution adopte est de limiter l'utilisation memoire, suite au not enough memory assez
			// frequent lors des phases de tests, donc j'utilise la methode uninstallAllConstraints qui nettoie
			// les dependances des strings et qui permet de retirer les sous-chaines des strings car elle agisse
			// directement comme des contraintes juste par leur presence. (voir la fonction clearConstraints du csp)
			csp_resolut = solveParameters(env,gexpr);
			if(csp_resolut == null) {
				TamagoCCLogger.infoln(2, "+++++++++++++++++++++++++++");
				TamagoCCLogger.infoln(2, "\t@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ IMPOSSIBLE BRANCH: "+localstate.toString());
				TamagoCCLogger.infoln(2, "+++++++++++++++++++++++++++");
				ctx.getReport().impossibleBranch(ctx,transition,localstate,gexpr);
			}
			else {
				updateProperties(env, env_props);
				TamagoCheckState nextstate = new TamagoCheckState(transition.getFinal(),localstate,transition);
				for (Entry<String, TamagoBuilder> entry : env_props.entrySet()) {
					nextstate.register(entry.getKey(), entry.getValue().getCSPvar().getAbstractDomain());
				}
				ctx.getReport().registered(localstate,nextstate);
				// -- par secu?
				Backtracking backtrack =  csp_resolut.getBacktrack();
				while(backtrack.size()>0) {
					tamago.csp.Triplet t = backtrack.pop();
					CSPvar v = t.getVariable();
					TamagoCCLogger.println(4, "    TRIPLET on "+v.getName());
					TamagoCCLogger.println(4, "            flag "+t.isForward());
					TamagoCCLogger.println(4, "            val "+t.getValue());
					try {
						v.retrieve(t);
					} catch (TamagoCSPException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} 
				// -- par secu?
				int old =global.findFixPoint(nextstate); 
				if(old != -1) {
					TamagoCCLogger.infoln(2, "+++++++++++++++++++++++++++");
					TamagoCCLogger.infoln(2, "\t@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ FIX POINT: "+nextstate.toString());
					TamagoCCLogger.infoln(2, "+++++++++++++++++++++++++++");
					ctx.getReport().findFixpoint(ctx,transition,nextstate,global.get(old));
				}
				else {
					global.register(nextstate);
					Triplet<GState, Integer, TamagoCheckState> triplet2 =
						new Triplet<GState, Integer, TamagoCheckState>(transition.getFinal(),prof+1,nextstate);
					TamagoCCLogger.infoln(2, "+++++++++++++++++++++++++++");
					TamagoCCLogger.infoln(2, "\tREGISTER: "+nextstate.toString());
					TamagoCCLogger.infoln(2, "+++++++++++++++++++++++++++");
					pile.add(triplet2);
				}
			}
		}
		ctx.getReport().closeDNF(ctx,transition,localstate);
	}
	
	private static void updateProperties(TamagoEnvironment env, TamagoEnvironment env_props) {
		ArrayList<String> keys = new ArrayList<String>();
		for (Entry<String, TamagoBuilder> entry : env_props.entrySet()) {
			if(entry.getKey().endsWith("@post")) {
				String props = entry.getKey().replaceFirst("@post", "");
				TamagoCCLogger.println(3, " => Modification of the property: "+props);
				env_props.put(props, entry.getValue());
				entry.getValue().setName(props);
				keys.add(entry.getKey());
			}
		}

		for (String string : keys) {
			env_props.remove(string);
			TamagoCCLogger.println(3,"Deleting temporarly keys: "+string);
		}
	}

	private static TamagoCSP solveParameters(TamagoEnvironment env, GExpression expr) throws TamagoCCException {
		TamagoCSPFlatten flat = new TamagoCSPFlatten(expr);
		expr = flat.flatten();

		TamagoCSP csp = new TamagoCSP();
		solvercreated++;

		TamagoCCLogger.println(3, "Registering CSP variables:");
		for (Entry<String, TamagoBuilder> entry : env.entrySet()) {
			try {
				TamagoCCLogger.print(3,"  Registering of "+entry.getKey());
				CSPvar var = entry.getValue().getCSPvar(null,null);
				entry.getValue().setBacktrack(csp.getBacktrack());
				csp.addVariable(var);
				TamagoCCLogger.println(3," [OK] "+var.toString());
			}
			catch(Exception e) {
				TamagoCCLogger.println(3," [KO]");
				TamagoCCLogger.print(4," Error during registering the CSPvar: "+entry.getKey());
				TamagoCCLogger.println(3,"");
			}
		}

		csp.clearConstraints();


		try {
			TamagoCCMakeReadableGExpression make = new TamagoCCMakeReadableGExpression(expr);
			TamagoCCLogger.println(3,"  -- constraints : "+make.getStrExpression());
		} catch (TamagoCCException e1) {
			e1.printStackTrace();
		}

		TamagoCSPInferConstraint infer = new TamagoCSPInferConstraint(csp,env);
		if((expr.getCategory() == GExprType.OPERATOR)
				&& (((GOperator)expr).getOperator().equals(TOpeName.opAnd))) {
			Iterator<GExpression> item = ((GOperator)expr).getOperands();
			while(item.hasNext()) {
				GExpression tmpexpr = item.next();
				TamagoCCMakeReadableGExpression tccmrg = new TamagoCCMakeReadableGExpression(tmpexpr);
				try {
					TamagoCCLogger.print(3," -- Conversion sub expr : ");
					TamagoCCLogger.println(3,tccmrg.getStrExpression());
				} catch (TamagoCCException e) {		}

				if(!infer.generate(tmpexpr)) {
					//TamagoCCMakeReadableGExpression tccmrg = new TamagoCCMakeReadableGExpression(tmpexpr);
					TamagoCCLogger.print(3,"  -- Problem to convert the sub expr ");
					try {
						TamagoCCLogger.print(3,": "+tccmrg.getStrExpression());
					} catch (TamagoCCException e) {		}
					TamagoCCLogger.println(3,"");

				} 

			}
		}
		else {
			if(infer.generate(expr)) {
				TamagoCCMakeReadableGExpression tccmrg = new TamagoCCMakeReadableGExpression(expr);
				TamagoCCLogger.print(3," -- Problem to convert the expr ");
				try {
					TamagoCCLogger.print(3,": "+tccmrg.getStrExpression());
				} catch (TamagoCCException e) {		}
				TamagoCCLogger.println(3,"");
			}
		}

		String tmp= csp.toString();
		try {
			csp.fixBacktrack();
			csp.minimize();
			TamagoCCLogger.println(3, "Minimize [OK]");
			TamagoCCLogger.println(3, csp.toString());
			return csp;
		}
		catch(Exception e) {
			Backtracking backtrack =  csp.getBacktrack();
			while(backtrack.size()>0) {
				tamago.csp.Triplet t = backtrack.pop();
				CSPvar v = t.getVariable();
				TamagoCCLogger.println(4, "    TRIPLET on "+v.getName());
				TamagoCCLogger.println(4, "            flag "+t.isForward());
				TamagoCCLogger.println(4, "            val "+t.getValue());
				try {
					v.retrieve(t);
				} catch (TamagoCSPException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
			String tmp2 = csp.toString();
			TamagoCCLogger.println(3, "Error during resolution:");
			TamagoCCLogger.infoln(3,e);
			TamagoCCLogger.println(4, "*** CSP backup restored?");
			TamagoCCLogger.println(4, tmp2);
			TamagoCCLogger.println(4, "*** END CSP backup restored?");
			if(!tmp.equals(tmp2))
				TamagoCCLogger.println(4, "restore env failed?!");
			else 
				TamagoCCLogger.println(4, "Env OK");
			return null;
		}
	}
	
	private static GBehavior buildDefaultBehavior(GTamago contract) {

		ArrayList<GAllow> allows = makeAllow(contract);

		GIState unique = new GIState(contract,"0", allows);

		ArrayList<GTransition> trans = makeTransitions(contract,unique);
		ArrayList<GState> states = new ArrayList<GState>();
		states.add(unique);

		try {
			GIBehavior behavior = new GIBehavior(unique,states,trans);
			return behavior;
		} catch (TamagoCCNotFoundState e) {
			e.printStackTrace();
		}

		return null;
	}

	private static ArrayList<GTransition> makeTransitions(GTamago contract, GIState unique) {
		ArrayList<GTransition> res = new ArrayList<GTransition>();
		for (GMethod meth : contract.getUniqueMethods()) {
			res.add(new GITransition(unique,unique,meth.getID(),GINoContract.NOCONTRACT));
		}
		return res;

	}

	private static ArrayList<GAllow> makeAllow(GTamago contract) {
		ArrayList<GAllow> res = new ArrayList<GAllow>();
		for (GMethod meth : contract.getUniqueMethods()) {
			res.add(new GIAllow(meth.getID()));
		}
		return res;
	}

	

	
	private static ArrayList<GTransition> searchAllTransitions(GState state) throws TamagoCCException {

		ArrayList<GAllow> meths = searchAllowedMethod(state);
		ArrayList<GTransition> transitions = new ArrayList<GTransition>();
		for (GAllow meth : meths) {

			if(meth == null)
				return transitions;

			enrichDeclaredTransitions(state,meth,transitions);

			needImplicitTransitions(state,meth, transitions);
		}
		return transitions;
	}

	private static void needImplicitTransitions(GState state, GAllow allow,
			ArrayList<GTransition> transitions) {
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

	private static void enrichDeclaredTransitions(GState state, GAllow allow,
			ArrayList<GTransition> transitions) {
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

	private static ArrayList<GAllow> searchAllowedMethod(GState state) {
		ArrayList<GAllow> allows = new ArrayList<GAllow>();
			if(state.sizeAllowedMethods() <= 0)
				return allows;
			Iterator<GAllow> meths = state.getAllowsMethod();
			while(meths.hasNext()) {
				allows.add(meths.next());
			}
			return allows;
		
	}

}
