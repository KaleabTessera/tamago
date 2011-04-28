/**
 * 
 */
package tamagocc.ast.api;

import java.util.Iterator;

import tamagocc.ast.TamagoCCAST;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AMethod extends TamagoCCAST {
	public static final int DECLARED = 0;
	public static final int IMPLEMENTED = 1;
	
	AComment getComment();
	
	int getMethodType();
		
	AIdent getIdent();
	AType getType();
	AVisibility getVisibility();
	
	Iterator<AThrowsException> getThrowsException();
	
	Iterator<AParameter> getParameters();
	
	AInstruction getBody();

	int getParameterNumber();
	
}
