package tamagotest;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import tamago.builder.TamagoBuilder;
import tamago.builder.TamagoEnvironment;
import tamago.csp.TamagoCSP;
import tamago.csp.convert.TamagoCSPDNF;
import tamago.csp.convert.TamagoCSPFlatten;
import tamago.csp.convert.TamagoCSPInferConstraint;
import tamago.csp.exception.TamagoCSPRuntime;
import tamago.csp.generic.CSPvar;
import tamago.lineparser.TamagoStaticDefaultContract;
import tamago.lineparser.TamagoTargetGenerator;
import tamago.lineparser.TamagoTestComponent;
import tamago.lineparser.TamagoTestComponentImpl;
import tamago.lineparser.TamagoTestDest;
import tamago.lineparser.TamagoTestGeneratorSelector;
import tamago.lineparser.TamagoTestJunitSuffix;
import tamago.lineparser.TamagoTestLengthScenario;
import tamago.lineparser.TamagoTestMode;
import tamago.lineparser.TamagoTestScenariiCount;
import tamago.lineparser.TamagoTestStrategySelector;
import tamagocc.TamagoCCParser;
import tamagocc.api.TOpeName;
import tamagocc.api.TTamago;
import tamagocc.api.TTamagoEntity;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.impl.AINoExpression;
import tamagocc.ast.impl.AISequence;
import tamagocc.exception.TamagoCCException;
import tamagocc.exception.TamagoCCNotFoundChild;
import tamagocc.exception.TamagoCCNotFoundState;
import tamagocc.generator.TamagoCCGeneratorCommon;
import tamagocc.generic.TamagoCCGPool;
import tamagocc.generic.api.GAllow;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GInvariant;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GOperator;
import tamagocc.generic.api.GParameter;
import tamagocc.generic.api.GPreExpression;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GTransition;
import tamagocc.generic.api.GExpression.GExprType;
import tamagocc.generic.impl.GIAllow;
import tamagocc.generic.impl.GIAtPreInitialisation;
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
import tamagotest.report.TamagoTestGeneratorJUnit;
import tamagotest.runtime.HarnessParser;
import tamagotest.runtime.TamagoTestPerform;
import tamagotest.util.NullTamagoTestUI;
import tamagotest.util.TamagoTestUI;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTest {

	public static final int VERSION_MAJOR = 0;
	public static final int VERSION_MINOR = 1;

	
	public static TamagoTestUI ui = new NullTamagoTestUI();


	private static TamagoTestUI ui() {
		if(ui == null)
			return new NullTamagoTestUI();
		else
			return ui;
	}
	
	private static TamagoTestContext ctx;
	private static boolean genjunit = false;

	public static TamagoTestContext getContext() {
		if(ctx == null)
			ctx = new TamagoTestContext();
		return ctx;
	}
	
	public static void resetContext() {
		ctx = null;
	}

	private static long executionTestFile(String file) {
		TamagoCCLogger.println(1, "Manipulation of the abstract test scenarios"+file+"...");

		HarnessParser parser = new HarnessParser();
		try {
			TamagoTestPerform perform =  parser.parse(file);
			perform.getContractInformation();
			TamagoCCLogger.println(2, "\tTarget entity:"+perform.getContractInformation().getName());
			TamagoCCLogger.println(2, "\tTarget module:"+perform.getContractInformation().getModule());
			TamagoCCLogger.println(2, "\tTarget percolation:"+perform.getContractInformation().getPercolator());
			TamagoCCLogger.println(2, "\tScenario count:"+perform.getContractInformation().getScenarioCount());

			try {
				String name = perform.getContractInformation().getName();
				String module = perform.getContractInformation().getModule();
				GTamago contract = (GTamago) TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(name, module);
				ctx.setContract(contract);
				for (TamagoTestScenario scenario : perform.getScenario()) {
					ctx.addScenario(scenario);
				}
				ctx.getGenReport().prepare();
				ctx.getGenReport().write();

			} catch (TamagoCCException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (TamagoCCNotFoundChild e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TamagoTestException e) {
			e.printStackTrace();
		}


		return System.currentTimeMillis();
	}


	public static long generateTestFile(String file) throws TamagoCCException {

		TamagoCCLogger.println(1, "Test Generation for "+file+"...");
		GTamago contract = searchContract(file);
		TamagoCCLogger.println(3, "Conversion OK.");
		return proceedTest(contract);
	}
	
	public static long generateTestFile(String name,String module) throws TamagoCCException {
		return proceedTest((GTamago) TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(name, module));
	}
	
	public static long generateTestFile(GTamago tamago) throws TamagoCCException {
		return proceedTest(tamago);
	}
	
	public static long generateTestFile(TTamago tamago) throws TamagoCCException {
		return proceedTest((GTamago) TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(tamago));
	}
	
		
		
	private static long proceedTest(GTamago contract) throws TamagoCCException {
		long t2 = System.currentTimeMillis();
		ctx.setContract(contract);
		while(ctx.getStrategy().makeNewScenario() && ui().canContinue()) {

			try {
				// les deux sont obligatoires pour remettre le systeme en etat.
				TamagoCCLogger.println(3," **** RESETTING SYSTEM **** ");
				ctx.setContract(contract); 
				ctx.getStrategy().reset();
				ui().beginScenario();
				TamagoCCLogger.println(3," **** Generation of Scenario : "+(ctx.getStrategy().getScenarioName())+" ****");
				TamagoCCLogger.println(3," **** Used Strategy : "+ctx.getStrategy().getName()+" ****");

				if(contract.getBehavior().countStates() == 0) {
					TamagoCCLogger.println(3, "No behavior detected");
					// DONE builds an automaton that allow every methods
					GBehavior def = buildDefaultBehavior(contract);
					followBehavior(contract,def.getDefaultStates().iterator().next());
				}
				else {
					TamagoCCLogger.println(3, "Behavior detected");
					// we run from the first state
					// by default the behavior is the projected automaton (completed with the product)
					followBehavior(contract,contract.getBehavior().getDefaultStates().iterator().next());
				}
			}
			catch(Exception e) {
				TamagoCCLogger.println(1, "Error during the animation");
				TamagoCCLogger.info(1, e);
			}
			ui().endScenario();
			TamagoCCLogger.println(3, ctx.getScenario().toString());
			ctx.addScenario(ctx.getScenario());
			TamagoCCLogger.println(3," **** End generation of Scenario : "+(ctx.getStrategy().getScenarioName())+" ****");

		}
		t2 = System.currentTimeMillis();


		// Generation du fichier permettant de tester les fonctions
		if(genjunit && ui().canContinue()) {
			try {
				ui().beginWriteJUnit();
				TamagoTestGeneratorJUnit ttga = new TamagoTestGeneratorJUnit(ctx);
				ttga.write();
				ui().endWriteJUnit();
			}
			catch(Exception e) {
				TamagoCCLogger.println(1, "Error during the generation of JUnit");
				TamagoCCLogger.info(1, e);
			}
		}
		if(ui.canContinue()) {
			TamagoCCLogger.println(3,"Generation of the abstract test cases");
			TamagoTestGenerator ttg = ctx.getGenReport();// = new TamagoTestGenerator(contract,ctx.getScenario(),ctx.getPercolation());
			try {
				ui().beginWriteTest();
				ttg.prepare();
				
				OutputStream output = ctx.getOutputStream();
				if(output == null)
					ttg.write();
				else
					ttg.write(output);
				ui().endWriteTest();
			}
			catch(Exception exc) {
				TamagoCCLogger.info(exc);	
			}

			TamagoCCLogger.println(3,"Test case Generation [OK]");
		}
		return t2;

	}

	public static GBehavior buildDefaultBehavior(GTamago contract) {
		
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

	/**
	 * @param args
	 * @throws TamagoCCException 
	 */
	public static void main(String[] args) throws TamagoCCException {
		TamagoCCLogger.println(1, "TamagoTest - version 0.1");
		ctx = new TamagoTestContext();

		LineParser lineparser = new LineParser("TamagoTest","Generate test case of contract written in the TamagoCDL language.\nAuthors: Hakim Belhaouari <hakim.belhaouari@lip6.fr>\n\t Frederic Peschanski <frederic.peschanski@lip6.fr>.");
		TamagoStaticDefaultContract filename = new TamagoStaticDefaultContract();
		TamagoTestMode mode = new TamagoTestMode();
		try {
			TamagoCCParser.getDefaultParser();
			TamagoCCPool.getDefaultPool();
			TamagoCCPercolation.initialisation();

			lineparser.addSpec(mode);
			lineparser.addSpec(new tamagocc.util.lineparser.TamagoCCLogFile());
			lineparser.addSpec(new TamagoCCLogLevel());
			lineparser.addSpec(new TamagoCCPathCmd());
			lineparser.addSpec(new TamagoCCSetXSD());
			lineparser.addSpec(new TamagoCCPercolator());
			lineparser.addSpec(new TamagoTargetGenerator(ctx));
			lineparser.addSpec(new TamagoTestGeneratorSelector(ctx));
			lineparser.addSpec(new TamagoTestLengthScenario(ctx));
			lineparser.addSpec(new TamagoTestDest(ctx));
			lineparser.addSpec(new TamagoTestScenariiCount(ctx));
			lineparser.addSpec(new TamagoTestComponent(ctx));
			lineparser.addSpec(new TamagoTestComponentImpl(ctx));
			lineparser.addSpec(new TamagoTestStrategySelector(ctx));
			lineparser.addSpec(new TamagoTestJunitSuffix(ctx));
			lineparser.setDefaultSpec(filename);
			lineparser.parse(args);	

			// Here we are parsed all arguments of the command line
			// and we can ask the context to initialize all stuffs
			ctx.initialize();
		}
		catch(Exception e) {
			TamagoCCLogger.infoln(1,lineparser.toString());
			TamagoCCLogger.print(1,"Error in the command line: ");
			TamagoCCLogger.infoln(1,e.getMessage());
		}
		lineparser = null;

		long d = System.currentTimeMillis();
		long t2 = System.currentTimeMillis();

		// maintenant il faut que chaque
		for (String file : filename.getItems()) {
			switch(mode.getMode()) {
			case GENERATION:
				t2 = generateTestFile(file);
				break;
			case EXECUTION:
				t2 = executionTestFile(file);
				break;
			}

		}

		long tps = (t2 - d);
		TamagoCCLogger.println(1,"Elapsed time: "+tps+ " ms");
	}

	private static void followBehavior(GTamago contract, GState initstate) throws TamagoCCException, TamagoTestException {
		long step = 0; // display information
		GState state = initstate;

		TamagoEnvironment env_props = new TamagoEnvironment();

		extractProperties(contract,env_props);

		minimizeProperties(contract,env_props);		

		
		GTransition transition = ctx.getFixPointDectector().getTransition(state, ctx);
		while(transition != null && ui().canContinue()){
			
			TamagoCCLogger.println(2, "Selection of transition: "+transition.toString());

			try {

				TamagoCCLogger.println(3, " **** **** STEP : "+(++step)+" **** ****");

				TamagoCCLogger.println(3," Properties states:");
				for (Entry<String, TamagoBuilder> entry : env_props.entrySet()) {
					TamagoCCLogger.println(3,"   "+entry.getKey()+" -> "+ entry.getValue().getCSPvar(null,null).toString());
				}

				// 1 on prepare la precondition ainsi que l'invariant
				GMethod meth = contract.getMethod(transition.getMethodID());
				TamagoCCLogger.println(3, "Call of the method: "+transition.getMethodID());

				// on doit chercher l'oracle en premier pour renommer les prop necessaires
				GExpression oracle = genPredictBehaviorExpression(contract,meth,transition,env_props);


				// 2 regle de reecritures pour transforme en FND
				GExpression constraints = genPrerequisiteExpression(contract, meth,transition,env_props);
				try {
					TamagoCCMakeReadableGExpression make = new TamagoCCMakeReadableGExpression(constraints);
					TamagoCCLogger.println(3, "Expression : "+make.getStrExpression());
				} catch (TamagoCCException e1) {
					e1.printStackTrace();
				}

				GExpression precondition = genPreconditionExpression(contract,meth,transition,env_props);

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
				do {
					// TODO: ici peut etre un probleme d'initialisation du CSP.
					// une solution serait de sauvegarder le CSP avant de lui ajouter les contraintes.
					// DONE: la solution adopte est de limiter l'utilisation memoire, suite au not enough memory assez
					// frequent lors des phases de tests, donc j'utilise la methode uninstallAllConstraints qui nettoie
					// les dependances des strings et qui permet de retirer les sous-chaines des strings car elle agisse
					// directement comme des contraintes juste par leur presence. (voir la fonction clearConstraints du csp)
					GExpression gexpr = selector(arraydnf);
					csp_resolut = solveParameters(env,gexpr);
					if(csp_resolut == null) {
						TamagoCCLogger.println(3, "I must take a new DNF");
						TamagoCCLogger.println(3, "Call of the method: "+transition.getMethodID());
					}
				} while(csp_resolut == null && ui().canContinue());	

				TamagoCCLogger.println(3, " --- Registering the current test case");

				registerTestCase(transition,meth,csp_resolut,precondition,oracle);


				//GExpression finaleff = genFinalEffPostcondition(contract,meth,transition);
				//updateProperties(finaleff,env_props);

				updateProperties(env,env_props);

				TamagoCCLogger.println(3, "End Step : "+step);
				ctx.getFixPointDectector().fetch();
				ui().finishStep();
				state = transition.getFinal();
			}
			catch(Exception e) {
				TamagoCCLogger.println(2, "The transition "+transition.toString()+" failed, I try to take a new one");
				TamagoCCLogger.infoln(3, e);
			}
			transition = ctx.getFixPointDectector().getTransition(state, ctx);
		}
	}

	private static void followBackBehavior(GTamago contract, GState finalstate) throws TamagoCCException, TamagoTestException {
		long step = 0; // display information
		GState state = finalstate;

		TamagoEnvironment env_props = new TamagoEnvironment();

		extractProperties(contract,env_props);

		
		
		GTransition transition = ctx.getFixPointDectector().getTransition(state, ctx);
		// affect first contrainst.
		
		// from this contraints we want to instanciate an object
		// and from method
		
		firstConstraintsProperties(contract, env_props);
		
		while(transition != null && ui().canContinue()){
			
			TamagoCCLogger.println(2, "Selection of transition: "+transition.toString());

			try {

				TamagoCCLogger.println(3, " **** **** BACK STEP : "+(++step)+" **** ****");

				TamagoCCLogger.println(3," Properties states:");
				for (Entry<String, TamagoBuilder> entry : env_props.entrySet()) {
					TamagoCCLogger.println(3,"   "+entry.getKey()+" -> "+ entry.getValue().getCSPvar(null,null).toString());
				}

				// 1 on prepare la precondition ainsi que l'invariant
				GMethod meth = contract.getMethod(transition.getMethodID());
				TamagoCCLogger.println(3, "Call of the method: "+transition.getMethodID());

				// on doit chercher l'oracle en premier pour renommer les prop necessaires
				GExpression oracle = genPredictBehaviorExpression(contract,meth,transition,env_props);


				// 2 regle de reecritures pour transforme en FND
				GExpression constraints = genPrerequisiteExpression(contract, meth,transition,env_props);
				try {
					TamagoCCMakeReadableGExpression make = new TamagoCCMakeReadableGExpression(constraints);
					TamagoCCLogger.println(3, "Expression : "+make.getStrExpression());
				} catch (TamagoCCException e1) {
					e1.printStackTrace();
				}

				GExpression precondition = genPreconditionExpression(contract,meth,transition,env_props);

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
				do {
					// TODO: ici peut etre un probleme d'initialisation du CSP.
					// une solution serait de sauvegarder le CSP avant de lui ajouter les contraintes.
					// DONE: la solution adopte est de limiter l'utilisation memoire, suite au not enough memory assez
					// frequent lors des phases de tests, donc j'utilise la methode uninstallAllConstraints qui nettoie
					// les dependances des strings et qui permet de retirer les sous-chaines des strings car elle agisse
					// directement comme des contraintes juste par leur presence. (voir la fonction clearConstraints du csp)
					GExpression gexpr = selector(arraydnf);
					csp_resolut = solveParameters(env,gexpr);
					if(csp_resolut == null) {
						TamagoCCLogger.println(3, "I must take a new DNF");
						TamagoCCLogger.println(3, "Call of the method: "+transition.getMethodID());
					}
				} while(csp_resolut == null && ui().canContinue());	

				TamagoCCLogger.println(3, " --- Registering the current test case");

				registerTestCase(transition,meth,csp_resolut,precondition,oracle);


				//GExpression finaleff = genFinalEffPostcondition(contract,meth,transition);
				//updateProperties(finaleff,env_props);

				updateProperties(env,env_props);

				TamagoCCLogger.println(3, "End Step : "+step);
				ctx.getFixPointDectector().fetch();
				ui().finishStep();
				state = transition.getFinal();
			}
			catch(Exception e) {
				TamagoCCLogger.println(2, "The transition "+transition.toString()+" failed, I try to take a new one");
				TamagoCCLogger.infoln(3, e);
			}
			transition = ctx.getFixPointDectector().getTransition(state, ctx);
		}
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

	private static GExpression genPredictBehaviorExpression(GTamago contract, GMethod meth, GTransition transition, TamagoEnvironment env) throws TamagoCCException {
		// renommer les proprietes dans la post
		GExpression effpost = ctx.getPercolation().genEffPostExpr(meth);


		// renommer les proprietes dans la transition
		GExpression guard = transition.getCondition();

		// renvoyer la concatenation

		GExpression invariant = (GExpression) ctx.selectedDNFInvariant();

		GIOperator ope = new GIOperator(TOpeName.opAnd);
		ope.addOperand(effpost);
		ope.addOperand(guard);
		ope.addOperand(invariant);

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
				GExpression expr = new GINot((GExpression)tran.getCondition());
				TamagoCCLogger.println(4, " * Transition with an other guard has been found and added: "+TamagoCCMakeReadableGExpression.toString(expr));
				ope.addOperand(expr);
			}
		}

		// fin nouvel algo

		TamagoCCLogger.println(3, " -- Raw Oracle: "+TamagoCCMakeReadableGExpression.toString(ope));		

		GExpression res = ctx.getStrategy().strategyForOracle(ope);
		TamagoCCLogger.println(3, " -- Oracle after strategy: "+TamagoCCMakeReadableGExpression.toString(res));

		return res;
	}

	private static void registerTestCase(GTransition transition,GMethod meth,TamagoCSP csp,GExpression gprecondition, GExpression goracle) throws TamagoTestException {
		TamagoCCGeneratorCommon common = new TamagoCCGeneratorCommon(null,ctx.getContract());
		try {

			// -- Preparation en langage A.
			ctx.getStrategy().registerTestCase(csp);

			// -- everything about the preconditions/prerequisite condition
			AISequence initprecond = new AISequence();
			AExpression precondition = AINoExpression.getNoExpression();
			try {
				precondition = (AExpression) gprecondition.visitExpression(common);
			}
			catch(Exception e) {	
				TamagoCCLogger.infoln(4,e);
			}

			{
				Collection<GPreExpression> preexpr= gprecondition.getPreExpression();
				for (GPreExpression expression : preexpr) {
					initprecond.addInstruction((AInstruction) expression.visit(common));
				}
			}


			// -- everything about the oracle
			AISequence initoracle = new AISequence(); // factorisation pour le calcul de la post
			AISequence atpreoracle = new AISequence(); // variable initialise dans la pre pour utiliser dans la post (atpre)
			AExpression oracle = AINoExpression.getNoExpression();
			try {
				oracle = (AExpression) goracle.visitExpression(common);
			}
			catch(Exception e) { 
				TamagoCCLogger.infoln(4,e);
			}

			{
				Collection<GPreExpression> preexpr= goracle.getPreExpression();
				for (GPreExpression expression : preexpr) {
					if(expression instanceof GIAtPreInitialisation)
						atpreoracle.addInstruction((AInstruction) expression.visit(common));
					else
						initoracle.addInstruction((AInstruction) expression.visit(common));
				}
			}


			Hashtable<String,  Triplet<AExpression, AInstruction,AInstruction>> res = new Hashtable<String, Triplet<AExpression,AInstruction,AInstruction>>();
			for(CSPvar var : csp.getVariables()) {
				res.put(var.getName(), new Triplet<AExpression,AInstruction, AInstruction>(
						var.getValue().toAExpression(),
						var.getValue().toPreExpression(), 
						var.getValue().toPostExpression()));
			}

			TamagoTestCase ttc = new TamagoTestCase(transition,meth.getID(),initprecond.flattern(),precondition,
					atpreoracle,initoracle,oracle);
			ctx.getScenario().addTestCase(ttc);

			// 3 on remplit le cas de test avec les valeurs de test trouve
			Iterator<GParameter> params = meth.getParameters();
			while(params.hasNext()) {
				GParameter param = params.next();
				if(res.containsKey(param.getName()))
					ttc.put(param.getName(), res.get(param.getName()));
			}
			
			if(meth.mustSaveResult()) {
				//ttc.put(meth.getSavedResult().getName(), res.get(meth.getSavedResult().getName()));
				Triplet<AExpression, AInstruction,AInstruction> value = res.get(meth.getSavedResult().getName());
				ttc.putResult(value.l(), value.c(), value.r());
			}

		}
		catch(TamagoCCException e) {
			throw new TamagoTestException(e);
		}

	}

	private static TamagoCSP solveParameters(TamagoEnvironment orienv, GExpression expr) throws TamagoCCException {
		TamagoCSPFlatten flat = new TamagoCSPFlatten(expr);
		expr = flat.flatten();
		
		TamagoEnvironment env = (TamagoEnvironment) orienv.clone();

		TamagoCSP csp = new TamagoCSP();
		
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
			csp.solve();
			TamagoCCLogger.println(3, "Resolve [OK]");
			TamagoCCLogger.println(3, csp.toString());
			return csp;
		}
		catch(Exception e) {
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
				TamagoBuilder tb = ctx.getStrategy().searchBuilder(param.getName(), param.getType(), meth, env);
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
				TamagoBuilder tb = ctx.getStrategy().searchBuilder(meth.getSavedResult().getName(), meth.getType(), meth,env);
				env.put(meth.getSavedResult().getName(), tb);
			}
			catch(Exception e) {
				TamagoCCLogger.println(3,"Error during searching the builder of this returned type");
			}
		}
		}

	}

	public static GExpression genPrerequisiteExpression(GTamago contract, GMethod meth, GTransition transition, TamagoEnvironment env) throws TamagoCCException {
		TamagoCCLogger.print(3, "Generation of prerequisite condition (percolator=");
		TamagoCCLogger.print(3,ctx.getPercolation().getName());
		TamagoCCLogger.println(3,")");
		GIOperator ope = new GIOperator(TOpeName.opAnd);


		TamagoTestRename ttr = new TamagoTestRename(env);
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


		if((ctx.selectedDNFInvariant() != null)	&& (ctx.selectedDNFInvariant().getCategory() != GExprType.NOCONTRACT))
		{
			TamagoCCLogger.println(3, " retrieve the specific DNF invariant");
			// DONE reflechir si cela etait interessant: ope.addOperand(ctx.selectedDNFInvariant());
			ope.addOperand(ctx.selectedDNFInvariant());
			// TODO ajouter les expressions
			GExpression postinvariant = (GExpression) ctx.selectedDNFInvariant().visitExpression(ttr);
			ope.addOperand(postinvariant);

		}

		ope.addOperand(ctx.getPercolation().genEffPreExpr(meth));

		/* algorithme dans ICLP et ASE
		if((transition != null) && (transition.getCondition() != null)
					&& (transition.getCondition().getCategory() != GExprType.NOCONTRACT))
		{
			TamagoCCLogger.println(3, " detection of a guard in the transition: "+
					TamagoCCMakeReadableGExpression.toString(transition.getCondition()));
			ope.addOperand(transition.getCondition());
		}*/

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

		TamagoCCLogger.println(3, " -- Raw Prerequisite condition: "+TamagoCCMakeReadableGExpression.toString(res));		

		res = ctx.getStrategy().strategyForPrerequisite(ope);
		TamagoCCLogger.println(3, " -- Prerequisite condition after strategy: "+TamagoCCMakeReadableGExpression.toString(res));

		return res;
	}

	private static GExpression genPreconditionExpression(GTamago contract, GMethod meth, GTransition transition, TamagoEnvironment env) throws TamagoCCException {
		TamagoCCLogger.print(3, "Generation of precondition condition (percolator=");
		TamagoCCLogger.print(3,ctx.getPercolation().getName());
		TamagoCCLogger.println(3,")");

		GIOperator ope = new GIOperator(TOpeName.opAnd);

		if((ctx.selectedDNFInvariant() != null)	&& (ctx.selectedDNFInvariant().getCategory() != GExprType.NOCONTRACT))
		{
			TamagoCCLogger.println(3, " retrieve the specific DNF invariant");
			// DONE reflechir si cela etait interessant: ope.addOperand(ctx.selectedDNFInvariant());
			ope.addOperand(ctx.selectedDNFInvariant());
		}

		ope.addOperand(ctx.getPercolation().genEffPreExpr(meth));

		TamagoCSPFlatten flat = new TamagoCSPFlatten(ope);
		GExpression res = flat.flatten();

		TamagoCCLogger.println(3, " -- Raw Precondition condition: "+TamagoCCMakeReadableGExpression.toString(res));		

		res = ctx.getStrategy().strategyForPrecondition(ope);
		TamagoCCLogger.println(3, " -- Precondition condition after strategy: "+TamagoCCMakeReadableGExpression.toString(res));

		return res;
	}


	private static void minimizeProperties(GTamago contract, TamagoEnvironment env) throws TamagoCCException {
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


		boolean branchdnf = false;
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
				csp.solve();
				TamagoCCLogger.println(3, "Minimize [OK]");
				TamagoCCLogger.println(3, csp.toString());
				branchdnf = false;
			}
			catch(Exception e) {
				TamagoCCLogger.println(3, "Error during minimization:");
				TamagoCCLogger.infoln(3,e);
				branchdnf = true;
			}
			return;
		}

		do {
			GExpression expr = selector(arraydnf);
			//ctx.setSelectedDNFInvariant(expr);
			ctx.setSelectedDNFInvariant(constraints);
			expr = TamagoCSPFlatten.flattern(expr);

			TamagoCSP csp = new TamagoCSP();

			for (TamagoBuilder builder : env.values()) {
				builder.setBacktrack(csp.getBacktrack());
				CSPvar var = builder.getCSPvar(null,null);
				var.uninstallAllConstraints();
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
			try {
				TamagoCCLogger.println(4, csp.toString());
				csp.solve();
				TamagoCCLogger.println(3, "Minimize [OK]");
				TamagoCCLogger.println(3, csp.toString());
				branchdnf = false;
			}
			catch(Exception e) {
				TamagoCCLogger.println(3, "Error during minimization:");
				TamagoCCLogger.infoln(3,e);
				branchdnf = true;
			}

		} while(branchdnf);
	}
	
	private static void firstConstraintsProperties(GTamago contract, TamagoEnvironment env) throws TamagoCCException {
		if(env.size() == 0) {
			TamagoCCLogger.println(3, "No property detected");
			return;
		}

		TamagoCCLogger.println(3, "Minimisation of extracted properties");
		GExpression constraints = new GIOperator(TOpeName.opAnd);
		for (GInvariant invariant : contract.getInvariants()) {
			((GIOperator) constraints).addOperand(invariant.getExpression());
		}
		
		constraints = ctx.getStrategy().strategyForInitialConstraint(constraints);

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


		boolean branchdnf = false;
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
				csp.solve();
				TamagoCCLogger.println(3, "Minimize [OK]");
				TamagoCCLogger.println(3, csp.toString());
				branchdnf = false;
			}
			catch(Exception e) {
				TamagoCCLogger.println(3, "Error during minimization:");
				TamagoCCLogger.infoln(3,e);
				branchdnf = true;
			}
			return;
		}

		do {
			GExpression expr = selector(arraydnf);
			//ctx.setSelectedDNFInvariant(expr);
			ctx.setSelectedDNFInvariant(constraints);
			expr = TamagoCSPFlatten.flattern(expr);

			TamagoCSP csp = new TamagoCSP();

			for (TamagoBuilder builder : env.values()) {
				builder.setBacktrack(csp.getBacktrack());
				CSPvar var = builder.getCSPvar(null,null);
				var.uninstallAllConstraints();
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
			try {
				TamagoCCLogger.println(4, csp.toString());
				csp.solve();
				TamagoCCLogger.println(3, "Minimize [OK]");
				TamagoCCLogger.println(3, csp.toString());
				branchdnf = false;
			}
			catch(Exception e) {
				TamagoCCLogger.println(3, "Error during minimization:");
				TamagoCCLogger.infoln(3,e);
				branchdnf = true;
			}

		} while(branchdnf);
	}

	private static void extractProperties(GTamago contract, TamagoEnvironment env_props) {
		TamagoCCLogger.println(3, "Extraction of properties:");
		for (GProperty prop : contract.getProperties()) {
			try {
				TamagoCCLogger.print(3,"  Extraction of "+prop.getName());
				TamagoBuilder tb = ctx.getStrategy().searchBuilder(prop.getName(), prop.getType(), contract, env_props);
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

	private static <T> T selector(ArrayList<T> arraydnf) {
		Random random = new Random();
		if(arraydnf.size() == 0) {
			TamagoCCLogger.println(3, "No more DNF");
			throw new TamagoCSPRuntime("No more DNF");
		}
		int pos = random.nextInt(arraydnf.size());
		T expr = arraydnf.get(pos);
		arraydnf.remove(pos);
		return expr;
	}

	/*
	private static TamagoBuilder searchBuilder(GParameter param, TamagoCSP csp,TamagoEnvironment env) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		TamagoBuilder tb = searchBuilder(param.getName(),param.getType(), env);
		tb.setBacktrack(csp.getBacktrack());
		return tb;
	}

	private static TamagoBuilder searchBuilder(GProperty param,TamagoEnvironment env) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		return searchBuilder(param.getName(),param.getType(),env);
	}

	public static TamagoBuilder searchBuilder(String name, GType type,TamagoEnvironment env) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {

		switch(type.catType()) {
		case ARRAY:
			return new Builderarray(env,name,type,new Backtracking());
		case BOOL:
		case INTEGER:
		case OBJECT:
		case REAL:
		case STRING:
		case VOID: {
			Class<?> cb = (Class<?>) Class.forName("tamago.builder.impl.Builder"+type.getType().toLowerCase());
			Constructor<?> cons = cb.getConstructor(new Class[] { TamagoEnvironment.class, String.class , GType.class, Backtracking.class });
			return (TamagoBuilder) cons.newInstance(new Object[] {env,  name, null,new Backtracking() });
		}
		}
		throw new TamagoCSPRuntime("Problem occur for searching the builder of "+name);
	}
	 */	

	private static GTamago searchContract(String file) throws TamagoCCException {
		try {
			TTamagoEntity contract = TamagoCCParser.getDefaultParser().parse(file);
			return (GTamago)TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(contract);
		}
		catch(Exception e) {
			throw new TamagoCCException(e);
		}
	}


}
