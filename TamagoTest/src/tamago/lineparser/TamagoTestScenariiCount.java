/**
 * 
 */
package tamago.lineparser;

import tamagocc.exception.LineParserException;
import tamagocc.util.lineparser.LineParserSpec;
import tamagotest.TamagoTestContext;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTestScenariiCount implements LineParserSpec {

	private TamagoTestContext ctx;
	
	/**
	 * 
	 */
	public TamagoTestScenariiCount(TamagoTestContext ctx) {
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
	 * @see tamagocc.util.lineparser.LineParserSpec#getCommand()
	 */
	public String getCommand() {
		return "-quantity";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getDescription()
	 */
	public String getDescription() {
		return "Specify the count of generated scenarios";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#immediateFire()
	 */
	public boolean immediateFire() {
		return true;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#isOptionnal()
	 */
	public boolean isOptionnal() {
		return true;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#setArgument(int, java.lang.String)
	 */
	public void setArgument(int pos, String value) throws LineParserException {
		ctx.setCount(Integer.parseInt(value));
	}

}
