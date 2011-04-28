/**
 * 
 */
package tamagocc.ast.api;

import tamagocc.ast.TamagoCCAST;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AInstruction extends TamagoCCAST {
	public static final int NOINSTRUCTION = 0;
	public static final int IFTHENELSE = 1;
	public static final int WHILE = 2;
	public static final int FOR = 3;
	public static final int SEQUENCE = 4;
	public static final int EXPRESSION = 5;
	public static final int AFFECTATION = 6;
	public static final int RETURN = 7;
	public static final int TRYCATCHFINAL = 8;
	public static final int THROWEXCEPTION = 9;
	public static final int INITIALISATION = 10;
	public static final int FOREACH = 11;
	
	int getInstructionType();
	
	AComment getComment();
}
