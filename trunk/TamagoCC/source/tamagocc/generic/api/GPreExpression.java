package tamagocc.generic.api;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;

/**
 * This interface indicates expression must be evaluate before an operation.
 * It is used in the operator <atpre> to specifie the new variable introduced to memory the 
 * returned value.
 * 
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface GPreExpression extends GObject {
	Object visitPreExpression(TamagoCCGPreExpressionVisitor visitor) throws TamagoCCException;
}
