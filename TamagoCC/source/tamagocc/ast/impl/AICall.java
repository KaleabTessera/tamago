/**
 * 
 */
package tamagocc.ast.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.ACall;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AIdent;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AICall implements ACall {

	private AIdent ident;
	private ArrayList<AExpression> arguments;
	
	/**
	 * 
	 */
	public AICall(AIdent ident,Collection<AExpression> args) {
		super();
		this.ident = ident;
		this.arguments = new ArrayList<AExpression>();
		this.arguments.addAll(args);
	}
	
	public AICall(AIdent ident) {
		super();
		this.ident = ident;
		this.arguments = new ArrayList<AExpression>();
	}

	/**
	 * @see tamagocc.ast.api.ACall#getIdent()
	 */
	public AIdent getIdent() {
		return ident;
	}

	/**
	 * @see tamagocc.ast.api.ACall#getArguments()
	 */
	public Iterator<AExpression> getArguments() {
		return arguments.iterator();
	}

	public void addArgument(AExpression expr) {
		arguments.add(expr);
	}
	
	/**
	 * @see tamagocc.ast.api.AExpression#getExpressionType()
	 */
	public int getExpressionType() {
		return AExpression.CALL;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitCall(this);
	}

	public boolean equals(Object o) {
		if (o instanceof AICall) {
			AICall c = (AICall) o;
			Iterator<? extends AExpression> args1 = c.getArguments();
			Iterator<? extends AExpression> args2 = getArguments();
			boolean result = true;
			while(args1.hasNext()&&args2.hasNext()) {
				AExpression expr1 = (AExpression)args1.next();
				AExpression expr2 = (AExpression)args2.next();
				result = result && expr1.equals(expr2);
			}
			if(args1.hasNext()||args2.hasNext())
				return false;
			else {
				return (result&&(getIdent().equals(c)));
			}
		}
		else
			return false;
	}

	public int getArgCount() {
		return arguments.size();
	}
	
}
