/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AAffectation;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AVariable;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIAffectation implements AAffectation {

	private AVariable variable;
	private AExpression expr;
	private AComment comment;
	
	/**
	 * 
	 */
	public AIAffectation(AVariable variable,AExpression expr) {
		super();
		this.variable = variable;
		this.expr = expr;
		comment = new AINoComment();
	}

	/**
	 * @see tamagocc.ast.api.AAffectation#getVariable()
	 */
	public AVariable getVariable() {
		return variable;
	}

	/**
	 * @see tamagocc.ast.api.AAffectation#getInitValeur()
	 */
	public AExpression getInitValeur() {
		return expr;
	}

	/**
	 * @see tamagocc.ast.api.AInstruction#getInstructionType()
	 */
	public int getInstructionType() {
		return AInstruction.AFFECTATION;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitAffectation(this);
	}
	
	public boolean equals(Object o) {
		if(o instanceof AIAffectation) {
			AIAffectation a = (AIAffectation)o;
			return getVariable().equals(a.getVariable())&&getInitValeur().equals(a.getInitValeur());
		}
		else 
			return false;
	}

	public AComment getComment() {
		return comment;
	}
	
	public void setComment(AComment comment) {
		this.comment=  comment;
	}

}
