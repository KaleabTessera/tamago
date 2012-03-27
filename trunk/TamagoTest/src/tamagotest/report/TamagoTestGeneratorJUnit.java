/**
 * 
 */
package tamagotest.report;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import tamagocc.api.TOpeName;
import tamagocc.ast.api.AEntity;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AInstExpression;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AMethod;
import tamagocc.ast.api.AOperator;
import tamagocc.ast.api.ATamagoNew;
import tamagocc.ast.api.AType;
import tamagocc.ast.impl.AConstructor;
import tamagocc.ast.impl.AIAffectation;
import tamagocc.ast.impl.AIBool;
import tamagocc.ast.impl.AICall;
import tamagocc.ast.impl.AICatchBlock;
import tamagocc.ast.impl.AIEntity;
import tamagocc.ast.impl.AIIdent;
import tamagocc.ast.impl.AIIfThenElse;
import tamagocc.ast.impl.AIInLabel;
import tamagocc.ast.impl.AIInherit;
import tamagocc.ast.impl.AIInitialisation;
import tamagocc.ast.impl.AIInlineComment;
import tamagocc.ast.impl.AIInstExpression;
import tamagocc.ast.impl.AIMemberVariable;
import tamagocc.ast.impl.AIMethod;
import tamagocc.ast.impl.AIModule;
import tamagocc.ast.impl.AINamespace;
import tamagocc.ast.impl.AINew;
import tamagocc.ast.impl.AINil;
import tamagocc.ast.impl.AINoInstruction;
import tamagocc.ast.impl.AINot;
import tamagocc.ast.impl.AIOperator;
import tamagocc.ast.impl.AIReturn;
import tamagocc.ast.impl.AISequence;
import tamagocc.ast.impl.AIString;
import tamagocc.ast.impl.AITamagoNew;
import tamagocc.ast.impl.AITryCatchFinal;
import tamagocc.ast.impl.AIType;
import tamagocc.ast.impl.AIVariable;
import tamagocc.ast.impl.AIVisibility;
import tamagocc.exception.TamagoCCException;
import tamagocc.generator.TamagoCCGeneratorTargetLanguage;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GParameter;
import tamagocc.generic.api.GTamago;
import tamagocc.logger.TamagoCCLogger;
import tamagotest.TamagoTestCase;
import tamagotest.TamagoTestContext;
import tamagotest.TamagoTestException;
import tamagotest.TamagoTestGenerator;
import tamagotest.TamagoTestScenario;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTestGeneratorJUnit extends TamagoTestGenerator {
	private AIEntity entity;
	private GTamago contract;
	private AIIdent ident = new AIIdent("code");
	private AIType type;
	private AIMemberVariable component;
	private AConverterScope trad;
	
	/**
	 * @param ctx
	 */
	public TamagoTestGeneratorJUnit(TamagoTestContext ctx) {
		super(ctx);
		this.contract = ctx.getContract();
		entity = null;
		type = (AIType) AIType.generateType(contract.getName());
		component = new AIMemberVariable(ident,type,AIVisibility.PRIVATE_VISIBILITY);
		trad = new AConverterScope(new AIVariable(component.getIdent()), contract);
	}

	public AEntity getEntity() throws TamagoCCException {
		if(entity == null) {
			entity = new AIEntity("Test"+contract.getName() + this.ctx.getJUnitSuffix(),
					new AIModule(contract.getModule().getFullModule()),
					new AType[0],AIEntity.CLASS);
			AINamespace ns = new AINamespace(contract.getModule().getFullModule());
			entity.addUsedNamespaces(ns);
			entity.setInherit(new AIInherit("TestCase",new AIModule("junit.framework")));
			entity.addVariablesMembers(component);
			makeConstructors();
			makeMethods();
		}
		return entity;
	}
	
	private void makeSetUp() {
		AIMethod method = new AIMethod(AMethod.IMPLEMENTED,new AIIdent("setUp"),AIType.generateType("void"),AIVisibility.PUBLIC_VISIBILITY);
		AISequence seq = new AISequence();
		AIVariable code = new AIVariable(ident);
		try {	
				AITamagoNew tc = new AITamagoNew(ctx.getComponent(),ATamagoNew.COMPONENT);
				AIAffectation aff = new AIAffectation(code,tc);
				entity.addUsedNamespaces(new AINamespace("tamago.ext.tamagocc","TamagoCC"));
				seq.addInstruction(aff);
			
		}
		catch(Exception e) {
			try {
				AINew tc = new AINew(AIType.generateType(ctx.getBusinessCode()));
				AIAffectation aff = new AIAffectation(code, tc);
				seq.addInstruction(aff);
			}
			catch(Exception e2) {
				AIAffectation aff = new AIAffectation(code,new AINil());
				seq.addInstruction(aff);
			}
		}
		AINoInstruction op = new AINoInstruction("Make user definition below this line");
		seq.addInstruction(op);
		method.setBody(seq);
		entity.addMethod(method);
	}
	
	private void makeMethods() throws TamagoCCException {
		makeSetUp();
		long sce = 0;
		for (TamagoTestScenario tts : ctx.getScenarios()) {
			AIMethod method = new AIMethod(AMethod.IMPLEMENTED,new AIIdent("testScenario"+sce),AIType.typeVOID,AIVisibility.PUBLIC_VISIBILITY);
			sce++;
			entity.addMethod(method);
			AISequence seq = new AISequence();
			method.setBody(seq);
			TamagoCCLogger.println(3," -- Generation of testing method");
			long pos = 0;
			for (TamagoTestCase ttc : tts) {
				makeStep(pos, ttc,seq);
				pos++;
			}
		}
	}
	
	private static boolean isRealExpression(AExpression e) {
		if(e == null || e.getExpressionType() == AExpression.NOEXPRESSION)
			return false;
		if(e instanceof AOperator) {
			if(((AOperator)e).getArity() == 0)
				return false;
			boolean res = true;
			Iterator<AExpression> exprs = ((AOperator)e).getOperands();
			while(exprs.hasNext()) {
				AExpression exp = exprs.next();
				res = isRealExpression(exp) && res;
			}
			return res;
		}
		return true;
	}
	
	private void makeStep(long pos, TamagoTestCase cas, AISequence mainseq) throws TamagoCCException {
		mainseq.addInstruction(AINoInstruction.getNoInstruction());
		mainseq.addInstruction(AINoInstruction.getNoInstruction());
		
		AISequence seq = new AISequence(); // instruction pour le test
		
		// METTRE LE CHANGEMENT DE SCOPE LA OU IL Y A DES PROPRIETES
		
		AINoInstruction ai = new AINoInstruction("Step: "+pos+ " Method: "+cas.method());
		mainseq.addInstruction(ai);
		
		if(cas.getTransition() != null) {
			ai = new AINoInstruction("Transition: "+cas.getTransition().toString());
			mainseq.addInstruction(ai);
		}
		
		// Preparation du traitement de l'erreur
		AICall callfail = new AICall(new AIIdent("fail"));
		AIOperator ope = new AIOperator(TOpeName.opPlus);
		ope.addOperand(new AIString("Test failed at step "+pos+". "));
		AICall callmsg = new AICall(new AIIdent("getMessage"));
		AIInLabel inlab = new AIInLabel(new AIVariable(new AIIdent("exc")), callmsg);
		ope.addOperand(inlab);
		
		callfail.addArgument(ope);
		
		AICatchBlock catchglob = new AICatchBlock(AIType.generateType("Exception"),
				new AIIdent("exc"),new AIInstExpression(callfail));
		
		AITryCatchFinal trycatch = new AITryCatchFinal(seq,catchglob);
		mainseq.addInstruction(trycatch);
		
		
		// preparation de la phase de test proprement dite
		Iterator<GParameter> params;
		
		GMethod gmethod = contract.getMethod(cas.method());
		
		
		// -- INITIALISATION DES ARGUMENTS
		params = gmethod.getParameters();
		while(params.hasNext()) {
			GParameter param = params.next();
			AIInitialisation init = new AIInitialisation(new AIIdent(param.getName()),AIType.generateType(param.getType().getType()));
			if(cas.get(param.getName()) != null) {
				init.setInitial(cas.get(param.getName()).l());
				seq.addInstruction(cas.get(param.getName()).c());
				seq.addInstruction(init);
				seq.addInstruction(cas.get(param.getName()).r());
			}
			else  {
				seq.addInstruction(new AINoInstruction("TODO fulfill the parameter: "+param.getName()));
				seq.addInstruction(init);
			}
		}
		
		
		// -- TEST DE LA PRECONDITION
		if(isRealExpression(cas.getPrecondition())) {
			// -- INITIALISATION DE LA PRECONDITION
			seq.addInstruction((AInstruction) cas.getInitPrecondition().visit(trad)); // valeur initiale, boucle ...
			AICall cfail = new AICall(new AIIdent("fail"));
			cfail.addArgument(new AIString("Wrong Test"));
			AINot cond = new AINot((AExpression) cas.getPrecondition().visit(trad));
			AIIfThenElse ifthen = new AIIfThenElse(cond, new AIInstExpression(cfail), AINoInstruction.getNoInstruction());
			seq.addInstruction(ifthen);
		}
		
		
		// -- BACKUP DES VALEURS ATPRE
		seq.addInstruction((AInstruction) cas.getAtPreOracle().visit(trad));
		
		// -- APPELLE DE LA METHODE A TESTER
		AICall testmeth = new AICall(new AIIdent(gmethod.getName()));
		params = gmethod.getParameters();
		while(params.hasNext()) {
			GParameter param = params.next();
			testmeth.addArgument(new AIVariable(new AIIdent(param.getName())));
		}
		
		AIInLabel incomponent = new AIInLabel(new AIVariable(component.getIdent()),testmeth);
		if(gmethod.mustSaveResult()) {
			AType type = AIType.generateType(gmethod.getType().getType());
			AIInitialisation init = new AIInitialisation(new AIIdent(gmethod.getSavedResult().getName()), type);
			init.setInitial(incomponent);
			seq.addInstruction(init);
		}
		else
			seq.addInstruction(new AIInstExpression(incomponent));
		
		
		// -- ECRITURE DE L'ORACLE
		seq.addInstruction((AInstruction) cas.getInitOracle().visit(trad)); // valeur initiale, boucle ...
		
		
		if(cas.getOracle() != null && (
			(cas.getOracle().getExpressionType() != AExpression.NOEXPRESSION && cas.getOracle().getExpressionType() != AExpression.OPERATOR) 
				|| (cas.getOracle().getExpressionType() == AExpression.OPERATOR && ((AOperator)cas.getOracle()).getArity() > 0)  )  ) {
			AIVariable oracle = new AIVariable(new AIIdent("__tamagotest_oracle"));
			AIInitialisation exproracle = new AIInitialisation(oracle.getIdent(), AIType.typeBOOLEAN,(AExpression) cas.getOracle().visit(trad) );
			seq.addInstruction(exproracle);
			AICall asserttrue = new AICall(new AIIdent("assertTrue"));
			asserttrue.addArgument(oracle);
			seq.addInstruction(new AIInstExpression(asserttrue));
		}
		else {
			AICall asserttrue = new AICall(new AIIdent("assertTrue"));
			
			asserttrue.addArgument(new AIBool(true));
			AIInstExpression calloracle = new AIInstExpression(asserttrue);
			calloracle.setComment(new AIInlineComment("TODO: write a better oracle"));
			seq.addInstruction(calloracle);
		}
	}

	private void makeConstructors() {
		TamagoCCLogger.println(3, " -- Construction of the constructor");
		AConstructor constructor = new AConstructor(new AIIdent(contract.getName()),AIVisibility.PUBLIC_VISIBILITY);
		entity.addMethod(constructor);
		TamagoCCLogger.println(3, " -- [OK]");
	}
	
	/**
	 * @see tamagotest.TamagoTestGenerator#prepare()
	 */
	public void prepare() throws TamagoTestException {
		

	}

	/**
	 * @see tamagotest.TamagoTestGenerator#write()
	 */
	public void write() throws TamagoTestException, IOException {
		try {
			AEntity entity = getEntity();
			TamagoCCGeneratorTargetLanguage target = ctx.getGenerator().getTargetLanguage(entity, ctx.getDestination());
			try {
				TamagoCCLogger.println(3, "Generation of the file : "+target.getFinalDestination().getCanonicalPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
			target.generate();
		}
		catch(TamagoCCException tcce) {
			throw new TamagoTestException(tcce);
		}
	}

	/**
	 * @see tamagotest.TamagoTestGenerator#write(java.io.OutputStream)
	 */
	public void write(OutputStream os) throws TamagoTestException, IOException {
		try {
			AEntity entity = getEntity();
			TamagoCCGeneratorTargetLanguage target = ctx.getGenerator().getTargetLanguage(entity,os);
			TamagoCCLogger.println(3, "Generation of "+entity.getName()+" in stream mode.");
			target.generate();
		}
		catch(TamagoCCException tcce) {
			throw new TamagoTestException(tcce);
		}
	}
}
