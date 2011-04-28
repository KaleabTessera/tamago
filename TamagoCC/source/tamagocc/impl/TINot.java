package tamagocc.impl;

import tamagocc.api.TExpression;
import tamagocc.api.TNot;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;
/**
 * @author Hakim BELHAOUARI
 * 10 oct. 2005 TINot.java
 */
/**
 */
public class TINot implements TNot {

    private TExpression term;
    
    /**
     * 
     */
    public TINot(TExpression t) {
        super();
        term = t;
    }

    /**
     * @see tamagocc.api.TNot#getTerm()
     */
    public TExpression getTerm() {
        return term;
    }

    /**
     * @see tamagocc.api.TExpression#getCat()
     */
    public ExprType getCat() {
        return ExprType.NOT;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof TNot) {
			TNot p = (TNot) o;
			return (getTerm().equals(p.getTerm()));
		}
    	return false;
    }

    /**
     * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitNot(this);
    }
    
    /**
     * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
     */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitNot(this);
	}

}
