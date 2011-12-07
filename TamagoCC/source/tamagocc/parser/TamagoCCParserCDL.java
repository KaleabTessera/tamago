/**
 * 
 */
package tamagocc.parser;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import tamagocc.exception.LineParserException;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.lineparser.LineParser;
import tamagocc.util.lineparser.TamagoCCDest;
import tamagocc.util.lineparser.TamagoCCLogFile;
import tamagocc.util.lineparser.TamagoCCLogLevel;
import tamagocc.util.lineparser.TamagoCCNoServiceInterface;
import tamagocc.util.lineparser.TamagoCCNoSkeleton;
import tamagocc.util.lineparser.TamagoCCPathCmd;
import tamagocc.util.lineparser.TamagoCCPercolator;
import tamagocc.util.lineparser.TamagoCCSetXSD;

import javapop.framework.DefaultParseContext;
import javapop.framework.ParseInput;
import javapop.framework.ParseResult;
import javapop.framework.generic.GenericGrammar;
import javapop.framework.generic.GrammarTree;
import javapop.framework.generic.tool.GrammarGenerator;
import javapop.framework.generic.tool.GrammarVisitorException;
import javapop.framework.input.StringParseInput;

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

	public static void prepareCDLGrammar() {
		try {
			GenericGrammar generic = new GenericGrammar();
			StringParseInput input = new StringParseInput(streamToString("CDLGrammarPop.txt"));
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
				List<GrammarTree> result = (List<GrammarTree>) obj.getResult();
				gen.init(result);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GrammarVisitorException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * @param args
	 * @throws LineParserException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws LineParserException, IOException {
		prepareCDLGrammar();
		DefCDLFile def = new DefCDLFile();
		
		/*LineParser lineparser = new LineParser("tamagocdl", "Compiler of textual CDL File into XML CDL file and more");
		lineparser.addSpec(new TamagoCCLogFile());
		lineparser.addSpec(new TamagoCCLogLevel());
		lineparser.addSpec(new TamagoCCDest("--d"));
		lineparser.addSpec(new TamagoCCDest("--destination"));
		lineparser.addSpec(new ShowGrammar("--show-grammar"));
		lineparser.addSpec(new ShowGrammar("-grammar"));
		lineparser.setDefaultSpec(def);
		lineparser.addSpec(new tamagocc.util.lineparser.TamagoCCGenerator());
		lineparser.addSpec(new TamagoCCPathCmd());
		lineparser.addSpec(new TamagoCCPercolator());
		lineparser.addSpec(new TamagoCCSetXSD());
		lineparser.addSpec(new TamagoCCNoSkeleton());
		lineparser.addSpec(new TamagoCCNoServiceInterface());
		
		lineparser.parse(args);
		*/
		def.addItem("Bucket.cdl");
		
		if(def.getFiles().size() == 0) {
			//TamagoCCLogger.println(1, lineparser.toString());
		}
		else {
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
				}
			}
		}
	}

}
