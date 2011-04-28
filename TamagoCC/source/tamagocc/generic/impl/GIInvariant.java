/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GCategory;
import tamagocc.generic.api.GCondition;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GInvariant;
import tamagocc.generic.api.GTamagoEntity;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class GIInvariant extends GICondition implements GInvariant {

	private GTamagoEntity owner;
	
	/**
	 * 
	 */
	public GIInvariant(GTamagoEntity owner,GExpression expression,GCategory category,String message) {
		super(category,expression,message);
		this.owner = owner;
	}
	
	public GIInvariant(GTamagoEntity owner,GCondition condition) {
		super(condition.getCategory(),condition.getExpression(),condition.getMessage());
		this.owner = owner;
	}

	/**
	 * @see tamagocc.generic.api.GInvariant#getOwner()
	 */
	public GTamagoEntity getOwner() {
		return owner;
	}
	
	public void setOwner(GTamagoEntity owner) {
		this.owner = owner;
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitInvariant(this);
	}
}
