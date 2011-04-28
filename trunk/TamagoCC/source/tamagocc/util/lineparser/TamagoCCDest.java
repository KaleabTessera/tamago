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
public class TamagoCCDest implements LineParserSpec {

	private String destdir;
	private String cmd;
	
	/**
	 * 
	 */
	public TamagoCCDest(String cmd) {
		destdir = ".";
		this.cmd = cmd;
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
	 * @see tamagocc.util.lineparser.LineParserSpec#getCommand()
	 */
	public String getCommand() {
		return cmd;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getDescription()
	 */
	public String getDescription() {
		return "Output directory";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#immediateFire()
	 */
	public boolean immediateFire() {
		return false;
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
		destdir = value;
	}

}
