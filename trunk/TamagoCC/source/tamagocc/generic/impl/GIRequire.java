/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GModule;
import tamagocc.generic.api.GRequire;
import tamagocc.generic.api.GType;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class GIRequire implements GRequire {

	private String servicename;
	private GModule servicemodule;
	private String label;
	private GType[] generics;
	
	/**
	 * 
	 */
	public GIRequire(String servicename,GModule servicemodule,String label, GType[] types) {
		super();
		this.servicename = servicename;
		this.servicemodule = servicemodule;
		this.label = label;
		generics = types;
	}

	/**
	 * @see tamagocc.generic.api.GRequire#getServiceName()
	 */
	public String getServiceName() {
		return servicename;
	}

	/**
	 * @see tamagocc.generic.api.GRequire#getServiceModule()
	 */
	public GModule getServiceModule() {
		return servicemodule;
	}

	/**
	 * @see tamagocc.generic.api.GRequire#getLabel()
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitRequire(this);
	}

	/**
	 * @see tamagocc.generic.api.GRequire#getParametrizedType()
	 */
	public GType[] getParametrizedType() {
		return generics;
	}

	/**
	 * @see tamagocc.generic.api.GRequire#isParametric()
	 */
	public boolean isParametric() {
		return generics != null && generics.length > 0;
	}

	/**
	 * @see tamagocc.generic.api.GRequire#getNameAsType()
	 */
	public GType getNameAsType() {
		StringBuilder sb = new StringBuilder();
		
		if((servicemodule != null) && (servicemodule.getFullModule().length() > 0)) {
			sb.append(servicemodule.getFullModule());
			sb.append(".");
		}
		
		sb.append(servicename);
		
		if(isParametric()) {
			sb.append("<");
			GType[] gens = getParametrizedType();
			for (GType type : gens) {
				sb.append(type.getType());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(">");
		}
		
		return GIType.generateType(sb.toString());
	}

}
