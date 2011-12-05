/**
 * 
 */
package tamagocc.parser.cdlast;

import tamagocc.api.TExpression;
import tamagocc.api.TType;
import tamagocc.impl.TIExistColl;
import tamagocc.impl.TIForallColl;
import tamagocc.impl.TIVariable;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLQColl extends CDLQuantifier {

	private CDLExpression coll;
	
	/**
	 * @param quant
	 * @param var
	 * @param type
	 * @param body
	 */
	public CDLQColl(String quant, String var, TType type, CDLExpression body, CDLExpression coll) {
		super(quant, var, type, body);
		this.coll = coll;
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
		sb.append(coll.getDescription());
		sb.append(" { ");
		sb.append(body.getDescription());
		sb.append(" }");
		return sb.toString();
	}

	@Override
	public TExpression toTExpression() {
		if("forall".equalsIgnoreCase(quant)) {
			return new TIForallColl(type,new TIVariable(var),coll.toTExpression(),body.toTExpression());
		}
		else {
			return new TIExistColl(type,new TIVariable(var),coll.toTExpression(),body.toTExpression());
		}
	}

}
