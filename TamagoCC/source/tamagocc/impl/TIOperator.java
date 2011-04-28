package tamagocc.impl;

import tamagocc.api.TExpression;
import tamagocc.api.TOpeName;
import tamagocc.api.TOperator;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.NilIterator;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;
/**
 * @author Hakim BELHAOUARI
 * 23 juin 2005 TIOperator.java
 */
/**
 */
public class TIOperator implements TOperator {
    
    private TOpeName operator;
    private ArrayList<TExpression> operands;
   
    
    /**
     * 
     */
    public TIOperator(TOpeName o,Collection<TExpression> op) {
        super();
        operator = o;
        operands = new ArrayList<TExpression>();
        operands.addAll(op);
    }

    public TIOperator(TOpeName o) {
        super();
        operator = o;
        operands = new ArrayList<TExpression>();
    }
    
    public void addOperand(TExpression expr) {
    	this.operands.add(expr);
    }
    
    /**
     * @see tamagocc.api.TOperator#getOperator()
     */
    public TOpeName getOperator() {
        return operator;
    }

    public Iterator<TExpression> getOperands() {
        return operands.iterator();
    }
    
    
    public boolean equals(Object o) {
    	if (o instanceof TOperator) {
			TOperator p = (TOperator) o;
			return (getOperator().equals(p.getOperator())
					&& NilIterator.areEqual(getOperands(),p.getOperands()));
		}
    	return false;
    }
    
    /**
     * @see tamagocc.api.TExpression#getCat()
     */
    public ExprType getCat() {
        return ExprType.OPERATOR;
    }

    /**
     * @see tamagocc.api.TObject#visit()
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitOperator(this);
    }
    
    /**
     * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
     */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitOperator(this);
	}

	public int size() {
		return operands.size();
	}

	public TExpression getOperand(int i) {
		return operands.get(i);
	}
	
}
