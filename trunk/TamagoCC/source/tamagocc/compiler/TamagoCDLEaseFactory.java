package tamagocc.compiler;

import java.util.ArrayList;
import java.util.Collection;

import tamagocc.api.TBehavior;
import tamagocc.api.TComponent;
import tamagocc.api.TCondition;
import tamagocc.api.TExpression;
import tamagocc.api.TExtendService;
import tamagocc.api.TImplements;
import tamagocc.api.TIncludeService;
import tamagocc.api.TIndexExpression;
import tamagocc.api.TInvariant;
import tamagocc.api.TMethod;
import tamagocc.api.TNamespace;
import tamagocc.api.TOpeName;
import tamagocc.api.TParameter;
import tamagocc.api.TPercolator;
import tamagocc.api.TProperty;
import tamagocc.api.TProvide;
import tamagocc.api.TRefineService;
import tamagocc.api.TRequire;
import tamagocc.api.TService;
import tamagocc.api.TTamagoEntity;
import tamagocc.api.TTransition;
import tamagocc.api.TType;
import tamagocc.exception.TamagoCCException;
import tamagocc.impl.TIAtPre;
import tamagocc.impl.TIBehavior;
import tamagocc.impl.TICall;
import tamagocc.impl.TICast;
import tamagocc.impl.TICategory;
import tamagocc.impl.TIComposant;
import tamagocc.impl.TICondition;
import tamagocc.impl.TIExistColl;
import tamagocc.impl.TIExistRange;
import tamagocc.impl.TIExistSet;
import tamagocc.impl.TIForallColl;
import tamagocc.impl.TIForallRange;
import tamagocc.impl.TIForallSet;
import tamagocc.impl.TIInLabel;
import tamagocc.impl.TIIncludeService;
import tamagocc.impl.TIInvariant;
import tamagocc.impl.TIMethod;
import tamagocc.impl.TINoContract;
import tamagocc.impl.TIOperator;
import tamagocc.impl.TIParameter;
import tamagocc.impl.TIPercolator;
import tamagocc.impl.TIProvide;
import tamagocc.impl.TIRead;
import tamagocc.impl.TIRefineService;
import tamagocc.impl.TIRequire;
import tamagocc.impl.TIReturn;
import tamagocc.impl.TIService;
import tamagocc.impl.TISet;
import tamagocc.impl.TITamago;
import tamagocc.impl.TITransition;
import tamagocc.impl.TIType;
import tamagocc.impl.TIVariable;
import tamagocc.util.TamagoCCPool;
import tamagocc.util.Triplet;

public class TamagoCDLEaseFactory {

	public static TRefineService refine(String n, String m) {
		try {
			TService service =(TService) TamagoCCPool.getDefaultPool().getTreeAbstractSyntax(n, m);
			return new TIRefineService(n, m, service);
		}
		catch(TamagoCCException e) {
			throw new RuntimeException(e);
		}
	}

	public static TIncludeService include(String n, String m)  {
		try {
			TService service =(TService) TamagoCCPool.getDefaultPool().getTreeAbstractSyntax(n, m);
			return new TIIncludeService(n, m, service);
		}
		catch(TamagoCCException e) {
			throw new RuntimeException(e);
		}
		
	}

	public static TMethod method(TType tType, String name,
			Collection<TParameter> collection, String id,
			TCondition pre, TCondition post) {
		
		TICondition nopre = new TICondition(new TINoContract());
		TICondition nopost = new TICondition(new TINoContract());
		
		return new TIMethod(name,id,tType,collection,pre==null? nopre: pre,post==null? nopost : post);
	}

	public static TParameter param(TType t, String string) {
		return new TIParameter(string,t);
	}

	public static TTransition transition(String from, String to,
			String with, TExpression guard) {
		if(guard == null)
			return new TITransition(from,to,with);
		else
			return new TITransition(from,to,with,guard);
	}

