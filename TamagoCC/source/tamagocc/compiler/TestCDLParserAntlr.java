package tamagocc.compiler;

import java.io.IOException;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import tamagocc.compiler.TamagoCDLParser.tamagoEntity_return;

public class TestCDLParserAntlr {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ANTLRFileStream file = new ANTLRFileStream("Bucket.cdl");
		TamagoCDLLexer lexer = new TamagoCDLLexer(file);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		TamagoCDLParser parser = new TamagoCDLParser(tokens);
		
		try {
			tamagoEntity_return tree = parser.tamagoEntity();
			System.out.println(tree.toString());
		} catch (RecognitionException e) {
			e.printStackTrace();
		}

	}

}
