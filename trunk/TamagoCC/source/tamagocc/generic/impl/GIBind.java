package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GBind;
import tamagocc.generic.api.GServiceInterface;

public class GIBind implements GBind {
	
	private String requirer;
	private String label;
	private String provider;
	private GServiceInterface service;
	
	
	public GIBind() {
		
	}
	
	public GIBind(GServiceInterface service,String provider,String requirer,String label) {
		this.provider = provider;
		this.requirer = requirer;
		this.label = label;
		this.service = service;
	}
	

	public String getProvider() {
		return provider;
	}
	
	public void setProvider(String provider) {
		this.provider = provider;
	}
	

	public String getRequirer() {
		return requirer;
	}

	public void setRequirer(String requirer) {
		this.requirer = requirer;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	public GServiceInterface getService() {
		return service;
	}
	public void setService(GServiceInterface service) {
		this.service = service;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitBind(this);
	}
}
