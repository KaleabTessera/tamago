/**
 * 
 */
package tamagocc.util.lineparser;

import tamagocc.TamagoCCParser;
import tamagocc.exception.LineParserException;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCSetXSD extends DefaultLineParserSpec {

	/**
	 * 
	 */
	public TamagoCCSetXSD() {
		super("--setxsd","Specify the URL of the XSD file, for validation of CDL files [obsolete]","-x");
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
		TamagoCCParser.XSD_LOCATION = value;

	}

}
