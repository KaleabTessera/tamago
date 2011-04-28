package tamagocc.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import tamagocc.api.TBehavior;
import tamagocc.api.TComponent;
import tamagocc.api.TImplements;
import tamagocc.api.TInvariant;
import tamagocc.api.TMethod;
import tamagocc.api.TNamespace;
import tamagocc.api.TPercolator;
import tamagocc.api.TProperty;
import tamagocc.api.TProvide;
import tamagocc.api.TRequire;
import tamagocc.api.TTamago;
import tamagocc.api.TType;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

/**
 * 
 * Projet : TamagoCC
 * 
 * @author Hakim BELHAOUARI & Frederic Peschanski
 * 22 juin 2005 TIComposant.java
 */
public class TIComposant extends TITamago implements TComponent {

    protected Collection<TProvide> provides;
    protected Collection<TRequire> requires;
    protected ArrayList<TPercolator> percolator;
    
    /**
     * @param paramtypes 
     * 
     */
    public TIComposant(String name,
    		           String module,
    		           Collection<TProperty> properties,
    		           Collection<TProvide> provides,
    		           Collection<TRequire> requires,
    		           Collection<TInvariant> invariants,
    		           Collection<TMethod> methods,
    		           TBehavior behavior,
    		           Collection<TPercolator> percolators,
    		           Collection<TImplements> impls,
    		    		Collection<TNamespace> namespaces,
    		    		Collection<TType> paramtypes)
    {
        super(name,module,methods,properties,invariants,behavior,impls,namespaces,paramtypes);
        this.provides = provides;
        this.requires = requires;
        percolator = new ArrayList<TPercolator>();
        percolator.addAll(percolators);
    }
    
    
    public TIComposant(String name, String module) {
    	super(name,module,new ArrayList<TMethod>(),
    			new ArrayList<TProperty>(),
    			new ArrayList<TInvariant>(),
    			TIBehavior.NoBehavior,
    			new ArrayList<TImplements>(),
    			new ArrayList<TNamespace>(),
    			new ArrayList<TType>());
        this.percolator = new ArrayList<TPercolator>();
        this.provides = new ArrayList<TProvide>();
        this.requires = new ArrayList<TRequire>();
	}


	public String getModule() {
        return module;
    }

    /**
     * @see tamagocc.api.TComposant#getProvides()
     */
    public Iterator<TProvide> getProvides() {
        return provides.iterator();
    }
    
    public boolean equals(Object o) {
    	return (o instanceof TComponent) && super.equals(o);
    }

    /**
     * @see tamagocc.api.TComposant#getRequires()
     */
    public Iterator<TRequire> getRequires() {
        return requires.iterator();
    }
    
    /**
     * @see tamagocc.api.TObject#visit()
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitComponent(this);
    }
    
    public int getTamagoType() {
        return TAMAGO_COMPONENT;
    }
    
    public TProperty getProperty(String name)
    throws NoSuchElementException
    {
        Iterator<TProperty> ps = properties.iterator();
        while(ps.hasNext()) {
            TProperty property = ps.next();
            if(property.getName().equals(name)) {
                return property;
            }
        }
        
        Iterator<TProvide> provides = getProvides();
        while(provides.hasNext()) {
            TProvide provide = provides.next();
            TTamago service = provide.getService();
            Iterator<TProperty> properties = service.getProperties();
            while(properties.hasNext()) {
                TProperty property = properties.next();
                if(property.getName().equals(name)) {
                    return property;
                }
            }
        }
        
        throw new NoSuchElementException("Unfound property : "+name+" in "+getName());
    }


	public Iterator<TPercolator> getAllowedPercolators() {
		return percolator.iterator();
	}
	
	public void addAllowedPercolators(TPercolator percolator) {
		this.percolator.add(percolator);
	}


	public void addProvide(TProvide provide) {
		if(!provides.contains(provide))
			provides.add(provide);
	}
	
	public void addRequire(TRequire require) {
		if(!requires.contains(require))
			requires.add(require);
	}

}
