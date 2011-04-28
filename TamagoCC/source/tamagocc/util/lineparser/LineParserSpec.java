/**
 * 
 */
package tamagocc.util.lineparser;

import tamagocc.exception.LineParserException;

/**
 * This interface correspond to a command for the {@link LineParser} class.
 * In the current implementation a command can not have alias of his name, and
 * of course any command has a finite number of arguments
 *  
 * @version 1.0
 * @author Hakim Belhaouari
 *
 */
public interface LineParserSpec {
	/**
	 * Return the name of the command (i.e. '-h' or '--help')
	 * @return Return the name
	 */
	String getCommand();
	
	/**
	 * Specify the arity of the current command. In the current implementation
	 * a command must have a finite number of arguments.
	 * 
	 * @return
	 */
	int getArity();
	
	/**
	 * This method is called by the {@link LineParser} with correct information.
	 * It fulfills argument of this command and must be stored.
	 * By specification the argument are not ordered, and the order is given by the line parser
	 * object.
	 * 
	 * @param pos : specify the position where the line parser find the argument
	 * @param value : the value of the argument 
	 * @throws LineParserException
	 */
	void setArgument(int pos,String value) throws LineParserException;
	
	/**
	 * Indicates information of the current command. It is used for to generate help of this command.
	 * @return Return the description of this command.
	 */
	String getDescription();
	
	/**
	 * When the command finished to get those arguments. the line parser object call this method.
	 * This method can be called immediatly when all arguments has been fulfilled or when the line parser
	 * finished to parse all arguments. The behavior depends of the boolean given by the immediateFire
	 * method.
	 * @throws LineParserException TODO
	 */
	void fire() throws LineParserException;
	
	/**
	 * Indicates if the command must be called when all arguments has been fulfilled.
	 * @return <b>true</b> if the command need immediate fire, else <b>false</b>
	 */
	boolean immediateFire();
	
	
	/**
	 * Indicates if the current command is optional or required in the application.
	 * @return <b>true</b> if the command is optional, and <b>false</b> if it is required.
	 */
	boolean isOptionnal();
}
