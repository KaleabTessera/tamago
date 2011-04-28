package org.tamago.eclipse.cdl.compiler.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import javapop.framework.DefaultParseContext;
import javapop.framework.ParseContext;
import javapop.framework.ParseResult;
import javapop.framework.Parser;
import javapop.framework.input.StringParseInput;
import javapop.framework.parser.EOF;
import javapop.framework.parser.tuple.One;

import org.junit.Test;
import org.tamago.eclipse.cdl.compiler.CDLGrammar;

import tamagocc.api.TExpression;
import tamagocc.api.TNot;
import tamagocc.api.TOpeName;
import tamagocc.api.TOperator;

public class CDLGrammarTest extends CDLGrammar {

	
	private <T> ParseResult<T> get(Parser<T> p, String code) {
		return p.parse(new StringParseInput(code));
	}
	
	private <T> void error(ParseResult<T> res) {
		assertNotNull(res);
		assertTrue(res.isError());
	}
	private <T> ParseResult<T>  run(String parser, String code) {
		Parser<T> p = (Parser<T>) fetch(parser);
		Parser<T> p2 = new One<T>().parser(p).then(EOF.getInstance());
		ParseContext<?> ctx = new DefaultParseContext();
		ctx.skip(ref("skip"));
		ParseResult<T> res = p2.parse(ctx, new StringParseInput(code));
		return res;
	}
	
	
	private <T> void error(String parser, String code) {
		Parser<T> p = fetch(parser);
		Parser<T> p2 = new One<T>().parser(p).then(EOF.getInstance());
		ParseContext<?> ctx = new DefaultParseContext();
		ctx.skip(ref("skip"));
		ParseResult<T> res = p2.parse(ctx, new StringParseInput(code));
		if(res == null) {
			System.err.println("NULL ParseResult for code: "+code);
		}
		assertNotNull(res);
		boolean flag = res.isError();
		if(flag)
			System.err.println("ERR-OK: "+code+" => "+res.toString());
		else
			System.err.println("ERR-KO: "+code+" => "+res.toString());
		assertTrue(flag);
	}
	
	private <T> ParseResult<?> noerror(String parser, String code) throws RuntimeException {
		System.err.println("------------------------------------------------------------");
		Parser<T> p = fetch(parser);
		Parser<T> p2 = new One<T>().parser(p).then(EOF.getInstance());
		ParseContext<?> ctx = new DefaultParseContext();
		ctx.skip(ref("skip"));
		ParseResult<?> res = p2.parse(ctx, new StringParseInput(code));
		if(res == null) {
			System.err.println("NULL ParseResult for code: "+code);
		}
		assertNotNull(res);
		boolean flag = res.isError();
		if(!flag)
			System.err.println("PARSE-OK: "+code+" => "+res.toString());
		else
			System.err.println("PARSE-KO: "+code+" => "+res.toString());
		assertFalse(flag);
		return res;
	}
	
	private <T> void noerror(ParseResult<T> res) {
		assertNotNull(res);
		assertFalse(res.isError());
	}
	
	@Test
	public void printGrammar() {
		System.out.println(new CDLGrammar());
	}
	
	@Test
	public void testSpaces() {
		noerror("spaces","  \n\r\t");
	}
	
	@Test
	public void testSpacesBlank() {
		noerror("spaces", "");
	}
	
	@Test
	public void testSpacesError() {
		error("spaces","f");
	}
	
	@Test
	public void testqualifIdentSinglePackageName() {
		noerror("qualif-ident","toto");
	}
	
	@Test
	public void testqualifIdentClassName() {
		noerror("qualif-ident","monService");
	}
	
	@Test
	public void testqualifIdentFullClassname() {
		noerror("qualif-ident","toto.monService");
	}
	
	@Test
	public void testqualifIdentComplexPackageName() {
		noerror("qualif-ident","to.oto.org.fr");
	}
	@Test
	public void testqualifIdentWrongBeginName() {
		error("qualif-ident","2T.oto");
	}
	
