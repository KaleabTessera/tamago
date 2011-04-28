/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AAffectation;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AFor;
import tamagocc.ast.api.AInstruction;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari
 *
 */
public class AIFor implements AFor {
	private AInstruction affect;
	private AExpression cond;
	private AInstruction incr;
	private AInstruction body;
	private AComment comment;
	
	/**
	 * 
	 */
	public AIFor() {
		
	}
	
	public AIFor(AInstruction a,AExpression cond,AInstruction incr,AInstruction body) {
		this.affect = a;
		this.cond = cond;
		this.incr = incr;
		this.body = body;
	}

	/**
	 * @see tamagocc.ast.api.AFor#getAffectation()
	 */
	public AInstruction getAffectation() {
		return affect;
	}
	
	public void setAffectation(AAffectation a) {
		affect = a;
	}

	/**
	 * @see tamagocc.ast.api.AFor#getBody()
	 */
	public AInstruction getBody() {
		return body;
	}
	
	public void setBody(AInstruction body) {
		this.body = body;
	}

	/**
	 * @see tamagocc.ast.api.AFor#getCondition()
	 */
	public AExpression getCondition() {
		return cond;
	}
	
	public void setCondition(AExpression cond) {
		this.cond = cond;
	}

	/**
	 * @see tamagocc.ast.api.AFor#getIncrement()
	 */
	public AInstruction getIncrement() {
		return incr;
	}
	
	public void setIncrement(AInstruction incr) {
		this.incr = incr;
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
	 * @see tamagocc.ast.api.AInstruction#getInstructionType()
	 */
	public int getInstructionType() {
		return AInstruction.FOR;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitFor(this);
	}

}
