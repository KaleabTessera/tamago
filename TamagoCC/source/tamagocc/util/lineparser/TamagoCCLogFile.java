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
public class TamagoCCLogFile extends DefaultLineParserSpec {

	/**
	 * 
	 */
	public TamagoCCLogFile() {
		super("--logfile","Specify the file where all log will be written (followed values are known : stdout, stderr)","-lf","-log");
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
		TamagoCCLogger.setOut(value);
	}
}
