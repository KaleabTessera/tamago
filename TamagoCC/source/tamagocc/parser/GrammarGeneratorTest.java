/**
 * 
 */
package tamagocc.parser;


import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


import tamagocc.util.TamagoCCMakeReadableGExpression;

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
	
	public static void main(String[] args) {
		setUpBeforeClass();
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
		testParseTarget("rel", "a < 1");
		testParseTarget("rel", "a == null");
		testParseTarget("rel", "(a+1) < 1");
		testParseTarget("preexpr", "a * 1.2");
		testParseTarget("postexpr", "a@pre");
		
		testParseTarget("bool", "true");
		testParseTarget("bool", "false");
		
		testParseTarget("int", "3");
		testParseTarget("int", "-3");
		
		testParseTarget("real", "3.0");
		testParseTarget("real", "-3.005");
		testParseTarget("real", "3e2");
		testParseTarget("real", "3e-2");
		
		testParseTarget("string", "\"toto\"");
		
//		testParseTarget("sprop", "#toto");
//		testParseTarget("sprop", "#toto[3+2]");
//		
//		testParseTarget("nil", "nil");
//		testParseTarget("nil", "null");
//		
//		testParseTarget("quant", "forall");
//		testParseTarget("quant", "exists");
//		
//		testParseTarget("in", "in");
//		testParseTarget("in", "IN");
//		
//		testParseTarget("cast", "(int) toto");
//		testParseTarget("cast", "|int| toto");
//		
//		testParseTarget("callarith", "sqrt(3,3)");
//		testParseTarget("callarith", "sqrt(3,3.2)");
//		
//		testParseTarget("quantcoll", "forall albert : int in toto { true }");
//		
//		testParseTarget("defaultState", "default state toto ;");
//		
//		testParseTarget("allow", "allow foo;");
//		
//		testParseTarget("state", "state toto { allow foo; }");
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
	public static void setUpBeforeClass() {
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
		catch (GrammarVisitorException e) {
			e.printStackTrace();
		}
	}

}
