/**
 * 
 */
package tamagocc.util.lineparser;

import tamagocc.TamagoCC;
import tamagocc.exception.LineParserException;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCGenerator  extends DefaultLineParserSpec {

	
	/**
	 * 
	 */
	public TamagoCCGenerator() {
		super("--generator","Specify the full name of the generator, that define a target language","-g");
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
		TamagoCC.setGenerator(value);
	}

}
