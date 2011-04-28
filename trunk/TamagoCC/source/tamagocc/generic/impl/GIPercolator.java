/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GPercolator;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class GIPercolator implements GPercolator {

	private String name;
	
	public GIPercolator(String name) {
		super();
		this.name = name;
	}
		
	public String getName() {
		return name;
	}
	
	public boolean equals(Object o) {
		if (o instanceof GPercolator) {
			GPercolator no = (GPercolator) o;
			return name.equals(no.getName());			
		}
		else
			return false;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitPercolator(this);
	}

}
