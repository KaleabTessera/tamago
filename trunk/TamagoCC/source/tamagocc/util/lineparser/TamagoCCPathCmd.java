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
public class TamagoCCPathCmd extends DefaultLineParserSpec {
	
	
	/**
	 * 
	 */
	public TamagoCCPathCmd() {
		super("--tamagoccpath","specify the path, where all CDL files are stocked (XML forms)","-p","--path","-tp");
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
		String[] strs = value.split(File.pathSeparator);
		for (String string : strs) {
			File directory = new File(string);
			if(!directory.exists()) {
				TamagoCCLogger.println(1,"The path does not exists : "+string);
				return;
			}
			if(!directory.isDirectory()) {
				TamagoCCLogger.println(1,"The path is not a directory : "+string);
				return;
			}
			TamagoCCPool.getDefaultPool().addTamagoCCListPath(string);
		}
	}

}
