/**
 * 
 */
package org.tamago.eclipse.cdl.compiler.cdlast;

import tamagocc.api.TExpression;
import tamagocc.impl.TIReturn;
import javapop.framework.parser.expr.OperandNode;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLReturn extends CDLExpression implements OperandNode {

	
	private CDLExpression index;
	
	/**
	 * 
	 */
	public CDLReturn() {
	}

	public CDLReturn(CDLExpression index) {
		this.index = index;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#getDescription()
	 */
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isOperand() {
		return true;
	}

	@Override
	public TExpression toTExpression() {
		if(index != null)
			return new TIReturn(index.toTExpression());
		else
			return new TIReturn();
	}

}
