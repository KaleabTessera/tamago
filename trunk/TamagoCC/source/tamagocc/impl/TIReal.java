package tamagocc.impl;

import tamagocc.api.TReal;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;

/**
 * 
 * @author Hakim BELHAOUARI
 */
public class TIReal implements TReal {

    private double value; 
    /**
     * 
     */
    public TIReal(double f) {
        super();
        value = f;
    }

    /**
     * @see tamagocc.api.TReal#getValue()
     */
    public double getValue() {
        return value;
    }
    
    /**
     * @see tamagocc.api.TExpression#getCat()
     */
    public ExprType getCat() {
        return ExprType.REAL;
    }
    
    public String toString() {
        return "<float val='"+value+"' />";
    }
    
    
    public boolean equals(Object o) {
    	if (o instanceof TReal) {
    		TReal p = (TReal) o;
			return (getValue() == p.getValue());
		}
    	return false;
    }
    
    /**
     * @see tamagocc.api.TObject#visit()
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitReal(this);
    }
    
    /**
     * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
     */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitReal(this);
	}
}
