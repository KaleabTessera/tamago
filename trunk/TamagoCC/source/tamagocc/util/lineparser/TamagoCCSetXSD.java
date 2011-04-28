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
public class TamagoCCSetXSD implements LineParserSpec {

	/**
	 * 
	 */
	public TamagoCCSetXSD() {
		// TODO Auto-generated constructor stub
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
		return "--setxsd";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getDescription()
	 */
	public String getDescription() {
		return "Specify the URL of the XSD file, for validation of CDL files";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#immediateFire()
	 */
	public boolean immediateFire() {
		return false;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#isOptionnal()
	 */
	public boolean isOptionnal() {
		return true;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#setArgument(int, java.lang.String)
	 */
	public void setArgument(int pos, String value) throws LineParserException {
		TamagoCCParser.XSD_LOCATION = value;

	}

}
