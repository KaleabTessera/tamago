package tamagocc.generic.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import tamagocc.exception.TamagoCCException;
import tamagocc.generic.TamagoCCGVisitor;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GExtendService;
import tamagocc.generic.api.GImplements;
import tamagocc.generic.api.GInvariant;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GModule;
import tamagocc.generic.api.GNamespace;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GServiceInterface;
import tamagocc.generic.api.GTamagoEntity;
import tamagocc.generic.api.GType;

public class GIServiceInterface extends GITamago implements GServiceInterface {
		
	
	private Collection<GExtendService> exts;
	private Collection<GExtendService> refines;
	private Collection<GExtendService> includes;
	
	public GIServiceInterface(String name,
			GModule module,
			Collection<GExtendService> exts,
			Collection<GMethod> declaredmethod,
			GBehavior behavior,
			Collection<GInvariant> invariants,
			Collection<GProperty> properties,
			Collection<GImplements> impls,
			Collection<GNamespace> namespaces,
			Collection<GType> paramtypes)
		throws TamagoCCException
	{
		super(name,module,invariants,declaredmethod,properties,behavior,impls,namespaces,paramtypes);
		this.exts = new ArrayList<GExtendService>(exts);
		
		refines = new ArrayList<GExtendService>();
		includes = new ArrayList<GExtendService>();
		Iterator<GExtendService> extensions = exts.iterator();
		while(extensions.hasNext()) {
			GExtendService serv = extensions.next();
			if(serv.isInclude())
				includes.add(serv);
			if(serv.isRefined())
				refines.add(serv);
			
			// heritage des methods et proprietes
			for (GProperty property : serv.getService().getProperties()) {
				registerProperty(property);
			}
			
			for (GMethod method : serv.getService().getAllMethods()) {
				registerMethod(method);
			}
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setModule(GModule module) {
		this.module = module;
	}
	public Iterator<GExtendService> getExtends() {
		return exts.iterator();
	}
	
	public void addExtend(GExtendService serv) throws TamagoCCException {
		this.exts.add(serv);
		if(serv.isInclude())
			includes.add(serv);
		if(serv.isRefined())
			refines.add(serv);
		// heritage des methods et proprietes
		for (GProperty property : serv.getService().getProperties()) {
			registerProperty(property);
		}
		
		for (GMethod method : serv.getService().getAllMethods()) {
			registerMethod(method);
		}
	}
	
	public Iterator<GExtendService> getIncludes() {
		return includes.iterator();
	}
	
	public Iterator<GExtendService> getRefines() {
		return refines.iterator();
	}

	public void addDeclaredMethod(GMethod method) throws TamagoCCException {
		this.registerMethod(method);
	}
	
	public void setBehavior(GBehavior behavior) {
		this.behavior = behavior;
	}
	
	
	public void setInvariants(Collection<GInvariant> invariants) {
		this.invariants = new ArrayList<GInvariant>(invariants);
	}
	
	public void addInvariant(GInvariant invariant) {
		this.invariants.add(invariant);
	}
	
	public void addProperty(GProperty property) throws TamagoCCException{
		registerProperty(property);
	}
	
	/**
	 * @see tamagocc.generic.api.GObject#visit(tamagocc.generic.TamagoCCGVisitor)
	 */
	public Object visit(TamagoCCGVisitor visitor) throws TamagoCCException {
		return visitor.visitService(this);
	}

	public int getCategoryEntity() {
		return GTamagoEntity.TAMAGO_SERVICE;
	}

}
