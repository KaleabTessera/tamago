/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GAccess;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GType;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class GIProperty implements GProperty {

	private String name;
	private GAccess access;
	private GType type;
	
	/**
	 * 
	 */
	public GIProperty(String name,GType type,GAccess access) {
		super();
		this.name = name;
		this.access = access;
		this.type = type;
	}

	/**
	 * @see tamagocc.generic.api.GProperty#getName()
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * @see tamagocc.generic.api.GProperty#getType()
	 */
	public GType getType() {
		return type;
	}
	
	public void setType(GType type) {
		this.type = type;
	}

	/**
	 * @see tamagocc.generic.api.GProperty#getAccess()
	 */
	public GAccess getAccess() {
		return access;
	}
	
	public void setAccess(GAccess access) {
		this.access = access;
	}
	
	public boolean equals(Object o) {
		if(o instanceof GProperty) {
			GProperty p = (GProperty)o;
			return (getName().equals(p.getName()));
		}
		else
			return false;
	}
	
	public boolean equivalentProperty(GProperty property) {
		return (getName().equals(property.getName()) 
				&& type.equals(property.getType())
				&& access.equals(property.getAccess()));
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitProperty(this);
	}
}
