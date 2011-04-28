/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GExport;
import tamagocc.generic.api.GExpression;
import tamagocc.generic.api.GModule;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class GIExport implements GExport {

	private String servicename;
	private GModule servicemodule;
	private GExpression provider;
	
	/**
	 * 
	 */
	public GIExport(String servicename,GModule servicemodule,GExpression provider) {
		super();
		this.servicemodule = servicemodule;
		this.servicename = servicename;
		this.provider = provider;
	}

	/**
	 * @see tamagocc.generic.api.GExport#getServiceName()
	 */
	public String getServiceName() {
		return servicename;
	}

	/**
	 * @see tamagocc.generic.api.GExport#getServiceModule()
	 */
	public GModule getServiceModule() {
		return servicemodule;
	}

	/**
	 * @see tamagocc.generic.api.GExport#getProviderLabel()
	 */
	public GExpression getProvider() {
		return provider;
	}
	
	public void setServiceName(String name) {
		this.servicename = name;
	}
	public void setServiceModule(GModule module) {
		this.servicemodule = module;
	}
	
	public void setProvider(GExpression expr) {
		this.provider = expr;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitExport(this);
	}
}
