/**
 * 
 */
package tamagocc.api;

import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

/**
 * This interface is the root of all abstract syntactic tree interfaces.  
 * All interfaces can be subject to the design pattern Visitor of an interface implements the 
 * TamagoCCVisitor.
 * 
 * @author Hakim Belhaouari
 */
public interface TObject {
	/**
	 * 
	 * @param visitor
	 * @return Return an object depending of the visitor
	 * @throws TamagoCCException
	 */
	Object visit(TamagoCCVisitor visitor) throws TamagoCCException;
}
