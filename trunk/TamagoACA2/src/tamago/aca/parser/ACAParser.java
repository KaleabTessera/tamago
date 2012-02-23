package tamago.aca.parser;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javapop.framework.ParseResult;
import javapop.framework.generic.tool.GrammarGenerator;
import javapop.framework.generic.tool.GrammarGeneratorException;
import javapop.framework.input.StringParseInput;
import tamago.aca.generator.ACASecurityGenerator;
import tamago.aca.term.ACA;
import tamago.aca.visitor.ConvertACAtoCDL;
import tamagocc.api.TTamago;
import tamagocc.ast.api.AEntity;
import tamagocc.exception.TamagoCCException;
import tamagocc.generator.TamagoCCGeneratorTargetLanguage;
import tamagocc.generator.TamagoCCIGenerator;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.generic.impl.GIPercolator;
import tamagocc.javasource.TamagoCCJavaSourceBuilder;
import tamagocc.percolation.TamagoCCPercolation;
import tamagocc.util.TamagoCCToXml;

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
		TamagoCCPercolation.initialisation();
		
		
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
			
			ConvertACAtoCDL convCDL = new ConvertACAtoCDL(aca);
			convCDL.convert();
			
			GTamagoEntity entity = convCDL.getGEntity();
			TTamago tentity = convCDL.getTEntity();
			FileOutputStream fos = new FileOutputStream("DepositSecurity.xml");
			TamagoCCToXml.generateXMLFile(tentity, fos);
			
			ACASecurityGenerator gencontainer = new ACASecurityGenerator(entity);
			AEntity container = gencontainer.getASTEntity();
			
			TamagoCCJavaSourceBuilder builder = new TamagoCCJavaSourceBuilder();
			builder.generateContainerImplementationName(entity, new GIPercolator("plugin"));
			FileOutputStream genstream = new FileOutputStream("Container.java");
			TamagoCCGeneratorTargetLanguage language = builder.getTargetLanguage(container, genstream);
			language.generate();
			//TamagoCCGenerator generator = new TamagoCCGenerator(builder, entity, "examples", true, true, true);
			//generator.generate();
			
		}
	}
}
