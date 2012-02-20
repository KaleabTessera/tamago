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
import tamago.check.model.NodeDNF;
import tamago.check.model.NodeDNFResult;
import tamago.check.model.NodeEmpty;
import tamago.check.model.NodeFixPoint;
import tamago.check.model.NodeState;
import tamago.check.model.NodeTransition;
import tamago.check.util.TamagoCheckContext;
import tamago.csp.domain.CSPAbstractDomain;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GTransition;
import tamagocc.util.TamagoCCIndentator;

/**
 * @author Hakim Belhaouari
 *
 */
public class TamagoCheckReportOpt implements GenericTamagoCheckReport {

	private GTamago contract;
	private TamagoCheckContext ctx;
	private ArrayList<NodeState> nodes;
	private OutputStream ps;
	private TamagoCCIndentator indent;
	
	private NodeDNF lastdnf;
	private NodeState laststate;
	private NodeTransition lasttransition;
	private ArrayList<TamagoCheckState> states;
	
	/**
	 * @throws FileNotFoundException 
	 * 
	 */
	public TamagoCheckReportOpt() {
		try {
			ps = new FileOutputStream("tamagocheck.xml");
		} catch (FileNotFoundException e) {
			ps = System.err;
		}
		this.contract = null;
		this.ctx = null;
		nodes = new ArrayList<NodeState>();
		indent = new TamagoCCIndentator(ps);
		states = new ArrayList<TamagoCheckState>();
	}


	public void setOutputStream(OutputStream ps) {
		this.ps = ps;
		indent = new TamagoCCIndentator(ps);
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
		lastdnf.setResult(NodeDNFResult.FIXPOINT, new NodeFixPoint(nextstate,oldstate));	
	}

	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#impossibleBranch(tamago.check.util.TamagoCheckContext, tamagocc.generic.api.GTransition, tamago.check.fixpoint.TamagoCheckState, tamagocc.generic.api.GExpression)
	 */
	public void impossibleBranch(TamagoCheckContext ctx,
			GTransition transition, TamagoCheckState localstate,
			GExpression gexpr) {
		lastdnf.setResult(NodeDNFResult.IMPOSSIBLE, new NodeEmpty("impossible transition"));
	}

	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#closeDNF(tamago.check.util.TamagoCheckContext, tamagocc.generic.api.GTransition, tamago.check.fixpoint.TamagoCheckState)
	 */
	public void closeDNF(TamagoCheckContext ctx, GTransition transition,
			TamagoCheckState localstate) {
		lasttransition.addDNF(lastdnf);
		lastdnf = null;		
	}

	/**
	 * @see tamago.check.report.GenericTamagoCheckReport#closeState(tamago.check.util.TamagoCheckContext, tamago.check.fixpoint.TamagoCheckState)
	 */
	public void closeState(TamagoCheckContext ctx,TamagoCheckState localstate) {
		nodes.add(laststate);
	}

	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#openState(tamago.check.util.TamagoCheckContext, tamago.check.fixpoint.TamagoCheckState)
	 */
	public void openState(TamagoCheckContext ctx, TamagoCheckState localstate) {
		laststate = new NodeState(localstate.getState().getName(),localstate);
	}

	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#openDNF(tamago.check.util.TamagoCheckContext, tamagocc.generic.api.GTransition, tamago.check.fixpoint.TamagoCheckState, tamagocc.generic.api.GExpression)
	 */
	public void openDNF(TamagoCheckContext ctx, GTransition transition, TamagoCheckState localstate,
			GExpression gexpr) {
		lastdnf = new NodeDNF(gexpr);
	}

	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#initialize(tamago.check.util.TamagoCheckContext, tamagocc.generic.api.GTamago)
	 */
	public void initialize(TamagoCheckContext ctx, GTamago contract) {
		this.contract = contract;
		this.ctx = ctx;
	}

	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#finish(long, long, long, long)
	 */
	public void finish(long tps, long statevisited, long transitionsvisited,
			long solvercreated) {
		
		try {
			indent.println("<?xml version=\"1.0\" ?>");
			indent.println("<?xml-stylesheet type=\"text/xsl\" href=\"http://tamago.googlecode.com/svn/trunk/TamagoCC/TamagoCDL.xsl\" ?>");
			indent.print("<tamagocheck contract=\"");
			indent.print(contract.getName());
			indent.print("\" module=\"");
			indent.print(contract.getModule().getFullModule());
			indent.println("\" >");
			indent.shiftright();
		}
		catch(TamagoCCException ex) {
			
		}
		
		for (NodeState nodestate : nodes) {
			nodestate.toXML(indent);
		}
		
		
		try {
			indent.println("<details>");
			for (TamagoCheckState state : states) {
				try {
					printState(state,indent);
				}
				catch(Exception e) {
					
				}
			}
			indent.println("</details>");
		} catch (TamagoCCException e) {
			e.printStackTrace();
		}
		
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
		laststate.setResult(NodeDNFResult.ERROR, new NodeEmpty(ex.getClass().getName() + " : "+ ConvertExprXml.toString(ex.getMessage())));
	}


	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#reachMaxDepth(tamago.check.util.TamagoCheckContext, tamagocc.generic.api.GState, tamago.check.fixpoint.TamagoCheckState)
	 */
	public void reachMaxDepth(TamagoCheckContext ctx2, GState state,
			TamagoCheckState localstate) {
		laststate.setResult(NodeDNFResult.MAXDEPTH, new NodeEmpty("max depth"));
		nodes.add(laststate);
	}


	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#close()
	 */
	public void close() {
		
	}


	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#registered(tamago.check.fixpoint.TamagoCheckState, tamago.check.fixpoint.TamagoCheckState)
	 */
	public void registered(TamagoCheckState localstate,
			TamagoCheckState nextstate) {
		states.add(nextstate);
		if(lastdnf != null)
			lastdnf.setResult(NodeDNFResult.NONE, new NodeNextState(nextstate));
	}


	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#openTransition(tamagocc.generic.api.GTransition)
	 */
	public void openTransition(GTransition trans) {
		lasttransition = new NodeTransition(trans);
	}


	/* (non-Javadoc)
	 * @see tamago.check.report.GenericTamagoCheckReport#closeTransition()
	 */
	public void closeTransition() {
		laststate.addTransition(lasttransition);
		lasttransition = null;
	}
	
	
	public static void printState(TamagoCheckState s,TamagoCCIndentator indent) {
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
}
