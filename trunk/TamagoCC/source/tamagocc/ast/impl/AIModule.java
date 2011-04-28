/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AModule;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIModule implements AModule {

	private String fullname;
	
	/**
	 * 
	 */
	public AIModule(String fullname) {
		super();
		this.fullname = fullname;
	}

	/**
	 * @see tamagocc.ast.api.AModule#getFullName()
	 */
	public String getFullName() {
		return fullname;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitModule(this);
	}
	
	public boolean equals(Object o) {
		if (o instanceof AIModule) {
			AIModule m = (AIModule) o;
			return getFullName().equals(m.getFullName());
		}
		else
			return false;
	}

}
