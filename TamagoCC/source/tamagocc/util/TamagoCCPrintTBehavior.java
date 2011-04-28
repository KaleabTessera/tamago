/**
 * 
 */
package tamagocc.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.JFrame;

import salvo.jesus.graph.DirectedGraph;
import salvo.jesus.graph.DirectedGraphImpl;
import salvo.jesus.graph.Edge;
import salvo.jesus.graph.Vertex;
import salvo.jesus.graph.VertexImpl;
import salvo.jesus.graph.visual.GraphEditor;
import salvo.jesus.graph.visual.VisualVertex;
import salvo.jesus.graph.visual.layout.ForceDirectedLayout;
import tamagocc.api.TBehavior;
import tamagocc.api.TState;
import tamagocc.api.TTamago;
import tamagocc.api.TTransition;
import tamagocc.exception.TamagoCCException;

/**
 * This class is usefull only if we want debug a contract is show a frame with the 
 * graph of the specified finite state automaton of the behavior.
 * <br>
 * This class need the openjgraph.jar.
 * @author Hakim Belhaouari
 *
 */
public final class TamagoCCPrintTBehavior extends JFrame {

	private static final long serialVersionUID = 1273032580378196435L;
	private DirectedGraph graph;
	private GraphEditor grapheditor;
	private TBehavior behavior;
	
	private Hashtable<String,Vertex> allstates;
	
	public TamagoCCPrintTBehavior(TBehavior behavior,String title) throws Exception {
		setTitle(title);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		allstates = new Hashtable<String,Vertex>();
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
	
	public TamagoCCPrintTBehavior(TTamago entity) throws Exception {
		super();
		
		setTitle("Behavior Viewer for the entity "+entity.getModule()+"#"+entity.getName());
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		allstates = new Hashtable<String,Vertex>();
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
		Iterator<TState> states =  behavior.getStates();
		while(states.hasNext()) {
			TState key = states.next();
			Vertex vertex = new VertexImpl(key.getState());
			allstates.put(key.getState(),vertex);
			graph.add(vertex);
			if(isFinal(key)) {
				VisualVertex vv = grapheditor.getVisualGraph().getVisualVertex(vertex);
				vv.setFillcolor(Color.GREEN);
			}
		}
		
		Iterator<TTransition> transitions = behavior.getTransitions(); 
		while(transitions.hasNext()) {
			TTransition transition = transitions.next();
			if(!allstates.containsKey(transition.getFrom()))
				throw new TamagoCCException("Etat introuvable <"+transition.getFrom()+">");
			Vertex v1 = (Vertex)allstates.get(transition.getFrom());
			
			if(!allstates.containsKey(transition.getFrom()))
				throw new TamagoCCException("Etat introuvable <"+transition.getTo()+">");
			Vertex v2 = (Vertex)allstates.get(transition.getTo());

			Edge edge = graph.addEdge(v1,v2);
			edge.setFollowVertexLabel(false);
			if(transition.getCondition() != null)
				edge.setLabel(transition.getMethod());
			else
				edge.setLabel(transition.getMethod()+"*");
		}
		
		String defaultstate = behavior.getDefaultState();
		if(allstates.containsKey(defaultstate)) {			
			Vertex v = (Vertex)allstates.get(defaultstate);
			VisualVertex vv = grapheditor.getVisualGraph().getVisualVertex(v);
			vv.setFillcolor(Color.RED);
		}
	}

	private boolean isFinal(TState key) {
		return !key.getAllow().hasNext();
	}

	
	
}
