/**
 * 
 */
package tamagocc.generic.api;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGPreExpressionVisitor;

/**
 * @author Hakim Belhaouari
 *
 */
public interface GQuantifier extends GPreExpression {
	
	GType getType();
	GVariable getVariable();
	GExpression getBody();
	GQuantifierVariable getResultExpression();
	
	GQuantifierType getQuantifierType();
	
	Object visitGQuantifier(TamagoCCGPreExpressionVisitor visitor) throws TamagoCCException;
}
