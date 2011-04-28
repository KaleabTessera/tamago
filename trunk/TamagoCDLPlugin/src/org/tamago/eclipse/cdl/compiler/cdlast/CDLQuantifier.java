/**
 * 
 */
package org.tamago.eclipse.cdl.compiler.cdlast;

import javapop.framework.parser.expr.OperandNode;
import tamagocc.api.TType;

/**
 * @author Hakim Belhaouari
 *
 */
public abstract class CDLQuantifier extends CDLExpression implements OperandNode {

	protected String quant;
	protected String var;
	protected TType type;
	protected CDLExpression body;
	
	public CDLQuantifier(String quant, String var, TType type, CDLExpression body) {
		this.quant = quant;
		this.var = var;
		this.type = type;
		this.body = body;
	}
	
	@Override
	public boolean isOperand() {
		return true;
	}	
}
