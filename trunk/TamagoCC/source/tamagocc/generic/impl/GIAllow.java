package tamagocc.generic.impl;

import tamagocc.api.TAllow;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GAllow;

public class GIAllow implements GAllow {

	private String id;
	
	public GIAllow(TAllow a) {
		id = a.getMethod();
	}
	
	
	public GIAllow(String id) {
		this.id = id;
	}
	
	public String getID() {
		return id;
	}

	
	public void setID(String id) {
		this.id = id;
	}
	
	public boolean equals(Object i) {
		if(i instanceof GAllow) {
			return getID().equals(((GAllow)i).getID()); 
		}
		else
			return false;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitAllow(this);
	}
	
}
