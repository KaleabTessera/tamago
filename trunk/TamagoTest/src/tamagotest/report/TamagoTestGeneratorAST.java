/**
 * 
 */
package tamagotest.report;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import tamagocc.ast.api.AEntity;
import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AType;
import tamagocc.ast.impl.AConstructor;
import tamagocc.ast.impl.AIAffectation;
import tamagocc.ast.impl.AIBodyMethodContainer;
import tamagocc.ast.impl.AICall;
import tamagocc.ast.impl.AIEntity;
import tamagocc.ast.impl.AIIdent;
import tamagocc.ast.impl.AIImplements;
import tamagocc.ast.impl.AIInLabel;
import tamagocc.ast.impl.AIInstExpression;
import tamagocc.ast.impl.AIMemberVariable;
import tamagocc.ast.impl.AIMethod;
import tamagocc.ast.impl.AIModule;
import tamagocc.ast.impl.AINamespace;
import tamagocc.ast.impl.AIParameter;
import tamagocc.ast.impl.AIReturn;
import tamagocc.ast.impl.AISequence;
import tamagocc.ast.impl.AIType;
import tamagocc.ast.impl.AIVariable;
import tamagocc.ast.impl.AIVisibility;
import tamagocc.exception.TamagoCCException;
import tamagocc.generator.TamagoCCGeneratorTargetLanguage;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GParameter;
import tamagocc.generic.api.GTamago;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.percolation.TamagoCCPercolation;
import tamagotest.TamagoTestCase;
import tamagotest.TamagoTestContext;
import tamagotest.TamagoTestException;
import tamagotest.TamagoTestGenerator;
import tamagotest.TamagoTestScenario;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTestGeneratorAST extends TamagoTestGenerator {
	private AIEntity entity;
	private GTamago contract;
	private TamagoTestScenario scenario;
	private AIIdent ident = new AIIdent("code");
	private AIType type;
	private AIMemberVariable component;
	//percolation = TamagoCCPercolation.getPercolator(percolator,(GComponentContainer)entity,this);
	private TamagoCCPercolation percolation;
	
	private static int tmpident = 0;
	private static AIdent ident() {
		return new AIIdent("arg"+(tmpident++));
	}
	
	/**
	 * 
	 * 
	 */
	public TamagoTestGeneratorAST(TamagoTestContext ctx) {
		super(ctx);
		this.contract = ctx.getContract();
		this.scenario = ctx.getScenario();
		entity = null;
		this.percolation = ctx.getPercolation();
		type = (AIType) AIType.generateType(contract.getName());
		component = new AIMemberVariable(ident,type,AIVisibility.PRIVATE_VISIBILITY);
	}
	
	public AEntity getEntity() throws TamagoCCException {
		if(entity == null) {
			entity = new AIEntity(contract.getName()+"TamagoTest",
					new AIModule(contract.getModule().getFullModule()),
					new AType[0],AIEntity.CLASS);
			AINamespace ns = new AINamespace(contract.getModule().getFullModule());
			entity.addUsedNamespaces(ns);
			entity.addImplement(new AIImplements(AIType.generateType("tamagotest.runtime.TamagoTestCase")));
			entity.addVariablesMembers(component);
			makeConstructors();
			makeMethods();
		}
		return entity;
	}
	
	
	private void makeConstructors() {
		TamagoCCLogger.println(3, " -- Construction of the constructor");
		AConstructor constructor = new AConstructor(new AIIdent(contract.getName()),AIVisibility.PUBLIC_VISIBILITY);
		AIParameter param = new AIParameter(ident(),type);
		constructor.addParameter(param);
		AISequence seq = new AISequence();
		constructor.setBody(seq);
		// ----
		seq.addInstruction(new AIAffectation(new AIVariable(ident),new AIVariable(param.getIdent())));
		// ----
		entity.addMethod(constructor);
		TamagoCCLogger.println(3, " -- [OK]");
	}
	
	private void makeMethods() throws TamagoCCException {
		TamagoCCLogger.println(3," -- Generation of inherited methods");
		makeTestCounter();
		TamagoCCLogger.println(3," -- Generation of testing methods");
		long pos = 0;
		for (TamagoTestCase ttc : scenario) {
			makeMethod(pos, ttc);
			pos++;
		}
		TamagoCCLogger.println(3," -- Generation terminated with "+pos+" methods");
	}
	
	private void makeMethod(long num, TamagoTestCase ttc) throws TamagoCCException {
		AIMethod method = new AIMethod(AIMethod.IMPLEMENTED,new AIIdent("tamagotest"+num),AIType.generateType("void"),AIVisibility.PUBLIC_VISIBILITY);
		AISequence seq = new AISequence();
		method.setBody(seq);
		
		GMethod gmethod = contract.getMethod(ttc.method());
		
		AICall callsubmeth = new AICall(new  AIIdent(gmethod.getName()));
		Iterator<GParameter> aparameters = gmethod.getParameters();
		while(aparameters.hasNext()) {
			seq.addInstruction(ttc.get(aparameters.next().getName()).r());
			callsubmeth.addArgument(ttc.get(aparameters.next().getName()).l());
		}
		AIInLabel insubcomponent = new AIInLabel(component.getCallMe(),callsubmeth);
		seq.addInstruction(new AIInstExpression(insubcomponent));
		
		AIBodyMethodContainer body = new AIBodyMethodContainer();
		percolation.fulfillEffPost(gmethod, body,null);
		seq.addInstruction(body.getPostcondition());
		
		entity.addMethod(method);
	}

	private void makeTestCounter() {
		AIMethod method = new AIMethod(AIMethod.IMPLEMENTED,new AIIdent("size"),AIType.generateType("long"),AIVisibility.PUBLIC_VISIBILITY);
		AIReturn ret = new AIReturn(new AIVariable(new AIIdent(""+scenario.size())));
		method.setBody(ret);
		entity.addMethod(method);
	}

	public void prepare() throws TamagoTestException {
		
	}

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
