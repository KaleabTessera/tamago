/**
 * 
 */
package tamagocc.generic.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GAllow;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.generic.api.GTransition;
import tamagocc.util.NilCollection;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class GIState implements GState {

	protected String name;
	protected Collection<GAllow> allowed;
	
	protected ArrayList<GTransition> incomingtransitions;
	protected ArrayList<GTransition> outgoingtransitions;
	
	protected GTamagoEntity parent;
	protected boolean implicit;
	
	public static final GState NOSTATE = new GIState(null,"",new NilCollection<GAllow>());
	/**
	 * 
	 */
	public GIState(GTamagoEntity parent,String name,Collection<GAllow> allowed) {
		super();
		this.name = name;
		this.allowed = allowed;
		
		this.parent = parent;
		this.incomingtransitions = new ArrayList<GTransition>();
		this.outgoingtransitions = new ArrayList<GTransition>();
		
		implicit = false;
	}
	
	public GIState(GTamagoEntity parent,String name,Collection<GAllow> allowed, boolean implicit) {
		this(parent,name,allowed);
		this.implicit = implicit;
	}
	
	public GIState(GState s) {
		super();
		this.name = s.getName();
		this.allowed = new ArrayList<GAllow>();
		this.parent = s.getParent();
		Iterator<GAllow> allows = s.getAllowsMethod();
		while(allows.hasNext()) {
			allowed.add(allows.next());
		}
		
		this.incomingtransitions = new ArrayList<GTransition>();
		this.outgoingtransitions = new ArrayList<GTransition>();
		implicit = s.isImplicit();
	}
	public GIState(GState s,boolean implicit) {
		this(s);
		this.implicit = implicit;
	}
	
	public GIState(GState s,String nname) {
		super();
		this.name = nname;
		this.allowed = new ArrayList<GAllow>();
		this.parent = s.getParent();
		Iterator<GAllow> allows = s.getAllowsMethod();
		while(allows.hasNext()) {
			allowed.add(allows.next());
		}
		
		this.incomingtransitions = new ArrayList<GTransition>();
		this.outgoingtransitions = new ArrayList<GTransition>();
		this.implicit = s.isImplicit();
	}
	public GIState(GState s,String nname,boolean implicit) {
		this(s,nname);
		this.implicit = implicit;
	}

	/**
	 * @see tamagocc.generic.api.GState#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see tamagocc.generic.api.GState#getAllowsMethod()
	 */
	public Iterator<GAllow> getAllowsMethod() {
		return allowed.iterator();
	}
	
	
	public boolean equals(Object o) {
		if(o instanceof GState) {
			GState s = (GState)o;
			return ((getFullName().equals(s.getFullName())));
		}
		return false;
	}
		
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitState(this);
	}

	public Iterator<GTransition> getIncomingTransitions() {
		return incomingtransitions.iterator();
	}

	public Iterator<GTransition> getOutgoingTransitions() {
		return outgoingtransitions.iterator();
	}

	public boolean addIncomingTransition(GTransition transition) {
		if(!incomingtransitions.contains(transition))
			return incomingtransitions.add(transition);
		else
			return false;
	}
	
	public boolean addOutgoingTransition(GTransition transition) {
		if(!outgoingtransitions.contains(transition))
			return outgoingtransitions.add(transition);
		else 
			return false;
	}
	
	public boolean addAllow(GAllow allow) {
		if(!allowed.contains(allow))
			return allowed.add(allow);
		else return false;
	}

	public GTamagoEntity getParent() {
		return parent;
	}
	
	public boolean isImplicit() {
		return implicit;
	}
	
	public void setIsImplicit(boolean b) {
		implicit = b;
	}

	public String getFullName() {
		StringBuffer sb = new StringBuffer();
		if(parent == null) {
			sb.append("");
		}
		else {
			sb.append(parent.getModule().getFullModule());
			sb.append(".");
			sb.append(parent.getName());
		}
		sb.append("#");
		sb.append(getName());
		return sb.toString();
	}
	
	public void setParent(GTamagoEntity parent) {
		this.parent = parent;
	}

	public int sizeOutgoingTransitions() {
		return outgoingtransitions.size();
	}

	public int sizeAllowedMethods() {
		return allowed.size();
	}
	
	public String toString() {
		return getFullName();
	}
}
