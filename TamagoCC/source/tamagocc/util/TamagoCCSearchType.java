/**
 * 
 */
package tamagocc.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import tamagocc.api.CatType;
import tamagocc.api.TAssembly;
import tamagocc.api.TAtPre;
import tamagocc.api.TBool;
import tamagocc.api.TCall;
import tamagocc.api.TCast;
import tamagocc.api.TComponent;
import tamagocc.api.TComposite;
import tamagocc.api.TDefinition;
import tamagocc.api.TExistColl;
import tamagocc.api.TExistRange;
import tamagocc.api.TExistSet;
import tamagocc.api.TExpression;
import tamagocc.api.TExpression.ExprType;
import tamagocc.api.TForallColl;
import tamagocc.api.TForallRange;
import tamagocc.api.TForallSet;
import tamagocc.api.TInLabel;
import tamagocc.api.TInState;
import tamagocc.api.TInteger;
import tamagocc.api.TLanguageExpr;
import tamagocc.api.TMethod;
import tamagocc.api.TNil;
import tamagocc.api.TNoContract;
import tamagocc.api.TNot;
import tamagocc.api.TOperator;
import tamagocc.api.TParameter;
import tamagocc.api.TProperty;
import tamagocc.api.TRead;
import tamagocc.api.TReal;
import tamagocc.api.TRequire;
import tamagocc.api.TReturn;
import tamagocc.api.TString;
import tamagocc.api.TTamago;
import tamagocc.api.TType;
import tamagocc.api.TVariable;
import tamagocc.exception.TamagoCCException;
import tamagocc.exception.TamagoCCTypeException;
import tamagocc.impl.TISimilarMethod;
import tamagocc.impl.TIType;

