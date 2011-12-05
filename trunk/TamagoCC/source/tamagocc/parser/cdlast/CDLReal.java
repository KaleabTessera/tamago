/**
 * 
 */
package tamagocc.parser.cdlast;

import tamagocc.api.TExpression;
import tamagocc.impl.TIReal;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLReal extends CDLValue<Double> {

	/**
	 * @param val
	 */
	public CDLReal(Double val) {
		super(val);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TExpression toTExpression() {
		return new TIReal(this.value);
	}

}
