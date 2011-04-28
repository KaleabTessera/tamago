package tamagocc.generic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import tamagocc.api.TOpeName;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GOperator;
import tamagocc.generic.api.GPreExpression;

public class GIOperator extends GIExpression implements GOperator {

	private TOpeName operator;
		  //Iterator<GExpression>
	private ArrayList<GExpression> operands;
	
	public GIOperator(TOpeName operator,Iterator<GExpression> operands) {
		this(operator);
		
		while(operands.hasNext()) {
			GExpression expr = operands.next();
			preexprs.addAll(expr.getPreExpression());
			this.operands.add(expr);
		}
	}
	public GIOperator(TOpeName operator) {
		super();
		this.operator = operator;
		preexprs = new ArrayList<GPreExpression>();
		this.operands = new ArrayList<GExpression>();
	}

	public Iterator<GExpression> getOperands() {
		return operands.iterator();
	}

	public void addOperand(GExpression expr) {
		operands.add(expr);
		preexprs.addAll(expr.getPreExpression());
	}
	
	
	public TOpeName getOperator() {
		return operator;
	}

	public GExprType getCategory() {
		return GExprType.OPERATOR;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitOperator(this);
	}
	
	/**
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitOperator(this);
	}
	
	public boolean equals(Object o) {
		if(o instanceof GOperator) {
			GOperator op = (GOperator)o;
			Iterator<GExpression> ops = op.getOperands();
			Iterator<GExpression> ops2 = getOperands();
			boolean result = true;
			while(ops.hasNext()) {
				if(!ops2.hasNext())
					return false;
				GExpression e1 = ops.next();
				GExpression e2 = ops2.next();
				result = result&&(e1.equals(e2));
			}
			if(ops2.hasNext())
				return false;
			return result&&(getOperator().getID()==op.getOperator().getID());
		}
		else
			return false;
			
	}

	public int getArity() {
		return operands.size();
	}
	public GExpression getOperand(int i) {
		return operands.get(i); 
	}
 
}
