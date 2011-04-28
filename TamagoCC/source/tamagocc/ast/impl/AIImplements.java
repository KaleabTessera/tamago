/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AImplements;
import tamagocc.ast.api.AModule;
import tamagocc.ast.api.AType;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIImplements implements AImplements {
	private String name;
	private AModule module;
	private String fullname;
	/**
	 * 
	 */
	public AIImplements(String name,AModule module) {
		super();
		this.name = name;
		this.module = module;
		fullname = this.module.getFullName()+"."+this.name;
	}
	
	public AIImplements(AType type) {
		fullname = type.getType();
		int index = fullname.lastIndexOf(".");
		if(index != -1) {
			name = fullname.substring(index+1);
			module = new AIModule(fullname.substring(0,index));
		}
		else {
			name = type.getType();
			module = new AIModule("");
		}
	}

	/**
	 * @see tamagocc.ast.api.AImplements#getFullNameImplements()
	 */
	public String getFullNameImplements() {
		return fullname;
	}

	public String getName() {
		return name;
	}
	public AModule getModule() {
		return module;
	}
	
	public boolean equals(Object o) {
		if(o instanceof AImplements) {
			AImplements impl = (AImplements)o;
			return impl.getFullNameImplements().equals(this.getFullNameImplements());
		}
		else
			return false;
	}

	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitImplement(this);
	}
}
