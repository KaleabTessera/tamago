/**
 * 
 */
package tamago.check.lineparser;

import tamago.check.util.TamagoCheckContext;
import tamagocc.exception.LineParserException;
import tamagocc.util.lineparser.LineParserSpec;

/**
 * @author Hakim Belhaouari
 *
 */
public class CheckMaxDepth implements LineParserSpec {

	private TamagoCheckContext ctx;
	/**
	 * 
	 */
	public CheckMaxDepth(TamagoCheckContext ctx) {
		this.ctx = ctx;
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
		return "--maxdepth";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getDescription()
	 */
	
	public String getDescription() {
		return "force the maximum depth search in the behavior (unlimited=-1)";
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
		ctx.setMaxDepth(Integer.parseInt(value));
	}

}
