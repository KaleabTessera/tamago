/**
 * 
 */
package tamagocc.parser.cdlast;

import tamagocc.api.TExpression;
import tamagocc.impl.TIRead;
import javapop.framework.parser.expr.OperandNode;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLRead extends CDLExpression implements OperandNode {

	private String name;
	private CDLExpression index;
	
	
	/**
	 * 
	 */
	public CDLRead(String name, CDLExpression index) {
		this.name = name;
		this.index = index;
	}
	
	public CDLRead(String name) {
		this.name = name;
		this.index = null;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#getDescription()
	 */
	public String getDescription() {
		if(index == null)
			return "#"+name;
		else
			return "#"+name+"["+index.getDescription()+"]";
	}

	@Override
	public boolean isOperand() {
		return true;
	}

	@Override
	public TExpression toTExpression() {
		if(index != null)
			return new TIRead(name,index.toTExpression());
		else
			return new TIRead(name);
	}
	
	@Override
	public String toString() {
		return "#"+name;
	}
	
}
