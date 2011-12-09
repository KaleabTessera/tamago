/**
 * 
 */
package tamagocc.impl;

import tamagocc.api.TImplements;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author Hakim Belhaouari
 *
 */
public class TIImplements implements TImplements {

	private String name;
	private String module;
	/**
	 * 
	 */
	public TIImplements(String name, String module) {
		this.name = name;
		this.module = module;
	}
	
	public TIImplements(String fullname) {
		int idx = fullname.lastIndexOf(".");
		if(idx == -1) {
			name = fullname;
			module = "";
		}
		else {
			name = fullname.substring(idx+1);
			module = fullname.substring(0, idx);
		}
	}

	/**
	 * @see tamagocc.api.TImplements#getModule()
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @see tamagocc.api.TImplements#getNameType()
	 */
	public String getNameType() {
		return name;
	}

	/**
	 * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
	 */
	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitImplements(this);
	}

}
