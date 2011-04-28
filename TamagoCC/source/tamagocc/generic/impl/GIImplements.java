/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GImplements;
import tamagocc.generic.api.GModule;
import tamagocc.generic.api.GType;

/**
 * @author Hakim Belhaouari
 *
 */
public class GIImplements implements GImplements {

	private GType type;
	private GModule module;
	
	/**
	 * 
	 */
	public GIImplements(GType type, GModule module) {
		this.type = type;
		this.module = module;
	}

	/**
	 * @see tamagocc.generic.api.GImplements#getModule()
	 */
	public GModule getModule() {
		return module;
	}

	/**
	 * @see tamagocc.generic.api.GImplements#getType()
	 */
	public GType getType() {
		return type;
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitImplements(this);
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o instanceof GImplements) {
			GImplements new_name = (GImplements) o;
			return new_name.getType().equals(type);
		}
		return false;
	}

}