	public static TInvariant invariant(TExpression tExpression, String msg) {
		if(msg == null)
			return new TIInvariant(tExpression, TICategory.NoCategory , "");
		else
			return new TIInvariant(tExpression, TICategory.NoCategory, msg);
	}

	public static TCondition precond(TExpression tExpression, String string) {
		if(string == null)
			return new TICondition(tExpression);
		else
			return new TICondition(tExpression, TICategory.NoCategory, string);
	}

	public static TCondition postcond(TExpression tExpression, String string) {
		if(string == null)
			return new TICondition(tExpression);
		else
			return new TICondition(tExpression, TICategory.NoCategory, string);
	}

	public static TExpression quant(String quant, String var, TType type,
			String kind, Collection<Object> collection,
			TExpression body) {
			if("set".equalsIgnoreCase(kind)) {
				Collection<TExpression> collexprs = new ArrayList<TExpression>();
				for (Object object : collection) {
					if(object instanceof TExpression)
						collexprs.add((TExpression) object);
				}
				TISet set = new TISet(type, collexprs);
				if("forall".equalsIgnoreCase(quant))
					return new TIForallSet(type, new TIVariable(var), set, body);
				else
					return new TIExistSet(type, new TIVariable(var), set, body);
			}
			else if("coll".equalsIgnoreCase(kind)) {
				TExpression coexpr = null;
				for (Object object : collection) {
					if(object instanceof TExpression)
						coexpr = (TExpression)object;
				}
				if(coexpr == null || collection.size() != 1)
					throw new RuntimeException("Quantifier expression on collection without correct expression!");
				if("forall".equalsIgnoreCase(quant))
					return new TIForallColl(type, new TIVariable(var), coexpr, body);
				else
					return new TIExistColl(type, new TIVariable(var), coexpr, body);
			}
			else if("range".equalsIgnoreCase(kind)) {
				TExpression start = null;
				TExpression end = null;
				for (Object object : collection) {
					if(object instanceof TExpression) {
						if(start == null)
							start = (TExpression) object;
						else
							end = (TExpression) object;
					}
				}
				if(start == null || end == null || collection.size() != 2)
					throw new RuntimeException("Quantifier expression on range is not correct!!!");
				if("forall".equalsIgnoreCase(quant))
					return new TIForallRange(type, new TIVariable(var), start, end, body);
				else
					return new TIExistRange(type, new TIVariable(var), start, end, body);
			}
			else
				throw new RuntimeException("Unknow kind of quantifier!!");
	}

	public static TExpression operator(String op, ArrayList<TExpression> exprs) {
		if(op == null) {
			if(exprs.size() == 1)
				return exprs.get(0);
			else
				throw new RuntimeException("unknow operator");
		}
		TOpeName ope = null;
		if("+".equalsIgnoreCase(op))
			ope = TOpeName.opPlus;
		else if("-".equalsIgnoreCase(op))
			ope = TOpeName.opMinus;
		else if("*".equalsIgnoreCase(op))
			ope = TOpeName.opTimes;
		else if("/".equalsIgnoreCase(op) || "div".equalsIgnoreCase(op))
			ope = TOpeName.opQuo;
		else if("%".equalsIgnoreCase(op) || "mod".equalsIgnoreCase(op))
			ope = TOpeName.opMod;
		else if("<=".equals(op))
			ope = TOpeName.opInfEg;
		else if("<".equals(op))
			ope = TOpeName.opInf;
		else if(">".equals(op))
			ope = TOpeName.opSup;
		else if(">=".equals(op))
			ope = TOpeName.opSupEg;
		else if("and".equalsIgnoreCase(op) || "&&".equals(op))
			ope = TOpeName.opAnd;
		else if("or".equalsIgnoreCase(op) || "||".equals(op))
			ope = TOpeName.opOr;
		else if("=>".equalsIgnoreCase(op) || "imply".equalsIgnoreCase(op) || "==>".equals(op))
			ope = TOpeName.opImply;
		else if("<=>".equalsIgnoreCase(op) || "equiv".equalsIgnoreCase(op) || "<==>".equals(op))
			ope = TOpeName.opEquiv;
		else if("==".equals(op) || "=".equals(op))
			ope = TOpeName.opEg;
		else if("!=".equals(op) || "<>".equals(op))
			ope = TOpeName.opNe;
		else
			throw new RuntimeException("Unknown operator '"+op+"'");
		TIOperator expr = new TIOperator(ope,exprs);
		return expr;
	}

