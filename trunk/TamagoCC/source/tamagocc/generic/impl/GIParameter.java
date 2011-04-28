/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GParameter;
import tamagocc.generic.api.GType;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class GIParameter implements GParameter {

	private String name;
	private GType type;
	/**
	 * 
	 */
	public GIParameter(String name,GType type) {
		super();
		this.name = name;
		this.type = type;
	}

	/**
	 * @see tamagocc.generic.api.GParameter#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see tamagocc.generic.api.GParameter#getType()
	 */
	public GType getType() {
		return type;
	}
	
	public boolean equals(Object o) {
		if(o instanceof GParameter) {
			return type.equals(((GParameter)o).getType());
		}
		return false;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitParameter(this);
	}

}
