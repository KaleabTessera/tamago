package tamago.aca.parser;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import tamago.aca.term.ACA;
import tamago.aca.visitor.ConvertACAtoCDL;
import tamagocc.exception.TamagoCCException;

import javapop.framework.ParseResult;
import javapop.framework.generic.tool.GrammarGenerator;
import javapop.framework.generic.tool.GrammarGeneratorException;
import javapop.framework.input.StringParseInput;

public class ACAParser {
	private static String streamToString(String string) throws IOException {
		DataInputStream dis =  new DataInputStream(new FileInputStream(string));
		StringBuilder sb = new StringBuilder();
		byte[] b = new byte[2048];
		while((dis.read(b)) >= 0) {
			sb.append(new String(b));
		} ;
		return sb.toString();

	}

	public static void main(String[] args) throws GrammarGeneratorException, IOException, TamagoCCException {
		FileInputStream grammar = new FileInputStream("ACAGrammarPop.txt");
		GrammarGenerator gen = GrammarGenerator.buildGrammarGenerator(grammar);
		String saca = streamToString("examples/cdls/DepositSecurity.cdl");
		ParseResult<ACA> paca = (ParseResult<ACA>) gen.parse(new StringParseInput(saca));
		if(paca.isError() || paca.isNull()) {
			System.out.println(paca.getDetailedErrorMessage());
		}
		else {
			ACA aca = paca.getResult();
			System.out.println("OK");
			System.out.println(aca);
			
			ConvertACAtoCDL conv = new ConvertACAtoCDL(aca);
			conv.convert();
		}
	}
}
