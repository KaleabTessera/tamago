package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GPreExpression;
import tamagocc.generic.api.GType;
import tamagocc.generic.api.GVariable;

public class GIVariable extends GIExpression implements GVariable {

	private String name;
	private GType type;
	private GExpression index;
	
	public GIVariable(String n,GType t,GPreExpression init) {
		name = n;
		type = t;
		addPreExpression(init);
		index = null;
	}
	
	public GIVariable(String n,GType t) {
		name = n;
		type = t;
		index = null;
	}
	
	public GIVariable(String n,GType t,GPreExpression init,GExpression idx) {
		name = n;
		type = t;
		addPreExpression(init);
		index = idx;
	}
	
	public GIVariable(String n,GType t,GExpression idx) {
		name = n;
		type = t;
		index = idx;
		if(idx != null)
			addAllPreExpression(idx.getPreExpression());
	}
	
	public GExprType getCategory() {
		return GExprType.VARIABLE;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public GType getType() {
		return type;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitVariable(this);
	}
	
	/**
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitVariable(this);
	}
	
	public boolean equals(Object o) {
		if (o instanceof GVariable) {
			GVariable nb = (GVariable) o;
			return (getName() == nb.getName())&&(getType().catType()==nb.getType().catType());
		}
		else
			return false;
	}
 
	public boolean hasIndex() {
		return (index != null);
	}
	
	public GExpression getIndex() {
		return index;
	}
}
