/**
 * 
 */
package tamagocc.generic.impl;

import java.util.ArrayList;
import java.util.Collection;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GInState;

/**
 * @author Hakim Belhaouari
 *
 */
public class GIInState extends GIExpression implements GInState {

	private ArrayList<String> instates;
	
	/**
	 * 
	 */
	public GIInState() {
		instates = new ArrayList<String>();
	}

	public GIInState(ArrayList<String> inState) {
		instates = new ArrayList<String>(inState);
	}

	/**
	 * @see tamagocc.generic.api.GInState#getInState()
	 */
	@Override
	public Collection<String> getInState() {
		return instates;
	}

	/**
	 * @see tamagocc.generic.api.GInState#size()
	 */
	@Override
	public int size() {
		return instates.size();
	}

	/**
	 * @see tamagocc.generic.api.GExpression#getCategory()
	 */
	@Override
	public GExprType getCategory() {
		return GExprType.INSTATE;
	}

	@Override
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor)
			throws TamagoCCException {
		return exprvisitor.visitInState(this);
	}

	@Override
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitInState(this);
	}

}
