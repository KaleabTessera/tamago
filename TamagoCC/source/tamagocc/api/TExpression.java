package tamagocc.api;

import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCExpressionVisitor;


/**
 * 
 * This interface is the root of all expression. An expression is used to specify the
 * contract defined in the CDL language.
 * An expression is based on a number which represents the category of the expression.
 * A visitor can through a full expression with a class TamagoCCExpressionVisitor, based on the
 * design pattern Visitor.
 *  
 * @author Hakim BELHAOUARI
 */
public interface TExpression extends TObject {
	
	public enum ExprType {
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
	    WRITE,
	    VARIABLE,
	    INLABEL,
	    FORALLSET,
	    FORALLRANGE,
	    FORALLCOLL,
	    EXISTSET,
	    EXISTRANGE,
	    EXISTCOLL,
	    NIL,
	    LANGEXPR,
	    CAST,
	    INSTATE
	}
	
	/*
    public static final int INT = 1;
    public static final int REAL = 2;
    public static final int STRING = 3;
    public static final int OPERATOR = 4;
    public static final int RETURN = 5;
    public static final int BOOL = 6;
    public static final int NOT = 7;
    public static final int NOCONTRACT = 8;    
    public static final int CALL = 9;
    public static final int ATPRE = 10;
    public static final int READ = 11;
    public static final int WRITE = 12;
    public static final int VARIABLE = 13;
    public static final int INLABEL = 14;
    public static final int FORALLSET = 15;
    public static final int FORALLRANGE = 16;
    public static final int EXISTSET = 17;
    public static final int EXISTRANGE = 18;
    public static final int NIL = 19;
    */
    
    /**
     * 
     * @return Return the category of the expression
     */
    public ExprType getCat();
    
    /**
     * Represent the entry point of the visitor. 
     * @param exprvisitor : an object which implements the interface TamagoCCExpressionVisitor.
     * @return Return an object depending of the visitor
     * @throws TamagoCCException
     */
    Object visitExpression(TamagoCCExpressionVisitor exprvisitor) throws TamagoCCException;
}
