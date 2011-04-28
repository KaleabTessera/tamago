package tamagocc.util;

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
import tamagocc.api.TString;
import tamagocc.api.TTamago;
import tamagocc.api.TTamagoEntity;
import tamagocc.api.TType;
import tamagocc.api.TVariable;
import tamagocc.exception.TamagoCCException;
/**
 * 
 * Projet : TamagoCC
 * 
 * @author Hakim BELHAOUARI
 * 2 juil. 2005 TamagoCCVisitor.java
 */
public interface TamagoCCVisitor {
	Object visitAccess(TAccess access) throws TamagoCCException;
	Object visitAllow(TAllow allow) throws TamagoCCException;
	Object visitAssembly(TAssembly assembly) throws TamagoCCException;
	Object visitBehavior(TBehavior behavior) throws TamagoCCException;
	Object visitBind(TBind bind) throws TamagoCCException;
	Object visitBool(TBool bool) throws TamagoCCException;
	Object visitCall(TCall call) throws TamagoCCException;
	Object visitCategory(TCategory category) throws TamagoCCException;
	Object visitComponent(TComponent component) throws TamagoCCException;
	Object visitComposite(TComposite composite) throws TamagoCCException;
	Object visitCondition(TCondition condition) throws TamagoCCException;
	Object visitDefinition(TDefinition definition) throws TamagoCCException;
	Object visitExport(TExport export) throws TamagoCCException;
	Object visitExpression(TExpression e) throws TamagoCCException;
	Object visitExtendService(TExtendService extendservice) throws TamagoCCException;
	Object visitIncludeService(TIncludeService includeservice) throws TamagoCCException;
	Object visitInvariant(TInvariant invariant) throws TamagoCCException;
	Object visitInteger(TInteger integer) throws TamagoCCException;
	Object visitMethod(TMethod method) throws TamagoCCException;
	Object visitNoContract(TNoContract nocontract) throws TamagoCCException;
	Object visitNot(TNot not) throws TamagoCCException;
	Object visitObject(TObject object) throws TamagoCCException;
	Object visitOpeName(TOpeName opeName) throws TamagoCCException;
	Object visitOperator(TOperator operator) throws TamagoCCException;
	Object visitParameter(TParameter parameter) throws TamagoCCException;
	Object visitAtPre(TAtPre pre) throws TamagoCCException;
	Object visitProperty(TProperty property) throws TamagoCCException;
	Object visitProvide(TProvide provide) throws TamagoCCException;
	Object visitRead(TRead read) throws TamagoCCException;
	Object visitReal(TReal real) throws TamagoCCException;
	Object visitRefineService(TRefineService refineService) throws TamagoCCException;
	Object visitRequire(TRequire require) throws TamagoCCException;
	Object visitReturn(TReturn ret) throws TamagoCCException;
	Object visitService(TService service) throws TamagoCCException;
	Object visitString(TString string) throws TamagoCCException;
	Object visitTamago(TTamago tamago) throws TamagoCCException;
	Object visitTamagoEntity(TTamagoEntity entity) throws TamagoCCException;
		
	Object visitType(TType type) throws TamagoCCException;
	Object visitVariable(TVariable variable) throws TamagoCCException;
	Object visitInLabel(TInLabel inlabel) throws TamagoCCException;
	Object visitInState(TInState instate) throws TamagoCCException;
	
	Object visitPercolator(TPercolator percolator) throws TamagoCCException;
	
	Object visitSet(TSet set) throws TamagoCCException;
	
	Object visitForallRange(TForallRange forallrange) throws TamagoCCException;
	Object visitForallSet(TForallSet forallset) throws TamagoCCException;
	
	Object visitExistRange(TExistRange existrange) throws TamagoCCException;
	Object visitExistSet(TExistSet existset) throws TamagoCCException;
	
	Object visitNil(TNil nil) throws TamagoCCException;
	
	Object visitImplements(TImplements impl) throws TamagoCCException;
	Object visitForallColl(TForallColl coll) throws TamagoCCException;
	Object visitExistColl(TExistColl coll) throws TamagoCCException;
	Object visitNamespace(TNamespace namespace) throws TamagoCCException;
	Object visitLanguageExpr(TLanguageExpr languageExpr) throws TamagoCCException;
	
	Object visitCast(TCast cast) throws TamagoCCException;
}
