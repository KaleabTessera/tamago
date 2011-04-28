package tamagocc.impl;

import tamagocc.api.TNoContract;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;
/**
 * @author Hakim BELHAOUARI and Frederic Peschanski
 */
public class TINoContract implements TNoContract {

    /**
     * 
     */
    public TINoContract() {
        super();

    }

    /**
     * @see tamagocc.api.TExpression#getCat()
     */
    public ExprType getCat() {
        return ExprType.NOCONTRACT;
    }

    public boolean equals(Object o) {
    	return (o instanceof TNoContract);
    }
    
    /**
     * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitNoContract(this);
    }
    
    /**
     * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
     */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitNoContract(this);
	}

}
