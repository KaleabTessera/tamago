/**
 * 
 */
package tamagotest;

import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AInstruction;
import tamagocc.generic.api.GTransition;
import tamagocc.util.Triplet;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTestCase implements Cloneable {

	private String mid;
	
	// represente les preexpression pour attribuer la valeur a l'exemple generer par le builder
	// expression initiale, pre-initial, post-initial
	private Hashtable<String,  Triplet<AExpression, AInstruction,AInstruction>> values;
	
	private AInstruction initprecond;
	private AExpression precond;
	
	private AInstruction atpreoracle;
	private AInstruction initoracle;
	private AExpression oracle;

	private AExpression resultExpr;

	private AInstruction resultPreInst;

	private AInstruction resultPostInst;
	
	private static long varmap = 0;
	
	private GTransition transition;
	
	public static String map() {
		return ""+(varmap++);
	}
	
	
	public TamagoTestCase(GTransition transition, String mid, AInstruction initprecond,AExpression precond, AInstruction atpreoracle, AInstruction io,AExpression oracle) {
		this.mid = mid;
		this.initprecond = initprecond;
		this.initoracle = io;
		this.atpreoracle = atpreoracle;
		this.precond = precond;
		this.oracle = oracle;
		this.transition = transition;
		values = new Hashtable<String, Triplet<AExpression,AInstruction,AInstruction>>();
	}
	public TamagoTestCase(String mid, AInstruction initprecond,AExpression precond, AInstruction atpreoracle, AInstruction io,AExpression oracle, Map<String,  Triplet<AExpression, AInstruction,AInstruction>> map) {
		this(null,mid,initprecond,precond,atpreoracle,io,oracle);
		this.values = new Hashtable<String, Triplet<AExpression,AInstruction,AInstruction>>(map);
	}
	
	public void put(String name, Triplet<AExpression, AInstruction,AInstruction> value) {
		values.put(name, value);
	}
	
	public void putResult(AExpression arg, AInstruction pre,AInstruction post ) {
		this.resultExpr = arg;
		this.resultPreInst = pre;
		this.resultPostInst = post;
	}
	
	public boolean hasResult() {
		return (resultExpr != null);
	}
	
	public AExpression getResultExpr() {
		return resultExpr;
	}
	public AInstruction getResultPreInst() {
		return resultPreInst;
	}
	
	public AInstruction getResultPostInst() {
		return resultPostInst;
	}
	
	public String method() {
		return mid;
	}
	
	public  Triplet<AExpression, AInstruction,AInstruction> getValue(String parameter) {
		return values.get(parameter);
	}
	
	public  Triplet<AExpression, AInstruction,AInstruction> get(String parameter) {
		return values.get(parameter);
	}
	
	public boolean contains(String parameter) {
		return values.containsKey(parameter);
	}
	
	public AInstruction getInitPrecondition() {
		return initprecond;
	}
	
	public AInstruction getAtPreOracle() {
		return atpreoracle;
	}
	
	public AInstruction getInitOracle() {
		return initoracle;
	}
	
	public AExpression getPrecondition() {
		return precond;
	}
	
	public AExpression getOracle() {
		return oracle;
	}
	
	public GTransition getTransition() {
		return transition;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(mid);
		sb.append("[");
		for (Entry<String, Triplet<AExpression, AInstruction,AInstruction>> arg : values.entrySet()) {
			sb.append(arg.getKey());
			sb.append("=");
			sb.append(arg.getValue().l().toString());
			sb.append(" ");
		}
		sb.append("]");
		if(hasResult()) {
			sb.append(" -> ");
			sb.append(resultExpr.toString());
		}
		return sb.toString();
	}
}
