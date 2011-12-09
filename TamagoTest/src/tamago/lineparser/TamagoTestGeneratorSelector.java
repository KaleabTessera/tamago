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
public class TamagoTestGeneratorSelector extends DefaultLineParserSpec {

	private TamagoTestContext ctx;
	
	/**
	 * 
	 */
	public TamagoTestGeneratorSelector(TamagoTestContext ctx) {
		super("--report","Specify the generator of the abstract test suite in generation mode, or precise the entity that will execute/manipulate the abstract test suite in the execution mode","-r");
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
		ctx.setGenReport(value);
	}

}
