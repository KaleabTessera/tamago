package tamagocc.ast;

import tamagocc.exception.TamagoCCException;

/**
 * This interface represents the root of all <em>final abstract syntactic tree</em>, for the generation.
 * This final AST may be represented as <em>byte-code</em>.<br>
 * Furthermore, all AST are designed for a design pattern Visitor, to make a third compute easily on
 * hierarchy.
 * 
 * @author Hakim Belhaouari
 *
 */
public interface TamagoCCAST {
	/**
	 * 
	 * @param astvisitor
	 * @return
	 * @throws TamagoCCException
	 */
	Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException;
	
	String toString();
}
