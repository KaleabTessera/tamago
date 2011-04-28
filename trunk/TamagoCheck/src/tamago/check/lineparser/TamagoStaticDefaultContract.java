package tamago.check.lineparser;

import java.util.ArrayList;

import tamagocc.exception.LineParserException;
import tamagocc.util.lineparser.LineParserDefaultSpec;

/**
 * @author hakim
 *
 */
public class TamagoStaticDefaultContract implements LineParserDefaultSpec {

	private String filename;
	/**
	 * 
	 */
	public TamagoStaticDefaultContract() {
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserDefaultSpec#addItem(java.lang.String)
	 */
	public void addItem(String item) {
		filename = item;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserDefaultSpec#canAddItem()
	 */
	public boolean canAddItem() {
		return true;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserDefaultSpec#fire()
	 */
	public void fire() throws LineParserException {
		
	}
	
	public String getFilename() {
		return filename;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserDefaultSpec#getArity()
	 */
	public int getArity() {
		return 1;
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserDefaultSpec#getDescription()
	 */
	public String getDescription() {
		return "Make verification on the specified contract";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserDefaultSpec#getItemName()
	 */
	public String getItemName() {
		return "ContractCDL";
	}

	public Iterable<String> getItems() {
		ArrayList<String> renvoie = new ArrayList<String>();
		renvoie.add(filename);
		return renvoie;
	}

	public boolean canBeEmpty() {
		return false;
	}

}
