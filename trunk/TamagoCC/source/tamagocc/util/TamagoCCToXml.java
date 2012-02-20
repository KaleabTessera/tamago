/**
 * 
 */
package tamagocc.util;

import java.io.OutputStream;
import java.util.Iterator;

import tamagocc.api.TAccess;
import tamagocc.api.TAllow;
import tamagocc.api.TAssembly;
import tamagocc.api.TAtPre;
import tamagocc.api.TBehavior;
import tamagocc.api.TBind;
import tamagocc.api.TBool;
import tamagocc.api.TCall;
import tamagocc.api.TCast;
import tamagocc.api.TCategory;
import tamagocc.api.TComponent;
import tamagocc.api.TComposite;
import tamagocc.api.TCondition;
import tamagocc.api.TDefinition;
import tamagocc.api.TExistColl;
import tamagocc.api.TExistRange;
import tamagocc.api.TExistSet;
import tamagocc.api.TExport;
import tamagocc.api.TExpression;
import tamagocc.api.TExtendService;
import tamagocc.api.TForallColl;
import tamagocc.api.TForallRange;
import tamagocc.api.TForallSet;
import tamagocc.api.TImplements;
import tamagocc.api.TInLabel;
import tamagocc.api.TInState;
import tamagocc.api.TIncludeService;
import tamagocc.api.TInteger;
import tamagocc.api.TInvariant;
import tamagocc.api.TIsBound;
import tamagocc.api.TLanguageExpr;
import tamagocc.api.TMethod;
import tamagocc.api.TNamespace;
import tamagocc.api.TNil;
import tamagocc.api.TNoContract;
import tamagocc.api.TNot;
import tamagocc.api.TObject;
import tamagocc.api.TOpeName;
import tamagocc.api.TOperator;
import tamagocc.api.TParameter;
import tamagocc.api.TPercolator;
import tamagocc.api.TProperty;
import tamagocc.api.TProvide;
import tamagocc.api.TRead;
import tamagocc.api.TReal;
import tamagocc.api.TRefineService;
import tamagocc.api.TRequire;
import tamagocc.api.TReturn;
import tamagocc.api.TService;
import tamagocc.api.TSet;
import tamagocc.api.TState;
import tamagocc.api.TString;
import tamagocc.api.TTamago;
import tamagocc.api.TTamagoEntity;
import tamagocc.api.TTransition;
import tamagocc.api.TType;
import tamagocc.api.TVariable;
import tamagocc.api.TExpression.ExprType;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCCToXml  {

	
	public static void generateXMLFile(TTamago t, OutputStream os) throws TamagoCCException {
		XMLGeneratorVisitor visitor = new XMLGeneratorVisitor(os);
		t.visit(visitor);
		
	}

	private static class XMLGeneratorVisitor implements TamagoCCVisitor {
	
		private TamagoCCIndentator indent;
		
		
		public XMLGeneratorVisitor(OutputStream os) throws TamagoCCException {
			indent = new TamagoCCIndentator(os);
			indent.println("<?xml version=\"1.0\" ?>");
			indent.println("<?xml-stylesheet type=\"text/xsl\" href=\"http://tamago.googlecode.com/svn/trunk/TamagoCC/TamagoCDL.xsl\" ?>");
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitAccess(tamagocc.api.TAccess)
		 */
		@Override
		public Object visitAccess(TAccess access) throws TamagoCCException {
			
			return ""+(access.canRead()? "r" : "")+(access.canWrite()? "w" : "");
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitAllow(tamagocc.api.TAllow)
		 */
		@Override
		public Object visitAllow(TAllow allow) throws TamagoCCException {
			throw new TamagoCCException("visitAllow shouldnt be called");
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitAssembly(tamagocc.api.TAssembly)
		 */
		@Override
		public Object visitAssembly(TAssembly assembly) throws TamagoCCException {
			throw new TamagoCCException("visitAssembly shouldnt be called");
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitAtPre(tamagocc.api.TAtPre)
		 */
		@Override
		public Object visitAtPre(TAtPre pre) throws TamagoCCException {
			indent.open("atpre");
			pre.getExpression().visit(this);
			indent.close("atpre");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitBehavior(tamagocc.api.TBehavior)
		 */
		@Override
		public Object visitBehavior(TBehavior behavior) throws TamagoCCException {
			
			if(behavior.getDefaultState() != null && behavior.getDefaultState().length() > 0) { 
				indent.println("<behavior default=\""+behavior.getDefaultState()+"\">");
				indent.shiftright();
				indent.open("states");
				Iterator<TState> states = behavior.getStates();
				while(states.hasNext()) {
					TState state = states.next();
					indent.println("<state name=\""+state.getState()+"\" >");
					indent.shiftright();
					Iterator<TAllow> allows = state.getAllow();
					while(allows.hasNext()) {
						indent.println("<allow method=\""+allows.next().getMethod()+"\" />");
					}
					indent.shiftleft();
					indent.println("</state>");
				}
				indent.close("states");
				indent.open("transitions");
				Iterator<TTransition> trans = behavior.getTransitions();
				while(trans.hasNext()) {
					TTransition t = trans.next();
					indent.open("transition", "from",t.getFrom(),"to",t.getTo(),"with",t.getMethod());
					if(t.getCondition().getCat() != ExprType.NOCONTRACT) {
						indent.open("condition");
						t.getCondition().visit(this);
						indent.close("condition");
					}
					indent.close("transition");
				}
				indent.close("transitions");
				indent.shiftleft();
				indent.println("</behavior>");
			}
			
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitBind(tamagocc.api.TBind)
		 */
		@Override
		public Object visitBind(TBind bind) throws TamagoCCException {
			throw new TamagoCCException("visitBind shouldnt be called");
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitBool(tamagocc.api.TBool)
		 */
		@Override
		public Object visitBool(TBool bool) throws TamagoCCException {
			indent.openclose("bool", "value",""+bool.getValue());
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitCall(tamagocc.api.TCall)
		 */
		@Override
		public Object visitCall(TCall call) throws TamagoCCException {
			indent.open("call","method",call.getName());
			indent.open("arguments");
			Iterator<TExpression> exprs = call.getArguments();
			while(exprs.hasNext()) {
				TExpression expr = exprs.next();
				expr.visit(this);
			}
			indent.close("arguments");
			indent.close("call");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitCast(tamagocc.api.TCast)
		 */
		@Override
		public Object visitCast(TCast cast) throws TamagoCCException {
			indent.open("cast","type",cast.getType().getType());
			cast.getExpression().visit(this);
			indent.close("cast");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitCategory(tamagocc.api.TCategory)
		 */
		@Override
		public Object visitCategory(TCategory category) throws TamagoCCException {
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitComponent(tamagocc.api.TComponent)
		 */
		@Override
		public Object visitComponent(TComponent component) throws TamagoCCException {
			indent.open("component", "name",component.getName(),
					"module",component.getModule());
			
			indent.open("coretypes");
			Iterator<TImplements> impls = component.getImplements().iterator();
			while(impls.hasNext()) {
				impls.next().visit(this);
			}
			indent.close("coretypes");
			
			indent.open("properties");
			Iterator<TProperty> props =  component.getProperties();
			while(props.hasNext()) {
				props.next().visit(this);
			}
			indent.close("properties");
			
			indent.open("percolators");
			Iterator<TPercolator> percos =  component.getAllowedPercolators();
			while(percos.hasNext()) {
				TPercolator perco = percos.next();
				perco.visit(this);
			}
			indent.close("percolators");
			
			indent.open("provides");
			Iterator<TProvide> pros =  component.getProvides();
			while(pros.hasNext()) {
				pros.next().visit(this);
			}
			indent.close("provides");
			
			indent.open("requires");
			Iterator<TRequire> reqs =  component.getRequires();
			while(reqs.hasNext()) {
				reqs.next().visit(this);
			}
			indent.close("requires");
			
			
			
			indent.open("invariants");
			Iterator<TInvariant> invs = component.getInvariants();
			while(invs.hasNext()) {
				invs.next().visit(this);
			}
			indent.close("invariants");
			
			indent.open("methods");
			Iterator<TMethod> meths = component.getMethods();
			while(meths.hasNext()) {
				meths.next().visit(this);
			}
			indent.close("methods");
			
			component.getBehavior().visit(this);
			
			indent.close("component");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitComposite(tamagocc.api.TComposite)
		 */
		@Override
		public Object visitComposite(TComposite composite) throws TamagoCCException {
			throw new TamagoCCException("visitComposite shouldnt be called");
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitCondition(tamagocc.api.TCondition)
		 */
		@Override
		public Object visitCondition(TCondition condition) throws TamagoCCException {
			throw new TamagoCCException("visitCondition shouldnt be called");
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitDefinition(tamagocc.api.TDefinition)
		 */
		@Override
		public Object visitDefinition(TDefinition definition)
		throws TamagoCCException {
			throw new TamagoCCException("visitDefinition shouldnt be called");
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitExistColl(tamagocc.api.TExistColl)
		 */
		@Override
		public Object visitExistColl(TExistColl coll) throws TamagoCCException {
			indent.open("quantifier",
					"quantifier","exist",
					"variable",coll.getVariable().getVariable(),
					"type",coll.getType().getType());
			indent.open("collection");
			coll.getCollection().visit(this);
			indent.close("collection");
			indent.open("body");
			coll.getBody().visit(this);
			indent.close("body");
			indent.close("quantifier");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitExistRange(tamagocc.api.TExistRange)
		 */
		@Override
		public Object visitExistRange(TExistRange q)
		throws TamagoCCException {
			indent.open("quantifier",
					"quantifier","exist",
					"variable",q.getVariable().getVariable(),
					"type",q.getType().getType());
			indent.open("range");
			indent.open("min");
			q.getMin().visit(this);
			indent.close("min");
			indent.open("max");
			q.getMax().visit(this);
			indent.close("max");
			indent.open("body");
			q.getBody().visit(this);
			indent.close("body");
			indent.close("quantifier");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitExistSet(tamagocc.api.TExistSet)
		 */
		@Override
		public Object visitExistSet(TExistSet q) throws TamagoCCException {
			indent.open("quantifier",
					"quantifier","exist",
					"variable",q.getVariable().getVariable(),
					"type",q.getType().getType());
			indent.open("set");
			for(TExpression expr : q.getSet().getElements()) {
				indent.open("element");
				expr.visit(this);
				indent.close("element");
			}
			indent.close("set");
			indent.open("body");
			q.getBody().visit(this);
			indent.close("body");
			indent.close("quantifier");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitExport(tamagocc.api.TExport)
		 */
		@Override
		public Object visitExport(TExport export) throws TamagoCCException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitExpression(tamagocc.api.TExpression)
		 */
		@Override
		public Object visitExpression(TExpression e) throws TamagoCCException {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitExtendService(tamagocc.api.TExtendService)
		 */
		@Override
		public Object visitExtendService(TExtendService extendservice)
		throws TamagoCCException {
			if(extendservice instanceof TRefineService) {
				indent.openclose("refine", "service",extendservice.getServiceName(),"module",extendservice.getModuleService());
			}
			else {
				indent.openclose("include", "service",extendservice.getServiceName(),"module",extendservice.getModuleService());
			}
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitForallColl(tamagocc.api.TForallColl)
		 */
		@Override
		public Object visitForallColl(TForallColl coll) throws TamagoCCException {
			indent.open("quantifier",
					"quantifier","forall",
					"variable",coll.getVariable().getVariable(),
					"type",coll.getType().getType());
			indent.open("collection");
			coll.getCollection().visit(this);
			indent.close("collection");
			indent.open("body");
			coll.getBody().visit(this);
			indent.close("body");
			indent.close("quantifier");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitForallRange(tamagocc.api.TForallRange)
		 */
		@Override
		public Object visitForallRange(TForallRange q)
		throws TamagoCCException {
			
			indent.open("quantifier",
					"quantifier","forall",
					"variable",q.getVariable().getVariable(),
					"type",q.getType().getType());
			indent.open("range");
			indent.open("min");
			q.getMin().visit(this);
			indent.close("min");
			indent.open("max");
			q.getMax().visit(this);
			indent.close("max");
			indent.open("body");
			q.getBody().visit(this);
			indent.close("body");
			indent.close("quantifier");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitForallSet(tamagocc.api.TForallSet)
		 */
		@Override
		public Object visitForallSet(TForallSet q) throws TamagoCCException {
			indent.open("quantifier",
					"quantifier","forall",
					"variable",q.getVariable().getVariable(),
					"type",q.getType().getType());
			indent.open("set");
			for(TExpression expr : q.getSet().getElements()) {
				indent.open("element");
				expr.visit(this);
				indent.close("element");
			}
			indent.close("set");
			indent.open("body");
			q.getBody().visit(this);
			indent.close("body");
			indent.close("quantifier");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitImplements(tamagocc.api.TImplements)
		 */
		@Override
		public Object visitImplements(TImplements impl) throws TamagoCCException {
			indent.openclose("implements", "type",impl.getNameType(),"module",impl.getModule());
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitInLabel(tamagocc.api.TInLabel)
		 */
		@Override
		public Object visitInLabel(TInLabel inlabel) throws TamagoCCException {
			indent.open("in");
			indent.open("scope");
			inlabel.getTarget().visit(this);
			indent.close("scope");
			indent.open("target");
			inlabel.getSubTerm().visit(this);
			indent.close("target");
			indent.close("in");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitIncludeService(tamagocc.api.TIncludeService)
		 */
		@Override
		public Object visitIncludeService(TIncludeService extendservice)
		throws TamagoCCException {
			indent.openclose("include", "service",extendservice.getServiceName(),"module",extendservice.getModuleService());
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitInteger(tamagocc.api.TInteger)
		 */
		@Override
		public Object visitInteger(TInteger integer) throws TamagoCCException {
			indent.openclose("int", "value",""+integer.getValue());
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitInvariant(tamagocc.api.TInvariant)
		 */
		@Override
		public Object visitInvariant(TInvariant invariant) throws TamagoCCException {
			indent.open("invariant", "message",invariant.getMessage());
			invariant.getExpression().visit(this);
			indent.close("invariant");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitLanguageExpr(tamagocc.api.TLanguageExpr)
		 */
		@Override
		public Object visitLanguageExpr(TLanguageExpr languageExpr)
		throws TamagoCCException {
			indent.print("<lang-expr>");
			indent.print(languageExpr.getExpression());
			indent.println("</lang-expr>");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitMethod(tamagocc.api.TMethod)
		 */
		@Override
		public Object visitMethod(TMethod method) throws TamagoCCException {
			indent.open("method", "name",method.getName(),
					"rettype",method.getType().getType(),
					"id",method.getID());
			indent.open("parameters");
			Iterator<TParameter> params = method.getParameters();
			while(params.hasNext()) {
				TParameter param = params.next();
				param.visit(this); 
			}
			indent.close("parameters");
			if(method.getPrecondition().getExpression().getCat() != ExprType.NOCONTRACT) {
				indent.open("pre","message",method.getPrecondition().getMessage());
				method.getPrecondition().getExpression().visit(this);
				indent.close("pre");
			}
			if(method.getPostcondition().getExpression().getCat() != ExprType.NOCONTRACT) {
				indent.open("post","message",method.getPostcondition().getMessage());
				method.getPostcondition().getExpression().visit(this);
				indent.close("post");
			}
			indent.close("method");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitNamespace(tamagocc.api.TNamespace)
		 */
		@Override
		public Object visitNamespace(TNamespace namespace) throws TamagoCCException {
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitNil(tamagocc.api.TNil)
		 */
		@Override
		public Object visitNil(TNil nil) throws TamagoCCException {
			indent.openclose("nil");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitNoContract(tamagocc.api.TNoContract)
		 */
		@Override
		public Object visitNoContract(TNoContract nocontract)
		throws TamagoCCException {
			indent.openclose("nocontract");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitNot(tamagocc.api.TNot)
		 */
		@Override
		public Object visitNot(TNot not) throws TamagoCCException {
			indent.open("not");
			not.getTerm().visit(this);
			indent.close("not");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitObject(tamagocc.api.TObject)
		 */
		@Override
		public Object visitObject(TObject object) throws TamagoCCException {
			throw new TamagoCCException("visitObject shouldnt be called");
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitOpeName(tamagocc.api.TOpeName)
		 */
		@Override
		public Object visitOpeName(TOpeName opeName) throws TamagoCCException {
			throw new TamagoCCException("visitOpeName shouldnt be called");
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitOperator(tamagocc.api.TOperator)
		 */
		@Override
		public Object visitOperator(TOperator operator) throws TamagoCCException {
			indent.open("operator", "name",toXmlOperator(operator.getOperator()));
			Iterator<TExpression> exprs = operator.getOperands();
			while(exprs.hasNext()) {
				TExpression expr = exprs.next();
				expr.visit(this);
			}
			indent.close("operator");
			return null;
		}

		private String toXmlOperator(TOpeName operator) {
			switch(operator.getID()) {
			case TOpeName.EG: return "eq";
	        case TOpeName.NE: return "ne";
	        case TOpeName.INF: return "lt";
	        case TOpeName.INFEG: return "le";
	        case TOpeName.SUP: return "gt";
	        case TOpeName.SUPEG: return "ge";
	        case TOpeName.PLUS: return "add";
	        case TOpeName.MINUS: return "sub";
	        case TOpeName.TIMES: return "mul";
	        case TOpeName.QUO: return "quo";
	        case TOpeName.MOD: return "mod";
	        case TOpeName.AND: return "and";
	        case TOpeName.OR: return "or";
	        case TOpeName.XOR: return "xor";
	        case TOpeName.IMPLY: return "impl";
	        case TOpeName.EQUIV: return "equiv";
	        default:
	        	return "unkown";
			}
		}
		
		/*
		 *       <xs:enumeration value="eq" />
      <xs:enumeration value="ne" />
      <xs:enumeration value="lt" />
      <xs:enumeration value="le" />
      <xs:enumeration value="gt" />
      <xs:enumeration value="ge" />
      <xs:enumeration value="add" />
      <xs:enumeration value="sub" />
      <xs:enumeration value="mul" />
      <xs:enumeration value="mod" />
      <xs:enumeration value="quo" />
      <xs:enumeration value="and" />
      <xs:enumeration value="or" />
      <xs:enumeration value="impl" />
      <xs:enumeration value="equiv" />
      */

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitParameter(tamagocc.api.TParameter)
		 */
		@Override
		public Object visitParameter(TParameter parameter) throws TamagoCCException {
			indent.openclose("parameter","name",parameter.getName(),"type",parameter.getType().getType());
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitPercolator(tamagocc.api.TPercolator)
		 */
		@Override
		public Object visitPercolator(TPercolator percolator)
		throws TamagoCCException {
			indent.openclose("percolator", "name",percolator.getName());
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitProperty(tamagocc.api.TProperty)
		 */
		@Override
		public Object visitProperty(TProperty property) throws TamagoCCException {
			indent.openclose("property","name",property.getName(),"type",property.getType().getType(),"access",""+ visitAccess(property.getAccess()));
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitProvide(tamagocc.api.TProvide)
		 */
		@Override
		public Object visitProvide(TProvide provide) throws TamagoCCException {
			indent.openclose("provide","service",provide.getServiceName(),"module",provide.getModule());
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitRead(tamagocc.api.TRead)
		 */
		@Override
		public Object visitRead(TRead read) throws TamagoCCException {
			if(!read.hasIndex())
				indent.openclose("read", "property",read.getName());
			else {
				indent.open("read", "property",read.getName());
				indent.open("index");
				read.getIndex().visit(this);
				indent.close("index");
				indent.close("read");
			}
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitReal(tamagocc.api.TReal)
		 */
		@Override
		public Object visitReal(TReal real) throws TamagoCCException {
			indent.openclose("real", "value",""+real.getValue());
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitRefineService(tamagocc.api.TRefineService)
		 */
		@Override
		public Object visitRefineService(TRefineService extendservice)
		throws TamagoCCException {
			indent.openclose("refine", "service",extendservice.getServiceName(),"module",extendservice.getModuleService());
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitRequire(tamagocc.api.TRequire)
		 */
		@Override
		public Object visitRequire(TRequire require) throws TamagoCCException {
			indent.openclose("require", "service",require.getServiceName(),
					"module",require.getModule(),
					"label",require.getLabel());
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitReturn(tamagocc.api.TReturn)
		 */
		@Override
		public Object visitReturn(TReturn ret) throws TamagoCCException {
			if(ret.hasIndex()) {
				indent.open("return");
				ret.getIndex().visit(this);
				indent.close("return");
			}
			else
				indent.openclose("return");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitService(tamagocc.api.TService)
		 */
		@Override
		public Object visitService(TService service) throws TamagoCCException {
			indent.open("service", "name",service.getName(),
					"module",service.getModule());
			indent.open("extends");
			Iterator<TExtendService> exts =  service.getExtends();
			while(exts.hasNext()) {
				exts.next().visit(this);
			}
			indent.close("extends");
			
			indent.open("coretypes");
			Iterator<TImplements> impls = service.getImplements().iterator();
			while(impls.hasNext()) {
				impls.next().visit(this);
			}
			indent.close("coretypes");
			
			indent.open("properties");
			Iterator<TProperty> props =  service.getProperties();
			while(props.hasNext()) {
				props.next().visit(this);
			}
			indent.close("properties");
			
			indent.open("invariants");
			Iterator<TInvariant> invs = service.getInvariants();
			while(invs.hasNext()) {
				invs.next().visit(this);
			}
			indent.close("invariants");
			
			indent.open("methods");
			Iterator<TMethod> meths = service.getMethods();
			while(meths.hasNext()) {
				meths.next().visit(this);
			}
			indent.close("methods");
			
			if(service.getBehavior() != null)
				service.getBehavior().visit(this);
			
			indent.close("service");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitSet(tamagocc.api.TSet)
		 */
		@Override
		public Object visitSet(TSet set) throws TamagoCCException {
			throw new TamagoCCException("visitSet shouldnt be called");
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitString(tamagocc.api.TString)
		 */
		@Override
		public Object visitString(TString string) throws TamagoCCException {
			indent.print("<string>");
			indent.print(string.getValue());
			indent.println("</string>");
			return null;
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitTamago(tamagocc.api.TTamago)
		 */
		@Override
		public Object visitTamago(TTamago tamago) throws TamagoCCException {
			throw new TamagoCCException("visitTamago shouldnt be called");
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitTamagoEntity(tamagocc.api.TTamagoEntity)
		 */
		@Override
		public Object visitTamagoEntity(TTamagoEntity entity)
		throws TamagoCCException {
			throw new TamagoCCException("visitTamagoEntity shouldnt be called");
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitType(tamagocc.api.TType)
		 */
		@Override
		public Object visitType(TType type) throws TamagoCCException {
			throw new TamagoCCException("visitType shouldnt be called");
		}

		/**
		 * @see tamagocc.util.TamagoCCVisitor#visitVariable(tamagocc.api.TVariable)
		 */
		@Override
		public Object visitVariable(TVariable variable) throws TamagoCCException {
			if(variable.hasIndex()) {
				if(variable.forcedType())
					indent.open("variable","name",variable.getVariable(),"type",variable.getType().getType());
				else
					indent.open("variable","name",variable.getVariable());
				variable.getIndex().visit(this);
				indent.close("variable");
			}
			else {
				if(variable.forcedType())
					indent.openclose("variable","name",variable.getVariable(),"type",variable.getType().getType());
				else
					indent.openclose("variable","name",variable.getVariable());
			}
			return null;
		}

		@Override
		public Object visitInState(TInState instate) throws TamagoCCException {
			indent.open("instate");
			for (String st : instate.getInState()) {
				indent.openclose("state", "name",st);
			}
			indent.close("instate");
			return null;
		}

		@Override
		public Object visitIsBound(TIsBound tiIsBound) throws TamagoCCException {
			indent.openclose("isbound","label",tiIsBound.getLabel());
			return null;
		}

	}
}