package tamagocc.impl;

import tamagocc.api.TAccess;
import tamagocc.api.TProperty;
import tamagocc.api.TType;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;
/**
 * @author Hakim BELHAOUARI
 * 10 oct. 2005 TIProperty.java
 */
/**
 */
public class TIProperty implements TProperty {

    private String name;
    private TType type;
    private TAccess access;
    
    /**
     * @param n : nom de la propriete
     * @param t : type de la propriete
     * @param a : type d'access
     */
    public TIProperty(String n,TType t,TAccess a) {
        super();
        name = n;
        type = t;
        access = a;
    }

    /**
     * @see tamagocc.api.TProperty#getName()
     */
    public String getName() {
        return name;
    }

    /**
     * @see tamagocc.api.TProperty#getType()
     */
    public TType getType() {
        return type;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof TProperty) {
			TProperty p = (TProperty) o;
			return (getName().equals(p.getName())
					&&getType().equals(p.getType())
					&&getAccess().equals(p.getAccess()));
		}
    	return false;
    }

    /**
     * @see tamagocc.api.TProperty#getAccess()
     */
    public TAccess getAccess() {
        return access;
    }

    /**
     * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitProperty(this);
    }

}
