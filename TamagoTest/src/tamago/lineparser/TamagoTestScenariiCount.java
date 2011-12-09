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
public class TamagoTestScenariiCount extends DefaultLineParserSpec {

	private TamagoTestContext ctx;
	
	/**
	 * 
	 */
	public TamagoTestScenariiCount(TamagoTestContext ctx) {
		super("-quantity","Specify the count of generated scenarios","-q");
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
		ctx.setCount(Integer.parseInt(value));
	}

}
