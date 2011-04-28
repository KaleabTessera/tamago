package tamagocc;

import tamagocc.exception.LineParserException;
import tamagocc.exception.TamagoCCException;
import tamagocc.logger.TamagoCCLogger;

//import tamagocc.util.*;
//import tamagocc.api.*;

/**
 * This class is the launcher of the Tamago Contract Compiler (TamagoCC)/ 
 * @author Hakim BELHAOUARI
 */
public class TamagoCCMain {

	/**

	 * @param args
	 */
    public static void main(String[] args) {
    	try {
    		TamagoCC.initialize(args);
    		TamagoCC.run();
    	}
    	catch(TamagoCCException exception) {
    		TamagoCCLogger.println(1,"the initialisation of TamagoCC fail.");
    		TamagoCCLogger.infoln(exception);
    		System.exit(4);
    	} catch (LineParserException e) {
    		TamagoCCLogger.println(1,"Parsing of the command line fail.");
    		TamagoCCLogger.infoln(e);
    		System.exit(5);
		}
    }
}
