/**
 * 
 */
package tamago.lineparser;

import tamagocc.exception.LineParserException;
import tamagocc.util.lineparser.DefaultLineParserSpec;
import tamagotest.TamagoTestContext;

/**
 * @author hakim
 *
 */
public class TamagoTestComponentImpl extends DefaultLineParserSpec {

	private TamagoTestContext ctx;
	/**
	 * 
	 */
	public TamagoTestComponentImpl(TamagoTestContext ctx) {
		super("--business","Choose the fullname for the business code (require default constructor)");
		this.ctx = ctx;
	}
	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getArity()
	 */
	public int getArity() {
		return 1;
	}
	
	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#immediateFire()
	 */
	public boolean immediateFire() {
		return true;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#setArgument(int, java.lang.String)
	 */
	public void setArgument(int pos, String value) throws LineParserException {
		ctx.setBusinessCode(value);
	}

}
