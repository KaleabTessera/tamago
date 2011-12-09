/**
 * 
 */
package tamago.lineparser;

import tamagocc.exception.LineParserException;
import tamagocc.util.lineparser.DefaultLineParserSpec;
import tamagotest.TamagoTestContext;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoTestComponent extends DefaultLineParserSpec {

	private TamagoTestContext ctx;
	/**
	 * 
	 */
	public TamagoTestComponent(TamagoTestContext ctx) {
		super("--component","specify the kind of the entity and the fullname of the entity (ex: --component mycomponent.Component)","-c");
		this.ctx = ctx;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getArity()
	 */
	public int getArity() {
		return 1;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#immediateFire()
	 */
	public boolean immediateFire() {
		return true;
	}
	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#setArgument(int, java.lang.String)
	 */
	public void setArgument(int pos, String value) throws LineParserException {
		ctx.setComponent(value);
	}

}
