package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GCategory;
import tamagocc.generic.api.GCondition;
import tamagocc.generic.api.GExpression;

public class GICondition implements GCondition {

	private GCategory category;
	private String message;
	private GExpression expression;
	
	public GICondition(GCategory category,GExpression expression,String message) {
		super();
		this.category = category;
		this.message = message;
		this.expression = expression;
	}

	public GCategory getCategory() {
		return category;
	}

	public String getMessage() {
		return message;
	}

	public GExpression getExpression() {
		return expression;
	}

	public void setCategory(GCategory cat) {
		this.category = cat;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setExpression(GExpression expr) {
		this.expression = expr;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitCondition(this);
	}
}
