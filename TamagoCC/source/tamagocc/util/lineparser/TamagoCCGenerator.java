/**
 * 
 */
package tamagocc.util.lineparser;

import tamagocc.TamagoCC;
import tamagocc.exception.LineParserException;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCGenerator implements LineParserSpec {

	/**
	 * 
	 */
	public TamagoCCGenerator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#fire()
	 */
	public void fire() throws LineParserException {
		// TODO Auto-generated method stub

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
		return "--generator";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getDescription()
	 */
	public String getDescription() {
		return "Specify the full name of the generator, that define a target language"; 
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
		TamagoCC.setGenerator(value);
	}

}
