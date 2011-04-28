/**
 * 
 */
package tamagocc.ast.impl;

import java.util.ArrayList;
import java.util.Iterator;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.ANew;
import tamagocc.ast.api.AType;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 */
public class AINew implements ANew {

	protected AType type;
	protected ArrayList<AExpression> arguments;
	
	public AINew(AType type) {
		super();
		this.type = type;
		arguments = new ArrayList<AExpression>();
	}
	
	/**
	 * @see tamagocc.ast.api.ANew#getType()
	 */
	public AType getType() {
		return type;
	}

	/**
	 * @see tamagocc.ast.api.ANew#getParameters()
	 */
	public Iterator<AExpression> getArguments() {
		return arguments.iterator();
	}
	
	public void addArguments(AExpression argument) {
		arguments.add(argument);
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitNew(this);
	}

	public int getExpressionType() {
		return AExpression.NEW;
	}

	public int size() {
		return arguments.size();
	}

}
