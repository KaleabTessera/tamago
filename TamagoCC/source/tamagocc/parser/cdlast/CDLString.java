/**
 * 
 */
package tamagocc.parser.cdlast;

import tamagocc.api.TExpression;
import tamagocc.impl.TIString;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLString extends CDLValue<String> {

	/**
	 * @param val
	 */
	public CDLString(String val) {
		super(val);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TExpression toTExpression() {
		return new TIString(value);
	}

}
