package tamagocc.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import tamagocc.api.TCondition;
import tamagocc.api.TMethod;
import tamagocc.api.TParameter;
import tamagocc.api.TType;
import tamagocc.exception.TamagoCCException;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GParameter;
import tamagocc.util.NilIterator;
import tamagocc.util.TamagoCCVisitor;

/**
  * @author Hakim BELHAOUARI
 */
public class TIMethod implements TMethod {

    private String name;
    private String id;
    private ArrayList<TParameter> params;
    private TType type;
    private TCondition precond;
    private TCondition postcond;
    
    /**
     * 
     */
    public TIMethod(String n,
            	String id,
            	TType t,
            	Collection<TParameter> a,
            	TCondition e,
            	TCondition o)
    {
        super();
        name = n;
        params = new ArrayList<TParameter>(a);
        type = t;
        this.id = id;
        precond = e;
        postcond = o;
    }

    /**
     *  Copy everything except precondition and postcondition
     * @param meth
     */
    public TIMethod(TMethod meth) {
		super();
		this.name = meth.getName();
		this.id = meth.getID();
		this.params = new ArrayList<TParameter>();
		Iterator<TParameter> par = meth.getParameters();
		while(par.hasNext()) {
			params.add(par.next());
		}
		this.type = meth.getType();
		precond = new TICondition(new TINoContract(), new TICategory(""), "");
		postcond = new TICondition(new TINoContract(), new TICategory(""), "");
	}

	public TIMethod(GMethod meth) {
		super();
		this.name = meth.getName();
		this.id = meth.getID();
		this.params = new ArrayList<TParameter>();
		Iterator<GParameter> params = meth.getParameters();
		while(params.hasNext()) {
			GParameter gparam = params.next();
			TType type = TIType.generateType(gparam.getType().getType());
			TIParameter param = new TIParameter(gparam.getName(), type);
			this.params.add(param);
		}
		this.type = TIType.generateType(meth.getType().getType());
		precond = new TICondition(new TINoContract(), new TICategory(""), "");
		postcond = new TICondition(new TINoContract(), new TICategory(""), "");
	}

	/**
     * @see tamagocc.api.TMethod#getID()
     */
    public String getID() {
        return id;
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
        return type;
    }

    /**
     * @see tamagocc.api.TMethod#getParameters()
     */
    public Iterator<TParameter> getParameters() {
        return params.iterator();
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
     * @see tamagocc.api.TObject#visit()
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitMethod(this);
    }

    public int getParameterNumber() {
        return params.size();
    }

	public void setType(TType type) {
		this.type = type;
	}

	public void setPrecond(TCondition precond) {
		this.precond = precond;
	}

	public void setPostcond(TCondition postcond) {
		this.postcond = postcond;
	}
	
	public void addParameter(TParameter param) {
		this.params.add(param);
	}
	
	public void addParameter(TType type, String name) {
		TIParameter param = new TIParameter(name, type);
		this.params.add(param);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TIMethod [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (type != null) {
			builder.append("type=");
			builder.append(type);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (params != null) {
			builder.append("params=");
			builder.append(params);
			builder.append(", ");
		}
		if (precond != null) {
			builder.append("precond=");
			builder.append(precond);
			builder.append(", ");
		}
		if (postcond != null) {
			builder.append("postcond=");
			builder.append(postcond);
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public TParameter getParameter(int p) {
		return params.get(p);
	}

	public void setId(String string) {
		this.id = string;
	}
}
