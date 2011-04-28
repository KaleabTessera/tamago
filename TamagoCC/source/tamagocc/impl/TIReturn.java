package tamagocc.impl;

import tamagocc.api.TExpression;
import tamagocc.api.TReturn;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;
/**
  * @author Hakim BELHAOUARI
 */
public class TIReturn implements TReturn {

	private TExpression idx;
	
	
    /**
     * 
     */
    public TIReturn() {
        super();
        idx = null;
    }
    
    public TIReturn(TExpression idx) {
        super();
        this.idx = idx;
    }
    /**
     * @see tamagocc.api.TExprBool#getCat()
     */
    public ExprType getCat() {
        return ExprType.RETURN;
    }
    
    public boolean equals(Object o) {
    	return (o instanceof TReturn);
    }
    /**
     * @see tamagocc.api.TObject#visit()
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitReturn(this);
    }
    
    /**
     * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
     */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitReturn(this);
	}
	public TExpression getIndex() {
		return idx;
	}
	public boolean hasIndex() {
		return (idx != null);
	}
}

