/**
 * 
 */
package tamago.check.model;

import tamago.check.report.ConvertExprXml;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCIndentator;

/**
 * @author Hakim Belhaouari
 *
 */
public class NodeEmpty implements Node {

	private String msg;
	/**
	 * 
	 */
	public NodeEmpty() {
		msg = "";
	}
	
	public NodeEmpty(String msg) {
		this.msg = msg;
	}

	/**
	 * @see tamago.check.model.Node#category()
	 */
	public NodeKind category() {
		return NodeKind.UNKNOWN;
	}
	
	public String getMessage() { return msg; }

	/**
	 * @see tamago.check.model.Node#toXML(TamagoCCIndentator)
	 */
	public void toXML(TamagoCCIndentator indent) {
		try {
			indent.print("<message>");
			indent.print(ConvertExprXml.toString(msg));
			indent.println("</message>");
		}
		catch(TamagoCCException e) { }
	}

}
