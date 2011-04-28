/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GVariable;

/**
 * @author Hakim Belhaouari
 *
 */
public class GIAtPreInitialisation extends GIInitialisation {

	/**
	 * @param variable
	 * @param init
	 */
	public GIAtPreInitialisation(GVariable variable, GExpression init) {
		super(variable, init);
		
	}

}
