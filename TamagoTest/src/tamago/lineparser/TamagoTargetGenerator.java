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
public class TamagoTargetGenerator extends DefaultLineParserSpec {

	private TamagoTestContext ctx;
	
	/**
	 * 
	 */
	public TamagoTargetGenerator(TamagoTestContext ctx) {
		super("--generator","Specify the full name of the generator, that define a target language (use only in the generation mode)","-b");
		this.ctx = ctx;
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
		ctx.setGenerator(value);
	}

}
