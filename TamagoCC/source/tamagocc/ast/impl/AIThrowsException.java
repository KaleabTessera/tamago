/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AThrowsException;
import tamagocc.ast.api.AType;
import tamagocc.exception.TamagoCCException;

/**
 * @author hakim
 *
 */
public class AIThrowsException implements AThrowsException {

	private AType type;
	/**
	 * 
	 */
	public AIThrowsException(AType type) {
		super();
		this.type = type;
	}

	/**
	 * @see tamagocc.ast.api.AThrowsException#getType()
	 */
	public AType getType() {
		return type;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitThrowsException(this);
	}
	
	public boolean equals(Object o) {
		if (o instanceof AIThrowsException) {
			AIThrowsException t = (AIThrowsException) o;
			return getType().equals(t.getType());
		}
		else
			return false;
	}

}
