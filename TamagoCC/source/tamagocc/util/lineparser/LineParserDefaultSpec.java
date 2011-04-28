package tamagocc.util.lineparser;

import tamagocc.exception.LineParserException;

/**
 * This class represents default argument of the command line.
 * The default arguments represents all information which does not depends of
 * any command. 
 * 
 * @author Hakim Belhaouari
 *
 */
public interface LineParserDefaultSpec {
	/**
	 * Return the description of elements which must be added to default elements
	 * @return Return the description of elements which must be added to default elements
	 */
	String getDescription();
	
	/**
	 * Return a name of elements
	 * @return Return a name of elements
	 */
	String getItemName();
	
	/**
	 * Return true if the command line can have some items or none
	 * @return Return true if the command line can have some items or none
	 */
	boolean canAddItem();
	
	/**
	 * This method add a new item identified by the {@link LineParser}
	 * @param item
	 */
	void addItem(String item);
	
	/**
	 * This method is the main function in this interface. It is called by the {@link LineParser}
	 * when the command line parsing is finish. The user can make now all he wants.
	 * 
	 * @throws LineParserException
	 */
	void fire() throws LineParserException;
	
	/**
	 * Specify the arity of unkown item. Specify 0 if you want infinite arguments
	 * @return Specify the arity of unkown item.
	 */
	int getArity();
	
	/**
	 * Indicates if we can have zero default argument, in the application. In this case the {@link LineParser}
	 * won't call the <em>fire</em> method.
	 * 
	 * @return
	 */
	boolean canBeEmpty();
}
