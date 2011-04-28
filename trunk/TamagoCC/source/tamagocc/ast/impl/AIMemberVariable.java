/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AMemberVariable;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVisibility;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIMemberVariable implements AMemberVariable {

	private AIdent ident;
	private AType type;
	private AVisibility visibility;
	private AComment comment;
	private AExpression mavar;
	
	/**
	 * 
	 */
	public AIMemberVariable(AIdent ident,AType type,AVisibility visibility) {
		super();
		this.ident = ident;
		this.type = type;
		this.visibility = visibility;
		comment = new AINoComment();
		mavar = new AIVariable(ident);
	}

	/**
	 * @see tamagocc.ast.api.AMemberVariable#getIdent()
	 */
	public AIdent getIdent() {
		return ident;
	}

	/**
	 * @see tamagocc.ast.api.AMemberVariable#getType()
	 */
	public AType getType() {
		return type;
	}

	/**
	 * @see tamagocc.ast.api.AMemberVariable#getVisibility()
	 */
	public AVisibility getVisibility() {
		return visibility;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitMemberVariable(this);
	}

	
	public boolean equals(Object o) {
		if (o instanceof AIMemberVariable) {
			AIMemberVariable m = (AIMemberVariable) o;
			return getType().equals(m.getType())&&getIdent().equals(m.getIdent())&&getVisibility().equals(m.getVisibility());
		}
		else
			return false;
		
	}

	public AComment getComment() {
		return comment;
	}
	
	public void setComment(AComment comment) {
		this.comment = comment;
	}

	public AExpression getCallMe() {
		return mavar;
	}
	
}
