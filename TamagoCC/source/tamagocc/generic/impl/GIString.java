package tamagocc.generic.impl;

import java.util.Collection;

import tamagocc.api.TString;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GPreExpression;
import tamagocc.generic.api.GString;
import tamagocc.util.NilCollection;

public class GIString implements GString {

	private String value;
	public GIString(String value) {
		super();
		this.value = value;
	}
	
	public GIString(TString tstring) {
		super();
		this.value = tstring.getValue();
	}

	public String getValue() {
		return value;
	}

	public GExprType getCategory() {
		return GExprType.STRING;
	}

	public Collection<GPreExpression> getPreExpression() {
		return new NilCollection<GPreExpression>();
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitString(this);
	}
	
	/**
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitString(this);
	}
	
	public Object visitPreExpression(TamagoCCGPreExpressionVisitor visitor) throws TamagoCCException {
		return this.visitExpression(visitor);
	}
	
	public boolean equals(Object o) {
		if (o instanceof GString) {
			GString nb = (GString) o;
			return (getValue() == nb.getValue());
		}
		else
			return false;
	}
 
}
