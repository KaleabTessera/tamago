/**
 * 
 */
package tamagocc.impl;

import tamagocc.api.TNamespace;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author Hakim Belhaouari
 *
 */
public class TINamespace implements TNamespace {

	private String namespace;
	private String type;
	
	/**
	 * 
	 */
	public TINamespace(String namespace,String type) {
		this.namespace = namespace;
		this.type = type;
	}

	public TINamespace(String content) {
		int idx = content.lastIndexOf(".");
		if(idx == -1) {
			type = content;
			namespace = "";
		}
		else {
			type = content.substring(idx+1);
			namespace = content.substring(0,idx-1);
		}
	}

	/**
	 * @see tamagocc.api.TNamespace#getNamespace()
	 */
	public String getNamespace() {
		return namespace;
	}

	/**
	 * @see tamagocc.api.TNamespace#getType()
	 */
	public String getType() {
		return type;
	}

	/**
	 * @see tamagocc.api.TNamespace#isPackage()
	 */
	public boolean isPackage() {
		return(type.equals("*"));
	}

	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitNamespace(this);
	}
}
