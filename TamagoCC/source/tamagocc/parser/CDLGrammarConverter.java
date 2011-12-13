package tamagocc.parser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javapop.framework.parser.MaybeParse;
import javapop.utils.Nonuple;
import javapop.utils.Pair;
import javapop.utils.Quadruple;
import javapop.utils.Quintuple;
import javapop.utils.Sixuple;
import javapop.utils.Triple;
import tamagocc.api.TAccess;
import tamagocc.api.TAllow;
import tamagocc.api.TBehavior;
import tamagocc.api.TCondition;
import tamagocc.api.TExpression;
import tamagocc.api.TExtendService;
import tamagocc.api.TImplements;
import tamagocc.api.TInvariant;
import tamagocc.api.TMethod;
import tamagocc.api.TNamespace;
import tamagocc.api.TParameter;
import tamagocc.api.TProperty;
import tamagocc.api.TService;
import tamagocc.api.TState;
import tamagocc.api.TTransition;
import tamagocc.api.TType;
import tamagocc.exception.TamagoCCException;
import tamagocc.impl.TIAccess;
import tamagocc.impl.TIAllow;
import tamagocc.impl.TIBehavior;
import tamagocc.impl.TICategory;
import tamagocc.impl.TICondition;
import tamagocc.impl.TIImplements;
import tamagocc.impl.TIIncludeService;
import tamagocc.impl.TIInvariant;
import tamagocc.impl.TIMethod;
import tamagocc.impl.TINamespace;
import tamagocc.impl.TINoContract;
import tamagocc.impl.TIParameter;
import tamagocc.impl.TIPercolator;
import tamagocc.impl.TIProperty;
import tamagocc.impl.TIRefineService;
import tamagocc.impl.TIService;
import tamagocc.impl.TIState;
import tamagocc.impl.TITransition;
import tamagocc.impl.TIType;
import tamagocc.parser.cdlast.CDLAtPre;
import tamagocc.parser.cdlast.CDLBool;
import tamagocc.parser.cdlast.CDLCall;
import tamagocc.parser.cdlast.CDLCast;
import tamagocc.parser.cdlast.CDLExpression;
import tamagocc.parser.cdlast.CDLInLabel;
import tamagocc.parser.cdlast.CDLInfix;
import tamagocc.parser.cdlast.CDLInteger;
import tamagocc.parser.cdlast.CDLNil;
import tamagocc.parser.cdlast.CDLNot;
import tamagocc.parser.cdlast.CDLQColl;
import tamagocc.parser.cdlast.CDLQRange;
import tamagocc.parser.cdlast.CDLQSet;
import tamagocc.parser.cdlast.CDLRead;
import tamagocc.parser.cdlast.CDLReal;
import tamagocc.parser.cdlast.CDLReturn;
import tamagocc.parser.cdlast.CDLString;
import tamagocc.parser.cdlast.CDLVariable;
import tamagocc.util.TamagoCCPool;

public class CDLGrammarConverter {
	public CDLInteger convInteger(Object o) {
		return new CDLInteger(Integer.parseInt(o.toString()));
	}

	public CDLReal convReal(Object o) {
		return new CDLReal(Double.parseDouble((String) o));
	}

	public CDLInfix convOperatorInfix(Object o) {
		String s = (String) o;
		return new CDLInfix(s);
	}
	
	public CDLNot convNot(Object o) {
		return new CDLNot((String)o);
	}

	// -- Methods for default method values
	public Object getNull() {
		return null;
	}

	// -- Methods for default method values
	public tamagocc.api.TType convType(Object content) {
		return TIType.generateType(content.toString());
	}

	public tamagocc.api.TExpression convExpression(Object content) {
		CDLExpression expr = (CDLExpression)content;
		return expr.toTExpression();
	}

	public tamagocc.parser.cdlast.CDLBool convBoolean(Object content) {
		if("false".equals(content))
			return new CDLBool(false);
		else
			return new CDLBool(true);
	}

	
	public tamagocc.parser.cdlast.CDLString convString(Object content) {
		return new CDLString((String) content);
	}

	public tamagocc.parser.cdlast.CDLRead convRead(Object content) {
		return new CDLRead((String)content);
	}

	public tamagocc.parser.cdlast.CDLVariable convVar(Object content) {
		return new CDLVariable((String)content);
	}

	public tamagocc.parser.cdlast.CDLNil convNil(Object content) {
		return new CDLNil();
	}

	public tamagocc.parser.cdlast.CDLVariable convVarIdx(Object content) {
		Pair<String, CDLExpression> p = (Pair<String, CDLExpression>)content;
		return new CDLVariable(p.getFirst(), p.getSecond());
	}

	public tamagocc.parser.cdlast.CDLRead convReadIdx(Object content) {
		Pair<String, CDLExpression> p = (Pair<String, CDLExpression>)content;
		return new CDLRead(p.getFirst(), p.getSecond());
	}

