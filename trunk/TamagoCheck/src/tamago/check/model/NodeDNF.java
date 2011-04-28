/**
 * 
 */
package tamago.check.model;

import tamago.check.report.ConvertExprXml;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GExpression;
import tamagocc.util.TamagoCCIndentator;
import tamagocc.util.TamagoCCMakeReadableGExpression;

/**
 * @author Hakim Belhaouari
 *
 */
public class NodeDNF implements Node {

	private GExpression expr;
	private NodeDNFResult result;
	private Node details;
	
	/**
	 * 
	 */
	public NodeDNF(GExpression expr) {
		this.expr = expr;
		result = NodeDNFResult.NONE;
		details = null;
	}
	
	public NodeKind category() {
		return NodeKind.DNF;
	}
	
	public GExpression getExpression() {return expr; }
	public NodeDNFResult getResult() { return result; }
	public Node getNodeDetail() { return details; }
	
	public void setResult(NodeDNFResult val, Node details) {
		result = val;
		this.details = details;
	}
	
	public void toXML(TamagoCCIndentator indent) {
		try {
			indent.print("<dnf expr=\"");
			indent.print(ConvertExprXml.toString(TamagoCCMakeReadableGExpression.toString(expr)));
			indent.println("\" >");
			indent.shiftright();
			//if(result != NodeDNFResult.NONE) {
				if(details == null) {
					indent.print("<unknow-element type=\"");
					indent.print(result.toString());
					indent.println("\" />");
				}
				else {
					details.toXML(indent);
				}
			//}
			indent.shiftleft();
			indent.println("</dnf>");
		}
		catch(TamagoCCException e) {
			
		}
	}
}
