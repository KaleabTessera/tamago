/**
 * 
 */
package org.tamago.eclipse.cdl.compiler.cdlast;

import javapop.framework.parser.expr.OperandNode;
import tamagocc.api.TExpression;
import tamagocc.api.TType;
import tamagocc.impl.TICast;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLCast extends CDLExpression implements OperandNode {

	private CDLExpression expr;
	private TType type;
	
	/**
	 * 
	 */
	public CDLCast(TType type, CDLExpression expr) {
		this.expr = expr;
		this.type = type;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#getDescription()
	 */
	@Override
	public String getDescription() {
		return "("+type.getType()+")"+expr.getDescription();
	}
	
	@Override
	public boolean isOperand() {
		return true;
	}

	@Override
	public TExpression toTExpression() {
		return new TICast(type,expr.toTExpression());
	}

}
