package tamagocc.impl;

import tamagocc.api.TAllow;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;
/**
 * @author Hakim BELHAOUARI
 * 10 oct. 2005 TIAllow.java
 */
/**
 */
public class TIAllow implements TAllow {

    private String idmethode;
    
    /**
     * @param id : representation textuelle de l'id d'une methode
     */
    public TIAllow(String id) {
        super();
        idmethode = id;
    }

    /**
     * @see tamagocc.api.TAllow#getMethod()
     */
    public String getMethod() {
        return idmethode;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof TAllow) {
			TAllow p = (TAllow) o;
			return getMethod().equals(p.getMethod());
		}
    	return false;
    }

    /**
     * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitAllow(this);
    }

}