	public tamagocc.parser.cdlast.CDLCast convCast(Object content) {
		Pair<String, CDLExpression> p = (Pair<String, CDLExpression>)content;
		return new CDLCast(TIType.generateType(p.getFirst()), p.getSecond());
	}

	public tamagocc.parser.cdlast.CDLCall convCall(Object content) {
		Pair<String, Collection<CDLExpression>> p = (Pair<String, Collection<CDLExpression>>)content;
		return new CDLCall(p.getFirst(), p.getSecond());
	}

	public tamagocc.parser.cdlast.CDLExpression convInLabel(Object content) {
		if(content instanceof Pair) {
			Pair<CDLExpression,CDLExpression> p = (Pair<CDLExpression, CDLExpression>)content; 
			return new CDLInLabel(p.getFirst(),p.getSecond());
		}
		else
			return (CDLExpression) content;
	}

	public tamagocc.parser.cdlast.CDLQSet convQSet(Object content) {
		Quintuple<String, String, String, List<CDLExpression>, CDLExpression> s = (Quintuple<String, String, String, List<CDLExpression>, CDLExpression>)content;
		return new CDLQSet(s.getFirst(), s.getSecond(), TIType.generateType(s.getThird()), s.getFifth(), s.getFourth());
	}

	public tamagocc.parser.cdlast.CDLQColl convQColl(Object content) {
		Quintuple<String, String, String, CDLExpression, CDLExpression> coll = (Quintuple<String, String, String, CDLExpression, CDLExpression>)content;
		return new CDLQColl(coll.getFirst(), coll.getSecond(), TIType.generateType(coll.getThird()), coll.getFifth() , coll.getFourth());
	}

	public tamagocc.parser.cdlast.CDLQRange convQRange(Object content) {
		Sixuple<String, String, String, CDLExpression, CDLExpression, CDLExpression> r= (Sixuple<String, String, String, CDLExpression, CDLExpression, CDLExpression>)content;
		return new CDLQRange(r.getFirst(), r.getSecond(), TIType.generateType(r.getThird()), r.getSixth(), r.getFourth(), r.getFifth());
	}

	public tamagocc.parser.cdlast.CDLAtPre convAtPre(Object content) {
		return new CDLAtPre();
	}

	public tamagocc.parser.cdlast.CDLReturn convReturn(Object content) {
		return new CDLReturn();
	}

	public tamagocc.api.TBehavior convBehavior(Object content) {
		Triple<MaybeParse<String>, Collection<TState>, Collection<TTransition>> p = (Triple<MaybeParse<String>, Collection<TState>, Collection<TTransition>>)content;
		TIBehavior t;
		if(!p.getFirst().hasResult())
			t=  new TIBehavior(p.getSecond(),p.getThird(),"");
		else
			t = new TIBehavior(p.getSecond(),p.getThird(),p.getFirst().getResult().getResult());
		return t;
	}

	public java.util.Collection<tamagocc.api.TState> convStates(Object content) {
		return (Collection<tamagocc.api.TState> )content;
	}

	public tamagocc.api.TState convState(Object content) {
		Pair<String, Collection<TAllow>> p = (Pair<String, Collection<TAllow>>)content;
		return new TIState(p.getFirst(), p.getSecond());
	}

	public tamagocc.api.TAllow convAllow(Object content) {
		return new TIAllow((String)content);
	}

	public java.util.Collection<tamagocc.api.TTransition> convTransitions(
			Object content) {
		return (Collection<tamagocc.api.TTransition>)content;
	}

	public tamagocc.api.TTransition convTransition(Object content) {
		Triple<String, String, String> p = (Triple<String, String, String>)content;
		return new TITransition(p.getFirst(), p.getSecond(), p.getThird());
	}
	public tamagocc.api.TTransition convTransitionG(Object content) {
		Quadruple<String, String, String, CDLExpression> p = (Quadruple<String, String, String, CDLExpression>)content;
		return new TITransition(p.getFirst(), p.getSecond(), p.getThird(), p.getFourth().toTExpression());
	}

	
	public tamagocc.api.TMethod convMethod(Object content) {
		Sixuple<String, String, Collection<TParameter>, MaybeParse<String>, MaybeParse<TCondition>, MaybeParse<TCondition>> s = (Sixuple<String, String, Collection<TParameter>, MaybeParse<String>, MaybeParse<TCondition>, MaybeParse<TCondition>>)content;
		String name = s.getSecond();
		String id = "";
		TType type = TIType.generateType(s.getFirst());
		Collection<TParameter> params = s.getThird();
		TCondition pre = new TICondition(new TINoContract(), new TICategory(""), "");;
		TCondition post = new TICondition(new TINoContract(), new TICategory(""), "");;
		
		if(s.getFourth().hasResult())
			id = s.getFourth().getResult().getResult();
		if(s.getFifth().hasResult())
			pre = s.getFifth().getResult().getResult();
		if(s.getSixth().hasResult())
			post = s.getSixth().getResult().getResult();
		
		TIMethod m = new TIMethod(name,id,type,params,pre,post);
		return m;
	}

