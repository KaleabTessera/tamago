package tamago.lineparser;

import java.util.ArrayList;
import java.util.Collection;

import tamagocc.exception.LineParserException;
import tamagocc.util.lineparser.LineParserDefaultSpec;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoStaticDefaultContract implements LineParserDefaultSpec {

	private ArrayList<String> filenames;
	/**
	 * 
	 */
	public TamagoStaticDefaultContract() {
		filenames = new ArrayList<String>();
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserDefaultSpec#addItem(java.lang.String)
	 */
	public void addItem(String item) {
		filenames.add(item);
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
	
	public Collection<String> getFilenames() {
		return filenames;
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
		return "Generate test for the specified contract or Apply a Generator on the specified abstract harness";
	}

	/**
	 * @see tamagocc.util.lineparser.LineParserDefaultSpec#getItemName()
	 */
	public String getItemName() {
		return "ContractCDL";
	}

	public Iterable<String> getItems() {
		return filenames;
	}

	public boolean canBeEmpty() {
		return false;
	}

}
