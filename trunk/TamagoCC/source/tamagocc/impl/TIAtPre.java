package tamagocc.impl;

import tamagocc.api.*;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;
/**
 * @author Hakim BELHAOUARI and Frederic Peschanski
 * 30 juin 2005 TIAtPre.java
 */
public class TIAtPre implements TAtPre {

    private TExpression expr; 
    
    /**
     * 
     */
    public TIAtPre(TExpression e) {
        super();
        expr = e;
    }

    /**
     * @see tamagocc.api.TAtPre#getExpression()
     */
    public TExpression getExpression() {
        return expr;
    }

    /**
     * @see tamagocc.api.TExpression#getCat()
     */
    public ExprType getCat() {
         return ExprType.ATPRE;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof TAtPre) {
			TAtPre p = (TAtPre) o;
			return (getExpression().equals(p.getExpression()));
		}
    	return false;
    }
    
    /**
     * @see tamagocc.api.TObject#visit()
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitAtPre(this);
    }

    /**
     * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
     */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitAtPre(this);
	}
}
