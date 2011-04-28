package tamagocc.impl;

import java.util.*;

import tamagocc.api.TCall;
import tamagocc.api.TExpression;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.NilIterator;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;

/**
 * 
 * @author Hakim BELHAOUARI
 */
public class TICall implements TCall {
    
    private String name;
    private Collection<TExpression> args;
    	

    /**
     * 
     */
    public TICall(String n, Collection<TExpression> p) {
        super();
        name = n;
        args = new ArrayList<TExpression>(p);
    }

    public TICall(String n) {
        super();
        name = n;
        args = new ArrayList<TExpression>();
    }
    
    public void addArgument(TExpression arg) {
    	this.args.add(arg);
    }
    /**
     * @see tamagocc.api.TCall#getName()
     */
    public String getName() {
        return name;
    }

   
    /**
     * @see tamagocc.api.TCall#getArguments()
     */
    public Iterator<TExpression> getArguments() {
        return args.iterator();
    }

    /**
     * @see tamagocc.api.TExpression#getCat()
     */
    public ExprType getCat() {
        return ExprType.CALL;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof TCall) {
			TCall p = (TCall) o;
			return (getName().equals(p.getName())
					&& NilIterator.areEqual(getArguments(),p.getArguments()));
		}
    	return false;
    }
    
    
    /**
     * @see tamagocc.api.TObject#visit()
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitCall(this);
    }
    
    /**
     * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
     */
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitCall(this);
	}
}
