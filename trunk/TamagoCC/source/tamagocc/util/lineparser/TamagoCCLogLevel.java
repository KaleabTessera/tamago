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
public class TamagoCCLogLevel extends DefaultLineParserSpec {

	/**
	 * 
	 */
	public TamagoCCLogLevel() {
		super("--debug","set the verbosity of the application","-l","-lvl");
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
		TamagoCCLogger.setLevel(Integer.parseInt(value));
	}
}
