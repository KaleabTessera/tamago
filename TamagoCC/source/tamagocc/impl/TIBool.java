package tamagocc.impl;

import tamagocc.api.TBool;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;

/**
  * @author Hakim BELHAOUARI
 */
public class TIBool implements TBool {

    private boolean b;
    
    /**
     * 
     */
    public TIBool(boolean bool) {
        super();
        b = bool;
    }

    public boolean equals(Object o) {
    	if (o instanceof TBool) {
			TBool p = (TBool) o;
			return (getValue() == p.getValue());
		}
    	return false;
    }
    
    /**
     * @see tamagocc.api.TBool#getValue()
     */
    public boolean getValue() {
        return b;
    }
    
    /**
     * @see tamagocc.api.TExpression#getCat()
     */
    public ExprType getCat() {
        return ExprType.BOOL;
    }

    public String toString() {
        return "<bool val+'"+String.valueOf(b)+"' />";
    }
    
    /**
     * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitBool(this);
    }

    /**
     * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
     */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitBool(this);
	}
    
    
}
