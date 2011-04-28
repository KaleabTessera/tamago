/**
 * 
 */
package tamagocc.ast.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import tamagocc.api.TOpeName;
import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.AOperator;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIOperator implements AOperator {

	private TOpeName operator;
	private ArrayList<AExpression> operands;
	
	/**
	 * 
	 */
	public AIOperator(TOpeName operator,Collection<AExpression> operands) {
		super();
		this.operator = operator;
		this.operands = new ArrayList<AExpression>();
		this.operands.addAll(operands);
	}
	
	/**
	 * 
	 */
	public AIOperator(TOpeName operator) {
		super();
		this.operator = operator;
		this.operands = new ArrayList<AExpression>();
	}

	/**
	 * @see tamagocc.ast.api.AOperator#getOperator()
	 */
	public TOpeName getOperator() {
		return operator;
	}

	/**
	 * @see tamagocc.ast.api.AOperator#getOperands()
	 */
	public Iterator<AExpression> getOperands() {
		return operands.iterator();
	}
	
	public void addOperand(AExpression expr) {
		operands.add(expr);
	}

	/**
	 * @see tamagocc.ast.api.AExpression#getExpressionType()
	 */
	public int getExpressionType() {
		return AExpression.OPERATOR;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitOperator(this);
	}

	
	public int getOperandsCount() {
		return this.operands.size();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<AExpression> exprs = operands.iterator();
		while(exprs.hasNext()) {
			sb.append(exprs.next().toString());
			if(exprs.hasNext())
				sb.append(operator.toString());
		}
		return sb.toString();
	}

	public int getArity() {
		return operands.size();
	}

	public AExpression getOperand(int n) {
		return operands.get(n);
	}
}
