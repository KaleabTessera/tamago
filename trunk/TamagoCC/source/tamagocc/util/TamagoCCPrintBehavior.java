/**
 * 
 */
package tamagocc.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Hashtable;

import javax.swing.JFrame;

import salvo.jesus.graph.DirectedGraph;
import salvo.jesus.graph.DirectedGraphImpl;
import salvo.jesus.graph.Edge;
import salvo.jesus.graph.Vertex;
import salvo.jesus.graph.VertexImpl;
import salvo.jesus.graph.visual.GraphEditor;
import salvo.jesus.graph.visual.VisualVertex;
import salvo.jesus.graph.visual.layout.ForceDirectedLayout;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GTransition;

/**
 * This class is usefull only if we want debug a contract is show a frame with the 
 * graph of the specified finite state automaton of the behavior.
 * <br>
 * This class need the openjgraph.jar.
 * @author Hakim Belhaouari
 *
 */
public final class TamagoCCPrintBehavior extends JFrame {

	private static final long serialVersionUID = 1273032580378196435L;
	private DirectedGraph graph;
	private GraphEditor grapheditor;
	private GBehavior behavior;
	
	private Hashtable<GState,Vertex> allstates;
	
	public TamagoCCPrintBehavior(GBehavior behavior,String title) throws Exception {
		setTitle(title);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		allstates = new Hashtable<GState,Vertex>();
		graph = new DirectedGraphImpl();
		grapheditor = new GraphEditor();
		
		this.behavior = behavior;
		
		grapheditor.setGraph(graph);
		prepareGraph();
				
		ForceDirectedLayout fdl = new ForceDirectedLayout(grapheditor.getVisualGraph());
		fdl.setSpringLength(150);
		
		grapheditor.getVisualGraph().setGraphLayoutManager(fdl);		
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(grapheditor,BorderLayout.CENTER);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    Dimension frameSize = new Dimension( screenSize.width/2, screenSize.height/2);
		    
		this.setSize( frameSize );
		//this.setLocation((int)(screenSize.getWidth() - frameSize.getWidth()) / 2, (int)(screenSize.getHeight() - frameSize.getHeight()) / 2);
		
	}
	
	public TamagoCCPrintBehavior(GTamago entity) throws Exception {
		super();
		
		setTitle("Behavior Viewer for the entity "+entity.getModule().getFullModule()+"#"+entity.getName());
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		allstates = new Hashtable<GState,Vertex>();
		graph = new DirectedGraphImpl();
		grapheditor = new GraphEditor();
		
		this.behavior = entity.getBehavior();
		
		grapheditor.setGraph(graph);
		prepareGraph();
		
		
		ForceDirectedLayout fdl = new ForceDirectedLayout(grapheditor.getVisualGraph());
		fdl.setSpringLength(150);
		
		grapheditor.getVisualGraph().setGraphLayoutManager(fdl);
		
		
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(grapheditor,BorderLayout.CENTER);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    Dimension frameSize = new Dimension( screenSize.width/2, screenSize.height/2);
		    
		this.setSize( frameSize );
		//this.setLocation((int)(screenSize.getWidth() - frameSize.getWidth()) / 2, (int)(screenSize.getHeight() - frameSize.getHeight()) / 2);
	}
	
	private void prepareGraph() throws Exception {
		for(GState key : behavior.getStates()) {
			Vertex vertex = new VertexImpl(key.getName());
			allstates.put(key,vertex);
			graph.add(vertex);
		}
		
		for (GTransition transition : behavior.getTransitions()) {
			if(!allstates.containsKey(transition.getOrigin()))
				throw new TamagoCCException("Etat introuvable <"+transition.getOrigin().getName()+">");
			Vertex v1 = (Vertex)allstates.get(transition.getOrigin());
			
			if(!allstates.containsKey(transition.getOrigin()))
				throw new TamagoCCException("Etat introuvable <"+transition.getFinal().getName()+">");
			Vertex v2 = (Vertex)allstates.get(transition.getFinal());

			Edge edge = graph.addEdge(v1,v2);
			edge.setFollowVertexLabel(false);
			if(transition.getCondition().getCategory() == GExpression.GExprType.NOCONTRACT)
				edge.setLabel(transition.getMethodID());
			else
				edge.setLabel(transition.getMethodID()+"*");
		}
		
//		if(allstates.containsKey(behavior.getDefaultState())) {			
//			Vertex v = (Vertex)allstates.get(behavior.getDefaultState());
//			graph.traverse(v);
//		}
		
		for(GState state : behavior.getDefaultStates()) {
			if(allstates.containsKey(state)) {			
				Vertex v = (Vertex)allstates.get(state);
				VisualVertex vv = grapheditor.getVisualGraph().getVisualVertex(v);
				vv.setFillcolor(Color.RED);
			}
		}
	}

	
	
}
