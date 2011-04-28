/**
 * 
 */
package org.tamago.eclipse.cdl.compiler.cdlast;

import tamagocc.api.TExpression;
import tamagocc.impl.TIInLabel;
import javapop.framework.parser.expr.OperandNode;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLInLabel extends CDLExpression implements OperandNode {

	private CDLExpression scope;
	private CDLExpression target;
	
	
	/**
	 * 
	 */
	public CDLInLabel(CDLExpression scope, CDLExpression target) {
		this.scope = scope;
		this.target = target;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#getDescription()
	 */
	public String getDescription() {
		return scope.getDescription()+"."+target.getDescription();
	}
	
	@Override
	public boolean isOperand() {
		return true;
	}

	@Override
	public TExpression toTExpression() {
		return new TIInLabel(scope.toTExpression(),target.toTExpression());
	}


}
