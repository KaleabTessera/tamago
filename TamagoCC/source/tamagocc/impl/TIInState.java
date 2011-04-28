/**
 * 
 */
package tamagocc.impl;

import java.util.ArrayList;

import tamagocc.api.TInState;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author aliquando
 *
 */
public class TIInState implements TInState {

	private ArrayList<String> states;
	/**
	 * 
	 */
	public TIInState() {
		states = new ArrayList<String>();
	}

	public TIInState(ArrayList<String> al) {
		states = new ArrayList<String>(al);
	}

	/**
	 * @see tamagocc.api.TInState#getInState()
	 */
	@Override
	public ArrayList<String> getInState() {
		return states;
	}
	
	public void addInState(String state) {
		states.add(state);
	}

	/**
	 * @see tamagocc.api.TInState#size()
	 */
	@Override
	public int size() {
		return states.size();
	}

	/**
	 * @see tamagocc.api.TExpression#getCat()
	 */
	@Override
	public ExprType getCat() {
		return ExprType.INSTATE;
	}

	/**
	 * @see tamagocc.api.TExpression#visitExpression(tamagocc.util.TamagoCCExpressionVisitor)
	 */
	@Override
	public Object visitExpression(TamagoCCExpressionVisitor exprvisitor)
			throws TamagoCCException {
		return exprvisitor.visitInState(this);
	}

	/**
	 * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
	 */
	@Override
	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitInState(this);
	}

}
