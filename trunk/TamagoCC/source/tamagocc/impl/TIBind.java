package tamagocc.impl;

import tamagocc.api.TBind;
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
 * 22 juin 2005 TIConnect.java
 */
public class TIBind implements TBind {

    private String provider;
    private String requirer;
    private String label;
    private String servicename;
    private String servicemodule;
    private boolean forceService;
    
    public TIBind(String provider,String requirer,String label) {
    	this.provider = provider;
    	this.requirer = requirer;
    	this.label = label;
    	
    	forceService = false;
    	servicemodule = "";
    	servicename = "";    	
    }
    
    public TIBind(String provider, String requirer,String label,String servicename,String servicemodule) {
    	this.provider = provider;
    	this.requirer = requirer;
    	this.label = label;
    	forceService = true;
    	this.servicemodule = servicemodule;
    	this.servicename = servicename;
    }
    	
	
	public String getProvider() {
		return provider;
	}

	public String getRequirer() {
		return requirer;
	}

	public String getLabel() {
		return label;
	}

	public boolean forcedService() {
		return forceService;
	}

	public String getServiceName() {
		return servicename;
	}

	public String getServiceModule() {
		return servicemodule;
	}
	
	public boolean equals(Object o) {
		if (o instanceof TBind) {
			TBind p = (TBind) o;
			return (
					getProvider().equals(p.getProvider())
					&& getRequirer().equals(p.getRequirer())
					&&getLabel().equals(p.getLabel())
					&&getServiceModule().equals(p.getServiceModule())
					&&getServiceName().equals(p.getServiceName())
					&&(forcedService() == p.forcedService())
					);
		}
		return false;
	}
	
	
    /**
     * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitBind(this);
    }
}
