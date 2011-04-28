/**
 * 
 */
package tamago.check.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map.Entry;

import tamago.check.fixpoint.TamagoCheckState;
import tamago.check.util.TamagoCheckContext;
import tamago.csp.domain.CSPAbstractDomain;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GTransition;
import tamagocc.util.TamagoCCIndentator;
import tamagocc.util.TamagoCCMakeReadableGExpression;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCheckReport implements GenericTamagoCheckReport {

	private GTamago contract;
	private TamagoCheckContext ctx;
	private OutputStream ps;	
	private TamagoCCIndentator indent;
	private ArrayList<TamagoCheckState> states;
	
	
	
	/**
	 * @throws FileNotFoundException 
	 * 
	 */
	public TamagoCheckReport() {
		try {
			ps = new FileOutputStream("tamagocheck.xml");
		} catch (FileNotFoundException e) {
			ps = System.err;
		}
		this.contract = null;
		this.ctx = null;
		states = new ArrayList<TamagoCheckState>();
		indent = new TamagoCCIndentator(ps);
	}


	public void setOutputStream(OutputStream ps) {
		this.ps = ps;
	}
	
	private void printState(TamagoCheckState s) {
		TamagoCheckState tmp = s;
		try {
			indent.println("<state cur=\""+s.getState().getName()+"\" id=\""+s.getID()+"\">");
			indent.shiftright();
			if(s.getPreviousState() != null)
				indent.println("<parent-state id=\""+s.getPreviousState().getID()+"\" />");
			if(tmp.getTransition() != null)
				indent.println("<transition value=\""+ConvertExprXml.toString(tmp.getTransition().toString())+"\" />");
			for (Entry<String, CSPAbstractDomain> prop : tmp.getEntries()) {
				indent.print("<property name=\""+prop.getKey()+"\" value=\"");
				indent.print(""+prop.getValue());
				indent.println("\" />");
			}
			indent.shiftleft();
			indent.println("</state>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	// ------------------------------------------------------------
	/*public void addError(TamagoCheckState localstate, GTransition trans,
			Exception cspex) {
		try {
			indent.print("ERROR: ");
			indent.println(""+cspex);
			
		} catch (TamagoCCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printState(localstate);
	}*/

	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#findFixpoint(tamago.check.util.TamagoCheckContext, tamagocc.generic.api.GTransition, tamago.check.fixpoint.TamagoCheckState, tamago.check.fixpoint.TamagoCheckState)
	 */
	public void findFixpoint(TamagoCheckContext ctx, GTransition trans, TamagoCheckState nextstate, TamagoCheckState oldstate) {
		try {
			indent.println("<fixpoint state=\""+nextstate.getID()+"\" oldstate=\""+oldstate.getID()+"\" />");
		} catch (TamagoCCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#impossibleBranch(tamago.check.util.TamagoCheckContext, tamagocc.generic.api.GTransition, tamago.check.fixpoint.TamagoCheckState, tamagocc.generic.api.GExpression)
	 */
	public void impossibleBranch(TamagoCheckContext ctx,
			GTransition transition, TamagoCheckState localstate,
			GExpression gexpr) {

		try {
			indent.println("<errorbranchimpossible transition=\""+ConvertExprXml.toString(transition.toString())+"\">");
			indent.shiftright();
			indent.print("<prerequisite expr=\"");
			indent.print(ConvertExprXml.toString(TamagoCCMakeReadableGExpression.toString(gexpr)));
			indent.println("\" />");
			printState(localstate);
			indent.shiftleft();
			indent.println("</errorbranchimpossible>");
		} catch (TamagoCCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#closeDNF(tamago.check.util.TamagoCheckContext, tamagocc.generic.api.GTransition, tamago.check.fixpoint.TamagoCheckState)
	 */
	public void closeDNF(TamagoCheckContext ctx, GTransition transition,
			TamagoCheckState localstate) {
		try {
			indent.shiftleft();
			indent.println("</dnf>");
		} catch (TamagoCCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	/**
	 * @see tamago.check.report.GenericTamagoCheckReport#closeState(tamago.check.util.TamagoCheckContext, tamago.check.fixpoint.TamagoCheckState)
	 */
	public void closeState(TamagoCheckContext ctx,TamagoCheckState localstate) {
		try {
			indent.shiftleft();
			indent.println("</openstate>");
		} catch (TamagoCCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#openState(tamago.check.util.TamagoCheckContext, tamago.check.fixpoint.TamagoCheckState)
	 */
	public void openState(TamagoCheckContext ctx, TamagoCheckState localstate) {
		try {
			indent.println("<openstate name=\""+localstate.getState().getName()+"\" state=\""+localstate.getID()+"\">");
			indent.shiftright();
			
			
		} catch (TamagoCCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#openDNF(tamago.check.util.TamagoCheckContext, tamagocc.generic.api.GTransition, tamago.check.fixpoint.TamagoCheckState, tamagocc.generic.api.GExpression)
	 */
	public void openDNF(TamagoCheckContext ctx, GTransition transition, TamagoCheckState localstate,
			GExpression gexpr) {
		try {
			indent.print("<dnf expr=\"");
			indent.print(ConvertExprXml.toString(TamagoCCMakeReadableGExpression.toString(gexpr)));
			indent.println("\" >");
			indent.shiftright();
		} catch (TamagoCCException e) {
			e.printStackTrace();
		}		
	}

	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#initialize(tamago.check.util.TamagoCheckContext, tamagocc.generic.api.GTamago)
	 */
	public void initialize(TamagoCheckContext ctx, GTamago contract) {
		try {	
			indent.print("<tamagocheck contract=\"");
			indent.print(contract.getName());
			indent.print("\" module=\"");
			indent.print(contract.getModule().getFullModule());
			indent.println("\" >");
			indent.shiftright();
			this.contract = contract;
			this.ctx = ctx;
		}
		catch(TamagoCCException ex) {
			
		}
	}

	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#finish(long, long, long, long)
	 */
	public void finish(long tps, long statevisited, long transitionsvisited,
			long solvercreated) {
		try {
			indent.print("<stat temps=\"");
			indent.print(""+tps);
			indent.print("\" state=\"");
			indent.print(""+statevisited);
			indent.print("\" transitions=\"");
			indent.print(""+transitionsvisited);
			indent.print("\" solver=\"");
			indent.print(""+solvercreated);
			indent.println("\" />");
			indent.shiftleft();
			indent.println("</tamagocheck>");
		}
		catch(TamagoCCException ex) {

		}
		
	}


	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#addError(tamago.check.fixpoint.TamagoCheckState, tamagocc.generic.api.GTransition, java.lang.Exception)
	 */
	public void addError(TamagoCheckState localstate, GTransition trans,
			Exception ex) {
		
		try {
			indent.println("<error>");
			indent.shiftright();
			indent.println("<transition str=\""+ConvertExprXml.toString(trans.toString())+"\" />");
			indent.println("<message type=\""+ex.getClass().getName()+"\">");
			indent.println(ConvertExprXml.toString(ex.getMessage()));
			indent.println("</message>");
			indent.shiftleft();
			indent.println("</error>");
		}
		catch(TamagoCCException ccex) {

		}
	}


	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#reachMaxDepth(tamago.check.util.TamagoCheckContext, tamagocc.generic.api.GState, tamago.check.fixpoint.TamagoCheckState)
	 */
	public void reachMaxDepth(TamagoCheckContext ctx2, GState state,
			TamagoCheckState localstate) {
		try {
			indent.println("<maxdepth statename=\""+state.getName()+"\" state=\""+localstate.getID()+"\" />");
			
		}
		catch(TamagoCCException ccex) {

		}
		
		closeState(ctx2,localstate);
		
	}


	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#close()
	 */
	public void close() {
		try {
			indent.println("<details>");
			for (TamagoCheckState state : states) {
				try {
					printState(state);
				}
				catch(Exception e) {
					
				}
			}
			indent.println("</details>");
		} catch (TamagoCCException e) {
			e.printStackTrace();
		}
	}


	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#registered(tamago.check.fixpoint.TamagoCheckState, tamago.check.fixpoint.TamagoCheckState)
	 */
	public void registered(TamagoCheckState localstate,
			TamagoCheckState nextstate) {
		states.add(nextstate);
	}


	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#openTransition(tamagocc.generic.api.GTransition)
	 */
	public void openTransition(GTransition trans) {
		try {
			indent.println("<transition value=\""+ConvertExprXml.toString(trans.toString())+"\">");
		} catch (TamagoCCException e) {
			e.printStackTrace();
		}
	}


	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#closeTransition()
	 */
	public void closeTransition() {
		try {
			indent.println("</transition>");
		} catch (TamagoCCException e) {
			e.printStackTrace();
		}
	}
}
