/**
 * 
 */
package tamagocc.generic.impl;

import java.util.Collection;
import java.util.Iterator;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GBind;
import tamagocc.generic.api.GCompositeContainer;
import tamagocc.generic.api.GExport;
import tamagocc.generic.api.GImplements;
import tamagocc.generic.api.GInstantiateComponent;
import tamagocc.generic.api.GInvariant;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GModule;
import tamagocc.generic.api.GNamespace;
import tamagocc.generic.api.GPercolator;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GProvide;
import tamagocc.generic.api.GRequire;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.generic.api.GType;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class GICompositeContainer extends GIComponentContainer implements
		GCompositeContainer 
{

	private Collection<GBind> binds;
	private Collection<GInstantiateComponent> instantiates;
	private Collection<GExport> exportations;

	
	/**
	 * 
	 * @param name
	 * @param module
	 * @param percolators
	 * @param imports
	 * @param methods
	 * @param provide
	 * @param properties
	 * @param binds
	 * @param instantiates
	 * @param invariants
	 * @param behavior
	 * @param exportations
	 */
	
	public GICompositeContainer(String name, GModule module,
			Collection<GPercolator> percolators,
			Collection<GRequire> requires,
			Collection<GMethod> methods,
			Collection<GProvide> provide,
			Collection<GProperty> properties,
			Collection<GBind> binds,
			Collection<GInstantiateComponent> instantiates,
			Collection<GInvariant> invariants,
			GBehavior behavior,
			Collection<GExport> exportations,
			Collection<GImplements> impls,
			Collection<GNamespace> namespaces,
			Collection<GType> paramtypes)
		throws TamagoCCException
	{
		super(name, module, percolators, requires, methods, provide, properties,
				invariants, behavior,impls,namespaces,paramtypes);
		this.binds = binds;
		this.instantiates = instantiates;
		this.exportations = exportations;
	}

	/**
	 * @see tamagocc.generic.api.GAssemblyContainer#getBinds()
	 */
	public Iterator<GBind> getBinds() {
		return binds.iterator();
	}

	/**
	 * @see tamagocc.generic.api.GAssemblyContainer#getInstatiateComponent()
	 */
	public Iterator<GInstantiateComponent> getInstatiateComponent() {
		return instantiates.iterator();
	}

	public Iterator<GExport> getExportations() {
		return exportations.iterator();
	}
	
	public void setExportations(Collection<GExport> exports) {
		this.exportations = exports;
	}
	public void addExportation(GExport export) {
		this.exportations.add(export);
	}
	public void setInsatiatedComponent(Collection<GInstantiateComponent> inst) {
		instantiates = inst;
	}
	public void addInsatiatedComponent(GInstantiateComponent component) {
		instantiates.add(component);
	}
	
	public void setBinds(Collection<GBind> binds) {
		this.binds = binds;
	}
	public void addBind(GBind bind) {
		this.binds.add(bind);
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitComposite(this);
	}

	/**
	 * @see tamagocc.generic.api.GTamagoEntity#getCategoryEntity()
	 */
	public int getCategoryEntity() {
		return GTamagoEntity.TAMAGO_COMPOSITE;
	}
}
