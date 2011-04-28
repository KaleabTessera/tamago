/**
 * 
 */
package tamagocc.util.lineparser;

import tamagocc.exception.LineParserException;
import tamagocc.logger.TamagoCCLogger;

/**
 * @author hakim
 *
 */
public class TamagoCCLogLevel implements LineParserSpec {

	/**
	 * 
	 */
	public TamagoCCLogLevel() {
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
		return "--debug";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getDescription()
	 */
	public String getDescription() {
		return "set the verbosity of the application";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#immediateFire()
	 */
	public boolean immediateFire() {
		return false;
	}
	public boolean isOptionnal() {
		return true;
	}
	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#setArgument(int, java.lang.String)
	 */
	public void setArgument(int pos, String value) throws LineParserException {
		TamagoCCLogger.setLevel(Integer.parseInt(value));
	}
}
