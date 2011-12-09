/**
 * 
 */
package tamagocc.parser;

import tamagocc.exception.LineParserException;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.lineparser.DefaultLineParserSpec;

/**
 * @author hbelhaou
 *
 */
public class ShowGrammar extends DefaultLineParserSpec {

	
	/**
	 * 
	 */
	public ShowGrammar() {
		super("--show-grammar","print in the output the textual CDL grammar","--grammar");
	}



	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#fire()
	 */
	@Override
	public void fire() throws LineParserException {
		TamagoCCLogger.println(0, TamagoCCParserCDL.gen.toString());
	}
}
