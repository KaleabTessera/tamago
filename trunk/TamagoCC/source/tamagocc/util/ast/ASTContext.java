/**
 * 
 */
package tamagocc.util.ast;

import java.util.Stack;

/**
 * @author Hakim Belhaouari
 *
 */
public class ASTContext {

	private ASTMemory memory;
	private ASTValue self;
	private Stack<ASTValue> scopes;
	
	/**
	 * 
	 */
	public ASTContext() {
		memory = new ASTMemory();
		scopes = new Stack<ASTValue>();
		self = null;
	}
	
	public ASTContext(ASTValue self) {
		memory = new ASTMemory();
		scopes = new Stack<ASTValue>();
		this.self = self;
	}

	public ASTMemory getMemory() {
		return memory;
	}
	
	public ASTValue scope() {
		return scopes.peek();
	}

	public void pushScope(ASTValue scope) {
		scopes.push(scope);
	}
	
	public ASTValue popScope() {
		return scopes.pop();
	}
}
