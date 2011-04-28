/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.ABool;
import tamagocc.ast.api.AExpression;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.impl.GIBool;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIBool implements ABool {

	private boolean value;
	
	/**
	 * 
	 */
	public AIBool(boolean value) {
		super();
		this.value = value;
	}

	/**
	 * @see tamagocc.ast.api.ABool#getValue()
	 */
	public boolean getValue() {
		return value;
	}

	/**
	 * @see tamagocc.ast.api.AExpression#getExpressionType()
	 */
	public int getExpressionType() {
		return AExpression.BOOL;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitBool(this);
	}
	
	public boolean equals(Object o) {
		if(o instanceof GIBool) {
			GIBool b = (GIBool)o;
			return b.getValue()==getValue();
		}
		else
			return false;
	}
	
	public String toString() {
		return ""+value;
	}

}
