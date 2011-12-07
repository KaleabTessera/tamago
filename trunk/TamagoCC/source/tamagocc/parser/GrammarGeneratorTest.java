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
import tamagocc.util.TamagoCCMakeReadableGExpression;
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
public class GrammarGeneratorTest {

	private static GrammarGenerator gen;
	
	public static void main(String[] args) throws LineParserException, IOException {
		prepareCDLGrammar();
		
		
		
//		System.out.println("3.230023".matches("(-)?[0-9]+((([.][0-9]+)|((e|E)[+-]?[0-9]+))|([.][0-9]+(e|E)[+-]?[0-9]+))?"));
//		System.out.println("3e10".matches    ("(-)?[0-9]+((([.][0-9]+)|((e|E)[+-]?[0-9]+))|([.][0-9]+(e|E)[+-]?[0-9]+))?"));
//		System.out.println("3.2e23".matches  ("(-)?[0-9]+((([.][0-9]+)|((e|E)[+-]?[0-9]+))|([.][0-9]+(e|E)[+-]?[0-9]+))?"));
//		System.out.println("\"toto\"".matches("\"[^\"]*\""));
//		
//		if(3%2 == 1)
//			throw new RuntimeException();
		testParseTarget("qualifident", "int int");
		
		
		// <ident>
		testParseTarget("ident", "bonjour");
		testParseTarget("ident", "Bonjour");
		testParseTarget("ident", "_bOplL");
		
		// <qualifident>
		testParseTarget("ident", "Bonjour");
		testParseTarget("ident", "Bonjour.toto.Loup");
		
		// <type>
		testParseTarget("type", "org.example.Loup");
		testParseTarget("type", "int");
		
		// <preexpr>
		testParseTarget("preexpr", "a");
		testParseTarget("arith", "a + 1");
		testParseTarget("arith", "a < 1");
		testParseTarget("arith", "a == null");
		testParseTarget("arith", "(a + 1) < 1"); // erreur
		testParseTarget("arith", "1 < (a + 1)"); // erreur
		
		testParseTarget("arith", "a * 1.2");
		//testParseTarget("rel", "a * 1.2");
		//testParseTarget("postexpr", "a@pre"); // erreur
		
		testParseTarget("bool", "true");
		testParseTarget("bool", "false");
		
		testParseTarget("int", "3");
		testParseTarget("int", "-3");
		
		testParseTarget("real", "3.0");
		testParseTarget("real", "-3.005");
		testParseTarget("real", "3e2");
		testParseTarget("real", "3e-2");
		
		testParseTarget("string", "\"toto\"");
		
		testParseTarget("prop", "#toto");
		testParseTarget("prop", "#toto[3+2]");
		
		testParseTarget("nil", "nil");
		testParseTarget("nil", "null");
		
		
		testParseTarget("in", "in");
		testParseTarget("in", "IN");
		
		testParseTarget("cast", "(int) toto");
		testParseTarget("cast", "|int| toto");
		
		testParseTarget("callarith", "sqrt(3,3)");
		testParseTarget("callarith", "sqrt(3,3.2)");
		
		testParseTarget("quant", "forall");
		testParseTarget("quant", "exists");
		
		testParseTarget("ident", "albert");
		
		testParseTarget("qualifident", "int");
		
		testParseTarget("ident", "toto");
		
		testParseTarget("expr", "true");
		
		
		testParseTarget("quantcoll", "forall albert:int in toto { true }");
		
		testParseTarget("quantset", "forall albert:int in [ 1 ,2, 3 ,4]{ true }");
		testParseTarget("quantset", "forall albert:int in [ 1 ] { true }");
		
		testParseTarget("quantrange", "forall albert:int in 0..10 { true }");
		
		testParseTarget("defaultState", "default state toto ;");
		
		testParseTarget("allow", "allow foo;");
		
		testParseTarget("state", "state toto { allow foo; }");
		
		testParseTarget("transition", "transition from start to end with func;");
		testParseTarget("transition", "transition from start to end with func when true;");
		
		testParseTarget("behavior", "behavior { default state toto; states { state toto { allow foo; } } transitions { transition from toto to toto with foo; } }");
		
		testParseTarget("method", "method void foo(int a) { id foo; pre false; post true; }");
		
		
		// Ici j'ai un probleme
		// TODO les expressions c la cata j'essaye de faire quelque chose de compliqué
		String file = streamToString("Bucket.cdl");
		testParseTarget("service", file);
		
		testParseTarget("expr", "0>2");
		
		testParseTarget("preexpr", "(0 > 2)");
		
	}

	private static String streamToString(String string) throws IOException {
		DataInputStream dis =  new DataInputStream(new FileInputStream(string));
		StringBuilder sb = new StringBuilder();
		byte[] b = new byte[2048];
		while((dis.read(b)) >= 0) {
			sb.append(new String(b));
		} ;
		return sb.toString();
	}

	/**
	 * @throws java.lang.Exception
	 */
	public static void prepareCDLGrammar() {
		try {
			GenericGrammar generic = new GenericGrammar();
			//generic.setMainParser(generic.fetch("keywords"));
			System.out.println(generic.toString());
			StringParseInput input = new StringParseInput(streamToString("CDLGrammarPop.txt"));
			DefaultParseContext ctx = new DefaultParseContext();
			ParseResult<?> obj = generic.parse(ctx,input);
			if(obj.isError()) {
				System.out.println(ctx.deepest().getDetailedErrorMessage());
				System.out.println(ctx.deepest().getErrorMessage());
				return;
			}
			else if(obj.isNull()) {
				System.out.println("C un NULL");
				return;
			}
			else {
				System.out.println(obj.getResult());
				gen = new GrammarGenerator();
				List<GrammarTree> result = (List<GrammarTree>) obj.getResult();
				gen.init(result);
				System.out.println("Grammar: "+gen);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GrammarVisitorException e) {
			e.printStackTrace();
		}
	}


	public static void testParseTarget(String ref,String sinput) {
		try {
			System.out.println("------------------------------");
			System.out.println("PARSE: "+sinput);
			System.out.println("TYPE: "+ref);
			ParseInput input = new StringParseInput(sinput);
			ParseResult<?> tree = gen.parseTarget(ref, input);
			if(tree.isError()) {
				System.out.println(tree.toString());
			}
			else if(tree.isNull()){
				System.out.println("NULL");
			}
			else {
				System.out.println("C OK");
				System.out.println(tree.getResult().getClass());
				System.out.println( tree.getResult());
			}
		} 
		catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

}
