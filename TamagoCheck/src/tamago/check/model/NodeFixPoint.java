/**
 * 
 */
package tamago.check.model;

import tamago.check.fixpoint.TamagoCheckState;
import tamago.check.report.TamagoCheckReportOpt;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCIndentator;

/**
 * @author Hakim Belhaouari
 *
 */
public class NodeFixPoint implements Node {

	private TamagoCheckState old;
	private TamagoCheckState state;
	
	/**
	 * 
	 */
	public NodeFixPoint(TamagoCheckState state,TamagoCheckState old) {
		this.old = old;
		this.state = state;
	}

	/**
	 * @see tamago.check.model.Node#category()
	 */
	public NodeKind category() {
		return NodeKind.FIXPOINT;
	}

	
	public TamagoCheckState getOldState() { return old; }
	public TamagoCheckState getState() { return state; }
	
	/**
	 * @see tamago.check.model.Node#toXML(TamagoCCIndentator)
	 */
	public void toXML(TamagoCCIndentator indent) {
		try {
			indent.println("<fixpoint>");
			indent.shiftright();
			
			TamagoCheckReportOpt.printState(old, indent);
			TamagoCheckReportOpt.printState(state, indent);
			
			indent.shiftleft();
			indent.println("</fixpoint>");
		}
		catch(TamagoCCException e) {
			
		}
	}
	
	

}
