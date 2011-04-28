/**
 * 
 */
package tamagocc.ast.api;

import tamagocc.api.CatType;
import tamagocc.ast.TamagoCCAST;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AType extends TamagoCCAST {
	CatType getCategoryType();
	String getType();

	boolean isArray();
	AType getArrayType();

	boolean isParametric();

	AType[] getParametricsTypes();

	AType getParametricType(int i);  
}
