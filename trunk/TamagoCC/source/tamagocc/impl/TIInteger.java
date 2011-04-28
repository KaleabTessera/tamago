package tamagocc.impl;

import tamagocc.api.TInteger;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;

/**
  * @author Hakim BELHAOUARI
 */
public class TIInteger implements TInteger {
    private int value;
    
    /**
     * 
     */
    public TIInteger(int i) {
        super();
        value = i;
    }

    /**
     * @see tamagocc.api.TInteger#getValue()
     */
    public int getValue() {
        return value;
    }

    /**
     * @see tamagocc.api.TExpression#getCat()
     */
    public ExprType getCat() {
        return ExprType.INT;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof TInteger) {
			TInteger p = (TInteger) o;
			return (getValue() == p.getValue());
		}
    	return false;
    }
    
    /**
     * @see tamagocc.api.TObject#visit()
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitInteger(this);
    }
    
    /**
     * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
     */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitInteger(this);
	}
}
