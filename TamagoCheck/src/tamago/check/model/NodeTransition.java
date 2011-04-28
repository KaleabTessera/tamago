/**
 * 
 */
package tamago.check.model;

import java.util.ArrayList;
import java.util.Collection;

import tamago.check.report.ConvertExprXml;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GTransition;
import tamagocc.util.TamagoCCIndentator;

/**
 * @author Hakim Belhaouari
 *
 */
public class NodeTransition implements Node {

	private GTransition transition;
	private ArrayList<NodeDNF> dnfs;
	
	/**
	 * 
	 */
	public NodeTransition(GTransition trans) {
		dnfs = new ArrayList<NodeDNF>();
		this.transition = trans;
	}

	public NodeKind category() {
		return NodeKind.TRANSITION;
	}

	public GTransition getTransition() { return transition; }
	public Collection<NodeDNF> getDNFs() { return dnfs; }
	
	public void toXML(TamagoCCIndentator indent) {
		try {
			indent.print("<transition form=\"");
			if(transition == null)
				indent.print("null");
			else
				indent.print(ConvertExprXml.toString(transition.toString()));
			indent.println("\">");
			indent.shiftright();
			for (NodeDNF dnf : dnfs) {
				dnf.toXML(indent);
			}
			indent.shiftleft();
			indent.println("</transition>");
		}
		catch(TamagoCCException e) { 
			
		}
	}

	public void addDNF(NodeDNF lastdnf) {
		dnfs.add(lastdnf);		
	}

}
