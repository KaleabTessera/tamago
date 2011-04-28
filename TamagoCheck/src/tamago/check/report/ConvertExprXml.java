/**
 * 
 */
package tamago.check.report;

/**
 * @author Hakim Belhaouari
 *
 */
public class ConvertExprXml {

	public static String toString(String expr) {
		if(expr == null)
			return "(null)";
		String tmp = expr.replaceAll("<", "&lt;");
		tmp = tmp.replaceAll(">", "&gt;");
		return tmp;
	}

}
