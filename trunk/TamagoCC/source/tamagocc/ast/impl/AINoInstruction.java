/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.ANoInstruction;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AINoInstruction implements ANoInstruction {

	private static final ANoInstruction vNoinst = new AINoInstruction();
	public static final ANoInstruction getNoInstruction() { return vNoinst; }
	
	private AComment comment;
	/**
	 * 
	 */
	public AINoInstruction() {
		super();
		comment = new AINoComment();
	}
	
	public AINoInstruction(String comment) {
		super();
		this.comment = new AIInlineComment(comment);
	}

	/**
	 * @see tamagocc.ast.api.AInstruction#getInstructionType()
	 */
	public int getInstructionType() {
		return AInstruction.NOINSTRUCTION;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitNoInstruction(this);
	}
	public AComment getComment() {
		return comment;
	}
	
	public void setComment(AComment comment) {
		this.comment = comment;
	}
}
