package tamagocc.parser;

import java.util.ArrayList;

import tamagocc.exception.LineParserException;
import tamagocc.util.lineparser.LineParserDefaultSpec;

public class DefCDLFile implements LineParserDefaultSpec {
	ArrayList<String> files = new ArrayList<String>();
	@Override
	public String getDescription() {
		return "textual CDL contract";
	}

	@Override
	public String getItemName() {
		return "File.cdl";
	}

	@Override
	public boolean canAddItem() {
		return true;
	}

	@Override
	public void addItem(String item) {
		files.add(item);
	}

	@Override
	public void fire() throws LineParserException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getArity() {
		return 0;
	}

	@Override
	public boolean canBeEmpty() {
		return true;
	}
	
	
	public ArrayList<String> getFiles() {
		return files;
	}
}