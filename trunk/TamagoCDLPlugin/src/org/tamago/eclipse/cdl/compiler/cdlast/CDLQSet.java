/**
 * 
 */
package org.tamago.eclipse.cdl.compiler.cdlast;

import java.util.ArrayList;
import java.util.List;

import tamagocc.api.TExpression;
import tamagocc.api.TType;
import tamagocc.impl.TIExistSet;
import tamagocc.impl.TIForallSet;
import tamagocc.impl.TISet;
import tamagocc.impl.TIVariable;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLQSet extends CDLQuantifier {

	private List<CDLExpression> set;
	
	
	/**
	 * @param quant
	 * @param var
	 * @param type
	 * @param body
	 */
	public CDLQSet(String quant, String var, TType type, CDLExpression body,List<CDLExpression> set) {
		super(quant, var, type, body);
		this.set = new ArrayList<CDLExpression>(set);
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
		sb.append(" [");
		sb.append(argstoString());
		sb.append("] { ");
		sb.append(body.getDescription());
		sb.append(" }");
		return sb.toString();
	}

	private String argstoString() {
		StringBuilder sb = new StringBuilder();
		if(set.size() >= 1) {
			sb.append(set.get(0).getDescription());
			for(int i = 1; i < set.size(); i++) { 
				sb.append(',');
				sb.append(set.get(i).getDescription());
			}
		}
		return sb.toString();
	}

	@Override
	public TExpression toTExpression() {
		ArrayList<TExpression> coll = new ArrayList<TExpression>();
		for(CDLExpression e : set) {
			coll.add(e.toTExpression());
		}
		
		TISet set = new TISet(type,coll);
		
		if("forall".equalsIgnoreCase(quant)) {
			return new TIForallSet(type,new TIVariable(var),set,body.toTExpression());
		}
		else {
			return new TIExistSet(type,new TIVariable(var),set,body.toTExpression());
		}
	}
	
	
}
