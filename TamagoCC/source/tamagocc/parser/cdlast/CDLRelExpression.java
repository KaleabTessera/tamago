package tamagocc.parser.cdlast;

import tamagocc.api.TExpression;
import javapop.framework.parser.expr.InfixNode;
import javapop.framework.parser.expr.OperandNode;
import javapop.framework.parser.expr.PostfixNode;
import javapop.framework.parser.expr.PrefixNode;

public class CDLRelExpression extends CDLExpression implements OperandNode {
	private CDLExpression relExpression;
	
	public CDLRelExpression(CDLExpression relExpression) {
		this.relExpression = relExpression;
	}
	
	public CDLExpression getExpression() {
		return relExpression;
	}
	
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
		return true;
	}

	public boolean isPostfix() {
		return false;
	}

	public boolean isPrefix() {
		return false;
	}

	@Override
	public String getDescription() {
		return "RelationalExpression{" + relExpression.getDescription()+"}";
	}

	@Override
	public TExpression toTExpression() {
		return relExpression.toTExpression();
	}
	
}
