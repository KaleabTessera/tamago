/**
 * 
 */
package tamagotest;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTestScenario implements Iterable<TamagoTestCase>{

	private ArrayList<TamagoTestCase> cases;
	private String perconame;
	
	/**
	 * 
	 */
	public TamagoTestScenario(String perconame) {
		cases = new ArrayList<TamagoTestCase>();
		this.perconame = perconame;
	}
	
	public Iterator<TamagoTestCase> iterator() {
		return cases.iterator();
	}
	
	public int size() {
		return cases.size();
	}
	
	public TamagoTestCase getTestCase(int i) {
		return cases.get(i);
	}
	
	void addTestCase(TamagoTestCase testcase) {
		cases.add(testcase);
	}
	
	public String getPercolator() {
		return perconame;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Scenario (");
		sb.append(perconame);
		sb.append(") {\n");
		for (TamagoTestCase step : cases) {
			sb.append("\t");
			sb.append(step.toString());
			sb.append("\n");
		}
		sb.append("} (taille=");
		sb.append(cases.size());
		sb.append(")");
		return sb.toString();
	}

	public void add(TamagoTestCase case1) {
		cases.add(case1);		
	}
	
}
