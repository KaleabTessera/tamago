package tamagocc.generic.api;

import java.util.Iterator;

import tamagocc.api.TOpeName;

public interface GOperator extends GExpression {
	// Iterator<GExpression>
	Iterator<GExpression> getOperands();
	TOpeName getOperator();
	
	int getArity();
	GExpression getOperand(int i);
}
