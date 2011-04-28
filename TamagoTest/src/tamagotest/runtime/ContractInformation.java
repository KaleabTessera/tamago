/**
 * 
 */
package tamagotest.runtime;


/**
 * @author Hakim Belhaouari
 *
 */
public class ContractInformation {

	private String name;
	private String module;
	
	private String perco;
	private int count;
	
	/**
	 * 
	 */
	public ContractInformation(String name, String module,String perco, int count) {
		this.name = name;
		this.module = module;
		this.perco = perco;
		this.count = count;
	}
	
	public String getName() {
		return name;
	}
	
	public String getModule() {
		return module;
	}
	
	public String getPercolator() {
		return perco;
	}
	
	public int getScenarioCount() {
		return count;
	}

}
