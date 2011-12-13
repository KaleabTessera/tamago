package tamagocc.parser;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import javapop.framework.ParseInput;
import javapop.framework.input.StringParseInput;

public final class CDLGrammarProvider {

	private static StringParseInput grammar;
	
	private CDLGrammarProvider() {
		
	}
	
	public static boolean setCDLGrammar(InputStream input) {
		grammar = null;
		try {
			grammar = new StringParseInput(parseInputFromStream(input));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static ParseInput getCDLGrammar() {
		if(grammar == null) {
			InputStream stream = CDLGrammarProvider.class.getResourceAsStream("CDLGrammarPop.txt");
			try {
				grammar = new StringParseInput(parseInputFromStream(stream));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return grammar;
	}
	
	
	private static String parseInputFromStream(InputStream stream) throws IOException {
		DataInputStream dis =  new DataInputStream(stream);
		StringBuilder sb = new StringBuilder();
		byte[] b = new byte[2048];
		while((dis.read(b)) >= 0) {
			sb.append(new String(b));
		}
		return sb.toString();
	}
}
