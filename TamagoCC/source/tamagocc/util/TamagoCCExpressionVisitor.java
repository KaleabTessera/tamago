package tamagocc.util;

import tamagocc.api.TAtPre;
import tamagocc.api.TBool;
import tamagocc.api.TCall;
import tamagocc.api.TCast;
import tamagocc.api.TExistColl;
import tamagocc.api.TExistRange;
import tamagocc.api.TExistSet;
import tamagocc.api.TForallColl;
import tamagocc.api.TForallRange;
import tamagocc.api.TForallSet;
import tamagocc.api.TInLabel;
import tamagocc.api.TInState;
import tamagocc.api.TInteger;
import tamagocc.api.TLanguageExpr;
import tamagocc.api.TNil;
import tamagocc.api.TNoContract;
import tamagocc.api.TNot;
import tamagocc.api.TOperator;
import tamagocc.api.TRead;
import tamagocc.api.TReal;
import tamagocc.api.TReturn;
import tamagocc.api.TString;
import tamagocc.api.TVariable;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim BELHAOUARI
 * 19 oct. 2005 TamagoCCExpressionVisitor.java
 */
/**
 */
public interface TamagoCCExpressionVisitor {
    
     
    Object visitAtPre(TAtPre atpre) throws TamagoCCException;
    Object visitBool(TBool bool) throws TamagoCCException;
    Object visitCall(TCall call) throws TamagoCCException;
    Object visitReal(TReal f) throws TamagoCCException;
    Object visitInteger(TInteger integer) throws TamagoCCException;
    Object visitNoContract(TNoContract nocontract) throws TamagoCCException;
    Object visitNot(TNot not) throws TamagoCCException;
    Object visitOperator(TOperator operator) throws TamagoCCException;
    Object visitRead(TRead read) throws TamagoCCException;
    Object visitString(TString string) throws TamagoCCException;
    Object visitVariable(TVariable variable) throws TamagoCCException;
    Object visitInLabel(TInLabel inlabel) throws TamagoCCException;
    Object visitReturn(TReturn greturn) throws TamagoCCException;
    
    Object visitForallRange(TForallRange forallrange) throws TamagoCCException;
	Object visitForallSet(TForallSet forallset) throws TamagoCCException;
	
	Object visitExistRange(TExistRange existrange) throws TamagoCCException;
	Object visitExistSet(TExistSet existset) throws TamagoCCException;
	
	Object visitNil(TNil nil) throws TamagoCCException;
	Object visitForallColl(TForallColl coll) throws TamagoCCException;
	Object visitExistColl(TExistColl coll) throws TamagoCCException;
	Object visitLanguageExpr(TLanguageExpr languageExpr) throws TamagoCCException;

	Object visitCast(TCast cast) throws TamagoCCException;
	Object visitInState(TInState tiInState) throws TamagoCCException;
}
