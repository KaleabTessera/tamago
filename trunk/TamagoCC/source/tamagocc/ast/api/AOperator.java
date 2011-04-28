/**
 * 
 */
package tamagocc.ast.api;

import java.util.Iterator;

import tamagocc.api.TOpeName;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AOperator extends AExpression {
	TOpeName getOperator();
	Iterator<AExpression> getOperands();
	int getArity();
	AExpression getOperand(int n);
}
