 package tamagocc.generic.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GComponentContainer;
import tamagocc.generic.api.GImplements;
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

public class GIComponentContainer extends GITamago implements GComponentContainer {

	private Collection<GPercolator> percolators;
	private Collection<GModule> imports;
	private Collection<GProvide> provides;
	private Collection<GRequire> requires;
	
	
	public GIComponentContainer(String name,GModule module,
			Collection<GPercolator> percolators,
			Collection<GRequire> require,
			Collection<GMethod> methods,
			Collection<GProvide> provides,
			Collection<GProperty> properties,
			Collection<GInvariant> invariants,
			GBehavior behavior,
			Collection<GImplements> impls,
			Collection<GNamespace> namespaces,
			Collection<GType> paramtypes)
		throws TamagoCCException
	{
		super(name,module,invariants,methods,properties,behavior,impls,namespaces,paramtypes);
		this.requires = require;
		this.provides = provides;
		
		this.imports = new ArrayList<GModule>();
		this.percolators = new ArrayList<GPercolator>(percolators);
		
		// transmission (heritage) des methodes et des proprietes
		for (GProvide provide : provides) {
			
			// heritage des methods et proprietes
			for (GProperty property : provide.getService().getProperties()) {
				registerProperty(property);
			}
			
			for (GMethod method : provide.getService().getAllMethods()) {
				registerMethod(method);
			}
		}
	}

	public Iterator<GPercolator> getAllowedPercolators() {
		return percolators.iterator();
	}

	public Iterator<GModule> getImports() {
		return imports.iterator();
	}

	public Iterator<GProvide> getAllProvide() {
		return provides.iterator();
	}
	public Iterator<GRequire> getAllRequire() {
		return requires.iterator();
	}
	
	
	public void addAllowedPercolator(GPercolator percolator) {
		if(!percolators.contains(percolator))
			this.percolators.add(percolator);
	}
	
	
	public void addMethod(GMethod method) throws TamagoCCException {
		registerMethod(method);
	}
	
	public void setImports(Collection<GModule> imports) {
		this.imports = imports;
	}
	
	public void addImport(GModule importation) {
		this.imports.add(importation);
	}
	
	public void setProvide(Collection<GProvide> provide) {
		this.provides = provide;
	}
	
	public void addProvide(GProvide provide) throws TamagoCCException {
		if(!this.provides.contains(provide))
			this.provides.add(provide);
	}
	
	public void setRequire(Collection<GRequire> require) {
		this.requires = require;
	}
	
	public void addRequire(GRequire require) {
		this.requires.add(require);
	}
	
	public void addProperty(GProperty property) throws TamagoCCException {
		registerProperty(property);
	}
	
	public void setInvariants(Collection<GInvariant> invariants) {
		this.invariants = new ArrayList<GInvariant>(invariants);
	}
	
	public void addInvariant(GInvariant condition) {
		this.invariants.add(condition);
	}
	
	public void setBehavior(GBehavior behavior) {
		this.behavior = behavior;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setModule(GModule module) {
		this.module = module;
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitComponent(this);
	}

	public int getCategoryEntity() {
		return GTamagoEntity.TAMAGO_COMPONENT;
	}
}
