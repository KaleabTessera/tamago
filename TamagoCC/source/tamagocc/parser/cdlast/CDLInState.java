/**
 * 
 */
package tamagocc.parser.cdlast;

import java.util.ArrayList;
import java.util.Collection;

import javapop.framework.parser.expr.OperandNode;

import tamagocc.api.TExpression;
import tamagocc.impl.TIInState;

/**
 * @author hbelhaou
 *
 */
public class CDLInState extends CDLExpression implements OperandNode{

	private ArrayList<String> names;
	
	/**
	 * 
	 */
	public CDLInState(Collection<String> names) {
		this.names = new ArrayList<String>(names);
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#getDescription()
	 */
	@Override
	public String getDescription() {
		StringBuilder sb = new StringBuilder("@instate(");
		for (String name : names) {
			sb.append(name);
			sb.append(",");
		}
		sb.append(")");
		return sb.toString();
	}
	

	@Override
	public boolean isOperand() {
		return true;
	}

	/**
	 * @see tamagocc.parser.cdlast.CDLExpression#toTExpression()
	 */
	@Override
	public TExpression toTExpression() {
		return new TIInState(names);
	}

}
