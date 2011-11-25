/**
 * 
 */
package org.tamago.eclipse.cdl.compiler;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javapop.framework.DefaultParseContext;
import javapop.framework.Grammar;
import javapop.framework.ParseContext;
import javapop.framework.ParseInput;
import javapop.framework.ParseResult;
import javapop.framework.Parser;
import javapop.framework.input.StringParseInput;
import javapop.framework.parser.Char;
import javapop.framework.parser.Choice;
import javapop.framework.parser.EOF;
import javapop.framework.parser.Forget;
import javapop.framework.parser.ForgetAll;
import javapop.framework.parser.LineComment;
import javapop.framework.parser.ListOf;
import javapop.framework.parser.Literal;
import javapop.framework.parser.Many;
import javapop.framework.parser.Maybe;
import javapop.framework.parser.MaybeOrDefault;
import javapop.framework.parser.RegexpString;
import javapop.framework.parser.Reserved;
import javapop.framework.parser.WhiteSpace;
import javapop.framework.parser.XForm;
import javapop.framework.parser.expr.ExprAssoc;
import javapop.framework.parser.expr.Expression;
import javapop.framework.parser.expr.PostfixNode;
import javapop.framework.parser.expr.PrefixNode;
import javapop.framework.parser.tuple.Five;
import javapop.framework.parser.tuple.Four;
import javapop.framework.parser.tuple.Nine;
import javapop.framework.parser.tuple.One;
import javapop.framework.parser.tuple.Six;
import javapop.framework.parser.tuple.Ten;
import javapop.framework.parser.tuple.Three;
import javapop.framework.parser.tuple.Two;
import javapop.framework.parser.tuple.Zero;
import javapop.utils.Decuple;
import javapop.utils.Nonuple;
import javapop.utils.Pair;
import javapop.utils.Quadruple;
import javapop.utils.Quintuple;
import javapop.utils.Sixuple;
import javapop.utils.Triple;

import org.tamago.eclipse.cdl.compiler.cdlast.CDLArithExpression;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLAtPre;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLBool;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLCall;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLCast;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLExpression;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLInLabel;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLInfix;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLInteger;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLMinus;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLNil;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLNot;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLQColl;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLQRange;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLQSet;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLRead;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLReal;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLRelExpression;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLReturn;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLString;
import org.tamago.eclipse.cdl.compiler.cdlast.CDLVariable;

import tamagocc.api.TAccess;
import tamagocc.api.TAllow;
import tamagocc.api.TBehavior;
import tamagocc.api.TComponent;
import tamagocc.api.TCondition;
import tamagocc.api.TExpression;
import tamagocc.api.TExtendService;
import tamagocc.api.TImplements;
import tamagocc.api.TIncludeService;
import tamagocc.api.TInvariant;
import tamagocc.api.TMethod;
import tamagocc.api.TNamespace;
import tamagocc.api.TParameter;
import tamagocc.api.TPercolator;
import tamagocc.api.TProperty;
import tamagocc.api.TProvide;
import tamagocc.api.TRefineService;
import tamagocc.api.TRequire;
import tamagocc.api.TService;
import tamagocc.api.TState;
import tamagocc.api.TTamago;
import tamagocc.api.TTransition;
import tamagocc.api.TType;
import tamagocc.exception.TamagoCCException;
import tamagocc.impl.TIAccess;
import tamagocc.impl.TIAllow;
import tamagocc.impl.TIBehavior;
import tamagocc.impl.TICategory;
import tamagocc.impl.TIComposant;
import tamagocc.impl.TICondition;
import tamagocc.impl.TIImplements;
import tamagocc.impl.TIIncludeService;
import tamagocc.impl.TIInvariant;
import tamagocc.impl.TIMethod;
import tamagocc.impl.TINoContract;
import tamagocc.impl.TIParameter;
import tamagocc.impl.TIPercolator;
import tamagocc.impl.TIProperty;
import tamagocc.impl.TIProvide;
import tamagocc.impl.TIRefineService;
import tamagocc.impl.TIRequire;
import tamagocc.impl.TIService;
import tamagocc.impl.TIState;
import tamagocc.impl.TITransition;
import tamagocc.impl.TIType;
import tamagocc.util.TamagoCCPool;

/**
 * @author Hakim Belhaouari
 *
 */
public class CDLGrammar extends Grammar {

	public CDLGrammar() {
		createGrammar();
		checkReferences();
	}
	
	protected Parser<TService> serviceParser() {
		
		return new XForm<Nonuple<String,String,List<TImplements>, 
		List<TRefineService>, 
		List<TIncludeService>, 
		List<TProperty>,
		List<TInvariant>, 
		List<TMethod>, 
		TBehavior>,
		TService>(new Nine<String,String,List<TImplements>, 
				List<TRefineService>, 
				List<TIncludeService>, 
				List<TProperty>, 
				List<TInvariant>, 
				List<TMethod>, 
				TBehavior>()
				.then(new Literal("module"))
				.first(this.<String>ref("qualif-ident"))
				.then(new Char(';'))
				.then(new Literal("service"))
				.second(this.<String>ref("ident"))
				.then(character('{'))
				.third(new Many<TImplements>(this.<TImplements>ref("implements")))
				.fourth(new Many<TRefineService>(this.<TRefineService>ref("refine")))
				.fifth(new Many<TIncludeService>(this.<TIncludeService>ref("include")))
				.sixth(new Many<TProperty>(this.<TProperty>ref("property")))				
				.seventh(new Many<TInvariant>(this.<TInvariant>ref("invariant")))
				.eighth(new Many<TMethod>(this.<TMethod>ref("method")))
				.ninth(new MaybeOrDefault<TBehavior>(this.<TBehavior>ref("behavior"),new TIBehavior(new ArrayList<TState>(), new ArrayList<TTransition>(), "") ))
				.then(character('}'))) {
					@Override
					public TService transform(ParseContext<?> ctx, Nonuple<String, String, 
							List<TImplements>, 
							List<TRefineService>, 
							List<TIncludeService>, 
							List<TProperty>, 
							List<TInvariant>, 
							List<TMethod>, 
							TBehavior> content) {
						List<TExtendService> extendServices = new ArrayList<TExtendService>();
						extendServices.addAll(content.getFourth());
						extendServices.addAll(content.getFifth());
						TBehavior beh = content.getNinth();
						/*if(content.getNinth().hasResult())
							beh = content.getNinth().getResult();
						else
							beh = new TIBehavior(new ArrayList<TState>(), new ArrayList<TTransition>(), "");
							*/
						return new TIService(content.getSecond(),content.getFirst(),
								"",content.getEighth(),content.getSixth(),
								content.getSeventh(),
								beh,
								extendServices,
								content.getThird(),
								new ArrayList<TNamespace>(),
								new ArrayList<TType>());
								
					}
		};				
	}

