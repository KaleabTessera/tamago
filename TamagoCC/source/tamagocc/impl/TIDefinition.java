package tamagocc.impl;

import tamagocc.api.TDefinition;
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
 * 22 juin 2005 TIDefinition.java
 */
public class TIDefinition implements TDefinition {

    private String compname;
    private String module;
    private String label;
    private String percolator;
    
    public TIDefinition(String type,String module,String label) {
        super();
        compname = type;
        this.module = module;
        this.label = label;
        percolator = "";
    }

    public TIDefinition(String type,String module,String label,String percolator) {
        super();
        compname = type;
        this.module = module;
        this.label = label;
        this.percolator = percolator;
    }
    
    /**
     * @see tamagocc.api.TDefinition#getProvider()
     */
    public String getComponentName() {
        return compname;
    }

    /**
     * @see tamagocc.api.TDefinition#getComponentModule()
     */
    public String getComponentModule() {
        return module;
    }

    public boolean equals(Object o) {
    	if (o instanceof TDefinition) {
			TDefinition p = (TDefinition) o;
			return (getComponentLabel().equals(p.getComponentLabel())
					&& getComponentModule().equals(p.getComponentModule())
					&& getComponentName().equals(p.getComponentName())
					&& getPercolator().equals(p.getPercolator()));
		}
    	return false;
    }
    
    public String toString() {
        return "<use name='"+compname+"' label='"+label+"' module='"+module+"' />";
    }
    

    /**
     * 
     * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitDefinition(this);
    }

	public String getComponentLabel() {
		return label;
	}

	public String getPercolator() {
		return percolator;
	}
}
