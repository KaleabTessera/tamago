/*
 * Created on 10 nov. 2005
 */
package tamagocc.impl;

import tamagocc.api.TExpression;
import tamagocc.api.TType;
import tamagocc.api.TVariable;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author Hakim Belhaouari
 *
 */
public class TIVariable implements TVariable {

	private String variable;
	private TType type;
	private boolean forcedType;
	private TExpression index;
	
	/**
	 * 
	 */
	public TIVariable(String v,boolean forcedType,String type) {
		super();
		variable = v;
		this.forcedType = forcedType;
		index = null;
		if(forcedType)
			this.type = TIType.generateType(type);
		else
			this.type = TIType.generateType("Object");
	}
	public TIVariable(String v,boolean forcedType,String type, TExpression idx) {
		this(v,forcedType,type);
		index = idx;
	}
	
	public TIVariable(String v) {
		this(v,false,"Object");
	}

	public TIVariable(String v,TExpression idx) {
		this(v);
		this.index = idx;
	}
	
	/**
	 * @see tamagocc.api.TVariable#getVariable()
	 */
	public String getVariable() {
		return variable;
	}
	
	/**
	 * @see tamagocc.api.TVariable#forcedType()
	 */
	public boolean forcedType() {
		return forcedType;
	}
	
	public boolean equals(Object o) {
		if (o instanceof TVariable) {
			TVariable p = (TVariable) o;
			return ( getVariable().equals(p.getVariable()));
		}
		return false;
	}
	
	/**
	 * @see tamagocc.api.TVariable#getType()
	 */
	public TType getType() {
		return type;
	}

	/**
	 * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
	 */
	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitVariable(this);
	}
	
	public ExprType getCat() {
		return ExprType.VARIABLE;
	}
	
    /**
     * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
     */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitVariable(this);
	}

	public TExpression getIndex() {
		return index;
	}

	public boolean hasIndex() {
		return (index != null);
	}
	@Override
	public void setIndex(TExpression idx) {
		index = idx;
	}

}
