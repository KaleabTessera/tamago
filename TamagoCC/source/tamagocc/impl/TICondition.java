package tamagocc.impl;

import tamagocc.api.TCategory;
import tamagocc.api.TCondition;
import tamagocc.api.TExpression;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCMakeReadableGExpression;
import tamagocc.util.TamagoCCVisitor;

public class TICondition implements TCondition {

	private TExpression expression;
	private String message;
	private TCategory category;
	
	public TICondition(TExpression expression,TCategory category,String message) {
		super();
		this.expression = expression;
		this.message = message;
		this.category = category;
	}

	public TExpression getExpression() {
		return expression;
	}
	
	public void setExpression(TExpression e) {
		this.expression = e;
	}

	public TCategory getCategory() {
		return category;
	}

	public String getMessage() {
		return message;
	}
	
	public boolean equals(Object o) {
		if (o instanceof TCondition) {
			TCondition p = (TCondition) o;
			return (getMessage().equals(p.getMessage())
					&&getCategory().equals(p.getCategory())
					&&getExpression().equals(p.getExpression()));
		}
		return false;
	}
	
	/**
     * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitCondition(this);        
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TICondition [");
		if (category != null && !category.equals("")) {
			builder.append("category=");
			builder.append(category);
			builder.append(", ");
		}
		if (expression != null) {
			builder.append("expression=");
			builder.append(expression);
			builder.append(", ");
		}
		if (message != null && !message.equals("")) {
			builder.append("message=");
			builder.append(message);
		}
		builder.append("]");
		return builder.toString();
	}

}
