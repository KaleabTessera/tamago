/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGPool;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GExtendService;
import tamagocc.generic.api.GModule;
import tamagocc.generic.api.GServiceInterface;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class GIIncludeService implements GExtendService {

	private String name;
	private GModule module;
	private GServiceInterface service;

	public GIIncludeService(String name,GModule module) throws TamagoCCException {
		super();
		this.name = name;
		this.module = module;
		service = (GServiceInterface)TamagoCCGPool.getDefaultTamagoCCGPool().getGTamagoEntity(name,module);
	}

	/**
	 * @see tamagocc.generic.api.GExtendService#getServiceName()
	 */
	public String getServiceName() {
		return name;
	}

	/**
	 * @see tamagocc.generic.api.GExtendService#getModule()
	 */
	public GModule getModule() {
		return module;
	}

	/**
	 * @see tamagocc.generic.api.GExtendService#isRefined()
	 */
	public boolean isRefined() {
		return false;
	}

	/**
	 * @see tamagocc.generic.api.GExtendService#isInclude()
	 */
	public boolean isInclude() {
		return true;
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
