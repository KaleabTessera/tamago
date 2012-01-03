/**
 * 
 */
package tamagocc.parser.cdlast;

import java.util.ArrayList;
import java.util.Iterator;

import javapop.framework.parser.expr.ExprAssoc;
import javapop.framework.parser.expr.ExprNode;
import javapop.framework.parser.expr.InfixNode;
import tamagocc.api.TExpression;
import tamagocc.api.TOpeName;
import tamagocc.api.TOperator;
import tamagocc.impl.TIOperator;
import tamagocc.parser.SearchTypeInfixOperator;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLInfix extends CDLExpression implements InfixNode {
	private CDLExpression left;
	private CDLExpression right;
	private String opName;
	private int prio;
	private ExprAssoc assoc;
	
	public CDLInfix(String opName) {
		this.opName = opName;
		generate(opName);
		prio = -1;
	}
	
	public boolean isInfix() {
		return true;
	}

	public ExprAssoc getAssoc() {
		return assoc;
	}

	public CDLExpression getLeftOperand() {
		return left;
	}

	public CDLExpression getRightOperand() {
		return right;
	}

	public void setAssoc(ExprAssoc assoc) {
		this.assoc = assoc;
	}

	public void setLeftOperand(ExprNode operand) {
		left = (CDLExpression) operand;
		checkType();
	}

	private void checkType() {
		if(left != null && right != null) {
			SearchTypeInfixOperator.getType(this);
		}
		
	}

	public void setRightOperand(ExprNode operand) {
		right = (CDLExpression) operand;
		checkType();
	}

	public int getPrio() {
		return prio;
	}

	public void setPrio(int prio) {
		this.prio = prio;
	}

	public String getDescription() {
		return opName;
	}

	@Override
	public TExpression toTExpression() {
		TOpeName myop = generate(opName);
		ArrayList<TExpression> coll = new ArrayList<TExpression>(2);
		TExpression eleft = left.toTExpression();
		if(eleft instanceof TOperator) {
			TOperator opleft = (TOperator) eleft;
			if(opleft.getOperator().getID() == myop.getID()) {
				Iterator<TExpression> ls =  opleft.getOperands();
				while(ls.hasNext()) {
					coll.add(ls.next());
				}
			}
			else
				coll.add(eleft);
		}
		else		
			coll.add(eleft);
		
		TExpression eright = right.toTExpression();
		if((eright instanceof TOperator) &&	(((TOperator) eright).getOperator().getID() == myop.getID())) {
				Iterator<TExpression> rs =  ((TOperator) eright).getOperands();
				while(rs.hasNext()) {
					coll.add(rs.next());
				}
		}
		else		
			coll.add(right.toTExpression());
		
		
		
		TIOperator ope = new TIOperator(myop,coll);
		return ope;
	}
	
	public static TOpeName generate(String o)  {
		if("<".equals(o))
			return TOpeName.opInf;
		else if("<=".equals(o))
			return TOpeName.opInfEg;
		else if("==".equals(o) || "=" .equals(o))
			return TOpeName.opEg;
		else if("!=".equals(o) || "<>".equals(o))
			return TOpeName.opNe;
		else if(">=".equals(o))
			return TOpeName.opSupEg;
		else if(">".equals(o))
			return TOpeName.opSup;
		else if("+".equals(o))
			return TOpeName.opPlus;
		else if("-".equals(o))
			return TOpeName.opMinus;
		else if("*".equals(o))
			return TOpeName.opTimes;
		else if("/".equals(o))
			return TOpeName.opQuo;
		else if("%".equals(o))
			return TOpeName.opMod;
		else if("&&".equals(o) || "and".equals(o))
			return TOpeName.opAnd;
		else if("or".equals(o)|| "||".equals(o))
			return TOpeName.opOr;
		else if("xor".equals(o) || "^".equals(o))
			return TOpeName.opXor;
		else if("impl".equals(o) || "=>".equals(o)|| "==>".equals(o)|| "-->".equals(o))
			return TOpeName.opImply;
		else if("equiv".equals(o)|| "<=>".equals(o) || "<==>".equals(o)|| "<->".equals(o) || "<-->".equals(o))
			return TOpeName.opEquiv;
		else
			throw new RuntimeException("Unknown binary operator: "+o);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(left.toString());
		sb.append(opName);
		sb.append(right.toString());
		return sb.toString();
	}
}
