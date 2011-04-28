/**
 * 
 */
package tamago.ext.tamagocc;

/**
 * @author belhaouari
 *
 */
public class TamagoCCFailInvariant extends TamagoCCFailCondition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 995705040300117962L;

	private TamagoCCFailItem item;
	private InvariantPosition position;
	
	
	/**
	 * @param message
	 * @param typeCondition
	 */
	public TamagoCCFailInvariant(String expr,String message) {
		super(expr,message, TamagoCCFailCondition.INVARIANT);
		item = (TamagoCCFailItem)failitems.iterator().next();
		position = InvariantPosition.UNKNOWN;
	}
	
	public TamagoCCFailInvariant(String expr) {
		super(expr,"", TamagoCCFailCondition.INVARIANT);
		item = (TamagoCCFailItem)failitems.iterator().next();
		position = InvariantPosition.UNKNOWN;
	}

	public TamagoCCFailInvariant(String expr,String message,InvariantPosition pos) {
		super(expr,message, TamagoCCFailCondition.INVARIANT);
		item = (TamagoCCFailItem)failitems.iterator().next();
		position = pos;
	}
	
	public TamagoCCFailInvariant(String expr,InvariantPosition pos) {
		super(expr,"", TamagoCCFailCondition.INVARIANT);
		item = (TamagoCCFailItem)failitems.iterator().next();
		position = pos;
	}

	public InvariantPosition getPosition() {
		return position;
	}

	public String getMessage() {
		StringBuffer sb = new StringBuffer();
		sb.append("Exception in an Invariant on the expression : ");
		sb.append(item.getExpression());
		sb.append(".");
		if(item.getMessage() != null || item.getMessage().length() == 0) {
			sb.append("With the message : ");
			sb.append(item.getMessage());
		}
		return sb.toString();
	}

}