/**
 * try to find the type of a specified expression (in the first intermediate language).
 * 
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class TamagoCCSearchType implements TamagoCCExpressionVisitor {

	private TTamago entity;
	private TMethod method;
	private TExpression expression;
	private TType type;
	
	private Stack<TInLabel> scopes;
	
	/**
	 * 
	 */
	public TamagoCCSearchType(TTamago entity,TMethod method,Stack<TInLabel> scopes,TExpression expression) {
		super();
		this.entity = entity;
		this.expression = expression;
		this.method = method;
		this.type = null;
		this.scopes = scopes;
	}

	public TType getType() throws TamagoCCException {
		if(type == null) {
			type = (TType)expression.visitExpression(this);
		}
		return type;
	}
	
	
	/**
	 * @see tamagocc.util.TamagoCCExpressionVisitor#visitAtPre(tamagocc.api.TAtPre)
	 */
	public Object visitAtPre(TAtPre atpre) throws TamagoCCException {
		return atpre.getExpression().visitExpression(this);
	}

	/**
	 * @see tamagocc.util.TamagoCCExpressionVisitor#visitBool(tamagocc.api.TBool)
	 */
	public Object visitBool(TBool bool) throws TamagoCCException {
		return TIType.generateType("bool");
	}

	/**
	 * @see tamagocc.util.TamagoCCExpressionVisitor#visitCall(tamagocc.api.TCall)
	 */
	public Object visitCall(TCall call) throws TamagoCCException {
		try {
			TMethod similar = new TISimilarMethod(entity,method,call);
			TMethod usermethod= entity.getSimilarMethods(similar);
			return usermethod.getType();
		}
		catch(NoSuchElementException nsee) {
			throw new TamagoCCException("TamagoCCSearchType<visitCall> : impossible to find the type of the call");
		}
	}

	/**
	 * @see tamagocc.util.TamagoCCExpressionVisitor#visitReal(tamagocc.api.TReal)
	 */
	public Object visitReal(TReal f) throws TamagoCCException {
		return TIType.generateType("real");
	}

	/**
	 * @see tamagocc.util.TamagoCCExpressionVisitor#visitInteger(tamagocc.api.TInteger)
	 */
	public Object visitInteger(TInteger integer) throws TamagoCCException {
		return TIType.generateType("int");
	}

	/**
	 * @see tamagocc.util.TamagoCCExpressionVisitor#visitNoContract(tamagocc.api.TNoContract)
	 */
	public Object visitNoContract(TNoContract nocontract)
			throws TamagoCCException {
		throw new TamagoCCTypeException("NOContract cant be typed");
	}

	/**
	 * @see tamagocc.util.TamagoCCExpressionVisitor#visitNot(tamagocc.api.TNot)
	 */
	public Object visitNot(TNot not) throws TamagoCCException {
		TType typecorrect = (TType) not.getTerm().visitExpression(this);
		if(typecorrect.catType() != CatType.BOOL) {
			throw new TamagoCCTypeException("the operator not is used with not a boolean expression.");
		}
		else
			return typecorrect;
	}

	/**
	 * @see tamagocc.util.TamagoCCExpressionVisitor#visitOperator(tamagocc.api.TOperator)
	 */
	public Object visitOperator(TOperator operator) throws TamagoCCException {
		
		TType type = null;
		Iterator<? extends TExpression> operands = operator.getOperands();
		while(operands.hasNext()) {
			TExpression operand = (TExpression)operands.next();
			if(type == null)
				type = (TType)operand.visitExpression(this);
			else {
				TType typeoperand = (TType)operand.visitExpression(this);
				int compare = type.compareTo(typeoperand);
				if(compare == 0) { 
					// rien a faire on continue
				} else {
					throw new TamagoCCTypeException("In an operator, all operands don't have the same type.");
				}
			}
		}
		return type;		
	}

	private TType search(String propname) throws TamagoCCException {
		if(scopes.size() == 0) {
			throw new TamagoCCException("TamagoCCSearchType<visitInLabel> : unfound type for :"+propname);
		}
		TInLabel scope = scopes.pop();
		TType type = null;
		try {
			switch(entity.getTamagoType()) {
			case TTamago.TAMAGO_SERVICE:
				throw new TamagoCCException("TamagoCCSearchType<visitInLabel> : no label in a service");
			case TTamago.TAMAGO_COMPONENT:
				type = searchLabelInComponent(scope);
				break;
			case TTamago.TAMAGO_COMPOSITE:
				type = searchLabelInComposite(scope);
				break;
			case TTamago.TAMAGO_ASSEMBLY:
				type = searchLabelInAssembly(scope);
				break;
			default:
				throw new TamagoCCException("TamagoCCSeachType<visitInLabel> : ");
			}
		}
		catch(TamagoCCException exc) {
			search(propname);
		}
		finally {
			scopes.push(scope);
		}
		return type;
	}
	
	/**
	 * @see tamagocc.util.TamagoCCExpressionVisitor#visitRead(tamagocc.api.TRead)
	 */
	public Object visitRead(TRead read) throws TamagoCCException {
		String propertyname = read.getName();
		try {
			TProperty property = entity.getProperty(propertyname);
			return property.getType();
		}
		catch(NoSuchElementException nsee) {
			return search(propertyname);
		}
	}

	/**
	 * @see tamagocc.util.TamagoCCExpressionVisitor#visitString(tamagocc.api.TString)
	 */
	public Object visitString(TString string) throws TamagoCCException {
		return TIType.generateType("String");
	}

	/**
	 * @see tamagocc.util.TamagoCCExpressionVisitor#visitVariable(tamagocc.api.TVariable)
	 */
	public Object visitVariable(TVariable variable) throws TamagoCCException {
		// pour le moment on cherche juste dans les methodes les arguments
		if(variable.forcedType())
			return variable.getType();
		
		Iterator<TParameter> parameters = method.getParameters();
		while(parameters.hasNext()) {
			TParameter parameter = (TParameter)parameters.next();
			if(variable.getVariable().equals(parameter.getName())) {
				return parameter.getType();
			}
		}
		
		throw new TamagoCCTypeException("Unfind the type of the variable : "+variable.getVariable());
	}

	/**
	 * @see tamagocc.util.TamagoCCExpressionVisitor#visitInLabel(tamagocc.api.TInLabel)
	 */
	public Object visitInLabel(TInLabel inlabel) throws TamagoCCException {
		if(inlabel.getTarget().getCat() != ExprType.VARIABLE) {
			throw new TamagoCCException("TamagoCCSeachType<visitInLabel> : Cannot search type for this label");
		}
		
		switch(entity.getTamagoType()) {
		case TTamago.TAMAGO_SERVICE:
			throw new TamagoCCException("TamagoCCSearchType<visitInLabel> : no label in a service");
		case TTamago.TAMAGO_COMPONENT:
			return searchLabelInComponent(inlabel);
		case TTamago.TAMAGO_COMPOSITE:
			return searchLabelInComposite(inlabel);
		case TTamago.TAMAGO_ASSEMBLY:
			return searchLabelInAssembly(inlabel);
		default:
			throw new TamagoCCException("TamagoCCSeachType<visitInLabel> : ");
		}
	}

	private TType searchLabelInComponent(TInLabel inlabel) throws TamagoCCException {
		// chercher dans les requires
		Iterator<TRequire> requires = ((TComponent)entity).getRequires();
		while(requires.hasNext()) {
			TRequire require = (TRequire)requires.next();
			if(require.getLabel().equals(((TVariable)inlabel.getTarget()).getVariable())) {
				TamagoCCSearchType searchtype = new TamagoCCSearchType(require.getService(),null, new Stack<TInLabel>(),inlabel.getSubTerm());
				return searchtype.getType();
			}
		}
		throw new TamagoCCException("TamagoCCSearchType<searchLabelInComponent> : unfind label in requires services ("+inlabel.getTarget()+")");
	}
	
	private TType searchLabelInComposite(TInLabel inlabel) throws TamagoCCException {
		// chercher dans les requires
		Iterator<TRequire> requires = ((TComposite)entity).getRequires();
		while(requires.hasNext()) {
			TRequire require = (TRequire)requires.next();
			if(require.getLabel().equals(((TVariable)inlabel.getTarget()).getVariable())) {
				TamagoCCSearchType searchtype = new TamagoCCSearchType(require.getService(),null,new Stack<TInLabel>(),inlabel.getSubTerm());
				return searchtype.getType();
			}
		}
		
		Iterator<TDefinition> definitions = ((TComposite)entity).getDefinitions();
		while(definitions.hasNext()) {
			TDefinition definition = (TDefinition)definitions.next();
			if(definition.getComponentLabel().equals(((TVariable)inlabel.getTarget()).getVariable())) {
				TTamago component = TamagoCCPool.getDefaultPool().getTreeAbstractSyntax(definition.getComponentName(),definition.getComponentModule());
				TamagoCCSearchType searchtype = new TamagoCCSearchType(component,null,new Stack<TInLabel>(),inlabel.getSubTerm());
				return searchtype.getType();
			}
		}
		throw new TamagoCCException("TamagoCCSearchType<searchLabelInComposite> : unfind label in requires services and definitions ("+inlabel.getTarget()+")");
	}
	
	private TType searchLabelInAssembly(TInLabel inlabel) throws TamagoCCException {
		// on cherche dans les definitions
		Iterator<TDefinition> definitions = ((TAssembly)entity).getDefinitions();
		while(definitions.hasNext()) {
			TDefinition definition = (TDefinition)definitions.next();
			if(definition.getComponentLabel().equals(((TVariable)inlabel.getTarget()).getVariable())) {
				TTamago component = TamagoCCPool.getDefaultPool().getTreeAbstractSyntax(definition.getComponentName(),definition.getComponentModule());
				TamagoCCSearchType searchtype = new TamagoCCSearchType(component,null,new Stack<TInLabel>(),inlabel.getSubTerm());
				return searchtype.getType();
			}
		}
		throw new TamagoCCException("TamagoCCSearchType<searchLabelInAssembly> : unfind label in definitions ("+inlabel.getTarget()+")");
	}
	/**
	 * @see tamagocc.util.TamagoCCExpressionVisitor#visitReturn(tamagocc.api.TReturn)
	 */
	public Object visitReturn(TReturn greturn) throws TamagoCCException {
		return method.getType();
	}

	
	
	public Object visitExistRange(TExistRange existrange) throws TamagoCCException {
		return TIType.generateType("bool");
	}

	public Object visitExistSet(TExistSet existset) throws TamagoCCException {
		return TIType.generateType("bool");
	}

	public Object visitForallRange(TForallRange forallrange) throws TamagoCCException {
		return TIType.generateType("bool");
	}

	public Object visitForallSet(TForallSet forallset) throws TamagoCCException {
		return TIType.generateType("bool");
	}

	public Object visitNil(TNil nil) throws TamagoCCException {
		return TIType.generateType("alpha");
	}

	public Object visitExistColl(TExistColl coll) throws TamagoCCException {
		return TIType.generateType("bool");
	}

	public Object visitForallColl(TForallColl coll) throws TamagoCCException {
		return TIType.generateType("bool");
	}

	public Object visitLanguageExpr(TLanguageExpr languageExpr)
			throws TamagoCCException {
		return TIType.generateType("alpha");
	}

	/**
	 * @see tamagocc.util.TamagoCCExpressionVisitor#visitCast(tamagocc.api.TCast)
	 */
	public Object visitCast(TCast cast) throws TamagoCCException {
		return cast.getType();
	}

	@Override
	public Object visitInState(TInState tiInState) throws TamagoCCException {
		return TIType.generateType("bool");
	}
}