	private void createGrammar() {
		Parser<Object> spaces = new Forget(new Many<Character>(WhiteSpace.getInstance()));
		register("spaces", spaces);
		
		Parser<Object> lineComment = new Forget(new LineComment(new Literal("//")));
		register("lineComment", lineComment);
		
		Parser<?> skipPart = new ForgetAll(ref("spaces"),new Maybe<Object>(ref("lineComment")));
		Parser<?> skip = new Many<Object>((Parser<Object>) skipPart);
		register("skip",skip);
		skip(ref("skip"));
		
		Set<String> reservedKeywords = new HashSet<String>();
		reservedKeywords.add("true");
		reservedKeywords.add("false");
		reservedKeywords.add("method");
		reservedKeywords.add("pre");
		reservedKeywords.add("post");
		reservedKeywords.add("forall");
		reservedKeywords.add("exists");
		reservedKeywords.add("method");
		reservedKeywords.add("property");
		reservedKeywords.add("read");
		reservedKeywords.add("write");
		reservedKeywords.add("readwrite");
		reservedKeywords.add("service");
		reservedKeywords.add("component");
		reservedKeywords.add("composite");
		reservedKeywords.add("assembly");
		reservedKeywords.add("require");
		reservedKeywords.add("in");
		reservedKeywords.add("provide");
		reservedKeywords.add("module");
		reservedKeywords.add("bind");
		reservedKeywords.add("with");
		reservedKeywords.add("from");
		reservedKeywords.add("to");
		reservedKeywords.add("when");
		reservedKeywords.add("percolator");
		reservedKeywords.add("behavior");
		reservedKeywords.add("implements");
		reservedKeywords.add("@pre");
		reservedKeywords.add("@return");
		reservedKeywords.add("pre");
		reservedKeywords.add("post");
		reservedKeywords.add("naming");
		reservedKeywords.add("by");
		reservedKeywords.add("transitions");
		reservedKeywords.add("transition");
		reservedKeywords.add("states");
		reservedKeywords.add("state");
		reservedKeywords.add("allow");
		reservedKeywords.add("default");
		reservedKeywords.add("export");
		reservedKeywords.add("as");
		reservedKeywords.add("id");
		reservedKeywords.add("refine");
		reservedKeywords.add("include");
		reservedKeywords.add("invariant");
		reservedKeywords.add("fail");
		reservedKeywords.add("using");
		reservedKeywords.add("provider");
		
		
		//reservedKeywords.add("int");
		//reservedKeywords.add("real");
		//reservedKeywords.add("bool");
		//reservedKeywords.add("string");
		
		Parser<String> qualifIdent = new Reserved<String>(new RegexpString("[a-zA-Z_]\\w*([.][a-zA-Z_]\\w*)*(\\[\\])?"),reservedKeywords);
		register("qualif-ident", qualifIdent);
		
		Parser<String> ident = new Reserved<String>(new RegexpString("[a-zA-Z_]\\w*"),reservedKeywords);
		register("ident", ident);
		
		Parser<String> quant= new RegexpString("forall|exists|FORALL|EXISTS");
		register("quant", quant);
		
		Parser<String> string = new  XForm<String, String>( new RegexpString("\"[^\"]*\"")) {
			@Override
			public String transform(ParseContext<?> arg0, String arg) {
				if(arg.length() == 2)
					return "";
				else
					return arg.substring(1, arg.length()-1);
			}
		};
		register("string",string);
		
		Parser<TAccess> access = new XForm<String, TAccess>(
				new RegexpString("readwrite|read|write")
				)
				{
					@Override
					public TAccess transform(ParseContext<?> ctx, String content) {
						if("read".equals(content))						
							return new TIAccess("r");
						else if("write".equals(content))
							return new TIAccess("w");
						else if("readwrite".equals(content))
							return new TIAccess("rw");
						else
							throw new RuntimeException("Unknown access:" + content);
					}
		};
		register("access",access);
		
		Parser<TType> type = new XForm<String, TType>(
				new Reserved<String>(new RegexpString("[a-zA-Z_]\\w*([.][a-zA-Z_]\\w*)*(\\[\\])?"),reservedKeywords)
			)
			{
				@Override
				public TType transform(ParseContext<?> ctx, String content) {
					return TIType.generateType(content);
				}
			};
		register("type",type);
			
		
		// -- service		
		Parser<TImplements> impl = implementsParser();
		register("implements",impl);
		
		Parser<TRefineService> refines = refineParser();
		register("refine",refines);
		
		Parser<TIncludeService> includes = includeParser();
		register("include",includes);
		
		Parser<TProperty> property = propertyParser();
		register("property",property);
		
		Parser<TInvariant> invariant = invariantParser();
		register("invariant",invariant);
		
		Parser<TMethod> method = methodParser();
		register("method",method);
		
		Parser<String> methodid = methodidParser();
		register("method-id",methodid);
		
		Parser<TCondition> precondition = conditionParser(true);
		register("precondition",precondition);
		
		Parser<TCondition> postcondition = conditionParser(false);
		register("postcondition",postcondition);
		
		Parser<TBehavior> behavior = behaviorParser();
		register("behavior",behavior);
		
		Parser<String> defaultstate = new One<String>()
			.then(literal("default"))
			.then(literal("state"))
			.parser(this.<String>ref( "ident"))
			.then(character(';'));
		register("default-state", new MaybeOrDefault<String>(defaultstate, ""));
		
		Parser<List<TState>> states = statesParser();
		register("states",states);
		
		Parser<TAllow> allow = allowParser();
		register("allow",allow);
		
		Parser<List<TAllow>> allows = new Many<TAllow>(this.<TAllow>ref( "allow"));

		register("list-allows",allows);
		
		Parser<TState> state = stateParser();
		register("state",state);
		
		Parser<List<TTransition>> transitions = transitionsParser();
		register("transitions",transitions);
		
		Parser<TTransition> transition = transitionParser();
		register("transition",transition);
		
		Parser<List<TTransition>> listtransitions = new Many<TTransition>(this.<TTransition>ref( "transition"));
		register("list-transitions",listtransitions);
		
		Parser<TParameter> parameter = parameterParser();
		register("parameter",parameter);
		Parser<List<TParameter>> parameters = parametersParser();
		register("parameters",parameters);
		
		// -- EXPRESSION
		
		Parser<CDLInteger> entier = new XForm<String, CDLInteger>(new RegexpString("(-)?([0-9]+)")) {

			@Override
			public CDLInteger transform(ParseContext<?> ctx, String content) {
				return new  CDLInteger(Integer.parseInt(content));
			}
		};
		register("int",entier);
		
		Parser<CDLBool> bool = new XForm<String, CDLBool>(new RegexpString("true|false")) {
			@Override
			public CDLBool transform(ParseContext<?> ctx, String content) {
				return new CDLBool(Boolean.parseBoolean(content));
			}
		};
		register("bool",bool);
		
		Parser<CDLReal> real = new XForm<String, CDLReal>(new RegexpString("(-)?[0-9]+\\.([0-9]+)?([eE]([+-])?[0-9]+)?")) {
			@Override
			public CDLReal transform(ParseContext<?> ctx, String content) {
				return new CDLReal(Double.parseDouble(content));
			}
		};
		register("real",real);
		
		Parser<CDLNil> nil = new XForm<String, CDLNil>(new RegexpString("null|nil")) {
			@Override
			public CDLNil transform(ParseContext<?> ctx, String content) {
				return new CDLNil();
			}
		};
		register("nil",nil);
		
		Parser<CDLReturn> ret = new XForm<String, CDLReturn>(new RegexpString("@return")) {
			@Override
			public CDLReturn transform(ParseContext<?> ctx, String content) {
				return new CDLReturn();
			}
		};
		register("return",ret);
		
		Parser<CDLString> exprstring = exprstringParser();
		register("exprstring",exprstring);
		
		// pre/inv expression
		Parser<CDLRelExpression> relexpr = relexprParser();
		register(REL_EXPR,relexpr);
		
		Parser<CDLArithExpression> arithexpr = arithexprParser();
		register(ARITH_EXPR,arithexpr);
		
		Parser<CDLExpression> logicexpr = logicexprParser();
		register(LOGIC_EXPR,logicexpr);
		
		
		Parser<TExpression> preexpr = preexpression();
		register(PREEXPR,preexpr);
		
		Parser<TExpression> postexpr = postexpression();
		register(POSTEXPR,postexpr);
		
		// post expression
		Parser<CDLRelExpression> prelexpr = postrelexprParser();
		register(POST_REL_EXPR,prelexpr);
		
		Parser<CDLArithExpression> parithexpr = postarithexprParser();
		register(POST_ARITH_EXPR,parithexpr);
		
		Parser<CDLExpression> plogicexpr = postlogicexprParser();
		register(POST_LOGIC_EXPR,plogicexpr);
		
		Parser<CDLCast> cast = castParser(true);
		register("cast",cast);
		
		// pre expression 
		
		Parser<CDLQSet> qset = quantSetParser(true);
		register("quantset",qset);
		
		Parser<CDLQRange> qrange = quantRangeParser(true);
		register("quantrange", qrange);
		
		Parser<CDLQColl> qcoll = quantCollParser(true);
		register("quantcoll", qcoll);
		
		Parser<CDLRead> read = readParser(true);
		register("read",read);
		
		Parser<CDLVariable> variable = varParser(true);
		register("var",variable);
		
		Parser<CDLCall> call = callParser(true);
		register("call",call);
		
		Parser<List<CDLExpression>> list_arguments = listArgumentsParser(true);
		register("list-arguments",list_arguments);
		
		Parser<CDLInLabel> inlabel = inlabelParser(true);
		register("inlabel",inlabel);
		
		
		// post expression
		
		Parser<CDLCast> pcast = castParser(false);
		register("postcast",pcast);
		
		Parser<CDLQSet> pqset = quantSetParser(false);
		register("postquantset",pqset);
		
		Parser<CDLQRange> pqrange = quantRangeParser(false);
		register("postquantrange", pqrange);
		
		Parser<CDLQColl> pqcoll = quantCollParser(false);
		register("postquantcoll", pqcoll);
		
		Parser<CDLRead> pread = readParser(false);
		register("postread",pread);
		
		Parser<CDLVariable> pvariable = varParser(false);
		register("postvar",pvariable);
		
		Parser<CDLCall> pcall = callParser(false);
		register("postcall",pcall);
		
		Parser<List<CDLExpression>> plist_arguments = listArgumentsParser(false);
		register("postlist-arguments",plist_arguments);
		
		Parser<CDLInLabel> pinlabel = inlabelParser(false);
		register("postinlabel",pinlabel);
				
		/*
		parser.operand(this.<CDLExpression>ref("postarithexpression"));
		parser.operand(this.<CDLExpression>ref("atpre"));
		parser.operand(this.<CDLRead>ref("postread"));
		parser.operand(this.<CDLVariable>ref("postvar"));
		parser.operand(this.<CDLReturn>ref("return"));
		parser.operand(this.<CDLCall>ref("postcall"));
		parser.operand(this.<CDLInLabel>ref("postinlabel"));
		parser.operand(this.<CDLNil>ref("nil"));
		parser.operand(this.<CDLQSet>ref("postquantset"));
		parser.operand(this.<CDLQRange>ref("postquantrange"));
		parser.operand(this.<CDLQColl>ref("postquantcoll"));*/
		
		// -- component
		//Parser<TComponent> component = new ComponentParser(null);
		//Parser<List<TProvide>> provides = new Many<TProvide>(this.<TProvide>ref("provide"));
		//Parser<List<TRequire>> requires = new Many<TRequire>(this.<TRequire>ref("require"));
		
		// ---
		
		Parser<TProvide> provide = provideParser();
		register("provide", provide);
		
		Parser<List<TProvide>> provides = providesParser();
		register("provides",provides);
		
		Parser<TRequire> require = requireParser();
		register("require",require);
		
		Parser<List<TRequire>> requires = requiresParser();
		register("requires",requires);
		
		Parser<List<TPercolator>> percolator = percolatorParser();
		register("percolator",percolator);

		// ----------- TAMAGO ENTITY
		Parser<? extends TTamago> serviceParser = serviceParser();
		register("service-parser",serviceParser);
		
		Parser<? extends TTamago> component = componentParser();
		register("component-parser",component);
		
		Parser<Object> tamago = new One<Object>()
			.parser(new Choice<Object>()
						.either((Parser<Object>) serviceParser)
						.orElse((Parser<Object>) component))
			.then(EOF.getInstance());
		//setMainParser(new One<TService>().parser(serviceParser).then(EOF.getInstance()));
		setMainParser(tamago);
	}

