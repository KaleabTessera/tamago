/**
 * 
 */
package tamagocc.generic.impl;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GInstantiateComponent;
import tamagocc.generic.api.GModule;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class GIInstantiateComponent implements GInstantiateComponent {

	private String componentname;
	private GModule componentmodule;
	private String label;
	private String percolator;
	
	public GIInstantiateComponent(String componentname,
					GModule module,String label,String percolator) {
		super();
		this.componentmodule = module;
		this.componentname = componentname;
		this.label = label;
		this.percolator = percolator;
	}
	
	/**
	 * @see tamagocc.generic.api.GInstantiateComponent#getComponentName()
	 */
	public String getComponentName() {
		return componentname;		
	}

	/**
	 * @see tamagocc.generic.api.GInstantiateComponent#getComponentModule()
	 */
	public GModule getComponentModule() {
		return componentmodule;
	}

	/**
	 * @see tamagocc.generic.api.GInstantiateComponent#getComponentLabel()
	 */
	public String getComponentLabel() {
		return label;
	}
	
	public void setComponentName(String componentname) {
		this.componentname = componentname;
	}
	public void setComponentModule(GModule componentmodule) {
		this.componentmodule = componentmodule;
	}
	public void setComponentLabel(String label) {
		this.label = label;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitDefinition(this);
	}

	public String getPercolatorName() {
		return percolator;
	}
}
