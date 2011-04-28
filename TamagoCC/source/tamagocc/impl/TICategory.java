package tamagocc.impl;

import tamagocc.api.TCategory;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

public class TICategory implements TCategory {

	public static TCategory NoCategory = new TICategory(""); 
	
	private String name;
	
	public TICategory(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public boolean equals(Object o) {
		if (o instanceof TCategory) {
			TCategory p = (TCategory) o;
			return getName().equals(p.getName());
		}
		return false;
	}
	
	  /**
     * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitCategory(this);
    }
}
