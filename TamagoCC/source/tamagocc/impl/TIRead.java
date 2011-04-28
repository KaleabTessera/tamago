package tamagocc.impl;

import tamagocc.api.TExpression;
import tamagocc.api.TRead;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;
/**
 * @author Hakim BELHAOUARI
 * 10 oct. 2005 TIRead.java
 */
/**
 */
public class TIRead extends TIVariable implements TRead {

    private String name;
    
    /**
     * @param n : nom de la propriete a lire
     */
    public TIRead(String name) {
        super(name);
        this.name = name;
    }

    public TIRead(String name,TExpression idx) {
        super(name,idx);
        this.name = name;
    }
    
    /**
     * @see tamagocc.api.TRead#getName()
     */
    public String getName() {
       return name;
    }
    
    /**
     * @see tamagocc.api.TExpression#getCat()
     */
    public ExprType getCat() {
        return ExprType.READ;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof TRead) {
			TRead p = (TRead) o;
			return (getName().equals(p.getName()));
		}
    	return false;
    }

     /**
     * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitRead(this);
    }

    /**
     * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
     */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitRead(this);
	}
}
