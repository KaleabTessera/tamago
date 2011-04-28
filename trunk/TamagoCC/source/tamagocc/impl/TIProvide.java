package tamagocc.impl;

import tamagocc.api.TProvide;
import tamagocc.api.TService;
import tamagocc.api.TType;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author Hakim BELHAOUARI
 */
public class TIProvide implements TProvide {

    private String service;
    private String module;
    private TService tamago;
	private TType[] generics;
    
    /**
     * 
     */
    public TIProvide(String s,String m, TType[] paramtypes,TService tamago) {
        super();
        service = s;
        module = m;
        this.tamago = tamago;
        generics = paramtypes;
    }
    
    public TIProvide(String s,String m, TService tamago) {
        super();
        service = s;
        module = m;
        this.tamago = tamago;
        generics = new TType[0];
    }

    /**
     * @see tamagocc.api.TProvide#getServiceName()
     */
    public String getServiceName() {
        return service;
    }

    /**
     * @see tamagocc.api.TProvide#getModule()
     */
    public String getModule() {
        return module;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof TProvide) {
			TProvide p = (TProvide) o;
			return (getServiceName().equals(p.getServiceName())
					&& getModule().equals(p.getModule()));
		}
    	return false;
    }
    
    /**
     * @see tamagocc.api.TProvide#getService()
     */
    public TService getService() {
        return tamago;
    }

    /**
     * @see tamagocc.api.TObject#visit()
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitProvide(this);
    }

	/**
	 * @see tamagocc.api.TProvide#getParametrizedType()
	 */
	public TType[] getParametrizedType() {
		return generics;
	}

	/**
	 * @see tamagocc.api.TProvide#isParametric()
	 */
	public boolean isParametric() {
		return generics != null && generics.length > 0;
	}
}
