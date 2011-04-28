/**
 * 
 */
package tamago.ext.tamagocc;

import tamago.TamagoException;

/**
 * @author hakim
 *
 */
public class TamagoCCBehaviorException extends TamagoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2779603249246846499L;
	
	private TamagoCCMethodID methodID;
	/**
	 * @param message
	 */
	public TamagoCCBehaviorException(TamagoCCMethodID methodID) {
		super("The method '"+methodID.getID()+"' is not activable");
		this.methodID = methodID;
	}
	
	public String getMethod() {
		return methodID.getID();
	}
	
	public TamagoCCMethodID getID() {
		return methodID;
	}
}
