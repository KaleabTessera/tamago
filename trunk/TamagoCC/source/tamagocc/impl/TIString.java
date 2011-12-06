package tamagocc.impl;

import tamagocc.api.TString;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;

/**
 * 
 * @author Hakim BELHAOUARI
 */
public class TIString implements TString {

    String s;
    /**
     * 
     */
    public TIString(String str) {
        super();
        s = str;
    }

    /**
     * @see tamagocc.api.TString#getValue()
     */
    public String getValue() {
        return s;
    }

    /**
     * @see tamagocc.api.TExpression#getCat()
     */
    public ExprType getCat() {
        return ExprType.STRING;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof TString) {
    		TString p = (TString) o;
			return (getValue() == p.getValue());
		}
    	return false;
    }
    
    /**
     * @see tamagocc.api.TObject#visit()
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitString(this);
    }
    
    /**
     * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
     */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitString(this);
	}
	
	public String toString() {
		return "TIString("+s+")"; 
	}
}
