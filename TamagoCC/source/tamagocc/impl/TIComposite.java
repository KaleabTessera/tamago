package tamagocc.impl;

import java.util.*;

import tamagocc.api.TBehavior;
import tamagocc.api.TBind;
import tamagocc.api.TComposite;
import tamagocc.api.TDefinition;
import tamagocc.api.TExport;
import tamagocc.api.TImplements;
import tamagocc.api.TInvariant;
import tamagocc.api.TMethod;
import tamagocc.api.TNamespace;
import tamagocc.api.TPercolator;
import tamagocc.api.TProperty;
import tamagocc.api.TProvide;
import tamagocc.api.TRequire;
import tamagocc.api.TType;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

/**
 * 
 * Projet : TamagoCC
 * 
 * Stage de Master de Recherche en Science et Technologie du Logiciel
 * Sp?cialit? Algorithmique-Programmation
 * Encadrant : Fr?d?ric Peschanski 
 * 
 * @author Hakim BELHAOUARI
 * 22 juin 2005 TIComposite.java
 */
public class TIComposite extends TIComposant implements TComposite {

    private Collection<TBind> binds;
    private Collection<TDefinition> definitions;
    private Collection<TExport> exports;
    
    
    /**
     * @param paramtypes 
     * 
     */
    public TIComposite(String n,
    				   String m,
    				   Collection<TDefinition> definitions,
    				   Collection<TExport> exports,
    				   Collection<TProperty> properties,
    				   Collection<TProvide> provides,
    				   Collection<TRequire> requires,
    				   Collection<TBind> binds,
    				   Collection<TInvariant> invariants,
    				   TBehavior behavior,
    				   Collection<TMethod> methods,
    				   Collection<TPercolator> percolators,
    				   Collection<TImplements> impls,
    		    		Collection<TNamespace> namespaces, Collection<TType> paramtypes)
    {
        super(n,m,properties,provides,requires,invariants,methods,behavior,percolators,impls,namespaces, paramtypes);
        this.definitions = definitions;
        this.exports = exports;
        this.binds = binds;
    }
    
    public boolean equals(Object o) {
    	return (o instanceof TComposite) && super.equals(o);
    }

    /**
     * @see tamagocc.api.TComposite#getDefinitions()
     */
    public Iterator<TDefinition> getDefinitions() {
        return definitions.iterator();
    }

    /**
     * @see tamagocc.api.TComposite#getExports()
     */
    public Iterator<TExport> getExports() {
        return exports.iterator();
    }


    /**
     * @see tamagocc.api.TComposite#getConnects()
     */
    public Iterator<TBind> getBinds() {
        return binds.iterator();
    }

    /**
     * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitComposite(this);
    }

    public int getTamagoType() {
        return TAMAGO_COMPOSITE;
    }
}
