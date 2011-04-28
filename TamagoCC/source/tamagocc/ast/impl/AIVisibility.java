/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AVisibility;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIVisibility implements AVisibility {

	private int access;
	
	
	public static AVisibility PUBLIC_VISIBILITY = new AIVisibility(AVisibility.PUBLIC);
	public static AVisibility PRIVATE_VISIBILITY = new AIVisibility(AVisibility.PRIVATE);
	public static AVisibility LIMITED_VISIBILITY = new AIVisibility(AVisibility.LIMITED);
	public static AVisibility PROTECTED_VISIBILITY = new AIVisibility(AVisibility.PROTECTED);
	
	/**
	 * 
	 */
	public AIVisibility(int visibility) {
		super();
		access = visibility;
	}

	/**
	 * @see tamagocc.ast.api.AVisibility#getVisibility()
	 */
	public int getVisibility() {
		return access;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitVisibility(this);
	}
	
	public boolean equals(Object o) {
		if (o instanceof AIVisibility) {
			AIVisibility v = (AIVisibility) o;
			return v.getVisibility() == getVisibility();
		}
		else
			return false;
	}

}
