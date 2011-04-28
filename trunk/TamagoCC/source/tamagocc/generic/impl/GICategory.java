package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GCategory;

public class GICategory implements GCategory {

	private String category;
	
	public GICategory(String category) {
		super();
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String cat) {
		this.category = cat;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitCategory(this);
	}
}
