/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GNamespace;

/**
 * @author Hakim Belhaouari
 *
 */
public class GINamespace implements GNamespace {

	private String namespace;
	private String type;
	
	/**
	 * 
	 */
	public GINamespace(String namespace,String type) {
		this.namespace = namespace;
		this.type = type;
	}

	/**
	 * @see tamagocc.generic.api.GNamespace#getNamespace()
	 */
	public String getNamespace() {
		return namespace;
	}

	/**
	 * @see tamagocc.generic.api.GNamespace#getType()
	 */
	public String getType() {
		return type;
	}

	/**
	 * @see tamagocc.generic.api.GNamespace#isPackage()
	 */
	public boolean isPackage() {
		return (type.equals("*"));
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitNamespace(this);
	}

}
