package tamago.check.report;

import tamago.check.fixpoint.TamagoCheckState;
import tamago.check.util.TamagoCheckContext;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GTransition;

public interface GenericTamagoCheckReport {

	public abstract void findFixpoint(TamagoCheckContext ctx,
			GTransition trans, TamagoCheckState nextstate,
			TamagoCheckState oldstate);

	public abstract void impossibleBranch(TamagoCheckContext ctx,
			GTransition transition, TamagoCheckState localstate,
			GExpression gexpr);

	public abstract void closeDNF(TamagoCheckContext ctx,
			GTransition transition, TamagoCheckState localstate);

	public abstract void closeState(TamagoCheckContext ctx,
			TamagoCheckState localstate);

	public abstract void openState(TamagoCheckContext ctx,
			TamagoCheckState localstate);

	public abstract void openDNF(TamagoCheckContext ctx,
			GTransition transition, TamagoCheckState localstate,
			GExpression gexpr);

	public abstract void initialize(TamagoCheckContext ctx, GTamago contract);

	public abstract void finish(long tps, long statevisited,
			long transitionsvisited, long solvercreated);

	public abstract void addError(TamagoCheckState localstate,
			GTransition trans, Exception ex);

	public abstract void reachMaxDepth(TamagoCheckContext ctx2, GState state,
			TamagoCheckState localstate);

	public abstract void close();

	public abstract void registered(TamagoCheckState localstate,
			TamagoCheckState nextstate);

	public abstract void openTransition(GTransition trans);

	public abstract void closeTransition();

}