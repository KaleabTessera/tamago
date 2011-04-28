package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GInitialisation;
import tamagocc.generic.api.GVariable;

public class GIInitialisation implements GInitialisation {

	private GVariable variable;
	private GExpression init;
	
	public GIInitialisation(GVariable variable,GExpression init) {
		super();
		this.variable = variable;
		this.init = init;
	}

	public GVariable getVariable() {
		return variable;
	}

	public GExpression getInitValue() {
		return init;
	}
	
	public void setVariable(GVariable variable) {
		this.variable = variable;
	}
	
	public void setInitValue(GExpression init) {
		this.init = init;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitInitialisation(this);
	}
	
	public Object visitPreExpression(TamagoCCGPreExpressionVisitor visitor) throws TamagoCCException {
		return visitor.visitInitialisation(this);
	}
}
