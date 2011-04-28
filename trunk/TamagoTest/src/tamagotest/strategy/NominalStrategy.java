/**
 * 
 */
package tamagotest.strategy;

import java.lang.reflect.Constructor;

import tamago.builder.TamagoBuilder;
import tamago.builder.TamagoEnvironment;
import tamago.builder.impl.Builderarray;
import tamago.csp.Backtracking;
import tamago.csp.TamagoCSP;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GType;
import tamagotest.TamagoTestContext;
import tamagotest.TamagoTestTransSelector;
import tamagotest.fixpoint.MaxDepth;

/**
 * @author Hakim Belhaouari
 *
 */
public class NominalStrategy implements TamagoTestStrategy {

	protected TamagoTestTransSelector depth;
	protected int maxlength;
	protected int currentScenario;
	protected int maxScenario;
	protected TamagoTestContext ctx;
	
	/**
	 * 
	 */
	public NominalStrategy() {
		depth = new MaxDepth();
		maxlength = 100;
		maxScenario = 10;
		currentScenario = 0;
	}

	/**
	 * @see tamagotest.strategy.TamagoTestStrategy#getFixPoint()
	 */
	public TamagoTestTransSelector getFixPoint() {
		return depth;
	}
	
	/**
	 * default search strategy
	 * @see tamago.builder.TamagoBuilderFactory#searchBuilder(tamago.builder.TamagoEnvironment, java.lang.String, tamagocc.generic.api.GType, tamago.csp.Backtracking)
	 */
	public TamagoBuilder searchBuilder(TamagoEnvironment env, String name, GType type, Backtracking b) {
		try {
			switch(type.catType()) {
			case ARRAY:
				return new Builderarray(this, env,name,type,new Backtracking());
			case BOOL:
			case INTEGER:
			case OBJECT:
			case REAL:
			case STRING:
			case VOID: {
				Class<?> cb = (Class<?>) Class.forName("tamago.builder.impl.Builder"+type.SimpleType().toLowerCase());
				Constructor<?> cons = cb.getConstructor(new Class[] { TamagoEnvironment.class, String.class , GType.class, Backtracking.class });
				return (TamagoBuilder) cons.newInstance(new Object[] {env,  name, type,new Backtracking() });
			}
			}
		}
		catch(Exception ex) {
			throw new TamagoTestStrategyException(ex);
		}
		throw new TamagoTestStrategyException("Problem occur for searching the builder of "+name);
	}
	
	/**
	 * @see tamagotest.strategy.TamagoTestStrategy#searchBuilder(java.lang.String, tamagocc.generic.api.GType, tamagocc.generic.api.GMethod)
	 */
	public TamagoBuilder searchBuilder(String name, GType type,
			GMethod method,TamagoEnvironment env) {
		return searchBuilder(env,name,type,null);
	}

	/**
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @see tamagotest.strategy.TamagoTestStrategy#searchBuilder(java.lang.String, tamagocc.generic.api.GType, tamagocc.generic.api.GTamago)
	 */
	public TamagoBuilder searchBuilder(String name, GType type,
			GTamago entity,TamagoEnvironment env) throws TamagoTestStrategyException {
		return searchBuilder(env,name,type,null);
	}

	/**
	 * @see tamagotest.strategy.TamagoTestStrategy#strategyForOracle(tamagocc.generic.api.GExpression)
	 */
	public GExpression strategyForOracle(GExpression expr) throws TamagoCCException {
		return expr;
	}

	/**
	 * @see tamagotest.strategy.TamagoTestStrategy#strategyForPrerequisite(tamagocc.generic.api.GExpression)
	 */
	public GExpression strategyForPrerequisite(GExpression expr) throws TamagoCCException {
		return expr;
	}

	public int getMaxLengthScenario() {
		return maxlength;
	}

	public void setMaxLengthScenario(int value) {
		maxlength = value;
		MaxDepth.MAX_DEPTH = value;
	}

	public void setFixPoint(TamagoTestTransSelector fixpoint) {
		this.depth = fixpoint;
		
	}

	public void reset() {
		depth.reset();
		currentScenario++;
	}

	public String getName() {
		return "Nominal Strategy";
	}

	public boolean makeNewScenario() {
		return  (currentScenario < maxScenario);
	}

	public String getScenarioName() {
		return "Scenario"+(currentScenario);
	}

	public void initialize(TamagoTestContext ctx) {
		maxScenario = ctx.getCount();
		this.ctx = ctx;
	}

	public GExpression strategyForPrecondition(GExpression expr) throws TamagoCCException {
		return expr;
	}

	public void registerTestCase(TamagoCSP csp) {
		// rien a faire a priori
	}

	
	
}
