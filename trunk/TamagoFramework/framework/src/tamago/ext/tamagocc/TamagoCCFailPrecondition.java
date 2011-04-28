/**
 * 
 */
package tamago.ext.tamagocc;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author belhaouari
 *
 */
public class TamagoCCFailPrecondition extends TamagoCCFailCondition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6250130388519200210L;

	/**
	 * @param message
	 * @param typeCondition
	 */
	public TamagoCCFailPrecondition(Collection<TamagoCCFailItem> failitems) {
		super(failitems, TamagoCCFailPrecondition.PRECONDITION);
	}

	public String getMessage() {
		StringBuffer sb = new StringBuffer();
		sb.append("Exception in a precondition, failed expressions are :\n");
		Iterator<TamagoCCFailItem> items = failitems.iterator();
		while(items.hasNext()) {
			TamagoCCFailItem item = items.next();
			sb.append("\t");
			sb.append(item.getExpression());
			if(item.getMessage() != null || item.getMessage().length()==0) {
				sb.append(" -> ");
				sb.append(item.getMessage());
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
