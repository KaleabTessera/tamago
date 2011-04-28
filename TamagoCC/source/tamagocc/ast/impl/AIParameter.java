/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AParameter;
import tamagocc.ast.api.AType;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIParameter implements AParameter {

	private AIdent ident;
	private AType type;
	
	/**
	 * 
	 */
	public AIParameter(AIdent ident,AType type) {
		super();
		this.ident = ident;
		this.type = type;
	}

	/**
	 * @see tamagocc.ast.api.AParameter#getIdent()
	 */
	public AIdent getIdent() {
		return ident;
	}

	/**
	 * @see tamagocc.ast.api.AParameter#getType()
	 */
	public AType getType() {
		return type;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitParameter(this);
	}

	
	public boolean equals(Object o) {
		if (o instanceof AIParameter) {
			AIParameter p = (AIParameter) o;
			return p.getType().equals(getType());
		}
		else
			return false;
	}
}
