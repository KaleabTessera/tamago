package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GModule;

public class GIModule implements GModule {

	private String full;
	
	public GIModule(String fullname) {
		full = fullname;
		
	}
	
	public String getFullModule() {
		return full;
	}
	
	public boolean equals(Object o) {
		if(o instanceof GModule)
			return full.equals(((GModule)o).getFullModule());
		else
			return false;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitModule(this);
	}
	
	
}
