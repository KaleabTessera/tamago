/**
 * 
 */
package tamago.check.fixpoint;

import java.util.ArrayList;

import tamago.check.util.TamagoCheckContext;
import tamagocc.generic.api.GTamago;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCheckFixPointSeeker {

	private ArrayList<TamagoCheckState> states;
	private GTamago contract;
	private TamagoCheckContext ctx;
	/**
	 * @param ctx 
	 * @param contract 
	 * 
	 */
	public TamagoCheckFixPointSeeker(GTamago contract, TamagoCheckContext ctx) {
		this.contract = contract;
		this.ctx = ctx;
		this.states = new ArrayList<TamagoCheckState>();
	}

	public int findFixPoint(TamagoCheckState localstate) {
		return states.indexOf(localstate);
	}

	public void register(TamagoCheckState localstate) {
		states.add(localstate);		
	}

	public TamagoCheckState get(int old) {
		return states.get(old);
	}

}
