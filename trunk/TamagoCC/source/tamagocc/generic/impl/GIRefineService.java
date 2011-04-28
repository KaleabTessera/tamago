package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGPool;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GExtendService;
import tamagocc.generic.api.GModule;
import tamagocc.generic.api.GServiceInterface;

public class GIRefineService implements GExtendService {

	private String name;
	private GModule module;
	private GServiceInterface service;
	
	public GIRefineService(String name,GModule module) throws TamagoCCException {
		super();
		this.name= name;
		this.module = module;
		
		service = (GServiceInterface)TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(name,module);
	}

	public String getServiceName() {
		return name;
	}

	public GModule getModule() {
		return module;
	}

	public boolean isRefined() {
		return true;
	}

	public boolean isInclude() {
		return false;
	}

	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitExtendService(this);
	}

	public GServiceInterface getService() {
		return service;
	}
}
