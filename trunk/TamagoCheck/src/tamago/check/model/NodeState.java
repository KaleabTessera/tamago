/**
 * 
 */
package tamago.check.model;

import java.util.ArrayList;
import java.util.Collection;

import tamago.check.fixpoint.TamagoCheckState;
import tamago.check.report.TamagoCheckReportOpt;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCIndentator;

/**
 * @author Hakim Belhaouari
 *
 */
public class NodeState implements Node {

	private ArrayList<NodeTransition> transitions;
	private String name;
	private TamagoCheckState state;
	private NodeDNFResult result;
	private Node detail;
	/**
	 * 
	 */
	public NodeState(String name, TamagoCheckState state) {
		this.name = name;
		this.state = state;
		transitions = new ArrayList<NodeTransition>();
		result = NodeDNFResult.NONE;
	}
	
	public NodeKind category() {
		return NodeKind.STATE;
	}
	
	public String getName() {return name; }
	public TamagoCheckState getState() { return state; }
	public Collection<NodeTransition> getTransitions() {return transitions; }
	public NodeDNFResult getResult() { return result; }
	public Node getDetail() { return detail; }
	
	
	public void toXML(TamagoCCIndentator indent) {
		try {
			indent.print("<openstate state=\"");
			indent.print(name);
			indent.println("\" >");
			indent.shiftright();
			
			TamagoCheckReportOpt.printState(state, indent);
			indent.println("<transitions>");
			indent.shiftright();
			for (NodeTransition transition : transitions) {
				transition.toXML(indent);
			}
			indent.shiftleft();
			indent.println("</transitions>");
			
			if(result != NodeDNFResult.NONE) {
				if(detail == null) {
					indent.print("<unknow-element type=\"");
					indent.print(result.toString());
					indent.println("\" />");
				}
				else {
					detail.toXML(indent);
				}
			}
			
			indent.shiftleft();
			indent.println("</openstate>");
		}
		catch(TamagoCCException e) {
			
		}
		
	}

	public void setResult(NodeDNFResult error, Node empty) {
		result = error;
		detail = empty;
	}

	public void addTransition(NodeTransition lasttransition) {
		transitions.add(lasttransition);
	}

}
