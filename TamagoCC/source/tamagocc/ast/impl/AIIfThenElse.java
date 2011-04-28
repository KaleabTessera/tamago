/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AIfThenElse;
import tamagocc.ast.api.AInstruction;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIIfThenElse implements AIfThenElse {

	private AExpression condition;
	private AInstruction consequence;
	private AInstruction alternant;
	private AComment comment;
	
	/**
	 * 
	 */
	public AIIfThenElse(AExpression condition,AInstruction consequence,AInstruction alternant) {
		super();
		this.condition = condition;
		this.consequence = consequence;
		this.alternant = alternant;
		comment = new AINoComment();
	}

	/**
	 * @see tamagocc.ast.api.AIfThenElse#getCondition()
	 */
	public AExpression getCondition() {
		return condition;
	}

	/**
	 * @see tamagocc.ast.api.AIfThenElse#getCons()
	 */
	public AInstruction getCons() {
		return consequence;
	}

	/**
	 * @see tamagocc.ast.api.AIfThenElse#getAlt()
	 */
	public AInstruction getAlt() {
		return alternant;
	}

	/**
	 * @see tamagocc.ast.api.AInstruction#getInstructionType()
	 */
	public int getInstructionType() {
		return AInstruction.IFTHENELSE;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitIfThenElse(this);
	}

	public AComment getComment() {
		return comment;
	}
	
	public void setComment(AComment comment) {
		this.comment = comment;
	}

	
	public void setCons(AInstruction inst) {
		this.consequence = inst;
	}
	
	public void setAlt(AInstruction inst) {
		this.alternant = inst;
	}
}
