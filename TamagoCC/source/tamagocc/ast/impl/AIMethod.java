/**
 * 
 */
package tamagocc.ast.impl;

import java.util.ArrayList;
import java.util.Iterator;
import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.AIdent;
import tamagocc.ast.api.AInstruction;
import tamagocc.ast.api.AMethod;
import tamagocc.ast.api.AParameter;
import tamagocc.ast.api.AThrowsException;
import tamagocc.ast.api.AType;
import tamagocc.ast.api.AVisibility;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.NilIterator;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIMethod implements AMethod {

	private AComment comment;
	private AIdent ident;
	private AType type;
	private AVisibility visibility;
	private AInstruction body;
	private ArrayList<AParameter> parameters;
	private ArrayList<AThrowsException> throwsexception;
	private int methodtype;
	
	/**
	 * 
	 */
	public AIMethod(int methodTYPE,AIdent ident,AType type,AVisibility visibility) {
		super();
		this.ident = ident;
		this.type = type;
		this.visibility = visibility;
		comment = new AIInlineComment("");
		body = new AINoInstruction();
		parameters = new ArrayList<AParameter>();
		throwsexception = new ArrayList<AThrowsException>();
		this.methodtype = methodTYPE;
	}

	/**
	 * @see tamagocc.ast.api.AMethod#getComment()
	 */
	public AComment getComment() {
		return comment;
	}

	/**
	 * @see tamagocc.ast.api.AMethod#getMethodType()
	 */
	public int getMethodType() {
		return methodtype; 
	}

	/**
	 * @see tamagocc.ast.api.AMethod#getIdent()
	 */
	public AIdent getIdent() {
		return ident;
	}

	/**
	 * @see tamagocc.ast.api.AMethod#getType()
	 */
	public AType getType() {
		return type;
	}

	/**
	 * @see tamagocc.ast.api.AMethod#getVisibility()
	 */
	public AVisibility getVisibility() {
		return visibility;
	}

	/**
	 * @see tamagocc.ast.api.AMethod#getThrowsException()
	 */
	public Iterator<AThrowsException> getThrowsException() {
		return throwsexception.iterator();
	}

	/**
	 * @see tamagocc.ast.api.AMethod#getParameters()
	 */
	public Iterator<AParameter> getParameters() {
		return parameters.iterator();
	}

	public void addParameters(AParameter parameter) {
		parameters.add(parameter);
	}
	
	
	public void addThrowsException(AThrowsException ate) {
		if(!throwsexception.contains(ate))
			throwsexception.add(ate);
	}
	
	
	/**
	 * @see tamagocc.ast.api.AMethod#getBody()
	 */
	public AInstruction getBody() {
		return body;
	}
	
	
	public void setBody(AInstruction inst) {
		body = inst;
	}

	public void setComment(AComment comment) {
		this.comment = comment;
	}
	
	
	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitMethod(this);
	}
	
	
	public boolean equals(Object o) {
		if (o instanceof AIMethod) {
			AIMethod m = (AIMethod) o;
			return m.getIdent().equals(getIdent())&&NilIterator.areEqual(m.getParameters(),getParameters());
		}
		else return false;
	}

	public int getParameterNumber() {
		return parameters.size();
	}

}