	public static TTamagoEntity entity(String mod, Collection<TNamespace> uses,
			TTamagoEntity value) {
		TITamago root = (TITamago) value;
		root.setModule(mod);
		root.addNamespaces(uses);
		return root;
	}

	public static TService service(TService source,String name, Collection<TImplements> impls,
			Collection<TRefineService> refs, Collection<TIncludeService> incs,
			Collection<TProperty> props, Collection<TInvariant> invs,
			Collection<TMethod> meths, TBehavior beh) {
		ArrayList<TExtendService> extensions = new ArrayList<TExtendService>(refs);
		extensions.addAll(incs);
		TIService service = new TIService(name, "","",meths, props,invs,beh,extensions,impls,new ArrayList<TNamespace>(), new ArrayList<TType>());
		return service;
	}

	public static TRequire require(String n, String m, String l) {
		try {
			TService service = (TService)TamagoCCPool.getDefaultPool().getTreeAbstractSyntax(n, m);
			return new TIRequire(l, n, m, service);
		} catch (TamagoCCException e) {
			throw new RuntimeException(e);
		}
	}

	public static TProvide provide(String n, String m) {
		TService tamago;
		try {
			tamago = (TService) TamagoCCPool.getDefaultPool().getTreeAbstractSyntax(n, m);
			return new TIProvide(n, m, tamago);
		} catch (TamagoCCException e) {
			throw new RuntimeException(e);
		}
		
	}

	public static TComponent component(TComponent source, String name,
			Collection<TImplements> impls, Collection<TProvide> refs,
			Collection<TRequire> incs, Collection<TProperty> props,
			Collection<TInvariant> invs, Collection<TMethod> meths,
			TBehavior beh, Collection<TPercolator> percos) {
		
		if(percos.size() == 0)
			percos.add(TIPercolator.getAllPercolator());
		
		return new TIComposant(name, "", props, refs, incs, invs, meths, beh==null? TIBehavior.NoBehavior : beh, percos, impls,new ArrayList<TNamespace>(), new ArrayList<TType>());
	}

	public static TExpression genAtomic(String scope,
			TExpression sub,
			Triplet<TExpression, Collection<TExpression>, Boolean> d)
	{

		TIndexExpression iscope;
		boolean markVar = false;
		
		if(scope.startsWith("#"))
			iscope = new TIRead(scope.substring(1));
		else if("@return".equalsIgnoreCase(scope))
			iscope = new TIReturn();
		else if("this".equalsIgnoreCase(scope))
			iscope = new TIVariable("this");
		else {
			iscope = new TIVariable(scope);
			markVar = true;
		}
		TExpression escope = iscope;
		
		// fin de l'expression
		TExpression idx = d.l();
		Collection<TExpression> args = d.c();
		boolean c = d.r();
		if(idx != null) {
			iscope.setIndex(idx);
		}
		if(args != null) {
			if(markVar) {
				escope = new TICall(scope,args); 
			}
		}
		
		if(c) {
			escope = new TIAtPre(escope);
		}
		
		if(sub != null) {
			escope = new TIInLabel(escope, sub);
		}
		
		return escope;
	}

	public static TExpression genCast(String string, TExpression tExpression) {
		TType type = TIType.generateType(string);
		TICast cast = new TICast(type, tExpression);
		return cast;
	}

	

}
