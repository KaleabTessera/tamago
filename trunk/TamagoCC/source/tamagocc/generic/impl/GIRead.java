package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GRead;

public class GIRead extends GIVariable implements GRead {

	private String name;
	
	public GIRead(String name) {
		super(name,null);
		this.name = name;
	}
	
	public GIRead(String name,GExpression idx) {
		super(name,null,idx);
		this.name = name;
	}
	

	public String getName() {
		return name;
	}
	
	public GExprType getCategory() {
		return GExprType.READ;
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitRead(this);
	}
	
	/**
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitRead(this);
	}
 
	
	public boolean equals(Object o) {
		if (o instanceof GRead) {
			GRead nb = (GRead) o;
			return (getName() == nb.getName());
		}
		else
			return false;
	}
}
