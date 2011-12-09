/**
 * 
 */
package tamagocc.util.lineparser;

import tamagocc.exception.LineParserException;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCNoServiceInterface extends DefaultLineParserSpec {

	/**
	 * 
	 */
	public TamagoCCNoServiceInterface() {
		super("-noserviceinterface","Disable generation of service interface");
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#fire()
	 */
	public void fire() throws LineParserException {
		tamagocc.TamagoCC.setFlagNoServiceInterface(true);
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#immediateFire()
	 */
	public boolean immediateFire() {
		return true;
	}
}
