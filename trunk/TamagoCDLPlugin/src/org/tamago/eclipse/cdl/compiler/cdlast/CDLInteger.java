/**
 * 
 */
package org.tamago.eclipse.cdl.compiler.cdlast;

import tamagocc.api.TExpression;
import tamagocc.impl.TIInteger;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLInteger extends CDLValue<Integer> {

	/**
	 * @param val
	 */
	public CDLInteger(Integer val) {
		super(val);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TExpression toTExpression() {
		return new TIInteger(this.value);
	}

}
