/**
 * 
 */
package tamagocc.util.lineparser;

import java.io.File;

import tamagocc.exception.LineParserException;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.TamagoCCPool;

/**
 * @author hakim
 *
 */
public class TamagoCCPathCmd implements LineParserSpec {
	
	
	/**
	 * 
	 */
	public TamagoCCPathCmd() {
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#fire()
	 */
	public void fire() throws LineParserException {
		
	}
	public boolean isOptionnal() {
		return true;
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
		return "--tamagoccpath";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#getDescription()
	 */
	public String getDescription() {
		return "specify the path, where all CDL files are stocked"; 
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#immediateFire()
	 */
	public boolean immediateFire() {
		return false;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserSpec#setArgument(int, java.lang.String)
	 */
	public void setArgument(int pos, String value) throws LineParserException {
		File directory = new File(value);
		if(!directory.exists()) {
			TamagoCCLogger.println(1,"The path does not exists : "+value);
			return;
		}
		
		if(!directory.isDirectory()) {
			TamagoCCLogger.println(1,"The path is not a directory : "+value);
			return;
		}
		
		TamagoCCPool.getDefaultPool().addTamagoCCListPath(value);
	}

}
