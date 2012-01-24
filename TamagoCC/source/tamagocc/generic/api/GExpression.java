package tamagocc.generic.api;

import java.util.Collection;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;

public interface GExpression extends GPreExpression {
	
	public enum GExprType {	
     INT,
     REAL,
     STRING,
     OPERATOR,
     RETURN,
     BOOL,
     NOT,
     NOCONTRACT,   
     CALL,
     ATPRE,
     READ,
     VARIABLE,
     INLABEL,
     NIL,
     LANGEXPR,
     CAST,
     INSTATE,
     ISBOUND
	}
    
	
	GExprType getCategory();
	
	// Collection<GPreExpression>
	Collection<GPreExpression> getPreExpression();
	
	
	Object visitExpression(TamagoCCGExpressionVisitor exprvisitor) throws TamagoCCException;
}
