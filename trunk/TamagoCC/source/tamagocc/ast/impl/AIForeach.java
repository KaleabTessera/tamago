/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AForeach;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVariable;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari
 *
 */
public class AIForeach implements AForeach {

	private AInstruction body;
	private AExpression set;
	private AVariable variable;
	private AType type;
	private AComment comment;
	
	/**
	 * 
	 */
	public AIForeach(AVariable var,AType t,AExpression s,AInstruction inst) {
		this.variable = var;
		this.set = s;
		this.body = inst;
		this.type = t;
		comment = new AINoComment();
	}

	/**
	 * @see tamagocc.ast.api.AForeach#getBody()
	 */
	public AInstruction getBody() {
		return body;
	}

	/**
	 * @see tamagocc.ast.api.AForeach#getCollection()
	 */
	public AExpression getCollection() {
		return set;
	}

	/**
	 * @see tamagocc.ast.api.AForeach#getVariable()
	 */
	public AVariable getVariable() {
		return variable;
	}

	/**
	 * @see tamagocc.ast.api.AForeach#getVariableType()
	 */
	public AType getVariableType() {
		return type;
	}

	/**
	 * @see tamagocc.ast.api.AInstruction#getComment()
	 */
	public AComment getComment() {
		return comment;
	}

	/**
	 * @see tamagocc.ast.api.AInstruction#getInstructionType()
	 */
	public int getInstructionType() {
		return AInstruction.FOREACH;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitForeach(this);
	}

}
