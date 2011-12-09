package tamago.lineparser;
import tamagocc.exception.LineParserException;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.lineparser.DefaultLineParserSpec;
import tamagotest.TamagoTestContext;

/**
 * 
 */

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTestLengthScenario extends DefaultLineParserSpec {

	private TamagoTestContext ctx;
	private int length;
	/**
	 * 
	 */
	public TamagoTestLengthScenario(TamagoTestContext ctx) {
		super("--scenario-size","Specify the maximal length of the generated scenario (use only if compatible)","-ss");
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
	 * @see tamagocc.util.lineparser.LineParserSpec#setArgument(int, java.lang.String)
	 */
	public void setArgument(int pos, String value) throws LineParserException {
		length = Integer.parseInt(value);
	}

}
