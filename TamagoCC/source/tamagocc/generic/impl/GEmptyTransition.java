/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTransition;
import tamagocc.generic.api.GExpression.GExprType;

/**
 * This class substitute a transition in the behavior. It can be used by fixpoint selector of the tamagotest
 * tools
 * @author Hakim Belhaouari
 *
 */
public class GEmptyTransition implements GTransition {

	private GMethod meth;
	private GState ori;
	private GState dest;
	public GEmptyTransition(GMethod s) {
		meth = s;
		this.ori = null;
		this.dest = null;
	}
	
	public GEmptyTransition(GState ori,GMethod s, GState dest) {
		meth = s;
		this.ori = ori;
		this.dest = dest;
	}
	/**
	 * @see tamagocc.generic.api.GTransition#getCondition()
	 */
	public GExpression getCondition() {
		return new GINoContract();
	}

	/**
	 * @see tamagocc.generic.api.GTransition#getFinal()
	 */
	public GState getFinal() {
		return dest;
	}

	/**
	 * @see tamagocc.generic.api.GTransition#getMethodID()
	 */
	public String getMethodID() {
		return meth.getID();
	}
	
	/**
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if(o instanceof GTransition) {
			GTransition t = (GTransition)o;
			return (ori.equals(t.getOrigin())
					&& dest.equals(t.getFinal())
					&& meth.equals(t.getMethodID())
					&& t.getCondition().getCategory() == GExprType.NOCONTRACT);
		}
		else 
			return false;
	}

	/**
	 * @see tamagocc.generic.api.GTransition#getOrigin()
	 */
	public GState getOrigin() {
		return ori;
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitTransition(this);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(ori != null)
			sb.append(ori.getName());
		if(meth == null)
			sb.append("?-{<nullMethod>}->");
		else if(meth.getID() == null)
			sb.append("?-{<badID>}->");
		else
			sb.append("?-{"+meth.getID()+"}->");
		if(dest != null)
			sb.append(dest.getName());
		sb.append("?");
		return sb.toString();
	}

}
