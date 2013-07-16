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
public class TamagoTestDest extends DefaultLineParserSpec {

	private TamagoTestContext ctx;
	/**
	 * 
	 */
	public TamagoTestDest(TamagoTestContext ctx) {
		super("--dest","Define the output directory (default: .)","-d");
		this.ctx = ctx;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#fire()
	 */
	public void fire() throws LineParserException {
		
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
		//ctx.setDestination(value);
	}

}
