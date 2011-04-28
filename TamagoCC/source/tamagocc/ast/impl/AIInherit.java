/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AImplements;
import tamagocc.ast.api.AInherit;
import tamagocc.ast.api.AModule;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIInherit implements AInherit {

	private String name;
	private AModule module;
	
	/**
	 * 
	 */
	public AIInherit(String name,AModule module) {
		super();
		this.name = name;
		this.module = module;
	}

	/**
	 * @see tamagocc.ast.api.AInherit#getFullNameInherit()
	 */
	public String getFullNameInherit() {
		return module.getFullName()+"."+name;
	}

	public AModule getModule() {
		return module;
	}

	public String getName() {
		return name;
	}
	
	public boolean equals(Object o) {
		if(o instanceof AImplements) {
			AIInherit impl = (AIInherit)o;
			return impl.getFullNameInherit().equals(this.getFullNameInherit());
		}
		else
			return false;
	}

	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitInherit(this);
	}

}
