package tamagocc.impl;

import tamagocc.api.TExtendService;
import tamagocc.api.TService;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

public abstract class TIExtendService implements TExtendService {

	private String name;
	private String module;

	private TService service;
	
	public TIExtendService(String name,String module,TService service) {
		super();
		this.name = name;
		this.module = module;
		this.service = service;
	}

	public String getServiceName() {
		return name;
	}

	public String getModuleService() {
		return module;
	}
	
	public TService getService() {
		return service;
	}

	
	public boolean equals(Object o) {
		if (o instanceof TExtendService) {
			TExtendService p = (TExtendService) o;
			return ( getServiceName().equals(p.getServiceName())
					&& getModuleService().equals(p.getModuleService())
					);
		}
		return false;
	}
	/**
     * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitExtendService(this);
    }
}
