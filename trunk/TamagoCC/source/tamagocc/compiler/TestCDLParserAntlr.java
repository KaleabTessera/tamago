package tamagocc.compiler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import tamagocc.TamagoCC;
import tamagocc.api.TTamago;
import tamagocc.api.TTamagoEntity;
import tamagocc.exception.LineParserException;
import tamagocc.exception.TamagoCCException;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.parser.CDLStopOnFirstError;
import tamagocc.parser.DefCDLFile;
import tamagocc.parser.ProduceXMLContract;
import tamagocc.percolation.TamagoCCPercolation;
import tamagocc.util.TamagoCCPool;
import tamagocc.util.TamagoCCToXml;
import tamagocc.util.lineparser.LineParser;
import tamagocc.util.lineparser.TamagoCCDest;
import tamagocc.util.lineparser.TamagoCCLogFile;
import tamagocc.util.lineparser.TamagoCCLogLevel;
import tamagocc.util.lineparser.TamagoCCNoServiceInterface;
import tamagocc.util.lineparser.TamagoCCNoSkeleton;
import tamagocc.util.lineparser.TamagoCCPathCmd;
import tamagocc.util.lineparser.TamagoCCPercolator;
import tamagocc.util.lineparser.TamagoCCSetXSD;

public class TestCDLParserAntlr {

		
	/**
	 * @param args
	 * @throws LineParserException 
	 * @throws IOException 
	 * @throws TamagoCCException 
	 */
	public static void main(String[] args) throws LineParserException, IOException, TamagoCCException {
		DefCDLFile def = new DefCDLFile();
		TamagoCCDest dest = new TamagoCCDest();
		ProduceXMLContract xml = new ProduceXMLContract();
		LineParser lineparser = new LineParser("tamagocdl", "Compiler of textual CDL File into XML CDL file and more (Use ANTLR compiler)");
		lineparser.addSpec(new TamagoCCLogFile());
		lineparser.addSpec(new TamagoCCLogLevel());
		lineparser.addSpec(dest);
		lineparser.addSpec(xml);
		lineparser.setDefaultSpec(def);
		lineparser.addSpec(new tamagocc.util.lineparser.TamagoCCGenerator());
		lineparser.addSpec(new TamagoCCPathCmd());
		lineparser.addSpec(new TamagoCCPercolator());
		lineparser.addSpec(new TamagoCCSetXSD());
		lineparser.addSpec(new TamagoCCNoSkeleton());
		lineparser.addSpec(new TamagoCCNoServiceInterface());
		CDLStopOnFirstError firsterror = new CDLStopOnFirstError();
		lineparser.addSpec(firsterror);
		
		lineparser.parse(args);
		
		def.addItem("Bucket.cdl");
		
		if(def.getFiles().size() == 0) {
			TamagoCCLogger.println(1, lineparser.toString());
		}
		else {
			ArrayList<TTamago> tast = new ArrayList<TTamago>();
			TamagoCCPercolation.initialisation();
			
			for (String string : def.getFiles()) {
				ANTLRFileStream input = new ANTLRFileStream(string);
				TamagoCDLLexer lexer = new TamagoCDLLexer(input);
				CommonTokenStream token = new CommonTokenStream(lexer);
				TamagoCDLParser parser = new TamagoCDLParser(token);
				TamagoCDLParser.tamagoEntity_return res;
				try {
					res = parser.tamagoEntity();
					if(res != null && res.value != null) {
						TTamagoEntity tamago = res.value;
						TamagoCCLogger.println(1,"compilation success");
						if(tamago instanceof TTamago) {
							TamagoCCPool.getDefaultPool().addEntry(tamago.getName(), tamago.getModule(), (TTamago)tamago);
							tast.add((TTamago) tamago);
						}
						else
							TamagoCCLogger.println(1, "Assembly not yet supported!");
					}
				} catch (RecognitionException e) {
					TamagoCCLogger.println(1, e.getMessage());
					if(firsterror.mustStopOnFirstError())
						return;
				}
				//TamagoCCLogger.println(1, "Number of errors: "+parser.getNumberOfSyntaxErrors());
			}
			
			for (TTamago t : tast) {
				if(xml.mustGenXML()) { 
					FileOutputStream fos = new FileOutputStream(dest.getOutputDir()+File.separator+t.getName()+".xml");
					TamagoCCToXml.generateXMLFile(t, fos);
				}
				else {
					try {
						TamagoCC.generate(t);
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	

	/*public static void main(String[] args) throws IOException {
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
	*/
}
