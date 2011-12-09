/**
 * 
 */
package tamago.lineparser;

import tamagocc.exception.LineParserException;
import tamagocc.util.lineparser.DefaultLineParserSpec;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTestMode extends DefaultLineParserSpec {
	public enum TestMode { GENERATION, EXECUTION }
	
	private TestMode mode;
	
	/**
	 * 
	 */
	public TamagoTestMode() {
		super("--mode","Force the mode of the TamagoTest tool, it can generates scenarios or produces some issues with a abstract testcase");
		mode = TestMode.GENERATION;
	}
	
	public TestMode getMode() {
		return mode;
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
		if("generation".equals(value))
			mode = TestMode.GENERATION;
		else if("execution".equals(value))
			mode = TestMode.EXECUTION;
		else
			throw new LineParserException("Unknow argument for the "+getCommand()+" only thoses values can use: generation or execution");
	}

}
