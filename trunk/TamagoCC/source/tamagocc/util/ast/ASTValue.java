/**
 * 
 */
package tamagocc.util.ast;


/**
 * @author Hakim Belhaouari
 *
 */
public interface ASTValue {
	ASTValueType getType();
	
	int intValue();
	double realValue();
	String stringValue();
	Object objectValue();
	boolean boolValue();
	
	Class<?> innerClass();
}