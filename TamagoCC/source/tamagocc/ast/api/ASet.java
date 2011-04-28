/**
 * 
 */
package tamagocc.ast.api;

import java.util.Collection;
import java.util.Iterator;

import tamagocc.ast.TamagoCCAST;

/**
 * @author Hakim Belhaouari
 *
 */
public interface ASet extends TamagoCCAST {
	Iterator<AExpression> getExpressions();	
	Collection<AExpression> getExpressionCollection();	
	int size();	
	String getName();
	
	AInstruction getInitialize();
}
