/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.ANamespace;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AINamespace implements ANamespace {

	private String name;
	private String type;
	private boolean isstatic;
	
	/**
	 * 
	 */
	public AINamespace(String namespace) {
		super();
		this.name = namespace;
		type = "";
		this.isstatic = false;
	}
	
	public AINamespace(String namespace,String type) {
		this.name = namespace;
		this.type = type;
		this.isstatic = false;
	}
	
	public AINamespace(String namespace,boolean isstatic) {
		super();
		this.name = namespace;
		type = "";
		this.isstatic = isstatic; 
	}
	
	public AINamespace(String namespace,String type,boolean isstatic) {
		this.name = namespace;
		this.type = type;
		this.isstatic = isstatic;
	}

	/**
	 * @see tamagocc.ast.api.ANamespace#getNamespace()
	 */
	public String getNamespace() {
		return name;
	}

	public String getType() {
		return type;
	}
	
	public boolean isPackage() {
		return (type.length()==0);
	}
	
	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitNamespace(this);
	}

	
	public boolean equals(Object o) {
		if(o instanceof ANamespace) {
			return name.equals(((ANamespace)o).getNamespace()) && type.equals(((ANamespace)o).getType());
		}
		else
			return false;
	}

	public boolean isStatic() {
		return isstatic;
	}
	
}
