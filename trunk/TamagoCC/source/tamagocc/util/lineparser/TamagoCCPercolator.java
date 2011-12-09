/**
 * 
 */
package tamagocc.util.lineparser;

import tamagocc.exception.LineParserException;
import tamagocc.exception.TamagoCCException;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.percolation.TamagoCCPercolation;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCPercolator extends DefaultLineParserSpec {

	
	private String name;
	private String fullname;
	
	/**
	 * 
	 */
	public TamagoCCPercolator() {
		super("--percolator","Specify the name and the fullname of a new percolator","-pp","-perc");
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#fire()
	 */
	public void fire() throws LineParserException {
		try {
			TamagoCCPercolation.addPercolator(name, fullname);
		} catch (TamagoCCException e) {
			TamagoCCLogger.println(1, "Error during the parsing of command line.");
			TamagoCCLogger.infoln(e);
		}

	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getArity()
	 */
	public int getArity() {
		return 2;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#immediateFire()
	 */
	public boolean immediateFire() {
		return true;
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
		if(pos == 0)
			name = value;
		else if(pos == 1)
			fullname = value;
		else
			throw new LineParserException("Too many Arguments for the --percolator option");
	}

}
