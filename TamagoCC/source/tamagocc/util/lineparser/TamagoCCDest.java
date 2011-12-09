/**
 * 
 */
package tamagocc.util.lineparser;

import tamagocc.TamagoCC;
import tamagocc.exception.LineParserException;

/**
 * This Command represents the command for specify the destination directory. 
 * 
 * @author Hakim Belhaouari
 */
public class TamagoCCDest extends DefaultLineParserSpec{

	private String destdir;

	public TamagoCCDest() {
		super("--destination","Output directory","-d");
		destdir = ".";
	}
	
	public String getOutputDir() {
		return destdir;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#fire()
	 */
	public void fire() throws LineParserException {
		TamagoCC.setOutputDir(destdir);
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
		destdir = value;
	}
}
