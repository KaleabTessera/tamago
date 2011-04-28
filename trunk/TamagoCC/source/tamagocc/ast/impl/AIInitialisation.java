/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AInitialisation;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AType;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIInitialisation implements AInitialisation {

	private AIdent identificateur;
	private AType type;
	private AExpression initvalue;
	private AComment comment;
	/**
	 * 
	 */
	public AIInitialisation(AIdent ident,AType type,AExpression initvalue) {
		super();
		identificateur = ident;
		this.type = type;
		this.initvalue = initvalue;
		comment = new AINoComment();
	}
	
	public AIInitialisation(AIdent ident,AType type) {
		super();
		identificateur = ident;
		this.type = type;
		this.initvalue = new AINoExpression();
		comment = new AINoComment();
	}
	
	public void setInitial(AExpression init) {
		this.initvalue = init;
	}

	/**
	 * @see tamagocc.ast.api.AInitialisation#getIdent()
	 */
	public AIdent getIdent() {
		return identificateur;
	}

	/**
	 * @see tamagocc.ast.api.AInitialisation#getType()
	 */
	public AType getType() {
		return type;
	}

	/**
	 * @see tamagocc.ast.api.AInitialisation#getInitial()
	 */
	public AExpression getInitial() {
		return initvalue;
	}

	/**
	 * @see tamagocc.ast.api.AInstruction#getInstructionType()
	 */
	public int getInstructionType() {
		return AInstruction.INITIALISATION;
	}

	/**
	 * @see tamagocc.ast.api.AInstruction#getComment()
	 */
	public AComment getComment() {
		return comment;
	}

	public void setComment(AComment comment) {
		this.comment = comment;
	}
	
	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitInitialisation(this);
	}
}
