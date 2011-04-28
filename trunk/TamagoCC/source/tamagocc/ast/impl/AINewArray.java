/**
 * 
 */
package tamagocc.ast.impl;

import java.util.Iterator;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.ANewArray;
import tamagocc.ast.api.AType;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.NilCollection;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AINewArray implements ANewArray {

	private int taille;
	private AType type;
	
	/**
	 * 
	 */
	public AINewArray(AType type,int taille) {
		super();
		this.type = type;
		this.taille = taille;
	}

	/**
	 * @see tamagocc.ast.api.ANew#getType()
	 */
	public AType getType() {
		return type;
	}
	
	public int getTaille() {
		return taille;
	}
	
	public void setType(AType type) {
		this.type = type;
	}
	
	public void setTaille(int taille) {
		this.taille = taille;
	}

	/**
	 * @see tamagocc.ast.api.ANew#getParameters()
	 */
	public Iterator<AExpression> getArguments() {
		return (new NilCollection<AExpression>()).iterator();
	}

	/**
	 * @see tamagocc.ast.api.AExpression#getExpressionType()
	 */
	public int getExpressionType() {
		return AExpression.NEW;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitNewArray(this);
	}

	public int size() {
		return 0;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		sb.append(type.getType());
		sb.append("[");
		sb.append(taille);
		sb.append("]");
		return sb.toString();
	}
}
