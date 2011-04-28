/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GModule;
import tamagocc.generic.api.GProvide;
import tamagocc.generic.api.GServiceInterface;
import tamagocc.generic.api.GType;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class GIProvide implements GProvide {

	private String name;
	private GModule module;
	private boolean isDeclaredService;
	private GServiceInterface service;
	private GType[] generics;
	
	/**
	 * 
	 */
	public GIProvide(GServiceInterface service,String name,GModule module,boolean isDeclaredService,GType[] type) {
		super();
		this.name = name;
		this.module = module;
		this.isDeclaredService = isDeclaredService;
		this.service = service;
		generics = type;
	}

	/**
	 * @see tamagocc.generic.api.GProvide#getModule()
	 */
	public GModule getModule() {
		return module;
	}

	/**
	 * @see tamagocc.generic.api.GProvide#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see tamagocc.generic.api.GProvide#isDeclaredService()
	 */
	public boolean isDeclaredService() {
		return isDeclaredService;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitProvide(this);
	}

	/**
	 * 
	 * @see tamagocc.generic.api.GProvide#getService()
	 */
	public GServiceInterface getService() {
		return service;
	}

	/**
	 * @see tamagocc.generic.api.GProvide#getParametrizedType()
	 */
	public GType[] getParametrizedType() {
		return generics;
	}

	/**
	 * @see tamagocc.generic.api.GProvide#isParametric()
	 */
	public boolean isParametric() {
		return generics != null && generics.length > 0;
	}

	/**
	 * @see tamagocc.generic.api.GProvide#getNameAsType()
	 */
	public GType getNameAsType() {
		StringBuilder sb = new StringBuilder();
		
		if((module != null) && (module.getFullModule().length() > 0)) {
			sb.append(module.getFullModule());
			sb.append(".");
		}
		
		sb.append(name);
		
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
