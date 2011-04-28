/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AProperty;
import tamagocc.ast.api.AType;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari
 *
 */
public class AIProperty implements AProperty {

	private String name;
	private AType type;
	private boolean emptybody;
	private boolean read;
	private boolean write;
	
	/**
	 * 
	 */
	public AIProperty(boolean empty,String name,AType type,boolean read,boolean write) {
		this.name = name;
		this.type = type;
		this.emptybody = empty;
		this.read = read;
		this.write = write;
	}

	/**
	 * @see tamagocc.ast.api.AProperty#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see tamagocc.ast.api.AProperty#getType()
	 */
	public AType getType() {
		return type;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitProperty(this);
	}
	
	public boolean equals(Object o) {
		if(o instanceof AProperty) {
			return name.equals(((AProperty) o).getName()) && type.equals(((AProperty) o).getType());
		}
		return false;
	}

	public boolean canRead() {
		return read;
	}

	public boolean canWrite() {
		return write;
	}

	public boolean emptyBody() {
		return emptybody;
	}

}