	private Parser<TComponent> componentParser() {
		List<TPercolator> allperco = new ArrayList<TPercolator>(1);
		allperco.add(TIPercolator.getAllPercolator());
		
		Ten<String, String, List<TImplements>, List<TProvide>, 
		List<TRequire>, List<TProperty>, List<TPercolator>, List<TInvariant>,
		List<TMethod>, TBehavior> source = new Ten<String, String, List<TImplements>, List<TProvide>, List<TRequire>, List<TProperty>, List<TPercolator>, List<TInvariant>, List<TMethod>, TBehavior>();
		
		source.then(new Literal("module"))
		.first(this.<String>ref("qualif-ident"))
		.then(new Char(';'))
		.then(new Literal("component"))
		.second(this.<String>ref("ident"))
		.then(character('{'))
		.third(new Many<TImplements>(this.<TImplements>ref("implements")))
		.fourth(this.<List<TProvide>>ref("provides"))
		.fifth(this.<List<TRequire>>ref("requires"))
		.sixth(new Many<TProperty>(this.<TProperty>ref("property")))
		.seventh(new MaybeOrDefault<List<TPercolator>>(this.<List<TPercolator>>ref("percolator"), allperco))
		.eighth(new Many<TInvariant>(this.<TInvariant>ref("invariant")))
		.ninth(new Many<TMethod>(this.<TMethod>ref("method")))
		.tenth(new MaybeOrDefault<TBehavior>(this.<TBehavior>ref("behavior"),new TIBehavior(new ArrayList<TState>(), new ArrayList<TTransition>(), "") ))
		.then(character('}'));		
		
		return new XForm<Decuple<String, String, List<TImplements>, List<TProvide>, 
			List<TRequire>, List<TProperty>, List<TPercolator>, List<TInvariant>,
			List<TMethod>, TBehavior>, TComponent>(source) {
				@Override
				public TComponent transform(
						ParseContext<?> ctx,
						Decuple<String, String, List<TImplements>, List<TProvide>, List<TRequire>, List<TProperty>, List<TPercolator>, List<TInvariant>, List<TMethod>, TBehavior> content) {
					return new TIComposant(content.getSecond(), content.getFirst(), content.getSixth(), 
							content.getFourth(), content.getFifth(), content.getEighth(), 
							content.getNinth(), content.getTenth(), content.getSeventh(), 
							content.getThird(), new ArrayList<TNamespace>(), new ArrayList<TType>());
				}
		};
	}

	private Parser<List<TPercolator>> percolatorParser() {
		 
		Parser<TPercolator> parser = new XForm<String, TPercolator>(this.<String>ref("ident")) {
			@Override
			public TPercolator transform(ParseContext<?> ctx, String content) {
				return new TIPercolator(content);
			}
		};
		
		ListOf<TPercolator> loft = new ListOf<TPercolator>(parser, 1, Integer.MAX_VALUE);
		loft.sep(new Zero().then(ref("skip")).then(character(',')).then(ref("skip"))); 
		
		One<List<TPercolator>> one = new One<List<TPercolator>>()
			.then(literal("percolator"))
			.parser(loft)
			.then(character(';'));
		
		One<Character> allperco = new One<Character>()
			.then(literal("percolator"))
			.parser(character('*'))
			.then(character(';'));
		Parser<List<TPercolator>> plp = new XForm<Character, List<TPercolator>>(allperco) {
			@Override
			public List<TPercolator> transform(ParseContext<?> ctx,
					Character content) {
				List<TPercolator> ltp = new ArrayList<TPercolator>(1);
				ltp.add(TIPercolator.getAllPercolator());
				return ltp;
			}
		};
		
		Choice<List<TPercolator>> ch = new Choice<List<TPercolator>>()
			.either(plp)
			.orElse(one);
		return ch;
	}

