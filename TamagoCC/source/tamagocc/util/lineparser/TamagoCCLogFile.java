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
public class TamagoCCLogFile implements LineParserSpec {

	/**
	 * 
	 */
	public TamagoCCLogFile() {
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
		return "--logfile";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getDescription()
	 */
	public String getDescription() {
		return "Specify the file where all log will be written (followed values are known : stdout, stderr)";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#immediateFire()
	 */
	public boolean immediateFire() {
		return false;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#setArgument(int, java.lang.String)
	 */
	public void setArgument(int pos, String value) throws LineParserException {
		TamagoCCLogger.setOut(value);
	}

	public boolean isOptionnal() {
		return true;
	}

}
