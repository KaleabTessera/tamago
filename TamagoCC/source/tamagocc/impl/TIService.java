package tamagocc.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import tamagocc.api.TBehavior;
import tamagocc.api.TExtendService;
import tamagocc.api.TImplements;
import tamagocc.api.TInvariant;
import tamagocc.api.TMethod;
import tamagocc.api.TNamespace;
import tamagocc.api.TProperty;
import tamagocc.api.TProvide;
import tamagocc.api.TService;
import tamagocc.api.TTamago;
import tamagocc.api.TType;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;
/**
 * @author Hakim BELHAOUARI
 * 10 oct. 2005 TIService.java
 */
/**
 */
public class TIService extends TITamago implements TService {

    private String generic;
    private Collection<TExtendService> subextends;
    /**
     * @param n : nom du service
     * @param module : nom du module 
     * @param g : nom generic pour une utilisation future
     * @param paramtypes 
     * @param m : Collection des methodes
     * @param p : Collection des proprietes
     * @param inv : expression representant l'invariant de la methode
     * @param b : objet representant le comportement du service
     * @param e : listes des services que ce service etend.
     */
    public TIService(String n,String module,
                String g,
            	Collection<TMethod> methods,
            	Collection<TProperty> properties,
            	Collection<TInvariant> invariants,
            	TBehavior behavior,
            	Collection<TExtendService> extension,
            	Collection<TImplements> impls,
        		Collection<TNamespace> namespaces,
        		Collection<TType> paramtypes)
    {
        super(n,module,methods,properties,invariants,behavior,impls,namespaces, paramtypes);
        generic = g;
        subextends = extension;
        
    }
  
    public TIService(String n, String module) {
		super(n, module);
		subextends = new ArrayList<TExtendService>();
	}

	/**
     * @see tamagocc.api.TService#getBehavior()
     */
    public TBehavior getBehavior() {
        return behavior;
    }

    /**
     * @see tamagocc.api.TService#getProperties()
     */
    public Iterator<TProperty> getProperties() {
        return properties.iterator();
    }


    /**
     * @see tamagocc.api.TObject#visit()
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitService(this);
    }
    
    /**
     * @see tamagocc.api.TTamago#getTamagoType()
     */
    public int getTamagoType() {
        return TAMAGO_SERVICE;
    }
    
    /**
     * @see tamagocc.api.TService#hasBehavior()
     */
    public boolean hasBehavior() {
        return behavior.getStates().hasNext();
    }

    public boolean isGeneric() {
    	return (generic.trim().length() > 0);
    }
    
    public String getGenericType() {
    	return generic;
    }
    
    public Iterator<TExtendService> getExtends() {
    	return subextends.iterator();
    }
    
    public boolean equals(Object o) {
    	return (o instanceof TService) && super.equals(o);
    }
    
    
    public TProperty getProperty(String name)
    throws NoSuchElementException
    {
        Iterator<TProperty> ps = properties.iterator();
        while(ps.hasNext()) {
            TProperty property = (TProperty)ps.next();
            if(property.getName().equals(name)) {
                return property;
            }
        }
        
        Iterator<?> extend = getExtends();
        while(extend.hasNext()) {
            TProvide provide = (TProvide)extend.next();
            TTamago service = provide.getService();
            Iterator<TProperty> properties = service.getProperties();
            while(properties.hasNext()) {
                TProperty property = (TProperty)properties.next();
                if(property.getName().equals(name)) {
                    return property;
                }
            }
        }
        
        throw new NoSuchElementException("Unfound property : "+name+" in "+getName());
    }

    public void addExtend(TExtendService es) {
    	if(!subextends.contains(es))
    		subextends.add(es);
    }
}
