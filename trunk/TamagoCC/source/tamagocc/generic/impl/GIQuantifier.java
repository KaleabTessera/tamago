/**
 * 
 */
package tamagocc.generic.impl;

import java.util.ArrayList;

import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GPreExpression;
import tamagocc.generic.api.GQuantifier;
import tamagocc.generic.api.GQuantifierVariable;
import tamagocc.generic.api.GType;
import tamagocc.generic.api.GVariable;
import tamagocc.util.TamagoCCMakeReadableGExpression;

/**
 * This class represents the root of all quantifier expression.
 * Here we consider two side of this component:
 * <ul>
 * <li>The first one corresponds to the quantified expression, considered as a pre
 * 	expression in our architecture</li>
 * <li>And the last one correspond to a particular variable, which represents the result of
 * the quantifier expression.<li>
 * </ul>
 * 
 * @author Hakim Belhaouari
 *
 */
public abstract class GIQuantifier implements GQuantifier {

	protected GType type;
	protected GVariable variable;
	protected GExpression body;
	protected ArrayList<GPreExpression> preexpr;
	protected GIQuantifierVariable resvar;
	
	/**
	 * 
	 */
	public GIQuantifier(GType t,GVariable v,GExpression body) {
		this.type = t;
		this.variable = v;
		this.body = body;
		
		preexpr = new ArrayList<GPreExpression>();
		resvar = new GIQuantifierVariable(this);	
		for (GPreExpression e : body.getPreExpression()) {
			resvar.addPreExpression(e);
		}
		resvar.addPreExpression(this);
	}

	/**
	 * @see tamagocc.generic.api.GQuantifier#getBody()
	 */
	public GExpression getBody() {
		return body;
	}

	/**
	 * @see tamagocc.generic.api.GQuantifier#getResultExpression()
	 */
	public GQuantifierVariable getResultExpression() {
		return resvar;
	}
	
	public void setName(String name) {
		resvar.setName(name);
	}

	/**
	 * @see tamagocc.generic.api.GQuantifier#getType()
	 */
	public GType getType() {
		return type;
	}

	/**
	 * @see tamagocc.generic.api.GQuantifier#getVariable()
	 */
	public GVariable getVariable() {
		return variable;
	}
	
	public String toString() {
		return TamagoCCMakeReadableGExpression.toString(this.getResultExpression());
	}
}
