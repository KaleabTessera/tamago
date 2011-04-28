/**
 * 
 */
package tamagocc.ast.api;

import tamagocc.ast.TamagoCCAST;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AExpression extends TamagoCCAST {
    public static final int INT = 1;
    public static final int REAL = 2;
    public static final int STRING = 3;
    public static final int OPERATOR = 4;
//    public static final int RETURN = 5;
    public static final int BOOL = 6;
    public static final int NOT = 7;
    
    public static final int NOEXPRESSION = 8;
    
    public static final int CALL = 9;
//  public static final int ATPRE = 10;
    public static final int READ = 11;
    public static final int VARIABLE = 12;
    public static final int INLABEL = 13;
    
	public static final int NEW = 14;
	public static final int TAMAGONEW = 15;
	public static final int SELF = 16;
	
	public static final int CONVERTTYPE = 17;
	public static final int NIL = 18;
	
	public static final int FORALLRANGE = 19;
	public static final int FORALLSET = 20;
	public static final int EXISTRANGE = 21;
	public static final int EXISTSET = 22;
	
	public static final int LANGEXPR = 23;
	public static final int INSTATE = 24;
	
	int getExpressionType();
}
