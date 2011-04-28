/*
 * Created on 9 nov. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tamago.ext.tamagocc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import tamago.TamagoException;

/**
 * @author belhaouari
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class TamagoCCFailCondition extends TamagoException {

	private static final long serialVersionUID = 7596833653866874899L;

	public static final int OTHER = 0;

	public static final int PRECONDITION = 1;

	public static final int POSTCONDITION = 2;

	public static final int INVARIANT = 3;

	protected int typecondition;

	protected Collection<TamagoCCFailItem> failitems;

	/**
	 * @param message
	 */
	protected TamagoCCFailCondition(Collection<? extends TamagoCCFailItem> failitems, int typeCondition) {
		super("");
		this.typecondition = typeCondition;
		this.failitems = new ArrayList<TamagoCCFailItem>();
		this.failitems.addAll(failitems);
	}
	
	protected TamagoCCFailCondition(String expr,String message,int typeCondition) {
		super("");
		this.typecondition = typeCondition;
		this.failitems = new ArrayList<TamagoCCFailItem>();
		failitems.add(new TamagoCCFailItem(expr,message));
	}

	public int getTypeCondition() {
		return typecondition;
	}

	public abstract String getMessage();
	
	public Iterator<TamagoCCFailItem> getTamagoCCFailItem() {
		return failitems.iterator();
	}

}
