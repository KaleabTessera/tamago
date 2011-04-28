/**
 * 
 */
package tamagocc.generic.impl;

import java.util.Collection;
import java.util.Iterator;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GAssemblyContainer;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GBind;
import tamagocc.generic.api.GInstantiateComponent;
import tamagocc.generic.api.GInvariant;
import tamagocc.generic.api.GModule;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.generic.api.GType;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class GIAssemblyContainer implements GAssemblyContainer {

	private Collection<GBind> binds;
	private Collection<GInstantiateComponent> instantiates;
	private String name;
	private GModule module;
	private Collection<GInvariant> invariants;
	private GBehavior behavior;
	private Collection<GProperty> properties;
	private GType[] generics;
	
	/**
	 * 
	 */
	public GIAssemblyContainer(String name,
			GModule module,
			Collection<GBind> binds,
			Collection<GInstantiateComponent> instantiates,
			Collection<GInvariant> invariants,
			GBehavior behavior,
			Collection<GProperty> properties,
			Collection<GType> paramtypes)
	{
		super();
		this.name = name;
		this.binds = binds;
		this.instantiates = instantiates;
		this.module = module;
		this.invariants = invariants;
		this.behavior = behavior;
		this.properties = properties;
		
		this.generics = new GType[paramtypes.size()];
		System.arraycopy(paramtypes.toArray(), 0, generics, 0, paramtypes.size());
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

	/**
	 * @see tamagocc.generic.api.GTamagoEntity#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see tamagocc.generic.api.GTamagoEntity#getModule()
	 */
	public GModule getModule() {
		return module;
	}

	/**
	 * @see tamagocc.generic.api.GTamagoEntity#getInvariants()
	 */
	public Iterator<GInvariant> getInvariants() {
		return invariants.iterator();
	}

	/**
	 * @see tamagocc.generic.api.GTamagoEntity#getBehavior()
	 */
	public GBehavior getBehavior() {
		return behavior;
	}
	
	public void setBehavior(GBehavior behavior) {
		this.behavior = behavior;
	}	
	
	public void setInstantiatedComponent(Collection<GInstantiateComponent> inst) {
		instantiates = inst;
	}
	public void addInstantiatedComponent(GInstantiateComponent component) {
		instantiates.add(component);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setModule(GModule module) {
		this.module = module;
	}
	
	public void setInvariants(Collection<GInvariant> invs) {
		invariants = invs;
	}
	public void addInvariant(GInvariant condition) {
		this.invariants.add(condition);
	}
	
	
	public void setBinds(Collection<GBind> binds) {
		this.binds = binds;
	}
	
	public void addBind(GBind bind) {
		this.binds.add(bind);
	}
	
	public Iterator<GProperty> getProperties() {
		return properties.iterator();
	}
	
	public void setProperties(Collection<GProperty> properties) {
		this.properties = properties;
	}
	
	public void addProperty(GProperty property) {
		this.properties.add(property);
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitAssembly(this);
	}

	public int getCategoryEntity() {
		return GTamagoEntity.TAMAGO_ASSEMBLY;
	}
	
	/**
	 * @see tamagocc.generic.api.GTamagoEntity#getNameAsType()
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
			GType[] gens = getParametrizedTypes();
			for (GType type : gens) {
				sb.append(type.getType());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(">");
		}
		
		return GIType.generateType(sb.toString());
	}

	/**
	 * @see tamagocc.generic.api.GTamagoEntity#getParametrizedType(int)
	 */
	public GType getParametrizedType(int i) {
		return generics[i];
	}

	/**
	 * @see tamagocc.generic.api.GTamagoEntity#getParametrizedTypes()
	 */
	public GType[] getParametrizedTypes() {
		return generics;
	}

	/**
	 * @see tamagocc.generic.api.GTamagoEntity#isParametric()
	 */
	public boolean isParametric() {
		return generics.length > 0;
	}
	
	public void addParametricType(GType type) {
		GType[] types = new GType[generics.length+1];
		System.arraycopy(generics, 0, types, 0, generics.length);
		types[generics.length] = type;
		generics = types;
	}
}
