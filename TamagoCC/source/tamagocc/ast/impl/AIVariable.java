/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AVariable;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIVariable implements AVariable {

	private AIdent ident;
	private AExpression index;
	
	/**
	 * 
	 */
	public AIVariable(AIdent ident) {
		super();
		this.ident = ident;
		index= null;
	}
	
	public AIVariable(AIdent ident,AExpression idx) {
		super();
		this.ident = ident;
		index = idx;
	}

	/**
	 * @see tamagocc.ast.api.AVariable#getIdent()
	 */
	public AIdent getIdent() {
		return ident;
	}

	/**
	 * @see tamagocc.ast.api.AExpression#getExpressionType()
	 */
	public int getExpressionType() {
		return AExpression.VARIABLE;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitVariable(this);
	}

	public String toString() {
		if(hasIndex())
			return ident.getName()+"[IDX]";
		else
			return ident.getName();
	}

	public AExpression getIndex() {
		return index;
	}

	public boolean hasIndex() {
		return (index != null);
	}
}
