/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GQuantifier;
import tamagocc.generic.api.GQuantifierVariable;
import tamagocc.generic.api.GVariable;

/**
 * @author Hakim Belhaouari
 *
 */
public class GIQuantifierVariable extends GIVariable implements GQuantifierVariable {

	private static int num = 0;
	private static String genName() {
		return "__quantifier_var"+(num++);
	}
	
	private GQuantifier quantifier;
	private GIVariable simple;
	
	/**
	 * 
	 */
	public GIQuantifierVariable(GQuantifier quant) {
		this(genName(),quant);
	}
	
	public GIQuantifierVariable(String name,GQuantifier quant) {
		super(name,GIType.TYPE_BOOL);
		this.quantifier = quant;
		simple = new GIVariable(this.getName(),this.getType());
	}

	
	public GQuantifier getQuantifier() {
		return quantifier;
	}
	
	public GVariable getSimpleVariable() {
		return simple;
	}
	
	/**
	 * @see tamagocc.generic.impl.GIVariable#setName(java.lang.String)
	 */
	public void setName(String name) {
		super.setName(name);
		simple.setName(name);
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitQuantifierVariable(this);
	}
	
	/**
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitQuantifierVariable(this);
	}
	

}
