/**
 * 
 */
package tamagocc.parser;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javapop.framework.DefaultParseContext;
import javapop.framework.ParseInput;
import javapop.framework.ParseResult;
import javapop.framework.generic.GenericGrammar;
import javapop.framework.generic.GrammarTree;
import javapop.framework.generic.tool.GrammarGenerator;
import javapop.framework.generic.tool.GrammarVisitorException;
import javapop.framework.input.StringParseInput;
import tamagocc.TamagoCC;
import tamagocc.api.TTamago;
import tamagocc.exception.LineParserException;
import tamagocc.exception.ParseException;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGPool;
import tamagocc.logger.TamagoCCLogger;
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

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCParserCDL {


	static GrammarGenerator gen;
	
	
	private static String streamToString(String string) throws IOException {
			DataInputStream dis =  new DataInputStream(new FileInputStream(string));
			StringBuilder sb = new StringBuilder();
			byte[] b = new byte[2048];
			while((dis.read(b)) >= 0) {
				sb.append(new String(b));
			} ;
			return sb.toString();
		
	}
	
	public static String parseInputFromStream(InputStream stream) throws IOException {
		DataInputStream dis =  new DataInputStream(stream);
		StringBuilder sb = new StringBuilder();
		byte[] b = new byte[2048];
		while((dis.read(b)) >= 0) {
			sb.append(new String(b));
		}
		return sb.toString();
	}

	public static void prepareCDLGrammar() {
		if(gen != null)
			return;
		try {
			GenericGrammar generic = new GenericGrammar();
			ParseInput input = CDLGrammarProvider.getCDLGrammar();
			/*// on recherche une ressource
			InputStream stream = cdlcontract.getClass().getResourceAsStream("CDLGrammarPop.txt");
			if(stream == null)
				cdlcontract = streamToString("CDLGrammarPop.txt");
			else
				cdlcontract = parseInputFromStream(stream);
			StringParseInput input = new StringParseInput(cdlcontract); */
			DefaultParseContext ctx = new DefaultParseContext();
			ParseResult<?> obj = generic.parse(ctx,input);
			if(obj.isError()) {
				System.out.println(ctx.deepest().getDetailedErrorMessage());
				System.out.println(ctx.deepest().getErrorMessage());
				return;
			}
			else if(obj.isNull()) {
				System.out.println("Error null node on the grammar");
				return;
			}
			else {
				gen = new GrammarGenerator();
				@SuppressWarnings("unchecked")
				List<GrammarTree> result = (List<GrammarTree>) obj.getResult();
				gen.init(result);
				
			}
		} catch (GrammarVisitorException e) {
			e.printStackTrace();
		}
	}
	
	public static TTamago parse(ParseInput input) throws ParseException {
		prepareCDLGrammar();
		ParseResult<?> tree = gen.parse(input);
		if(tree.isError()) {
			TamagoCCLogger.println(1,tree.toString());
			throw new ParseException("compilation failed :"+tree.toString());
		}
		else if(tree.isNull()){
			
			throw new ParseException("compilation end on a NULL node");
		}
		else {
			TTamago tamago = (TTamago) tree.getResult();
			TamagoCCPool.getDefaultPool().remove(tamago);
			TamagoCCGPool.getDefaultTamagoCCGPool().remove(tamago.getName(), tamago.getModule());
			TamagoCCPool.getDefaultPool().addEntry(tamago.getName(), tamago.getModule(), tamago);
			return tamago;
		}
		
	}
	
	/**
	 * @param args
	 * @throws LineParserException 
	 * @throws IOException 
	 * @throws TamagoCCException 
	 */
	public static void main(String[] args) throws LineParserException, IOException, TamagoCCException {
		prepareCDLGrammar();
		DefCDLFile def = new DefCDLFile();
		TamagoCCDest dest = new TamagoCCDest();
		ProduceXMLContract xml = new ProduceXMLContract();
		LineParser lineparser = new LineParser("tamagocdl", "Compiler of textual CDL File into XML CDL file and more");
		lineparser.addSpec(new TamagoCCLogFile());
		lineparser.addSpec(new TamagoCCLogLevel());
		lineparser.addSpec(dest);
		lineparser.addSpec(xml);
		lineparser.addSpec(new ShowGrammar());
		lineparser.setDefaultSpec(def);
		lineparser.addSpec(new tamagocc.util.lineparser.TamagoCCGenerator());
		lineparser.addSpec(new TamagoCCPathCmd());
		lineparser.addSpec(new TamagoCCPercolator());
		lineparser.addSpec(new TamagoCCSetXSD());
		lineparser.addSpec(new TamagoCCNoSkeleton());
		lineparser.addSpec(new TamagoCCNoServiceInterface());
		
		lineparser.parse(args);
		
		//def.addItem("Bucket.cdl");
		
		if(def.getFiles().size() == 0) {
			TamagoCCLogger.println(1, lineparser.toString());
		}
		else {
			ArrayList<TTamago> tast = new ArrayList<TTamago>();
			TamagoCCPercolation.initialisation();
			
			for (String string : def.getFiles()) {
				ParseInput input = new StringParseInput(streamToString(string));
				ParseResult<?> tree = gen.parse(input);
				if(tree.isError()) {
					TamagoCCLogger.println(1,tree.toString());
				}
				else if(tree.isNull()){
					TamagoCCLogger.println(1,"compilation end on a NULL node");
				}
				else {
					System.out.println("C OK");
					System.out.println(tree.getResult().getClass());
					System.out.println( tree.getResult());
					TTamago tamago = (TTamago) tree.getResult();
					TamagoCCPool.getDefaultPool().addEntry(tamago.getName(), tamago.getModule(), tamago);
					tast.add(tamago);
				}
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

}
