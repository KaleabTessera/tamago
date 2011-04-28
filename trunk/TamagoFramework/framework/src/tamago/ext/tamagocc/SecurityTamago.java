package tamago.ext.tamagocc;

public abstract class SecurityTamago {
	
	protected TamagoCCContainer caller;
	protected TamagoCCComponent dest;
	
	public SecurityTamago(TamagoCCContainer caller,TamagoCCComponent dest) {
		this.caller = caller;
		this.dest = dest;
	}
	
	public abstract boolean checkValid();
}
