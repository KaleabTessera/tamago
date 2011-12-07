/**
 * 
 */
package tamagocc.parser;

import tamagocc.exception.LineParserException;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.lineparser.LineParserSpec;

/**
 * @author hbelhaou
 *
 */
public class ShowGrammar implements LineParserSpec {

	String cmd;
	/**
	 * 
	 */
	public ShowGrammar(String cmd) {
		this.cmd = cmd;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getCommand()
	 */
	@Override
	public String getCommand() {
		return cmd;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getArity()
	 */
	@Override
	public int getArity() {
		return 0;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#setArgument(int, java.lang.String)
	 */
	@Override
	public void setArgument(int pos, String value) throws LineParserException {
		
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getDescription()
	 */
	@Override
	public String getDescription() {
		return "print in the output the textual CDL grammar";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#fire()
	 */
	@Override
	public void fire() throws LineParserException {
		TamagoCCLogger.println(0, TamagoCCParserCDL.gen.toString());
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#immediateFire()
	 */
	@Override
	public boolean immediateFire() {
		return false;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#isOptionnal()
	 */
	@Override
	public boolean isOptionnal() {
		return true;
	}

}