	@Test
	public void testqualifIdentCorrectPackageNameWithNumber() {
		noerror("qualif-ident","T2.oto");
	}
	
	@Test
	public void testqualifIdentWrongPackageNameWithForbiddenCharacter() {
			error("qualif-ident","T..oto");
	}
	
	@Test
	public void testIdentSingleChar() {
		noerror("ident","a");
	}
	
	@Test
	public void testIdentMultiChars() {
		noerror("ident","myIdent");
	}
	
	@Test
	public void testIdentMultiCharsCaps() {
		noerror("ident","MyIdent");
	}
	
	@Test
	public void testIdentMultiCharsWithNumber() {
		noerror("ident","a8");
	}
	
	@Test
	public void testIdentMultiCharsWithNumberBeginUnderline() {
		noerror("ident","_a8");
	}
	
	@Test
	public void testIdentComplexCorrectIdent() {
		noerror("ident","_a8T_12");
	}
	
	@Test
	public void testIncorrectIdentStartNumber() {
		error("ident","2a");
	}
	
	@Test
	public void testQuant() {
		noerror("quant","forall");
		noerror("quant","FORALL");
		noerror("quant","EXISTS");
		noerror("quant","exists");
		error("quant","forAll");
		error("quant","toto");
	}
	
	@Test
	public void testString() {
		noerror("string","\"mystring\"");
		noerror("string","\"\"");
		noerror("string","\"ju fd fjdfd jfd\"");
		error("string","mystring");
		error("string","\"mystring");
		error("string","mystring\"");
	}
	
