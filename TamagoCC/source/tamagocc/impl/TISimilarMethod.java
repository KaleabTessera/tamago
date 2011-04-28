/**
 * 
 */
package tamagocc.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

import tamagocc.api.TCall;
import tamagocc.api.TCondition;
import tamagocc.api.TExpression;
import tamagocc.api.TInLabel;
import tamagocc.api.TMethod;
import tamagocc.api.TParameter;
import tamagocc.api.TTamago;
import tamagocc.api.TType;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.NilIterator;
import tamagocc.util.TamagoCCSearchType;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class TISimilarMethod implements TMethod {

    private String name;
    private Collection<TParameter> params;
    private TCondition precond;
    private TCondition postcond; 
	/**
	 * 
	 */
	public TISimilarMethod(TTamago entity,TMethod method,TCall call) throws TamagoCCException
	{
		super();
		name = call.getName();
		params = new ArrayList<TParameter>();
		precond = new TICondition(new TINoContract(),new TICategory(""),"");
		postcond = precond;
		
		int argnum = 0;
		Iterator<? extends TExpression> args = call.getArguments();
		while(args.hasNext()) {
			TExpression arg = (TExpression)args.next();
			TamagoCCSearchType typeur = new TamagoCCSearchType(entity,method,new Stack<TInLabel>(), arg);
			TType type = (TType)typeur.getType();
			TIParameter parameter = new TIParameter("arg"+argnum,type);
			params.add(parameter);
		}
	}

	/**
	 * @see tamagocc.api.TMethod#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see tamagocc.api.TMethod#getType()
	 */
	public TType getType() {
		return TIType.generateType("void");
	}

	/**
	 * @see tamagocc.api.TMethod#getID()
	 */
	public String getID() {
		return "";
	}

	/**
	 * @see tamagocc.api.TMethod#getParameters()
	 */
	public Iterator<TParameter> getParameters() {
		return params.iterator();
	}

    public boolean equals(Object o) {
    	if (o instanceof TMethod) {
			TMethod p = (TMethod) o;
			return (getName().equals(p.getName())
					&& getID().equals(p.getID())
					&& getType().equals(p.getType())
					&& getPrecondition().equals(p.getPrecondition())
					&& getPostcondition().equals(p.getPostcondition())
					&& NilIterator.areEqual(getParameters(),p.getParameters()));
		}
    	return false;
    }
	
	/**
	 * @see tamagocc.api.TMethod#getParameterNumber()
	 */
	public int getParameterNumber() {
		return params.size();
	}

	/**
	 * @see tamagocc.api.TMethod#getPrecondition()
	 */
	public TCondition getPrecondition() {
		return precond;
	}

	/**
	 * @see tamagocc.api.TMethod#getPostcondition()
	 */
	public TCondition getPostcondition() {
		return postcond;
	}

	/**
	 * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
	 */
	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		throw new TamagoCCException("TISimilarMethod : impossible feature");
	}

}
