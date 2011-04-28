/**
 * 
 */
package tamagotest.runtime;

import java.util.ArrayList;
import java.util.Collection;

import tamagotest.TamagoTestScenario;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTestPerform {

	private ContractInformation ci;
	private ArrayList<TamagoTestScenario> scenarios;
	
	/**
	 * 
	 */
	public TamagoTestPerform(ContractInformation ci, Collection<TamagoTestScenario> scenarios) {
		this.ci = ci;
		this.scenarios = new ArrayList<TamagoTestScenario>(scenarios);
	}

	public ContractInformation getContractInformation() {
		return ci;
	}
	
	public Collection<TamagoTestScenario> getScenario() {
		return scenarios;
	}
}