	@Test
	public void testAccess() {
		noerror("access","read");
		noerror("access","readwrite");
		noerror("access","write");
		error("access","raed");
		error("access","autre");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testType() {
		Parser<String> p  = this.<String>fetch("type"); 
		noerror(get(p,"toto"));
		noerror(get(p,"T.oto"));
		error(get(p,"2T.oto"));
		noerror(get(p,"T2.oto"));
	}
	
	@Test
	public void testTypeSimple() {
		noerror("type","Type");
	}
	
	@Test
	public void testImplements1() {
		noerror("implements","implements monService;");
	}
	@Test
	public void testImplements2() {
		noerror("implements","implements toto.monService;");
	}
	@Test
	public void testImplements3() {
		noerror("implements","implements monService;");
	}
	@Test
	public void testImplements4() {
		noerror("implements","implements     monService;");
	}
	@Test
	public void testImplements5() {
		noerror("implements","implementsmonService;");
	}

	// on les attends car Tamago-CC ne trouve pas le service donne
	// attention : le parser ne transmet pas les TamagoCCException
	@Test(expected=RuntimeException.class) 
	public void testRefineService() {
		run("refine","refine service Toto module monservice;");
	}
	
	// on les attends car Tamago-CC ne trouve pas le service donne
	// attention : pas de TamagoCCException
	@Test(expected=RuntimeException.class) 
	public void testIncludeService() {
		run("include","include service Toto module monservice;");
	}
	
	@Test
	public void testPropertyCorrect() {
		noerror("property","property read int entier;");
	}
	
	@Test
	public void testPropertyWrong() {
		error("property","property read int entier");
	}
	
	@Test
	public void testMethodIDCorrect() {
		noerror("method-id","id monid;");
	}
	@Test
	public void testMethodIDWrong() {
		error("method-id","id monid");
	}
	
	@Test
	public void testEmptyMethodOneLine() {
		noerror("method","method void foo() { }");
	}
	@Test
	public void testEmptyMethodMultiLines() {
		noerror("method","method void foo() {\n\t \n}");
	}
	
	@Test
	public void testInvariantSimple() {
		noerror("invariant","invariant true;");
	}
	
	@Test
	public void testInvariantSimpleWithMessage() {
		noerror("invariant","invariant true fail \"message\";");
	}
	@Test
	public void testInvariantWrongPostexpr1() {
		error("invariant","invariant var@pre;");
	}
	
	@Test
	public void testInvariantWrongPostexpr2() {
		error("invariant","invariant @return;");
	}
	
	@Test
	public void testPreconditionSimple() {
		noerror("precondition","pre true;");
	}
	@Test
	public void testPreconditionSimpleWithMessage() {
		noerror("precondition","pre true fail \"message\";");
	}
	@Test
	public void testPreconditionWrongPostexpr1() {
		error("precondition","pre var@pre;");
	}
	@Test
	public void testPreconditionWrongPostexpr2() {
		error("precondition","pre @return;");
	}
	
	@Test
	public void testPostconditionSimple() {
		noerror("postcondition","post true;");
	}
	@Test
	public void testPostconditionSimpleWithMessage() {
		noerror("postcondition","post true fail \"message\";");
	}
	@Test
	public void testPostconditionAtPostWrong() {
		error("postcondition","post var@post;");
	}
	@Test
	public void testPostconditionAtPre() {
		noerror("postcondition","post var@pre;");
	}
	@Test
	public void testPostconditionAtReturn() {
		noerror("postcondition","post @return;");
	}
	
	@Test
	public void testMethodId() {
		noerror("method","method void foo(int a, int b) {" +
				"id foo;" +
				"pre true;" +
				"post false;"+
				"}");
	}
	
	@Test
	public void testMethodIdPre() {
		noerror("method","method void foo(int a, int b) {" +
				"id foo;" +
				"pre true;" +
				"}");
	}
	
	@Test
	public void testMethodIdPost() {
		noerror("method","method void foo(int a, int b) {" +
				"id foo;" +
				"post false;"+
				"}");
	}
	
	@Test
	public void testMethodIdPrePost() {
		noerror("method","method void foo(int a, int b) {" +
				"id foo;" +
				"pre true;" +
				"}");
	}
	
	
	@Test
	public void testMethodPrePost() {
		noerror("method","method void foo(int a, int b) {" +
				"pre true;" +
				"post false;"+
				"}");
	}
	
	@Test
	public void testDefaultState() {
		noerror("default-state","default state ident;");
	}
	
	@Test
	public void testAllow() {
		noerror("allow","allow foo;");
		error("allow","allow foo");
	}
	
		
	@Test
	public void testStateOneAllowed() {
		noerror("state","state init { allow foo; }");
	}
	@Test
	public void testStateFinal() {
		noerror("state","state init { }");
	}
	@Test
	public void testStateWrongNoIdent() {
		error("state","state  { }");
	}
	@Test
	public void testStateWrongAllowedSequence() {
		error("state","state  init { all; }");
	}
	
	@Test
	public void testStatesEmpty() {
		noerror("states","states { } ");
	}
	@Test
	public void testStatesMultiState() {
		noerror("states","states { state init { } state end { }} ");
	}
	@Test
	public void testStatesErrorMissLeftBracket() {
		error("states","states  } ");
	}
	
	@Test
	public void testTransitionUnconditionnal() {
		noerror("transition","transition from start to end with foo;");
	}
	@Test
	public void testTransitionConditionnal() {
		noerror("transition","transition from start to end with foo when true;");
	}
	
	@Test
	public void testTransitionsEmpty() {
		noerror("transitions","transitions { }");
	}
	@Test
	public void testTransitionsTwoTransition() {
		noerror("transitions","transitions { transition from start to end with foo; transition from start to end with foo2; }");
	}
	@Test
	public void testTransitionsMissRightBracket() {
		error("transitions","transitions { ");
	}
	@Test
	public void testTransitionsMissLeftBracket() {
		error("transitions","transitions }} ");
	}
	
	@Test
	public void testBehaviorEmpty() {
		noerror("behavior","behavior { default state init; states { } transitions { } }");
	}
	@Test
	public void testBehaviorEmptyWithoutDefaultState() {
		noerror("behavior","behavior { states { } transitions { } }");
	}

	@Test
	public void testParameter() {
		noerror("parameter","int entier");
	}
	
	@Test
	public void testParametersWithOneParameter() {
		noerror("parameters","int entier");
	}
	@Test
	public void testParametersWith2Parameters() {
		noerror("parameters","int entier, bool foo");
	}
	@Test
	public void testParametersWithThreeParameters() {
		noerror("parameters","int entier, bool foo, char s");
	}
	@Test
	public void testParametersWithTwoParametersMissComma() {
		error("parameters","int entier int foo");
	}
	
	/// --------- Test des expressions
	
	@Test
	public void testInteger123() {
		noerror("int","123");
	}
	@Test
	public void testInteger0() {
		noerror("int","0");
	}
	@Test
	public void testIntegerNeg0() {
		noerror("int","-0");
	}
	@Test
	public void testIntegerReal() {
		error("int","0.2");
	}
	@Test
	public void testInteger2L() {
		error("int","2L");
	}
	
	@Test
	public void testBoolTrue() {
		noerror("bool","true");
	}
	@Test
	public void testBoolFalse() {
		noerror("bool","false");
	}
	@Test
	public void testBoolWrongTypeInt() {
		error("bool","0");
	}
	@Test
	public void testBoolWrongTypeReal() {
		error("bool","0.4");
	}
	@Test
	public void testBoolTrueScheme() {
		error("bool","#t");
	}
	@Test
	public void testBoolFalseScheme() {
		error("bool","#f");
	}
	
	@Test
	public void testRealSimple() {
		noerror("real","0.4");
	}
	@Test
	public void testRealExponent() {
		noerror("real","0.4e9");
	}
	@Test
	public void testRealNegExponent() {
		noerror("real","0.4e-9");
	}
	@Test
	public void testRealNeg() {
		noerror("real","-4.e-9");
	}
	@Test
	public void testRealHexNumber() {
		error("real","0x874");
	}
	
	@Test
	public void testNil() {
		noerror("nil","nil");
		noerror("nil","null");
		error("nil","NULL");
	}
	
	@Test
	public void testReturn() {
		noerror("return","@return");
		error("return","return");
		error("return","@result");
	}
	
	@Test
	public void testCastAlt1() {
		noerror("cast","|Type|myvar");
	}
	
	@Test
	public void testCastAlt2() {
		noerror("cast","(Type)myvar");
	}
	
	@Test
	public void testCastIncorrect() {
		error("cast","<Type>true");
	}
	
	@Test
	public void testCastIncorrect2() {
		noerror("cast","(Type)true");
	}

	@Test
	public void testReadSimple() {
		noerror("read","#ident");
	}
	@Test
	public void testReadSimpleIndex() {
		noerror("read","#ident[0]");
	}
	@Test
	public void testReadComplexIndex() {
		noerror("read","#ident[0+89]");
	}
	@Test
	public void testReadWithoutSharp() {
		error("read","ident");
	}
	@Test
	public void testReadBooleanIndex() {
		error("read","#ident[true]");
	}
	
	@Test
	public void testVarSimple() {
		noerror("var","ident");
	}
	@Test
	public void testVarSimpleIndex() {
		noerror("var","ident[0]");
	}
	
	@Test
	public void testVarComplexIndex() {
		noerror("var","ident[0+89]");
	}
	@Test
	public void testVarWithSharp() {
		error("var","#ident");
	}
	@Test
	public void testVarBooleanIndex() {
		error("var","ident[true]");
	}
	
	@Test
	public void testCallWithoutArgs() {
		noerror("call","foo()");
	}
	@Test
	public void testCallOneArg() {
		noerror("call","foo(a)");
	}
	@Test
	public void testCallTwoArgs() {
		noerror("call","foo(a,b)");
	}
	
	@Test
	public void testInlabelVar_Call() {
		noerror("inlabel","var.foo()");
	}

	@Test
	public void testInlabelVar_Attr() {
		noerror("inlabel","var.object");
	}

	@Test
	public void testInlabelVarIndex_Attr() {
		noerror("inlabel","var[i].object");
	}
	@Test
	public void testInlabelPropIndex_Attr() {
		noerror("inlabel","#var[i].object");
	}

	@Test
	public void testInlabelCall_Attr() {
		error("inlabel","foo().object");
	}
	@Test
	public void testInlabelLit_Call() {
		error("inlabel","3.toString()");
	}

	@Test
	public void testInlabelArithExpr_Call() {
		error("inlabel","(3+1).toString()");
	}

	@Test
	public void testQSetSimple() {
		noerror("quantset","forall i:int in [3 , 5 , 1] { i < 10 }");
	}
	
	@Test
	public void testQSetWrongSemiColonInBody() {
		error("quantset","forall i:int in [3 , 5 , 1] { (i < 10); }");
	}
	
	@Test
	public void testQSetWrongDeclLoopVar() {
		error("quantset","forall (i:int) in [3 , 5 , 1] { (i < 10) }");
	}
	
	@Test
	public void testQRange() {
		noerror("quantrange","forall i:int in 0..3 { foo(i) }");
		error("quantrange","forall i:int in 0..3 { foo(i); }");
		error("quantrange","exists i:int in 0..true { foo(i); }");
	}
	
	@Test
	public void testQColl() {
		noerror("quantcoll","exists i:int in array { foo(i) }");
		noerror("quantcoll","exists i:int in calc() { foo(i) }");
	}
	
	@Test
	public void testQCollR() {
		noerror("quantcoll","forall g:int in grille {(0 < g) && (g <= 9)}");
	}
	
	@Test 
	public void testServiceEmpty() {
		noerror("service-parser","module toto.org; service Service { }");
	}
	
	@Test 
	public void testService1() {
		noerror("service-parser","module toto.org; service Service { property read int entier; method void simple() { id simple; }  }");
	}
	
	@Test(expected=RuntimeException.class)
	public void testProvide() {
		noerror("provide","provide service MyService in tamago.exec.ml;");
	}
	
	@Test(expected=RuntimeException.class)
	public void testRequire() {
		noerror("require","require service MyService in tamago.exec.ml as myservice;");
	}
	
	@Test
	public void testPercolator() {
		noerror("percolator","percolator plugin;");
	}
	
	@Test
	public void testPercolators() {
		noerror("percolator","percolator plugin,exact;");
	}
	
	@Test
	public void testPercolatorsSpaces() {
		noerror("percolator","percolator plugin, exact;");
	}
	
	@Test
	public void testPercolatorAll() {
		noerror("percolator","percolator *;");
	}
	
	@Test
	public void testPercolatorError() {
		error("percolator","percolator *, plugin;");
		error("percolator","percolator plugin,*;");
	}
	
	@Test
	public void testExpressionLogic0() {
		noerror(PREEXPR,"true");
	}
	
	@Test
	public void testExpressionLogic1() {
		noerror(PREEXPR,"(q >= 0) && true");
	}
	
	@Test
	public void testExpressionLogic2() {
		noerror(PREEXPR,"#quantity >= 0");
	}
	
	@Test
	public void testExpressionLogic5() {
		noerror(PREEXPR,"(q >= 0) && (#quantity >= 0)");
	}
	
	@Test
	public void testExpressionLogic6() {
		noerror(PREEXPR,"true || 5 != 6");
	}
	

	@Test
	public void testExpressionLogic7() {
		noerror(PREEXPR,"5 != 6");
	}
	
	@Test
	public void testCastAsRelExpr() {
		noerror(REL_EXPR,"(Type)myvar");
	}
	
	@Test
	public void testCastAsArithExpr() {
		noerror(ARITH_EXPR,"(Type)myvar");
	}
	
	@Test
	public void testExpressionLogic3pre() {
		noerror(PREEXPR,"(#quantity + q == #quantity)");
	}
	
	@Test
	public void testExpressionArithPost1() {
		noerror(POST_ARITH_EXPR,"(#quantity)@pre");
	}
	
	@Test
	public void testExpressionArithPost2() {
		noerror(POST_ARITH_EXPR,"((#quantity)@pre) + 1");
	}

	@Test
	public void testExpressionArithPost3() {
		noerror(POST_ARITH_EXPR,"1 + ((#quantity)@pre)");
	}

	
	@Test
	public void testExpressionRelPost1() {
		noerror(POST_ARITH_EXPR,"#quantity@pre");
	}
	
	@Test
	public void testExpressionRelPost2() {
		noerror(POST_LOGIC_EXPR,"#quantity@pre < 1");
	}

	@Test
	public void testExpressionRelthPost3() {
		noerror(POST_LOGIC_EXPR,"1 != #quantity@pre");
	}


	@Test
	public void testExpressionLogic3() {
		noerror(POSTEXPR,"(#quantity@pre + q == #quantity)");
	}
	@Test
	public void testExpressionLogic4() {
		noerror(POSTEXPR,"(#quantity@pre == #quantity - q)");
	}
	
	@Test
	public void testComponent() {
		noerror("component-parser","module tamago.tme;\ncomponent BucketImpl {\n\t"+
						"\n"+ "}");//provide service Bucket in tamago.tme;
	}
	
	@Test
	public void testNotExpr() {
		// Cas du probleme de l'operateur prefixe not
		// l'arbre obtenu ne contient pas de TNot
		ParseResult<?> res = noerror(PREEXPR,"!#isEmpty");
		assertTrue(res.getResult() instanceof TNot);
		
	}
	
	@Test
	public void testNotExpression() {
		// Cas du probleme de l'operateur prefixe not
		// l'arbre obtenu ne contient pas de TNot
		ParseResult<?> res = noerror(PREEXPR,"((!#isEmpty) && (#balance != 0))");
		TOperator ope = (TOperator)res.getResult();
		System.err.println("ope="+ope.getOperator().getOperator()+" ");
		Iterator<TExpression> exprs = ope.getOperands();
		TExpression read = exprs.next();
		TExpression eg = exprs.next();
		assertTrue(eg instanceof TOperator);
		assertTrue(((TOperator)eg).getOperator().getID() == TOpeName.NE);
		assertTrue(read instanceof TNot);
		
	}
	
	@Test
	public void testInvariantEchoue() {
		// cas a priori l'erreur est du a un probleme dans la grammaire
		//noerror("invariant","invariant (#quantity >= 0) fail \"negative quantity\";\n\t"+
		//	"invariant (#isEmpty <--> (#quantity == 0)) fail \"ambiguity between properties\";");
	}
	
	
	@Test
	public void testTypeArray() {
		noerror("qualif-ident","int[]");
	}
	
	@Test
	public void testPropertyArray() {
		noerror("property","property read int[] elements;");
	}
	
	@Test
	public void testQuantificateur() {
		noerror(LOGIC_EXPR,"(forall g:int in grille {(0 < g) && (g <= 9) })");
	}
	
	@Test
	public void testQuantificateurComplex() {
		noerror(LOGIC_EXPR,"(forall g:int in grille {(0 < g) && (g <= 9) }) && (81 == grille.length)");
	}
	
	@Test
	public void testComplexQuantificateur() {
		noerror(LOGIC_EXPR,"(81 == grille.length) && (forall g:int in grille {(0 < g) && (g <= 9) })");
	}
	
	@Test
	public void testComplexQuantificateurSansParen() {
		noerror(LOGIC_EXPR,"(81 == grille.length) && forall g:int in grille {(0 < g) && (g <= 9) }");
	}
}
