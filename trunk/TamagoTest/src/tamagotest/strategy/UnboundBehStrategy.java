/**
 * 
 */
package tamagotest.strategy;

import tamago.csp.convert.TamagoCSPFlatten;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.impl.GIBool;
import tamagocc.generic.impl.GINot;
import tamagocc.logger.TamagoCCLogger;
import tamagotest.fixpoint.MaxDepth;

/**
 * @author Hakim Belhaouari
 *
 */
public class UnboundBehStrategy extends UnboundStrategy {
	public UnboundBehStrategy() {
	}

	@Override
	public String getName() {
		return "Outbound Deep Strategy";
	}
	

	/**
	 * @see tamagotest.strategy.TamagoTestStrategy#strategyForPrerequisite(tamagocc.generic.api.GExpression)
	 */
	public GExpression strategyForPrerequisite(GExpression expr)throws TamagoCCException {
		if((rand.nextDouble() < 0.1) || (minedepth.getDepth() == (MaxDepth.MAX_DEPTH - 1))) {
			GExpression preexpr = super.strategyForPrerequisite(expr);
			GINot not = new GINot(preexpr);
			TamagoCSPFlatten flatten = new TamagoCSPFlatten(not);
			minedepth.setDepth(MaxDepth.MAX_DEPTH); // on force l'arret des scenarios
			TamagoCCLogger.println(3,"UnboundBehStrategy: reach negation of precondition");
			return flatten.flatten();	
		}
		else
			return super.strategyForPrerequisite(expr);
		
	}
	
	public GExpression strategyForPrecondition(GExpression expr) throws TamagoCCException {
		if(minedepth.getDepth() == MaxDepth.MAX_DEPTH) {
			/*GTransition lasttrans = minedepth.lastTransition();
			GMethod meth = ctx.getContract().getMethod(lasttrans.getMethodID());
			GExpression preexpr = ctx.getPercolation().genEffPreExpr(meth);
			GINot not = new GINot(preexpr);
			TamagoCSPFlatten flatten = new TamagoCSPFlatten(not);
			return flatten.flatten();*/
			return new GIBool(true);
		}
		else
			return super.strategyForPrecondition(expr);
	}
	
}
