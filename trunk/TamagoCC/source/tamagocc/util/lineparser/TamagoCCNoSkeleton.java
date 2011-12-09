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
public class TamagoCCNoSkeleton extends DefaultLineParserSpec {
	/**
	 * 
	 */
	public TamagoCCNoSkeleton() {
		super("-noskeleton","indicates to TamagoCC do not generate skeleton of component/composite");
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#fire()
	 */
	public void fire() throws LineParserException {
		tamagocc.TamagoCC.setFlagNoSkeleton(true);
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#immediateFire()
	 */
	public boolean immediateFire() {
		return true;
	}
}
