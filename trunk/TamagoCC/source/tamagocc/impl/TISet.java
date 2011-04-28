/**
 * 
 */
package tamagocc.impl;

import java.util.ArrayList;
import java.util.Collection;

import tamagocc.api.TExpression;
import tamagocc.api.TSet;
import tamagocc.api.TType;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

/**
 * This class implements the interface a Tamago Set. We recall that a 
 * Tamago Set is a range or a classic set of value. 
 * 
 * @author Hakim Belhaouari
 *
 */
public class TISet implements TSet {

	private ArrayList<TExpression> set;
	private TType type;
	
	/**
	 * 
	 */
	public TISet(TType t, Collection<TExpression> s) {
		set = new ArrayList<TExpression>();
		set.addAll(s);
		this.type =t;
	}
	
	

	public boolean add(TExpression e) {
		return set.add(e);
	}

	/**
	 * This method indicates the number of elements presents in the current set.
	 * @see tamagocc.api.TSet#size()
	 * @return Return the number of element included in the set.
	 */
	public int size() {
		return set.size();
	}

	/**
	 * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
	 */
	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitSet(this);
	}

	public Iterable<TExpression> getElements() {
		return set;
	}



	public TType getType() {
		return type;
	}

}
