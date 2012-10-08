/**
 * 
 */
package tamagocc.parser;

import tamagocc.exception.LineParserException;
import tamagocc.util.lineparser.DefaultLineParserSpec;

/**
 * @author hakim
 *
 */
public class CDLStopOnFirstError extends DefaultLineParserSpec {

	private boolean activate;
	
	public CDLStopOnFirstError() {
		super("-errorstop", "Stop compilation at the first error.", "-es");
		activate = false;
	}

	@Override
	public void fire() throws LineParserException {
		activate = true;
	}
	
	public boolean mustStopOnFirstError() {
		return activate;
	}
}
