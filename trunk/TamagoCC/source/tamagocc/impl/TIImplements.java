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

	public TIImplements(String content) {
		int idx = content.lastIndexOf(".");
		if(idx == -1) {
			name = content;
			module = "";
		}
		else  {
			name = content.substring(idx+1);
			module = content.substring(idx-1);
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
