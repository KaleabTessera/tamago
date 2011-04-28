/**
 * 
 */
package tamago.lineparser;

import tamagocc.exception.LineParserException;
import tamagocc.util.lineparser.LineParserSpec;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTestMode implements LineParserSpec {
	public enum TestMode { GENERATION, EXECUTION }
	
	private TestMode mode;
	
	/**
	 * 
	 */
	public TamagoTestMode() {
		mode = TestMode.GENERATION;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#fire()
	 */
	public void fire() throws LineParserException {
		
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
	 * @see tamagocc.util.lineparser.LineParserSpec#getCommand()
	 */
	public String getCommand() {
		return "--mode";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getDescription()
	 */
	public String getDescription() {
		return "Force the mode of the TamagoTest tool, it can generates scenarios or produces some issues with a abstract testcase";
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
		return false;
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
