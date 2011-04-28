package tamagocc.impl;

import tamagocc.api.TRequire;
import tamagocc.api.TService;
import tamagocc.api.TType;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author Hakim BELHAOUARI
 */
public class TIRequire implements TRequire {

    private String label;
    private String module;
    private String nom;
    private TService service;
	private TType[] generics;

    public TIRequire(String l,String s,String m,TService service,TType[] types) {
        super();
        label = l;
        module = m;
        nom = s;
        this.service = service;
        this.generics = types;
    }

    /**
     * @see tamagocc.api.TRequire#getServiceName()
     */
    public String getServiceName() {
        return nom;
    }

    /**
     * @see tamagocc.api.TRequire#getLabel()
     */
    public String getLabel() {
        return label;
    }

    /**
     * @see tamagocc.api.TRequire#getModule()
     */
    public String getModule() {
        return module;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof TRequire) {
			TRequire p = (TRequire) o;
			return (getServiceName().equals(p.getServiceName())
					&& getLabel().equals(p.getLabel())
					&& getModule().equals(p.getModule()));
		}
    	return false;
    }
    
    /**
     * @see tamagocc.api.TRequire#getService()
     */
    public TService getService() {
    	return service;
    }
    
    /**
     * @see tamagocc.api.TObject#visit()
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitRequire(this);
    }

	/**
	 * @see tamagocc.api.TRequire#getParametrizedType()
	 */
	public TType[] getParametrizedType() {
		return generics;
	}

	/**
	 * @see tamagocc.api.TRequire#isParametric()
	 */
	public boolean isParametric() {
		return generics != null && generics.length > 0;
	}

}
