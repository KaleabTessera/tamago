/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AIdent;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIIdent implements AIdent {

	private String name;
	
	/**
	 * 
	 */
	public AIIdent(String name) {
		super();
		this.name = name;
	}

	/**
	 * @see tamagocc.ast.api.AIdent#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitIdent(this);
	}

	
	public boolean equals(Object o) {
		if (o instanceof AIdent) {
			AIdent ind = (AIdent) o;
			return getName().equals(ind.getName());			
		}
		else
			return false;
	}
	
}
