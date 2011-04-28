/**
 * 
 */
package tamagocc.util.lineparser;

import tamagocc.exception.LineParserException;

/**
 * This class manage the argument '-noskeleton', that force to Tamago does not generate 
 * Skeleton of component/composite
 * 
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCNoSkeleton implements LineParserSpec {
	/**
	 * 
	 */
	public TamagoCCNoSkeleton() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#fire()
	 */
	public void fire() throws LineParserException {
		tamagocc.TamagoCC.setFlagNoSkeleton(true);
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getArity()
	 */
	public int getArity() {
		return 0;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getCommand()
	 */
	public String getCommand() {
		return "-noskeleton";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getDescription()
	 */
	public String getDescription() {
		return "indicates to TamagoCC do not generate skeleton of component/composite";
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
		
	}

}
