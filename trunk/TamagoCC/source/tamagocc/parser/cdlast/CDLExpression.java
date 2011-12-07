/**
 * 
 */
package tamagocc.parser.cdlast;

import tamagocc.api.TExpression;
import javapop.framework.parser.expr.ExprNode;
import javapop.framework.parser.expr.InfixNode;
import javapop.framework.parser.expr.OperandNode;
import javapop.framework.parser.expr.PostfixNode;
import javapop.framework.parser.expr.PrefixNode;

/**
 * @author Hakim Belhaouari
 *
 */
public abstract class CDLExpression implements ExprNode {

	public InfixNode asInfix() {
		return (InfixNode) this;
	}

	public OperandNode asOperand() {
		return (OperandNode) this;
	}

	public PostfixNode asPostfix() {
		return (PostfixNode) this;
	}

	public PrefixNode asPrefix() {
		return (PrefixNode) this;
	}

	public boolean isInfix() {
		return false;
	}

	public boolean isOperand() {
		return false;
	}

	public boolean isPostfix() {
		return false;
	}

	public boolean isPrefix() {
		return false;
	}

	public abstract TExpression toTExpression();
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName());
		sb.append("[[[");
		sb.append(this.getDescription());
		sb.append("]]]");
		return sb.toString();				
	}
}
