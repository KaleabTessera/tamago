/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GState;
import tamagocc.generic.api.GTransition;
import tamagocc.generic.api.GExpression.GExprType;
import tamagocc.util.TamagoCCMakeReadableGExpression;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class GITransition implements GTransition {

	private GState origin;
	private GState destination;
	private GExpression condition;
	private String methodid;
		
	private int id;
	
	
	private static int posID = 0;
	private static int creatID() {
		return (++posID);
	}
	
	/**
	 * 
	 */
	public GITransition(GState origin,GState destination,String methodid,GExpression condition) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.condition = condition;
		this.methodid = methodid;
		
		this.id = creatID();
		
		
		origin.addOutgoingTransition(this);
		destination.addIncomingTransition(this);
	}
	
	
	public GITransition(GTransition transition,GState origin,GState dest) {
		super();
		this.origin = origin;
		this.destination= dest;
		this.condition = transition.getCondition();
		this.methodid = transition.getMethodID();
		
		this.id = creatID();
		
		origin.addOutgoingTransition(this);
		destination.addIncomingTransition(this);
	}

	/**
	 * @see tamagocc.generic.api.GTransition#getOrigin()
	 */
	public GState getOrigin() {
		return origin;
	}

	/**
	 * @see tamagocc.generic.api.GTransition#getFinal()
	 */
	public GState getFinal() {
		return destination;
	}

	
	/**
	 * @see tamagocc.generic.api.GTransition#getCondition()
	 */
	public GExpression getCondition() {
		return condition;
	}

	public String getMethodID() {
		return methodid;
	}
	
	public int getID() {
		return id;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitTransition(this);
	}
	
	/**
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if(o instanceof GTransition) {
			GTransition t = (GTransition)o;
			return (origin.equals(t.getOrigin())
					&& destination.equals(t.getFinal())
					&& methodid.equals(t.getMethodID())
					&& condition.equals(t.getCondition())
					);
		}
		else 
			return false;
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(origin.getName());
		sb.append("--{");
		sb.append(methodid);
		
		if(condition.getCategory() != GExprType.NOCONTRACT) {
			sb.append("<");
			sb.append(TamagoCCMakeReadableGExpression.toString(condition));
			sb.append(">");
		}
		sb.append("}-->");
		sb.append(destination.getName());
		return sb.toString();
	}
}
