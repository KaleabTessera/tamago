/**
 * 
 */
package tamagocc.generic;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GExistColl;
import tamagocc.generic.api.GExistRange;
import tamagocc.generic.api.GExistSet;
import tamagocc.generic.api.GForallColl;
import tamagocc.generic.api.GForallRange;
import tamagocc.generic.api.GForallSet;
import tamagocc.generic.api.GInitialisation;
import tamagocc.generic.api.GPreInitialisation;
import tamagocc.generic.api.GSet;
import tamagocc.generic.impl.GIQuantifierVariable;

/**
 * @author Hakim Belhaouari
 *
 */
public interface TamagoCCGPreExpressionVisitor extends TamagoCCGExpressionVisitor {
	Object visitSet(GSet set) throws TamagoCCException;
	
	Object visitForallRange(GForallRange r) throws TamagoCCException;
	Object visitForallSet(GForallSet s) throws TamagoCCException;
	Object visitForallColl(GForallColl f) throws TamagoCCException;
	
	Object visitExistRange(GExistRange r) throws TamagoCCException;
	Object visitExistSet(GExistSet s) throws TamagoCCException;
	Object visitExistColl(GExistColl f) throws TamagoCCException;
	
	Object visitQuantifierVariable(GIQuantifierVariable variable) throws TamagoCCException;

	Object visitInitialisation(GInitialisation initialisation) throws TamagoCCException;

	/**
	 * @param preInitialisation
	 * @return
	 */
	Object visitPreInitialisation(GPreInitialisation preInitialisation) throws TamagoCCException;
}