	private Parser<List<TRequire>> requiresParser() {
		return new Many<TRequire>(this.<TRequire>ref("require"));
	}

	private Parser<TRequire> requireParser() {
		Three<String, String, String> source = new Three<String, String, String>()
			.then(literal("require"))
			.then(literal("service"))
			.first(this.<String>ref("ident"))
			.then(literal("in"))
			.second(this.<String>ref("qualif-ident"))
			.then(literal("as"))
			.third(this.<String>ref("ident"))
			.then(character(';'));
			
		return new XForm<Triple<String, String, String>, TRequire>(source) {
			@Override
			public TRequire transform(ParseContext<?> ctx,
					Triple<String, String, String> content) {
				TService service;
				try {
					service = (TService) TamagoCCPool.getDefaultPool().getTreeAbstractSyntax(content.getFirst(), content.getSecond());
					return new TIRequire(content.getThird(), content.getFirst(), content.getSecond(), service, new TType[] { });
				} catch (TamagoCCException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		};
	}

	private Parser<List<TProvide>> providesParser() {
		return new Many<TProvide>(this.<TProvide>ref("provide"));
	}

	private Parser<TProvide> provideParser() {
		Two<String, String> two = new Two<String, String>()
			.then(literal("provide"))
			.then(literal("service"))
			.first(this.<String>ref("ident"))
			.then(literal("in"))
			.second(this.<String>ref("qualif-ident"))
			.then(character(';'));
		
		return new XForm<Pair<String, String>, TProvide>(two) {
			@Override
			public TProvide transform(ParseContext<?> ctx,
					Pair<String, String> content) {
				TService tamago;
				try {
					tamago = (TService)TamagoCCPool.getDefaultPool().getTreeAbstractSyntax(content.getFirst(), content.getSecond());
					return new TIProvide(content.getFirst(), content.getSecond(), new TType[] { }, tamago);
				} catch (TamagoCCException e) {
					e.printStackTrace();
					return null;
				}
			}
		};
	}

	protected Parser<CDLCast> castParser(boolean pre) {
		Two<TType, CDLExpression> source = new Two<TType, CDLExpression>()
			.then(character('('))
			.first(this.<TType>ref( "type"))
			.then(character(')'))
			.second(this.<CDLExpression>ref( pre? REL_EXPR : POST_REL_EXPR));
		
		Two<TType, CDLExpression> sourceAlt = new Two<TType, CDLExpression>()
		.then(character('|'))
		.first(this.<TType>ref( "type"))
		.then(character('|'))
		.second(this.<CDLExpression>ref( pre? REL_EXPR : POST_REL_EXPR));
		
		Choice<CDLCast> ch = new Choice<CDLCast>();
		
		ch.either(new XForm<Pair<TType,CDLExpression>, CDLCast>(source) {

			@Override
			public CDLCast transform(ParseContext<?> ctx,
					Pair<TType, CDLExpression> content) {
				return new CDLCast(content.getFirst(), content.getSecond());
			}
		});
		
		ch.orElse(new XForm<Pair<TType,CDLExpression>, CDLCast>(sourceAlt) {

			@Override
			public CDLCast transform(ParseContext<?> ctx,
					Pair<TType, CDLExpression> content) {
				return new CDLCast(content.getFirst(), content.getSecond());
			}
		});
	
		return ch;
	}




	protected Parser<List<CDLExpression>> listArgumentsParser(boolean pre) {
		
		Parser<List<CDLExpression>> listArgs = new ListOf<CDLExpression>(this.<CDLExpression>ref( (!pre? POST_LOGIC_EXPR : LOGIC_EXPR)))
		.sep(character(','));
		
		return listArgs;
	}




	protected Parser<CDLInLabel> inlabelParser(boolean precond) {
		Two<CDLVariable, CDLExpression> source = new Two<CDLVariable, CDLExpression>()
			.first(this.<CDLVariable>ref( (precond? "var" : "postvar")))
			.then(character('.'))
			.second(this.<CDLExpression>ref( precond? REL_EXPR : POST_REL_EXPR));
		
		Parser<CDLInLabel> p = new XForm<Pair<CDLVariable,CDLExpression>, CDLInLabel>(source) {
			@Override
			public CDLInLabel transform(ParseContext<?> ctx,
					Pair<CDLVariable, CDLExpression> content) {
				return new CDLInLabel(content.getFirst(), content.getSecond());
			}
		};
		
		Two<CDLRead, CDLExpression> rsource = new Two<CDLRead, CDLExpression>()
		.first(this.<CDLRead>ref( precond? "read" : "postread"))
		.then(character('.'))
		.second(this.<CDLExpression>ref(precond? REL_EXPR : POST_REL_EXPR));
	
	Parser<CDLInLabel> pr = new XForm<Pair<CDLRead,CDLExpression>, CDLInLabel>(rsource) {
		@Override
		public CDLInLabel transform(ParseContext<?> ctx,
				Pair<CDLRead, CDLExpression> content) {
			return new CDLInLabel(content.getFirst(), content.getSecond());
		}
	};
		Choice<CDLInLabel> ch = new Choice<CDLInLabel>();
		ch.either(pr);
		ch.orElse(p);
		
		return ch;
	}

	protected Parser<CDLCall> callParser(boolean pre) {
		Two<String, List<CDLExpression>> source = new Two<String, List<CDLExpression>>()
			.first(this.<String>ref( "ident"))
			.then(character('('))
			.second(this.<List<CDLExpression>>ref( pre? "list-arguments" : "postlist-arguments"))
			.then(character(')'));
			
		
		return new XForm<Pair<String, List<CDLExpression>>, CDLCall>(source) {
			@Override
			public CDLCall transform(ParseContext<?> ctx,
					Pair<String, List<CDLExpression>> content) {
				return new CDLCall(content.getFirst(), content.getSecond());
			}
		};
	}

	protected Parser<CDLVariable> varParser(boolean pre) {
		Two<String, CDLExpression> tab = new Two<String, CDLExpression>()
			.first(this.<String>ref("ident"))
			.then(character('['))
			.second(this.<CDLExpression>ref(pre? ARITH_EXPR : POST_ARITH_EXPR))
			.then(character(']'));
		
		Parser<CDLVariable> atab = new XForm<Pair<String,CDLExpression>, CDLVariable>(tab) {

			@Override
			public CDLVariable transform(ParseContext<?> ctx,
					Pair<String, CDLExpression> content) {
				return new CDLVariable(content.getFirst(),content.getSecond());
			}
		};
		
		Parser<CDLVariable> simple = new XForm<String, CDLVariable>(this.<String>ref( "ident")) {

			@Override
			public CDLVariable transform(ParseContext<?> ctx, String content) {
				return new CDLVariable(content);
			}
		}; 
		
		
		return new Choice<CDLVariable>()
			.either(atab)
			.orElse(simple);
	}




	protected Parser<CDLRead> readParser(boolean pre) {
		Two<String, CDLExpression> tab = new Two<String, CDLExpression>()
		.then(character('#'))
		.first(this.<String>ref("ident"))
		.then(character('['))
		.second(this.<CDLExpression>ref(pre? ARITH_EXPR : POST_ARITH_EXPR))
		.then(character(']'));
	
	Parser<CDLRead> atab = new XForm<Pair<String,CDLExpression>, CDLRead>(tab) {

		@Override
		public CDLRead transform(ParseContext<?> ctx,
				Pair<String, CDLExpression> content) {
			return new CDLRead(content.getFirst(),content.getSecond());
		}
	};
	
	Parser<CDLRead> simple = new XForm<String, CDLRead>(new One<String>()
				.then(character('#'))
				.parser(this.<String>ref( "ident"))
	) {

		@Override
		public CDLRead transform(ParseContext<?> ctx, String content) {
			return new CDLRead(content);
		}
	}; 
	
	
	return new Choice<CDLRead>()
		.either(atab)
		.orElse(simple);
	}


	// PRE EXPRESSION


	protected Parser<CDLQColl> quantCollParser(boolean pre) {
		Five<String, String, TType, CDLExpression, CDLExpression> source = new Five<String, String, TType, CDLExpression, CDLExpression>()
		.first(this.<String>ref("quant"))
		.second(this.<String>ref("ident"))
		.then(character(':'))
		.third(this.<TType>ref("type"))
		.then(literal("in"))
		.fourth(this.<CDLExpression>ref(pre? LOGIC_EXPR : POST_LOGIC_EXPR))
		.then(character('{'))
		.fifth(this.<CDLExpression>ref(pre? LOGIC_EXPR : POST_LOGIC_EXPR))
		.then(character('}'));
	
	return new XForm<Quintuple<String, String, TType, CDLExpression, CDLExpression>, CDLQColl>(source) {
		@Override
		public CDLQColl transform(
				ParseContext<?> ctx,
				Quintuple<String, String, TType, CDLExpression, CDLExpression> content) {
			return new CDLQColl(content.getFirst(), content.getSecond(), content.getThird(), content.getFifth(), content.getFourth());
		}
	};
	}




	protected Parser<CDLQRange> quantRangeParser(boolean pre) {
		Six<String, String, TType, CDLExpression, CDLExpression, CDLExpression> source = new Six<String, String, TType, CDLExpression, CDLExpression, CDLExpression>()
			.first(this.<String>ref("quant"))
			.second(this.<String>ref("ident"))
			.then(character(':'))
			.third(this.<TType>ref("type"))
			.then(literal("in"))
			.fourth(this.<CDLExpression>ref(pre? ARITH_EXPR : POST_ARITH_EXPR))
			.then(literal(".."))
			.fifth(this.<CDLExpression>ref(pre? ARITH_EXPR : POST_ARITH_EXPR))
			.then(character('{'))
			.sixth(this.<CDLExpression>ref(pre? LOGIC_EXPR : POST_LOGIC_EXPR))
			.then(character('}'));
		
		return new XForm<Sixuple<String, String, TType, CDLExpression, CDLExpression, CDLExpression>, CDLQRange>(source) {
			@Override
			public CDLQRange transform(
					ParseContext<?> ctx,
					Sixuple<String, String, TType, CDLExpression, CDLExpression, CDLExpression> content) {
				return new CDLQRange(content.getFirst(), content.getSecond(), content.getThird(), content.getSixth(), content.getFourth(), content.getFifth());
			}
		};
	}




	protected Parser<CDLQSet> quantSetParser(boolean pre) {
		Five<String, String, TType, List<CDLExpression>, CDLExpression> source = new Five<String, String, TType, List<CDLExpression>, CDLExpression>()
		.first(this.<String>ref("quant"))
		.second(this.<String>ref("ident"))
		.then(character(':'))
		.third(this.<TType>ref("type"))
		.then(literal("in"))
		.then(character('['))
		.fourth(this.<List<CDLExpression>>ref( pre? "list-arguments" : "postlist-arguments"))
		.then(character(']'))
		.then(character('{'))
		.fifth(this.<CDLExpression>ref(pre? LOGIC_EXPR : POST_LOGIC_EXPR))
		.then(character('}'));
	
	return new XForm<Quintuple<String, String, TType, List<CDLExpression>, CDLExpression>, CDLQSet>(source) {
		@Override
		public CDLQSet transform(
				ParseContext<?> ctx,
				Quintuple<String, String, TType, List<CDLExpression>, CDLExpression> content) {
			return new CDLQSet(content.getFirst(), content.getSecond(), content.getThird(), content.getFifth(), content.getFourth());
		}
	};
	}

	protected Parser<TCondition> conditionParser(boolean pre) {
		Two<TExpression, String> detail = new Two<TExpression, String>()
		.then(literal(pre? "pre" : "post"))
		.first(this.<TExpression>ref(pre? PREEXPR : POSTEXPR))
		.then(literal("fail"))
		.second(this.<String>ref("string"))
		.then(character(';'));
	
	
	One<TExpression> raw = new One<TExpression>()
	.then(literal(pre? "pre" : "post"))
	.parser(this.<TExpression>ref(pre? PREEXPR : POSTEXPR))
	.then(character(';'));
	
	Choice<TCondition> ch = new Choice<TCondition>();
	ch.either(new XForm<Pair<TExpression, String>, TCondition>(detail) {
			@Override
			public TCondition transform(ParseContext<?> ctx, Pair<TExpression, String> content) {
				return new TICondition(content.getFirst(),new TICategory(""),content.getSecond());
			}
		});
	ch.orElse(new XForm<TExpression,TCondition>(raw) {
		@Override
		public TCondition transform(ParseContext<?> ctx, TExpression content) {
			return new TICondition(content,new TICategory(""),"");
		}
	});
	
	return ch;
}
	

	protected Parser<String> methodidParser() {
		One<String> p = new One<String>()
			.then(literal("id"))
			.parser(this.<String>ref("ident"))
			.then(character(';'));
		return p;
	}

	protected Parser<TTransition> transitionParser() {
		Four<String, String, String, TExpression> s = new Four<String, String, String, TExpression>()
			.then(literal("transition"))
			.then(literal("from"))
			.first(this.<String>ref( "ident"))
			.then(literal("to"))
			.second(this.<String>ref( "ident"))
			.then(literal("with"))
			.third(this.<String>ref( "ident"))
			.then(literal("when"))
			.fourth(this.<TExpression>ref(PREEXPR))
			.then(character(';'));
		Parser<TTransition> guarded =  new XForm<Quadruple<String, String, String, TExpression>, TTransition>(s) {

			@Override
			public TTransition transform(ParseContext<?> ctx,
					Quadruple<String, String, String, TExpression> content) {
				return new TITransition(content.getFirst(), content.getSecond(), content.getThird(), content.getFourth());
			}
		};
		
		Three<String, String, String> sdirect = new Three<String, String, String>()
		.then(literal("transition"))
		.then(literal("from"))
		.first(this.<String>ref( "ident"))
		.then(literal("to"))
		.second(this.<String>ref( "ident"))
		.then(literal("with"))
		.third(this.<String>ref( "ident"))
		.then(character(';'));
	Parser<TTransition> noguarded =  new XForm<Triple<String, String, String>, TTransition>(sdirect) {

		@Override
		public TTransition transform(ParseContext<?> ctx,
				Triple<String, String, String> content) {
			return new TITransition(content.getFirst(), content.getSecond(), content.getThird(), new TINoContract());
		}
	};
		Choice<TTransition> ch = new Choice<TTransition>();
		ch.either(guarded);
		ch.orElse(noguarded);
		return ch;
	}

	protected Parser<List<TTransition>> transitionsParser() {
		One<List<TTransition>> p = new One<List<TTransition>>()
			.then(literal("transitions"))
			.then(character('{'))
			.parser(this.<List<TTransition>>ref( "list-transitions"))
			.then(character('}'));
		return p;
	}

	protected Parser<TAllow> allowParser() {
		One<String> s = new One<String>()
			.then(literal("allow"))
			.parser(this.<String>ref( "ident"))
			.then(character(';'));
		
		return new XForm<String, TAllow>(s) {
			@Override
			public TAllow transform(ParseContext<?> ctx, String content) {
				return new TIAllow(content);
			}
		};
	}

	protected Parser<TState> stateParser() {
		Two<String, List<TAllow>> s = new Two<String, List<TAllow>>()
			.then(literal("state"))
			.first(this.<String>ref( "ident"))
			.then(character('{'))
			.second(new MaybeOrDefault<List<TAllow>>(this.<List<TAllow>>ref( "list-allows"),
					new ArrayList<TAllow>()))
			.then(character('}'));
		return new XForm<Pair<String,List<TAllow>>, TState>(s) {
			@Override
			public TState transform(ParseContext<?> ctx,
					Pair<String, List<TAllow>> content) {
				return new TIState(content.getFirst(), content.getSecond());
			}
		};
	}

	
	protected Parser<List<TState>> statesParser() {
		One<List<TState>> states = new One<List<TState>>()
			.then(literal("states"))
			.then(character('{'))
			.parser(new Many<TState>(this.<TState>ref( "state")))
			.then(character('}'));
		return states;
	}

	protected Parser<List<TParameter>> parametersParser() {
		Choice<List<TParameter>> ch = new Choice<List<TParameter>>();
		
		/*Parser<List<TParameter>> suitesparams = new Many<TParameter>(
				new One<TParameter>()
					.then(character(','))
					.then(ref("skip"))
					.parser(this.<TParameter>ref("suites-parameters"))
				);
		register("suites-parameters",suitesparams);
		*/
		Two<TParameter, List<TParameter>> list = new Two<TParameter, List<TParameter>>()
			.first(this.<TParameter>ref( "parameter"))
			.then(character(','))
			.second(this.<List<TParameter>>ref( "parameters"));
			
		
		Parser<List<TParameter>> params = new XForm<Pair<TParameter, List<TParameter>>, List<TParameter>>(list) {
			@Override
			public List<TParameter> transform(ParseContext<?> ctx,
					Pair<TParameter, List<TParameter>> content) {
				ArrayList<TParameter> ps = new ArrayList<TParameter>();
				ps.add(content.getFirst());
				ps.addAll(content.getSecond());
				return ps;
			}
		};
		
		ch.either(params);
		
		ch.orElse(new XForm<TParameter, List<TParameter>>(this.<TParameter>ref( "parameter")) {
			@Override
			public List<TParameter> transform(ParseContext<?> ctx,
					TParameter content) {
				ArrayList<TParameter> al = new ArrayList<TParameter>();
				al.add(content);
				return al;
			}
		});
		
		return ch;
		
	}

	protected Parser<TParameter> parameterParser() {
		Two<TType, String> p = new Two<TType, String>()
			.first(this.<TType>ref( "type"))
			.second(this.<String>ref( "ident"));
		
		return new XForm<Pair<TType,String>, TParameter>(p) {

			@Override
			public TParameter transform(ParseContext<?> ctx,
					Pair<TType, String> content) {
				return new TIParameter(content.getSecond(), content.getFirst());
			}
		};
	}

	protected Parser<TBehavior> behaviorParser() {
		Three<String, List<TState>, List<TTransition>> source = new Three<String, List<TState>, List<TTransition>>();

		source.then(literal("behavior"));
		source.then(literal("{"));
		source.first(this.<String>ref("default-state"));
		source.second(this.<List<TState>>ref("states"));
		source.third(this.<List<TTransition>>ref("transitions"));
		source.then(literal("}"));

		
		return new XForm<Triple<String, List<TState>, List<TTransition>>, TBehavior>(
					source
				) {

					@Override
					public TBehavior transform(
							ParseContext<?> ctx,
							Triple<String, List<TState>, List<TTransition>> content) {
						return new TIBehavior(content.getSecond(), content.getThird(), content.getFirst());
					}
		};
	}

	protected Parser<TMethod> methodParser() {
		return new XForm<Sixuple<TType, String, List<TParameter>, String, TCondition, TCondition>, TMethod>(
				new Six<TType, String, List<TParameter>, String, TCondition, TCondition>()
				.then(literal("method"))
				.first(this.<TType>ref("type"))
				.second(this.<String>ref("ident"))
				.then(literal("("))
				.third(new MaybeOrDefault<List<TParameter>>(this.<List<TParameter>>ref("parameters"),
						new ArrayList<TParameter>()))
				.then(literal(")"))
				.then(literal("{"))
				.fourth(new MaybeOrDefault<String>(this.<String>ref("method-id"),""))
				.fifth(new MaybeOrDefault<TCondition>(this.<TCondition>ref("precondition"),
						new TICondition(new TINoContract(), new TICategory(""), "")))
				.sixth(new MaybeOrDefault<TCondition>(this.<TCondition>ref("postcondition"),
						new TICondition(new TINoContract(), new TICategory(""), "")))
				.then(literal("}"))
		) {

			@Override
			public TMethod transform(
					ParseContext<?> ctx,
					Sixuple<TType, String, List<TParameter>, String, TCondition, TCondition> content) {
				return new TIMethod(content.getSecond(), content.getFourth(), content.getFirst(), content.getThird(), content.getFifth(), content.getSixth());
			}
		};
	}

	protected Parser<TInvariant> invariantParser() {
		return new Choice<TInvariant>()
			.either(		
			new XForm<Pair<TExpression, String>,TInvariant>(
					new Two<TExpression, String>()
					.then(literal("invariant"))
					.first(this.<TExpression>ref(PREEXPR))
					.then(literal("fail"))
					.second(this.<String>ref("string"))
					.then(character(';'))
				) {

					@Override
					public TInvariant transform(ParseContext<?> ctx,
							Pair<TExpression, String> content) {
						return new TIInvariant(content.getFirst(), new TICategory(""), content.getSecond());
					}
		})
		.orElse( new XForm<TExpression,TInvariant>(
				new One<TExpression>()
				.then(literal("invariant"))
				.parser(this.<TExpression>ref(PREEXPR))
				.then(character(';'))
			) {

				@Override
				public TInvariant transform(ParseContext<?> ctx,
						TExpression content) {
					return new TIInvariant(content, new TICategory(""), "");
				}
	});

	}

	protected Parser<TProperty> propertyParser() {
		return new XForm<Triple<TAccess, TType, String>,TProperty>(
				new Three<TAccess, TType, String>()
				.then(literal("property"))
				.first(this.<TAccess>ref("access"))
				.second(this.<TType>ref("type"))
				.third(this.<String>ref("ident"))
				.then(literal(";"))
		) {

			@Override
			public TProperty transform(ParseContext<?> ctx, Triple<TAccess, TType, String> content) {
				return new TIProperty(content.getThird(),content.getSecond(),content.getFirst());
			}
		};
	}

	protected Parser<TIncludeService> includeParser() {
		return new XForm<Pair<String, String>, TIncludeService>(
				new Two<String, String>() 
				.then(literal("include"))
				.then(literal("service"))
				.first(this.<String>ref("ident"))
				.then(literal("module"))
				.second(this.<String>ref("qualif-ident"))
				.then(literal(";"))
			) {

				@Override
				public TIncludeService transform(ParseContext<?> ctx, Pair<String, String> content) {
					TService servpool = null;
					try {
						servpool = (TService) TamagoCCPool.getDefaultPool().getTreeAbstractSyntax(content.getFirst(), content.getSecond());
					} catch (TamagoCCException e) {
						e.printStackTrace();
						throw new RuntimeException("Unfound service :"+content.getSecond()+"."+content.getFirst());
					}
					return new TIIncludeService(content.getFirst(),content.getSecond(),servpool);
				}
	};
	}

	protected Parser<TRefineService> refineParser() {
		return new XForm<Pair<String, String>, TRefineService>(
					new Two<String, String>() 
					.then(literal("refine"))
					.then(literal("service"))
					.first(this.<String>ref("ident"))
					.then(literal("module"))
					.second(this.<String>ref("qualif-ident"))
					.then(literal(";"))
				) {

					@Override
					public TRefineService transform(ParseContext<?> ctx, Pair<String, String> content) {
						TService servpool = null;
						try {
							servpool = (TService) TamagoCCPool.getDefaultPool().getTreeAbstractSyntax(content.getFirst(), content.getSecond());
						} catch (TamagoCCException e) {
							e.printStackTrace();
							throw new RuntimeException("Service not found :"+content.getSecond()+"."+content.getFirst());
						}
						return new TIRefineService(content.getFirst(),content.getSecond(),servpool);
					}
		};
	}

	protected Parser<TImplements> implementsParser() {
		return new XForm<String, TImplements>(
				new One<String>()
				.then(literal("implements"))
				.parser(this.<String>ref("qualif-ident"))
				.then(character(';'))
				) {

					@Override
					public TImplements transform(ParseContext<?> ctx, String content) {
						if(content.contains(".")) {
							String module = content.substring(0, content.lastIndexOf("."));
							String nom = content.substring(content.lastIndexOf("."));
							return new TIImplements(nom,module);
						}
						else {
							System.err.println("*Warning* depreacated use of an empty namespace");
							return new TIImplements(content,"");
						}
					}			
		};
	}
	
	/* ======================================================================================= */
	
	protected static final String LOGIC_EXPR = "logicexpr";
	protected static final String REL_EXPR = "relexpr";
	protected static final String ARITH_EXPR = "arithexpr";
	protected static final String POST_LOGIC_EXPR = "postlogicexpr";
	protected static final String POST_REL_EXPR = "postrelexpr";
	protected static final String POST_ARITH_EXPR = "postarithexpr";
	
	
	protected static final String PREEXPR = "preexpression";
	protected static final String POSTEXPR = "postexpression";
	
	
	protected Parser<TExpression> preexpression() {
		return new XForm<CDLExpression, TExpression>(this.<CDLExpression>ref(LOGIC_EXPR)) {
			@Override
			public TExpression transform(ParseContext<?> ctx, CDLExpression content) {
				return content.toTExpression();
			}
		};
	}
	
	protected Parser<TExpression> postexpression() {
		return new XForm<CDLExpression, TExpression>(this.<CDLExpression>ref(POST_LOGIC_EXPR)) {
			@Override
			public TExpression transform(ParseContext<?> ctx, CDLExpression content) {
				return content.toTExpression();
			}
		};
	}
	
	
	protected Parser<CDLExpression> logicexprParser() {
		Expression<CDLExpression> parser = new Expression<CDLExpression>("logic-expr");
		parser.bracket(character('('), character(')'));
		
		parser.operand(this.<CDLRelExpression>ref(REL_EXPR));
		parser.operand(this.<CDLQSet>ref("quantset"));
		parser.operand(this.<CDLQRange>ref("quantrange"));
		parser.operand(this.<CDLQColl>ref("quantcoll"));
		
		parser.infix(80, ExprAssoc.LEFT, infixParser("&&"));
		parser.infix(80, ExprAssoc.LEFT, infixParser("and"));
		parser.infix(40, ExprAssoc.LEFT, infixParser("||"));
		parser.infix(40, ExprAssoc.LEFT, infixParser("or"));
		parser.infix(40, ExprAssoc.LEFT, infixParser("^"));
		parser.infix(40, ExprAssoc.LEFT, infixParser("xor"));
		parser.infix(90, ExprAssoc.LEFT, infixParser("-->"));
		parser.infix(90, ExprAssoc.LEFT, infixParser("<-->"));
		parser.infix(90, ExprAssoc.LEFT, infixParser("<->"));
		parser.prefix(90, prefixParser("not"));
		parser.prefix(90, prefixParser("!"));
		return parser;
	}
	
	protected Parser<CDLRelExpression> relexprParser() {
		Expression<CDLExpression> parser = new Expression<CDLExpression>("rel-expr");
		parser.bracket(character('('), character(')'));
		
		parser.operand(this.<CDLArithExpression>ref(ARITH_EXPR));
		parser.operand(this.<CDLInLabel>ref("inlabel"));
		parser.operand(this.<CDLBool>ref("bool"));
		parser.operand(this.<CDLCast>ref("cast"));
		parser.operand(this.<CDLRead>ref("read"));
		parser.operand(this.<CDLCall>ref("call"));
		parser.operand(this.<CDLVariable>ref("var"));
		
		parser.infix(80, ExprAssoc.LEFT, infixParser("<="));
		parser.infix(80, ExprAssoc.LEFT, infixParser("=="));
		parser.infix(80, ExprAssoc.LEFT, infixParser(">="));
		parser.infix(80, ExprAssoc.LEFT, infixParser("<"));
		parser.infix(80, ExprAssoc.LEFT, infixParser("!="));
		parser.infix(80, ExprAssoc.LEFT, infixParser(">"));
		
		return new XForm<CDLExpression,CDLRelExpression>(parser){
			@Override
			public CDLRelExpression transform(ParseContext<?> ctx,
					CDLExpression content) {
				return new CDLRelExpression(content);
			}
		};
	}
	
	protected Parser<CDLArithExpression> arithexprParser() {
		Expression<CDLExpression> parser = new Expression<CDLExpression>("arith-expr");
		parser.bracket(character('('), character(')'));
		
		parser.operand(this.<CDLInLabel>ref("inlabel"));
		parser.operand(this.<CDLCast>ref("cast"));
		parser.operand(this.<CDLReal>ref("real"));
		parser.operand(this.<CDLInteger>ref("int"));
		parser.operand(this.<CDLString>ref("exprstring"));
		parser.operand(this.<CDLRead>ref("read"));
		parser.operand(this.<CDLCall>ref("call"));
		parser.operand(this.<CDLVariable>ref("var"));
		parser.operand(this.<CDLNil>ref("nil"));
		
		parser.infix(10, ExprAssoc.LEFT, infixParser("+"));
		parser.infix(10, ExprAssoc.LEFT, infixParser("-"));
		parser.infix(80, ExprAssoc.LEFT, infixParser("*"));
		parser.infix(80, ExprAssoc.LEFT, infixParser("/"));
		parser.infix(80, ExprAssoc.LEFT, infixParser("%"));
		//parser.prefix(100, prefixMinusParser());
		
		return new XForm<CDLExpression,CDLArithExpression>(parser){
			@Override
			public CDLArithExpression transform(ParseContext<?> ctx,
					CDLExpression content) {
				return new CDLArithExpression(content);
			}
		};
	}
	
	/* ======================================================================================= */
	
	protected Parser<CDLExpression> postlogicexprParser() {
		Expression<CDLExpression> parser = new Expression<CDLExpression>("post-logic-expr");
		parser.bracket(character('('), character(')'));
		
		parser.operand(this.<CDLRelExpression>ref(POST_REL_EXPR));
		parser.operand(this.<CDLQSet>ref("postquantset"));
		parser.operand(this.<CDLQRange>ref("postquantrange"));
		parser.operand(this.<CDLQColl>ref("postquantcoll"));
		
		parser.infix(60, ExprAssoc.LEFT, infixParser("&&"));
		parser.infix(60, ExprAssoc.LEFT, infixParser("and"));
		parser.infix(40, ExprAssoc.LEFT, infixParser("||"));
		parser.infix(40, ExprAssoc.LEFT, infixParser("or"));
		parser.infix(40, ExprAssoc.LEFT, infixParser("^"));
		parser.infix(40, ExprAssoc.LEFT, infixParser("xor"));
		parser.infix(70, ExprAssoc.LEFT, infixParser("-->"));
		parser.infix(70, ExprAssoc.LEFT, infixParser("<-->"));
		parser.infix(70, ExprAssoc.LEFT, infixParser("<->"));
		
		parser.prefix(175, prefixParser("not"));
		parser.prefix(175, prefixParser("!"));
		
		return parser;
	}
	
	protected Parser<CDLRelExpression> postrelexprParser() {
		Expression<CDLExpression> parser = new Expression<CDLExpression>("post-rel-expr");
		parser.bracket(character('('), character(')'));
		
		parser.operand(this.<CDLArithExpression>ref(POST_ARITH_EXPR));
		parser.operand(this.<CDLInLabel>ref("postinlabel"));
		parser.operand(this.<CDLCast>ref("postcast"));
		parser.operand(this.<CDLBool>ref("bool"));
		parser.operand(this.<CDLReturn>ref("return"));
		parser.operand(this.<CDLRead>ref("postread"));
		parser.operand(this.<CDLCall>ref("postcall"));
		parser.operand(this.<CDLVariable>ref("postvar"));
		
		parser.postfix(200,postfixAtPreParser());
		
		parser.infix(80, ExprAssoc.LEFT, infixParser("<="));
		parser.infix(80, ExprAssoc.LEFT, infixParser("=="));
		parser.infix(80, ExprAssoc.LEFT, infixParser(">="));
		parser.infix(80, ExprAssoc.LEFT, infixParser("<"));
		parser.infix(80, ExprAssoc.LEFT, infixParser("!="));
		parser.infix(80, ExprAssoc.LEFT, infixParser(">"));
		
		return new XForm<CDLExpression,CDLRelExpression>(parser){
			@Override
			public CDLRelExpression transform(ParseContext<?> ctx,
					CDLExpression content) {
				return new CDLRelExpression(content);
			}
		};
	}
	
	
	protected Parser<CDLArithExpression> postarithexprParser() {
		Expression<CDLExpression> parser = new Expression<CDLExpression>("post-arith-expr");
		parser.bracket(character('('), character(')'));
		
		parser.operand(this.<CDLCast>ref("postcast"));
		parser.operand(this.<CDLInLabel>ref("postinlabel"));
		parser.operand(this.<CDLReal>ref("real"));
		parser.operand(this.<CDLInteger>ref("int"));
		parser.operand(this.<CDLString>ref("exprstring"));
		parser.operand(this.<CDLNil>ref("nil"));
		parser.operand(this.<CDLRead>ref("postread"));
		parser.operand(this.<CDLVariable>ref("postvar"));
		parser.operand(this.<CDLCall>ref("postcall"));
		parser.operand(this.<CDLReturn>ref("return"));
		
		parser.postfix(300,postfixAtPreParser());

		parser.infix(90, ExprAssoc.LEFT, infixParser("+"));
		parser.infix(90, ExprAssoc.LEFT, infixParser("-"));
		parser.infix(95, ExprAssoc.LEFT, infixParser("*"));
		parser.infix(95, ExprAssoc.LEFT, infixParser("/"));
		parser.infix(95, ExprAssoc.LEFT, infixParser("%"));
		//parser.prefix(100, prefixMinusParser());
		
		return new XForm<CDLExpression,CDLArithExpression>(parser){
			@Override
			public CDLArithExpression transform(ParseContext<?> ctx,
					CDLExpression content) {
				return new CDLArithExpression(content);
			}
		};
	}
	
	private Parser<CDLString> exprstringParser() {
		return new XForm<String, CDLString>(this.<String>ref("string")) {

			@Override
			public CDLString transform(ParseContext<?> arg0, String arg1) {
				return new CDLString(arg1);
			}
		};
	}

	
	protected Parser<? extends PostfixNode> postfixAtPreParser() {
		return new XForm<String, CDLAtPre>(new Literal("@pre")) {
			@Override
			public CDLAtPre transform(ParseContext<?> ctx, String content) {
				return new CDLAtPre();
			}
		};
	}

	protected Parser<? extends PrefixNode> prefixMinusParser() {
		return new XForm<String, PrefixNode>(new Literal("-")) {
			@Override
			public CDLMinus transform(ParseContext<?> ctx, String content) {
				return new CDLMinus(content);
			}
		};
	}
	
	protected Parser<? extends PrefixNode> prefixParser(String string) {
		return new XForm<String, PrefixNode>(new Literal(string)) {
			@Override
			public CDLNot transform(ParseContext<?> ctx, String content) {
				return new CDLNot(content);
			}
		};
	}
	protected Parser<CDLInfix> infixParser(String opName) {
		return new XForm<String,CDLInfix>(new Literal(opName)) {
			@Override
			public CDLInfix transform(ParseContext<?> ctx, String content) {
				return new CDLInfix(content);
			}
		};
	}
	
	public static void main(String[] args) {
		CDLGrammar gram = new CDLGrammar();
		System.out.println(gram.toString());
		System.out.flush();
		
		ParseInput input;
		try {
			input = new StringParseInput(streamToString("BankAccount.cdl"));
			System.err.println("Flux a parser:"+input);
			DefaultParseContext ctx = new DefaultParseContext();
			ParseResult<?> expr = gram.parse(ctx,input);
			if (expr.isError()) {
				System.err
						.println("Error: " + expr.asError().getErrorMessage());
				System.err.println("==> " + input.show(0, 80));
				
				//System.err.println(ctx.toString());
			} else {
				try {
					System.out.println(expr.toString());
				} catch (Error e) {
					System.err.println("Error: cannot evaluate\n  ==> "
							+ expr.toString());
				}
			}
			System.err.println("---------------------");
			System.err.println(ctx.deepest().getStart().getLinePos());
			System.err.println(ctx.deepest().getEnd().getLinePos());
			System.err.println(ctx.deepest().getDetailedErrorMessage());
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}




	private static String streamToString(String string) throws IOException {
		DataInputStream dis =  new DataInputStream(new FileInputStream(string));
		StringBuilder sb = new StringBuilder();
		byte[] b = new byte[2048];
		int lu = 0;
		while((lu = dis.read(b)) >= 0) {
			sb.append(new String(b,0,lu));
		}
		return sb.toString();
	}
	
	static String example = "module org.upmc.tamago.tme.bucket;\n" +
	"service Bucket {\n" +
	"    // implements <fullname interface>;\n" +
	"    // refine service <service> module <module>;\n" +
	"    // include service <service> module <module>;\n" +
	"    property read real quantity;\n" +
	"    property read bool isEmpty;\n" +
	"    invariant (#quantity >= 0);\n" +
	"    invariant (#isEmpty <-> (#quantity == 0));\n" +
	"    \n" +
	"	 method void init() {\n" +
	"    	id init;\n" +
	"       post (#isEmpty);\n" +
	"    }\n" +
	"    method void fill(real q) {\n" +
	"       id fill;\n" +
	"       post (q >= 0) && (#quantity == (#quantity@pre + q))\n" +
	"          && (#isEmpty => (#isEmpty@pre && (q==0)));\n" +
	"    }\n" +
	"    method void pump(real q) {\n" +
	"       id pump;\n" +
	"       pre (#quantity >= q) && (q >= 0);\n" +
	"       post (#quantity == (#quantity@pre - q))\n" +
	"          && (#isEmpty => (q == #quantity));\n" +
	"    }\n" +
	"    behavior {\n" +
	"    	default state empty;\n" +
	"       states {\n" +
	"       	state empty {\n" +
	"      			allow fill;\n" +
	"           }\n" +
	"           state nempty {\n" +
	"               allow fill;\n" +
	"               allow pump;\n" +
	"           }\n" +
	"       } \n" +
	"       transitions { \n" +
	"           transition from empty to nempty with fill when (#quantity > 0);\n" +
	"           transition from nempty to empty with pump when (#quantity == 0);\n" +
	"       }\n" +
	"    }\n" +
	" }";
}
