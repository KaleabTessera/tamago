/**
 * 
 */
package tamagocc.impl;

import tamagocc.api.TPercolator;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class TIPercolator implements TPercolator {

	private String name;
	
	public static TPercolator getAllPercolator() { return new TIPercolator("*"); }
	

	public TIPercolator(String name) {
		super();
		this.name = name;
	}

	/**
	 * @see tamagocc.api.TPercolator#getName()
	 */
	public String getName() {
		return name;
	}
	
	public boolean equals(Object o) {
		if (o instanceof TPercolator) {
			TPercolator p = (TPercolator) o;
			return (getName().equals(p.getName()));
		}
		return false;
	}

	/**
	 * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
	 */
	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitPercolator(this);
	}

}
