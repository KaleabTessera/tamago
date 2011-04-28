package tamagocc.generic.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGExpressionVisitor;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GCall;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GPreExpression;

public class GICall extends GIExpression implements GCall {
	     // Iterator<GExpression>
	private ArrayList<GExpression> arguments;
	private String name;
	
	
	public GICall(String name,Collection<GExpression> arguments) {
		this.name = name;
		this.arguments = new ArrayList<GExpression>(arguments);
		
		preexprs = new ArrayList<GPreExpression>();
		Iterator<GExpression> iarguments = arguments.iterator();
		while(iarguments.hasNext()) {
			GExpression arg = iarguments.next();
			preexprs.addAll(arg.getPreExpression());
		}		
	}	
	
	public Iterator<GExpression> getArguments() {
		return arguments.iterator();
	}

	public String getName() {
		return name;
	}

	public GExprType getCategory() {
		return GExprType.CALL;
	}

	public void setArguments(Collection<GExpression> arguments) {
		this.arguments = new ArrayList<GExpression>(arguments);
		preexprs = new ArrayList<GPreExpression>();
		Iterator<GExpression> iarguments = arguments.iterator();
		while(iarguments.hasNext()) {
			GExpression arg = (GExpression)iarguments.next();
			preexprs.addAll(arg.getPreExpression());
		}	
	}
	
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitCall(this);
	}
	
	/**
	 * @see tamagocc.generic.api.GExpression#visitExpression(tamagocc.generic.TamagoCCGExpressionVisitor)
	 */
	public Object visitExpression(TamagoCCGExpressionVisitor exprvisitor) throws TamagoCCException {
		return exprvisitor.visitCall(this);
	}
	
	public boolean equals(Object o) {
		if (o instanceof GCall) {
			GCall nc = (GCall) o;
			boolean result = true;
			Iterator<GExpression> args = getArguments();
			Iterator<GExpression> args2 = nc.getArguments();
			while(args.hasNext()) {
				if(!args2.hasNext())
					return false;
				GExpression e = args.next();
				GExpression e2 = args2.next();
				result = result&&(e.equals(e2));
			}
			if(args2.hasNext())
				return false;
			return nc.getName().equals(getName())&&result;
		}
		else
			return false;
	}


	public int getArgCount() {
		return arguments.size();
	}


	public GExpression getArgument(int pos) {
		return arguments.get(pos);
	}

}
