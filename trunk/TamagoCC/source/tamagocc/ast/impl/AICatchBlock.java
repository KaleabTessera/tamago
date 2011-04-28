/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.ACatchBlock;
import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AType;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AICatchBlock implements ACatchBlock {

	private AIdent exceptionident;
	private AType exceptiontype;
	private AInstruction body;
	
	
	/**
	 * 
	 */
	public AICatchBlock(AType typeexception,AIdent identexception,AInstruction body) {
		super();
		this.body = body;
		this.exceptionident = identexception;
		this.exceptiontype = typeexception;
	}

	/**
	 * @see tamagocc.ast.api.ACatchBlock#getIdent()
	 */
	public AIdent getIdent() {
		return exceptionident;
	}

	/**
	 * @see tamagocc.ast.api.ACatchBlock#getExceptionType()
	 */
	public AType getExceptionType() {
		return exceptiontype;
	}

	/**
	 * @see tamagocc.ast.api.ACatchBlock#getBody()
	 */
	public AInstruction getBody() {
		return body;
	}



	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitCatchBlock(this);
	}
	
	public boolean equals(Object o) {
		if (o instanceof AICatchBlock) {
			AICatchBlock a = (AICatchBlock) o;
			return getIdent().equals(a.getIdent())
				&& getBody().equals(a.getBody())
				&& getExceptionType().equals(a.getExceptionType());
		}
		else
			return false;
	}

}
