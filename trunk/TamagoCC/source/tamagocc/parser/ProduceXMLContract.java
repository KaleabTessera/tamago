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
public class ProduceXMLContract extends DefaultLineParserSpec {
	private boolean genXML;
	/**
	 * @param cmd
	 */
	public ProduceXMLContract() {
		super("-xml","Generate the xml contract");
		genXML = false;
	}

	@Override
	public void fire() throws LineParserException {
		genXML = true;
	}
	
	public boolean mustGenXML() {return genXML; }
}
