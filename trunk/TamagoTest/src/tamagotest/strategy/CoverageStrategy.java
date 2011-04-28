/**
 * 
 */
package tamagotest.strategy;

import tamago.csp.convert.TamagoCSPDNF;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GTransition;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.Pair;
import tamagotest.TamagoTestContext;
import tamagotest.TamagoTestTransSelector;
import tamagotest.util.AllSequencesScenario;

/**
 * @author Hakim Belhaouari
 *
 */
public class CoverageStrategy extends NominalStrategy {

	private AllStaticScenario alltransitioncoverage;
	
	private class AllStaticScenario implements TamagoTestTransSelector {
		protected int currentstep;
		protected AllSequencesScenario ass;
		private boolean fetched;
		
		
		public AllStaticScenario(GTamago tamago) {
			ass = new AllSequencesScenario(getMaxLengthScenario(), tamago);
			fetched = true;
		}
		
		@Override
		public boolean fetch() {
			fetched = true;
			return (currentstep++) < ass.getScenario(currentScenario).size();
		}

		@Override
		public GTransition getTransition(GState state, TamagoTestContext ctx)
				throws TamagoCCException {
			if(fetched) { // Il faut bien alterner getTransition et fetch qui assure le passage au pas suivant
					// sinon ca signifie qu'il y a une erreur et donc le scenario est faux et sert a rien
				fetched = false;
				return ass.getTransition(currentScenario, currentstep);
			}
			else {
				return null;
			}
		}

		@Override
		public GTransition lastTransition() {
			return ass.getTransition(currentScenario, currentstep);
		}

		@Override
		public void reset() {
			//currentscenario = 0;
			currentstep = 0;
			fetched = true;
		}
		
		protected Pair<GTransition, Integer> lastPairTransition() {
			return ass.getScenario(currentScenario).get(currentstep);
		}
	}
	
	/**
	 * 
	 */
	public CoverageStrategy() {
		
	}

	@Override
	public TamagoTestTransSelector getFixPoint() {
		return alltransitioncoverage;
	}
	
	@Override
	public String getName() {
		return "All Transition Coverage";
	}
	
	@Override
	public void initialize(TamagoTestContext ctx) {
		super.initialize(ctx);
		//alltransitioncoverage = new AllStaticScenario(ctx.getContract());
	}
	
	@Override
	public boolean makeNewScenario() {
		if(alltransitioncoverage == null || alltransitioncoverage.ass == null)
			alltransitioncoverage = new AllStaticScenario(ctx.getContract());
		return (currentScenario < alltransitioncoverage.ass.size());
	}
	
	
	@Override
	public void setMaxLengthScenario(int value) {
		super.setMaxLengthScenario(value);
		//alltransitioncoverage.ass = new AllSequencesScenario(value,this.ctx.getContract());
	}
	
	@Override
	public void reset() {
		alltransitioncoverage.reset();
		currentScenario++;
		TamagoCCLogger.println(3, "Passage SCENARIO: "+currentScenario);
	}
	
	@Override
	public GExpression strategyForPrerequisite(GExpression expr)
			throws TamagoCCException {
		if(alltransitioncoverage == null)
			return expr;
		TamagoCSPDNF dnf = new TamagoCSPDNF(expr);
		int pos = alltransitioncoverage.lastPairTransition().getR();
		return dnf.getCollection().get(pos);
	}
	
	@Override
	public GExpression strategyForOracle(GExpression expr)
			throws TamagoCCException {
		if(alltransitioncoverage == null)
			return expr;
		TamagoCSPDNF dnf = new TamagoCSPDNF(expr);
		int pos = alltransitioncoverage.lastPairTransition().getR();
		return dnf.getCollection().get(pos);
	}
}
