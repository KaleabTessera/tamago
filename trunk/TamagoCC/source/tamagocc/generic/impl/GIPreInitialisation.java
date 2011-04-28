/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GAtPre;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GPreInitialisation;
import tamagocc.generic.api.GVariable;

/**
 * @author Hakim Belhaouari
 *
 */
public class GIPreInitialisation extends GIInitialisation implements
		GPreInitialisation {

	
	private GAtPre owner;
	/**
	 * @param variable
	 * @param init
	 */
	public GIPreInitialisation(GAtPre owner,GVariable variable, GExpression init) {
		super(variable, init);
		this.owner = owner;
	}

	/**
	 * @see tamagocc.generic.api.GPreInitialisation#getOwner()
	 */
	public GAtPre getOwner() {
		return owner;
	}
	
	/**
	 * @see tamagocc.generic.impl.GIInitialisation#visitPreExpression(tamagocc.generic.TamagoCCGPreExpressionVisitor)
	 */
	public Object visitPreExpression(TamagoCCGPreExpressionVisitor visitor)
			throws TamagoCCException {
		return visitor.visitPreInitialisation(this);
	}
	
	/**
	 * @see tamagocc.generic.impl.GIInitialisation#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitPreInitialisation(this);
	}

}
