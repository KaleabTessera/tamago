package tamago.lineparser;
import tamagocc.exception.LineParserException;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.lineparser.LineParserSpec;
import tamagotest.TamagoTestContext;

/**
 * 
 */

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTestLengthScenario implements LineParserSpec {

	private TamagoTestContext ctx;
	private int length;
	/**
	 * 
	 */
	public TamagoTestLengthScenario(TamagoTestContext ctx) {
		this.ctx = ctx;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#fire()
	 */
	public void fire() throws LineParserException {
		try {
			ctx.getStrategy().setMaxLengthScenario(length);
		}
		catch(Exception e) {
			TamagoCCLogger.info(e);
		}

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
		return "--scenario-size";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getDescription()
	 */
	public String getDescription() {
		return "Specify the length of the generated scenario";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#immediateFire()
	 */
	public boolean immediateFire() {
		return false;
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
		length = Integer.parseInt(value);
	}

}
