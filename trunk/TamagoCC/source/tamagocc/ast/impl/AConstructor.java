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

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AConstructor implements AMethod {

	private AComment comment;
	private AIdent ident;
	private AVisibility visibility;
	private AInstruction body;
	private ArrayList<AParameter> parameters;
	private ArrayList<AThrowsException> throwsexception;
	
	
	/**
	 * 
	 */
	public AConstructor(AIdent ident,AVisibility visibility) {
		super();
		
		this.comment = new AINoComment();
		this.ident = ident;
		this.visibility = visibility;
		this.body = new AINoInstruction();
		this.parameters = new ArrayList<AParameter>();
		this.throwsexception = new ArrayList<AThrowsException>();
	}

	/**
	 * @see tamagocc.ast.api.AMethod#getComment()
	 */
	public AComment getComment() {
		return comment;
	}

	public void setComment(AComment comment) {
		this.comment = comment;
	}
	
	/**
	 * @see tamagocc.ast.api.AMethod#getMethodType()
	 */
	public int getMethodType() {
		return AMethod.IMPLEMENTED;
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
		return null;
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

	public void addThrowsException(AThrowsException ate) {
		throwsexception.add(ate);
	}
	
	/**
	 * @see tamagocc.ast.api.AMethod#getParameters()
	 */
	public Iterator<AParameter> getParameters() {
		return parameters.iterator();
	}

	public void addParameter(AParameter param) {
		parameters.add(param);
	}
	
	/**
	 * @see tamagocc.ast.api.AMethod#getBody()
	 */
	public AInstruction getBody() {
		return body;
	}

	public void setBody(AInstruction body) {
		this.body = body;
	}
	
	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitConstructor(this);
	}

	public int getParameterNumber() {
		return parameters.size();
	}

}
