/**
 * 
 */
package tamago.lineparser;

import tamagocc.exception.LineParserException;
import tamagocc.util.lineparser.LineParserSpec;
import tamagotest.TamagoTestContext;
import tamagotest.strategy.BadScenarioStrategy;
import tamagotest.strategy.BoundedStrategy;
import tamagotest.strategy.CoverageStrategy;
import tamagotest.strategy.NominalStrategy;
import tamagotest.strategy.TamagoTestStrategy;
import tamagotest.strategy.UnboundBehStrategy;
import tamagotest.strategy.UnboundStrategy;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTestStrategySelector implements LineParserSpec {

	private TamagoTestContext ctx;
	private String name;
	/**
	 * 
	 */
	public TamagoTestStrategySelector(TamagoTestContext ctx) {
		this.ctx = ctx;
		name = "nominal";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#fire()
	 */
	public void fire() throws LineParserException {
		if(name.equals("nominal"))
			ctx.setStrategy(new NominalStrategy());
		else if(name.equals("unbound"))
			ctx.setStrategy(new UnboundStrategy());
		else if(name.equals("unboundbeh"))
			ctx.setStrategy(new UnboundBehStrategy());
		else if(name.equals("bound"))
			ctx.setStrategy(new BoundedStrategy());
		else if(name.equals("badscenario"))
			ctx.setStrategy(new BadScenarioStrategy());
		else if(name.equals("alltransitions"))
			ctx.setStrategy(new CoverageStrategy());
		else {
			Class<?> strat;
			try {
				strat = Class.forName(name);
				ctx.setStrategy((TamagoTestStrategy) strat.newInstance());
			} catch (Exception e) {
				throw new LineParserException(e);
			} 
		}
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getArity()
	 */
	public int getArity() {
		return 1;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getCommand()
	 */
	public String getCommand() {
		return "--strategy";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getDescription()
	 */
	public String getDescription() {
		return "Allow user to specify a test strategy among: nominal, bound, unbound, unboundbeh, badscenario, alltransitions";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#immediateFire()
	 */
	public boolean immediateFire() {
		return true;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#isOptionnal()
	 */
	public boolean isOptionnal() {
		return true;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#setArgument(int, java.lang.String)
	 */
	public void setArgument(int pos, String value) throws LineParserException {
		name = value;
	}


}
