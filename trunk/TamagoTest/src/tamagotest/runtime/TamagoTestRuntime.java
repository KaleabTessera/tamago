/**
 * 
 */
package tamagotest.runtime;

import java.util.ArrayList;
import java.util.Iterator;

import tamago.ext.tamagocc.TamagoCC;
import tamago.ext.tamagocc.TamagoCCComponent;
import tamago.ext.tamagocc.TamagoCCContainer;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.impl.AICall;
import tamagocc.ast.impl.AIIdent;
import tamagocc.ast.impl.AIInLabel;
import tamagocc.ast.impl.AIVariable;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGPool;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GParameter;
import tamagocc.generic.api.GTamago;
import tamagocc.util.ast.ASTObject;
import tamagocc.util.ast.ASTValue;
import tamagocc.util.ast.Interpreter;
import tamagotest.TamagoTestCase;
import tamagotest.TamagoTestScenario;


/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTestRuntime {

	private TamagoTestPerform testcases;
	private String component_name;
	private ComponentBusinessFactory factory;
	private GTamago contract;
	
	/**
	 * 
	 */
	public TamagoTestRuntime(TamagoTestPerform testcases, ComponentBusinessFactory business,String component) {
		this.testcases = testcases;
		this.factory = business;
		this.component_name = component;
	}
	
	public void run() throws TamagoCCException {
		contract = (GTamago) TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(testcases.getContractInformation().getName(), testcases.getContractInformation().getModule());
		for (TamagoTestScenario scenario : testcases.getScenario()) {
			runScenario(scenario);
		}
	}

	private void runScenario(TamagoTestScenario scenario) {
		TamagoCCComponent component = factory.provide();
		TamagoCCContainer container = (TamagoCCContainer) TamagoCC.LookUp(component_name, testcases.getContractInformation().getPercolator(), component);
		
		// 
		for (TamagoTestCase testCase : scenario) {
			runTestCase(testCase,component,container);
		}
	}

	private void runTestCase(TamagoTestCase testCase, TamagoCCComponent component, TamagoCCContainer container) {
		GMethod method = contract.getMethod(testCase.method());
		Iterator<GParameter> params = method.getParameters();
		ArrayList<AExpression> oparams = new ArrayList<AExpression>();
		while(params.hasNext()) {
			GParameter param = params.next();
			if(testCase.contains(param.getName())) {
				AExpression value = testCase.getValue(param.getName()).l();
				oparams.add(value);
			}
			else {
				// Chercher la valeur de ce parametre
			}
		}
		AICall call = new AICall(new AIIdent(method.getName()),oparams);
		Interpreter inter = new Interpreter();
		inter.put("__tamagotest_code",new ASTObject(container));
		AIInLabel inlabl = new AIInLabel(new AIVariable(new AIIdent("__tamagotest_code")),call);
		
		try {
			ASTValue u = inter.eval(inlabl);
			u.toString();
		} catch (TamagoCCException e) {
			e.printStackTrace();
		}
		
		
		throw new RuntimeException("Not yet implemented");
	}

}
