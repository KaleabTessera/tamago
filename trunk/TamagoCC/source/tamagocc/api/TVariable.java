package tamagocc.api;

import tamagocc.api.TExpression;;

/**
 * This interface is used in assertion to represent a variable.
 * @author Hakim Belhaouari
 */
public interface TVariable extends TExpression, TIndexExpression {
	
	/**
	 * @return Return the name of the variable
	 */
	public String getVariable();
	
	/**
	 * Indicates if the type of this variable is defined in the file description
	 * @return Return true when the type is defined, and false else. 
	 */
	public boolean forcedType();
	
	/**
	 * Contains the type of the variable. Caution this variable is correct when the boolean
	 * forcedType is true, else the type is defined to Object
	 * @return
	 */
	public TType getType();
	
	boolean hasIndex();
	
	TExpression getIndex();
}
