/**
 * 
 */
package tamagocc.generic.impl;

import java.util.ArrayList;
import java.util.Collection;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GSet;
import tamagocc.generic.api.GType;

/**
 * @author belhaouari
 *
 */
public class GISet implements GSet {

	private ArrayList<GExpression> set;
	private GType type;
	/**
	 * 
	 */
	public GISet(GType t,Collection<GExpression> s) {
		set = new ArrayList<GExpression>(s);
		this.type = t;
	}

	/**
	 * @see tamagocc.generic.api.GSet#getElements()
	 */
	public Iterable<GExpression> getElements() {
		return set;
	}

	/**
	 * @see tamagocc.generic.api.GSet#size()
	 */
	public int size() {
		return set.size();
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitSet(this);
	}

	public GType getType() {
		return type;
	}

}