	public tamagocc.api.TCondition convCondition(Object content) {
		if(content instanceof Pair) {
			Pair<TExpression, String> p = (Pair<TExpression, String>)content;
			return new TICondition(p.getFirst(), TICategory.NoCategory, p.getSecond());
		}
		else {
			TExpression p = (TExpression)content;
			return new TICondition(p, TICategory.NoCategory, "");
		}
	}

	public tamagocc.api.TInvariant convInvariant(Object content) {
		if(content instanceof Pair) {
			Pair<TExpression, String> p = (Pair<TExpression, String>)content;
			return new TIInvariant(p.getFirst(), TICategory.NoCategory, p.getSecond());
		}
		else {
			TExpression p = (TExpression)content;
			return new TIInvariant(p, TICategory.NoCategory, "");
		}
	}
	
	public tamagocc.api.TParameter convParameter(Object content) {
		Pair<String, String> p = (Pair<String, String>)content;
		return new TIParameter(p.getFirst(), TIType.generateType(p.getSecond()));
	}

	public tamagocc.api.TProperty convProperty(Object content) {
		Triple<TAccess, TType,String> p = (Triple<TAccess, TType,String>) content;
		return new TIProperty(p.getThird(),p.getSecond(),p.getFirst());
	}

	public tamagocc.api.TAccess convAccess(Object content) {
		if("readwrite".equals(content))
			return new TIAccess("readwrite");
		else if("write".equals(content))
			return new TIAccess("write");
		else
			return new TIAccess("read");
	}

	public tamagocc.api.TNamespace convNamespace(Object content) {
		return new TINamespace((String)content);
	}

	public tamagocc.api.TImplements convImplement(Object content) {
		return new TIImplements((String)content);
	}

	public java.lang.String convModule(Object content) {
		return (String)content;
	}

	public tamagocc.api.TIncludeService convInclude(Object content) {
		String name = (String)content;
		String module = "";
		int idx = name.lastIndexOf(".");
		if(idx == -1) {
			
		}
		else {
			module = name.substring(1,idx-1);
			name = name.substring(idx+1);
		}
		TService service;
		try {
			service = (TService) TamagoCCPool.getDefaultPool().getTreeAbstractSyntax(name, module);
			return new TIIncludeService(name, module, service);
		} catch (TamagoCCException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public tamagocc.api.TRefineService convRefine(Object content) {
		String name = (String)content;
		String module = "";
		int idx = name.lastIndexOf(".");
		if(idx == -1) {
			
		}
		else {
			module = name.substring(1,idx-1);
			name = name.substring(idx+1);
		}
		TService service;
		try {
			service = (TService) TamagoCCPool.getDefaultPool().getTreeAbstractSyntax(name, module);
			return new TIRefineService(name, module, service);
		} catch (TamagoCCException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public tamagocc.api.TPercolator convPercolator(Object content) {
		return new TIPercolator((String)content);
	}

	public tamagocc.api.TService convService(Object content) {
		@SuppressWarnings("unchecked")
		Nonuple<String, Collection<TNamespace>, String, Collection<TImplements>, Collection<TExtendService>, Collection<TProperty>, Collection<TInvariant>, Collection<TMethod>, MaybeParse<TBehavior>> n = (Nonuple<String, Collection<TNamespace>, String, Collection<TImplements>, Collection<TExtendService>, Collection<TProperty>, Collection<TInvariant>, Collection<TMethod>, MaybeParse<TBehavior>>)content;
		String name = n.getThird();
		String module = n.getFirst();
		Collection<TMethod> methods = n.getEighth();
    	Collection<TProperty> properties = n.getSixth();
    	Collection<TInvariant> invariants = n.getSeventh();
    	Collection<TExtendService> extension = n.getFifth();
    	Collection<TImplements> impls = n.getFourth();
		Collection<TNamespace> namespaces = n.getSecond();
		Collection<TType> paramtypes = new ArrayList<TType>();
		TBehavior behavior = TIBehavior.NoBehavior;
		if(n.getNinth().hasResult())
			behavior = n.getNinth().getResult().getResult();
		return new TIService(name, module, "", methods, properties, invariants, behavior, extension, impls, namespaces, paramtypes);
	}

	public tamagocc.api.TRequire convRequire(Object content) {
		// TODO complete this method
		return null;
	}

	public tamagocc.api.TProvide convProvide(Object content) {
		// TODO complete this method
		return null;
	}

	public tamagocc.api.TComponent convComponent(Object content) {
		// TODO complete this method
		return null;
	}

	
	public static void main(String args[]) {
		String name = "*";//"org.name.toto.Gnu";
		TINamespace n = new TINamespace(name);
		System.out.println(n.getNamespace());
		System.out.println(n.getType());
		
	}
}
