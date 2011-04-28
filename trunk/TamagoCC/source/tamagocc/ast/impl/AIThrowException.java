/**
 * 
 */
package tamagocc.ast.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AThrowException;
import tamagocc.ast.api.AType;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIThrowException implements AThrowException {

	private ArrayList<AExpression> arguments;
	private AType typeexception;
	private AComment comment;
	
	/**
	 * 
	 */
	public AIThrowException(AType typeexception,Collection<AExpression> arguments) {
		super();
		this.arguments = new ArrayList<AExpression>();
		this.arguments.addAll(arguments);
		this.typeexception = typeexception;
		comment = new AINoComment();
	}
	
	/**
	 * 
	 */
	public AIThrowException(AType typeexception) {
		super();
		this.arguments = new ArrayList<AExpression>();
		this.typeexception = typeexception;
	}

	/**
	 * @see tamagocc.ast.api.AThrowException#getType()
	 */
	public AType getType() {
		return typeexception;
	}

	/**
	 * @see tamagocc.ast.api.AThrowException#getArguments()
	 */
	public Iterator<AExpression> getArguments() {
		return arguments.iterator();
	}

	public void addArgument(AExpression e) {
		arguments.add(e);
	}
	
	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitThrowException(this);
	}

	public int getInstructionType() {
		return AInstruction.THROWEXCEPTION;
	}
	
	public AComment getComment() {
		return comment;
	}
	
	public void setComment(AComment comment) {
		this.comment = comment;
	}

	public int size() {
		return arguments.size();
	}

}
