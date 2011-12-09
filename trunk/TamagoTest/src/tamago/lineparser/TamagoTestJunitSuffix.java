/**
 * 
 */
package tamago.lineparser;

import tamagocc.exception.LineParserException;
import tamagocc.util.lineparser.DefaultLineParserSpec;
import tamagotest.TamagoTestContext;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTestJunitSuffix extends DefaultLineParserSpec {

	private TamagoTestContext ctx;
	
	public TamagoTestJunitSuffix(TamagoTestContext ctx) {
		super("--junitsuffix","Allow user to specify a suffix to the Junit file.","-suffix");
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
		ctx.setJUnitSuffix(value);
	}

}
