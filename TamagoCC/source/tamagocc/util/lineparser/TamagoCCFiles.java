/**
 * 
 */
package tamagocc.util.lineparser;

import java.util.ArrayList;

import tamagocc.exception.LineParserException;

/**
 * This class represents defaults arguments of the command line. In other words
 * it is represents all contract that Tamago-CC must compile.
 * 
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCFiles implements LineParserDefaultSpec {

	
	private ArrayList<String> files;
	
	/**
	 * 
	 */
	public TamagoCCFiles() {
		files = new ArrayList<String>();
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserDefaultSpec#addItem(java.lang.String)
	 */
	public void addItem(String item) {
		files.add(item);
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserDefaultSpec#canAddItem()
	 */
	public boolean canAddItem() {
		return true;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserDefaultSpec#canBeEmpty()
	 */
	public boolean canBeEmpty() {
		return true;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserDefaultSpec#fire()
	 */
	public void fire() throws LineParserException {
		
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserDefaultSpec#getArity()
	 */
	public int getArity() {
		return 0;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserDefaultSpec#getDescription()
	 */
	public String getDescription() {
		return "CDL Files which will be parsed by TamagoCC";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserDefaultSpec#getItemName()
	 */
	public String getItemName() {
		return "File";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserDefaultSpec#getItems()
	 */
	public Iterable<String> getItems() {
		return files;
	}

}
