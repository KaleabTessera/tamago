/**
 * 
 */
package tamagocc.generic;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GAtPre;
import tamagocc.generic.api.GBool;
import tamagocc.generic.api.GCall;
import tamagocc.generic.api.GCast;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GInLabel;
import tamagocc.generic.api.GInState;
import tamagocc.generic.api.GInteger;
import tamagocc.generic.api.GLanguageExpr;
import tamagocc.generic.api.GNil;
import tamagocc.generic.api.GNoContract;
import tamagocc.generic.api.GNot;
import tamagocc.generic.api.GOperator;
import tamagocc.generic.api.GRead;
import tamagocc.generic.api.GReal;
import tamagocc.generic.api.GReturn;
import tamagocc.generic.api.GString;
import tamagocc.generic.api.GVariable;
import tamagocc.generic.impl.GIQuantifierVariable;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface TamagoCCGExpressionVisitor {
	
	/* Inutile
	 * Object visitPreExpression(GPreExpression preexpression) throws TamagoCCException;
	 * Object visitInitialisation(GInitialisation initialisation) throws TamagoCCException;
	 */
	
	Object visitExpression(GExpression expression) throws TamagoCCException;
	Object visitPre(GAtPre atpre) throws TamagoCCException;
	Object visitBool(GBool bool) throws TamagoCCException;
	Object visitCall(GCall call) throws TamagoCCException;
	Object visitInLabel(GInLabel inlabel) throws TamagoCCException;
	Object visitInteger(GInteger interger) throws TamagoCCException;
	Object visitNoContract(GNoContract nocontract) throws TamagoCCException;
	Object visitNot(GNot not) throws TamagoCCException;
	Object visitOperator(GOperator operator) throws TamagoCCException;
	Object visitRead(GRead read) throws TamagoCCException;
	Object visitReal(GReal real) throws TamagoCCException;
	Object visitReturn(GReturn ret) throws TamagoCCException;
	Object visitString(GString string) throws TamagoCCException;
	Object visitVariable(GVariable variable) throws TamagoCCException;
	

	Object visitQuantifierVariable(GIQuantifierVariable variable) throws TamagoCCException;
	
	Object visitNil(GNil nil) throws TamagoCCException;
	Object visitLanguageExpr(GLanguageExpr languageExpr) throws TamagoCCException;
	/**
	 * @param cast
	 * @return
	 */
	Object visitCast(GCast cast) throws TamagoCCException;
	Object visitInState(GInState giInState) throws TamagoCCException;
	
}
