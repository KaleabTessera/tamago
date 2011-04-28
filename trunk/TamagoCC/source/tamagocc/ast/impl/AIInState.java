/**
 * 
 */
package tamagocc.ast.impl;

import java.util.ArrayList;
import java.util.Collection;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AInState;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari
 *
 */
public class AIInState implements AInState {

	private ArrayList<String> states;
	
	/**
	 * 
	 */
	public AIInState() {
		states = new ArrayList<String>();
	}
	
	public AIInState(Collection<String> sts) {
		states = new ArrayList<String>(sts);
	}

	/**
	 * @see tamagocc.ast.api.AInState#getStates()
	 */
	@Override
	public Collection<String> getStates() {
		return states;
	}

	/**
	 * @see tamagocc.ast.api.AInState#size()
	 */
	@Override
	public int size() {
		return states.size();
	}

	/**
	 * @see tamagocc.ast.api.AExpression#getExpressionType()
	 */
	@Override
	public int getExpressionType() {
		return AExpression.INSTATE;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	@Override
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitInState(this);
	}

}
