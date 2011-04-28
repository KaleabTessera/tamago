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
public class TamagoCCFailPostcondition extends TamagoCCFailCondition {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8568197135553245691L;

	/**
	 * @param message
	 * @param typeCondition
	 */
	public TamagoCCFailPostcondition(Collection<TamagoCCFailItem> failitems) {
		super(failitems, TamagoCCFailPrecondition.POSTCONDITION);
	}

	public String getMessage() {
		StringBuffer sb = new StringBuffer();
		sb.append("Exception in a postcondition, failed expressions are :\n");
		Iterator<TamagoCCFailItem> items = failitems.iterator();
		while(items.hasNext()) {
			TamagoCCFailItem item = (TamagoCCFailItem)items.next();
			sb.append("\t");
			sb.append(item.getExpression());
			if(item.getMessage() != null || item.getMessage().length() == 0) {
				sb.append(" -> ");
				sb.append(item.getMessage());
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
