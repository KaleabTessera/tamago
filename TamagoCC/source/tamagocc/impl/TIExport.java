package tamagocc.impl;

import tamagocc.api.TExport;
import tamagocc.api.TExpression;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

/**
 * 
  * @author Hakim BELHAOUARI
 */
public class TIExport implements TExport {

    private String service;
    private String servmodule;
    private TExpression component;
    /**
     * 
     */
    public TIExport(String s,String m,TExpression c) {
        super();
        service = s;
        servmodule = m;
        component = c;
    }

    /**
     * @see tamagocc.api.TExport#getService()
     */
    public String getService() {
        return service;
    }

    /**
     * @see tamagocc.api.TExport#getComponent()
     */
    public TExpression getProvider() {
        return component;
    }

    /**
     * @see tamagocc.api.TExport#getServiceModule()
     */
    public String getServiceModule() {
        return servmodule;
    }

    public boolean equals(Object o) {
    	if (o instanceof TExport) {
			TExport p = (TExport) o;
			return ( getService().equals(p.getService())
					&& getServiceModule().equals(p.getServiceModule())
					&& getProvider().equals(p.getProvider()));
		}
    	return false;
    }
    
    
    public String toString() {
        return "<export service='"+service+"' module='"+servmodule+"' component='"+component+"' />";
    }
    
    /**
     * @see tamagocc.api.TObject#visit()
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitExport(this);
    }
}
