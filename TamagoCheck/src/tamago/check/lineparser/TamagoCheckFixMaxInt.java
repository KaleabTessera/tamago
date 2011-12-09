/**
 * 
 */
package tamago.check.lineparser;

import java.util.ArrayList;
import java.util.Collection;

import tamago.csp.var.Intvar;
import tamagocc.exception.LineParserException;
import tamagocc.util.lineparser.LineParserSpec;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCheckFixMaxInt implements LineParserSpec {

	/**
	 * 
	 */
	public TamagoCheckFixMaxInt() {
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
		return "--fixmaxint";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getDescription()
	 */
	public String getDescription() {
		return "fix the max value of default integer of default int builder";
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
		try {
			Intvar.MAXINT = Integer.parseInt(value);
		}
		catch(Exception e) {
			
		}
	}


	@Override
	public Collection<String> getAliases() {
		return new ArrayList<String>();
	}
}
