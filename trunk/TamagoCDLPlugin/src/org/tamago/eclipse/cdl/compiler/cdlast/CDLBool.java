/**
 * 
 */
package org.tamago.eclipse.cdl.compiler.cdlast;

import tamagocc.api.TExpression;
import tamagocc.impl.TIBool;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLBool extends CDLValue<Boolean> {

	/**
	 * @param val
	 */
	public CDLBool(Boolean val) {
		super(val);
	}

	@Override
	public TExpression toTExpression() {
		return new TIBool(this.value);
	}

}
