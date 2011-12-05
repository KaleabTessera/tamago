/**
 * 
 */
package tamagocc.parser.cdlast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tamagocc.api.TExpression;
import tamagocc.impl.TICall;

import javapop.framework.parser.expr.OperandNode;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLCall extends CDLExpression implements OperandNode {

	private String name;
	private List<CDLExpression> args;
	
	
	/**
	 * 
	 */
	public CDLCall(String name, Collection<CDLExpression> args) {
		this.name = name;
		this.args = new ArrayList<CDLExpression>(args);
	}

	@Override
	public boolean isOperand() {
		return true;
	}
	
	/**
	 * @see javapop.framework.parser.expr.ExprNode#getDescription()
	 */
	public String getDescription() {
		return name+"("+argstoString()+")";
	}

	private String argstoString() {
		StringBuilder sb = new StringBuilder();
		if(args.size() >= 1) {
			sb.append(args.get(0).getDescription());
			for(int i = 1; i < args.size(); i++) { 
				sb.append(',');
				sb.append(args.get(i).getDescription());
			}
		}
		return sb.toString();
	}

	@Override
	public TExpression toTExpression() {
		ArrayList<TExpression> coll = new ArrayList<TExpression>();
		for (CDLExpression e : this.args) {
			coll.add(e.toTExpression());
		}
		
		return new TICall(name,coll);
	}

}
