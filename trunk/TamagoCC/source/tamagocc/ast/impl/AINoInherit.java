/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AInherit;
import tamagocc.ast.api.AModule;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public final class AINoInherit implements AInherit {

	/**
	 * 
	 */
	public AINoInherit() {
		super();
	}

	public String getFullNameInherit() {
		return "";
	}

	public AModule getModule() {
		return null;
	}

	public String getName() {
		return "";
	}

	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitInherit(this);
	}


}
