/**
 * 
 */
package tamagotest.strategy;

import java.util.Random;

import tamago.csp.convert.TamagoCSPFlatten;
import tamagocc.api.TOpeName;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GInvariant;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GTransition;
import tamagocc.generic.api.GExpression.GExprType;
import tamagocc.generic.impl.GIAtPre;
import tamagocc.generic.impl.GIBool;
import tamagocc.generic.impl.GINot;
import tamagocc.generic.impl.GIOperator;
import tamagocc.generic.impl.GIRead;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.TamagoCCMakeReadableGExpression;
import tamagotest.TamagoTestTransSelector;
import tamagotest.fixpoint.MaxDepth;

/**
 * @author Hakim Belhaouari
 *
 */
public class UnboundStrategy extends NominalStrategy {

	protected Random rand;
	protected MaxDepth minedepth;
	
	/**
	 * 
	 */
	public UnboundStrategy() {
		minedepth = new MaxDepth();
		this.depth = minedepth;
		rand = new Random();
	}

	@Override
	public String getName() {
		return "Outbound Strategy";
	}
	
	/**
	 * @see tamagotest.strategy.TamagoTestStrategy#reset()
	 */
	public void reset() {
		super.reset();
		minedepth.reset();
	}

	/**
	 * @see tamagotest.strategy.TamagoTestStrategy#setFixPoint(tamagotest.TamagoTestTransSelector)
	 */
	public void setFixPoint(TamagoTestTransSelector fixpoint) {
		TamagoCCLogger.info(3, "Statement of new fixpoint impossible in this strategy");
	}
	public TamagoTestTransSelector getFixPoint() {
		return minedepth;
	}

	/**
	 * @see tamagotest.strategy.TamagoTestStrategy#strategyForOracle(tamagocc.generic.api.GExpression)
	 */
	public GExpression strategyForOracle(GExpression expr) throws TamagoCCException {
		if(minedepth.getDepth() == (MaxDepth.MAX_DEPTH - 1)) {
			GIOperator ope = new GIOperator(TOpeName.opAnd);
			for (GInvariant inv : ctx.getContract().getInvariants()) {
				if(inv.getExpression() != null && inv.getExpression().getCategory() != GExprType.NOCONTRACT) {
					ope.addOperand(inv.getExpression());
				}
			}
			for (GProperty property : ctx.getContract().getProperties()) {
				GIRead apres = new GIRead(property.getName());
				GIAtPre atpre = new GIAtPre(new GIRead(property.getName()), property.getType());
				GIOperator egalite = new GIOperator(TOpeName.opEg);
				egalite.addOperand(apres);
				egalite.addOperand(atpre);
				
				ope.addOperand(egalite);
			}
			TamagoCCLogger.println(3, "UnboundStrategy oracle : "+TamagoCCMakeReadableGExpression.toString(ope));
			TamagoCSPFlatten flatten = new TamagoCSPFlatten(ope);
			if(ope.getArity() == 0)
				return new GIBool(true);
			else
				return flatten.flatten();	
		}
		else
			return super.strategyForOracle(expr);
		
	}

	/**
	 * @see tamagotest.strategy.TamagoTestStrategy#strategyForPrerequisite(tamagocc.generic.api.GExpression)
	 */
	public GExpression strategyForPrerequisite(GExpression expr)throws TamagoCCException {
		if((rand.nextDouble() < 0.1) || (minedepth.getDepth() == (MaxDepth.MAX_DEPTH - 1))) {
			GTransition lasttrans = minedepth.lastTransition();
			GMethod meth = ctx.getContract().getMethod(lasttrans.getMethodID());
			GExpression preexpr = ctx.getPercolation().genEffPreExpr(meth);
			GINot not = new GINot(preexpr);
			TamagoCSPFlatten flatten = new TamagoCSPFlatten(not);
			minedepth.setDepth(MaxDepth.MAX_DEPTH); // on force l'arret des scenarios
			TamagoCCLogger.println(3,"UnboundStrategy: reach negation of precondition");
			return flatten.flatten();	
		}
		else
			return super.strategyForPrerequisite(expr);
		
	}
	
	public GExpression strategyForPrecondition(GExpression expr) throws TamagoCCException {
		if(minedepth.getDepth() == MaxDepth.MAX_DEPTH) {
			GTransition lasttrans = minedepth.lastTransition();
			GMethod meth = ctx.getContract().getMethod(lasttrans.getMethodID());
			GExpression preexpr = ctx.getPercolation().genEffPreExpr(meth);
			GINot not = new GINot(preexpr);
			TamagoCSPFlatten flatten = new TamagoCSPFlatten(not);
			return flatten.flatten();
		}
		else
			return super.strategyForPrecondition(expr);
	}
	
}
