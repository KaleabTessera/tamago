/**
 * 
 */
package tamago.check.report;

import tamago.check.fixpoint.TamagoCheckState;
import tamago.check.model.Node;
import tamago.check.model.NodeKind;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCIndentator;

/**
 * @author Hakim Belhaouari
 *
 */
public class NodeNextState implements Node {

	private TamagoCheckState nextstate;
	
	/**
	 * 
	 */
	public NodeNextState(TamagoCheckState nextstate) {
		this.nextstate = nextstate;
	}

	/**
	 * @see tamago.check.model.Node#category()
	 */
	public NodeKind category() {
		return NodeKind.NEXTSTATE;
	}

	/**
	 * @see tamago.check.model.Node#toXML(tamagocc.util.TamagoCCIndentator)
	 */
	public void toXML(TamagoCCIndentator indent) {
		try {
			indent.println("<nextstate>");
			indent.shiftright();
			TamagoCheckReportOpt.printState(nextstate, indent);
			indent.shiftleft();
			indent.println("</nextstate>");
		}
		catch(TamagoCCException e) {
			
		}
	}

}
