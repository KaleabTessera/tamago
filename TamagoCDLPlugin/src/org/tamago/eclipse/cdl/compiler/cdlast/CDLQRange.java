/**
 * 
 */
package org.tamago.eclipse.cdl.compiler.cdlast;

import tamagocc.api.TExpression;
import tamagocc.api.TType;
import tamagocc.impl.TIExistRange;
import tamagocc.impl.TIForallRange;
import tamagocc.impl.TIVariable;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLQRange extends CDLQuantifier {

	private CDLExpression min;
	private CDLExpression max;
	
	/**
	 * @param quant
	 * @param var
	 * @param type
	 * @param body
	 */
	public CDLQRange(String quant, String var, TType type, CDLExpression body,CDLExpression min, CDLExpression max) {
		super(quant, var, type, body);
		this.min = min;
		this.max = max;
	}

	/**
	 * @see javapop.framework.parser.expr.ExprNode#getDescription()
	 */
	public String getDescription() {
		StringBuilder sb = new StringBuilder();
		sb.append(quant);
		sb.append(" ");
		sb.append(var);
		sb.append(":");
		sb.append(type.getType());
		sb.append(" ");
		sb.append(min.getDescription());
		sb.append("..");
		sb.append(max.getDescription());
		sb.append(" { ");
		sb.append(body.getDescription());
		sb.append(" }");
		return sb.toString();
	}

	@Override
	public TExpression toTExpression() {
		if("forall".equalsIgnoreCase(quant))
			return new TIForallRange(type,new TIVariable(var),min.toTExpression(),max.toTExpression(),body.toTExpression());
		else
			return new TIExistRange(type,new TIVariable(var),min.toTExpression(),max.toTExpression(),body.toTExpression());
	}

}
