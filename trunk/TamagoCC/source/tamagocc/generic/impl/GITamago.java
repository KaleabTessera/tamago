package tamagocc.generic.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import tamagocc.exception.TamagoCCException;
import tamagocc.exception.TamagoCCNotFoundMethod;
import tamagocc.exception.TamagoCCTypeException;
import tamagocc.generic.api.GAccess;
import tamagocc.generic.api.GBehavior;
import tamagocc.generic.api.GImplements;
import tamagocc.generic.api.GInvariant;
import tamagocc.generic.api.GMethod;
import tamagocc.generic.api.GModule;
import tamagocc.generic.api.GNamespace;
import tamagocc.generic.api.GProperty;
import tamagocc.generic.api.GTamago;
import tamagocc.generic.api.GType;
import tamagocc.logger.TamagoCCLogger;
import tamagocc.util.NilIterator;

/**
 * @author Hakim Belhaouari
 *
 */
public abstract class GITamago implements GTamago {

	protected String name;
	protected GModule module;
	protected GBehavior behavior;
	//protected ArrayList<GProperty> properties;
	protected Hashtable<String, GMethod> methods;
	protected Hashtable<GMethod, Collection<String>> methodsid;
	protected ArrayList<GInvariant> invariants;
	protected ArrayList<GMethod> allmethods;
	protected ArrayList<GMethod> methodwithoutids;
	
	protected Hashtable<String, GProperty> properties;
	protected ArrayList<GImplements> impls; 
	protected ArrayList<GNamespace> namespaces;
	private GType[] generics; 
	
	/**
	 * 
	 */
	public GITamago(String name, GModule module, 
			Collection<GInvariant> invs, 
			Collection<? extends GMethod> meths, 
			Collection<GProperty> props, 
			GBehavior beh,
			Collection<GImplements> impls,
			Collection<GNamespace> namespaces,
			Collection<GType> paramtypes)
		throws TamagoCCException
	{
		this.name =name;
		this.module = module;
		
		behavior = beh;
		properties = new Hashtable<String, GProperty>();
		invariants = new ArrayList<GInvariant>(invs);
		
		methods = new Hashtable<String, GMethod>();
		methodsid = new Hashtable<GMethod, Collection<String>>();
		allmethods = new ArrayList<GMethod>();
		methodwithoutids = new ArrayList<GMethod>();
		
		for (GMethod method : meths) {
			registerMethod(method);
		}
		
		for(GProperty prop : props) {
			registerProperty(prop);
		}
		this.impls = new ArrayList<GImplements>(impls);
		this.namespaces = new ArrayList<GNamespace>(namespaces);
		
		this.generics = new GType[paramtypes.size()];
		System.arraycopy(paramtypes.toArray(), 0, generics, 0, paramtypes.size());
	}

	public void registerProperty(GProperty prop) throws TamagoCCException {
		if(properties.containsKey(prop.getName())) {
			GProperty p = properties.get(prop.getName());
			if(p.getType().equals(prop.getType())) {
				// on fait une fusion sur les access en lecture/ecriture
				GAccess newaccess = new GIAccess(prop.getAccess().canRead() || p.getAccess().canRead(),
						prop.getAccess().canWrite() || p.getAccess().canWrite());
				GProperty newprop = new GIProperty(prop.getName(),prop.getType(),newaccess);
				properties.put(prop.getName(), newprop);
			}
			else {
				throw new TamagoCCTypeException("Cannot check the type of the property "+prop.getName());
			}
		}
		else
			properties.put(prop.getName(), prop);
	}

	/**
	 * @see tamagocc.generic.api.GTamago#getAllMethodID()
	 */
	public Iterable<String> getAllMethodID() {
		return methods.keySet();
	}

	/**
	 * @see tamagocc.generic.api.GTamago#getBehavior()
	 */
	public GBehavior getBehavior() {
		return behavior;
	}

	/**
	 * @see tamagocc.generic.api.GTamago#getInvariants()
	 */
	public Iterable<GInvariant> getInvariants() {
		return invariants;
	}

	/**
	 * @see tamagocc.generic.api.GTamago#getUniqueMethods()
	 */
	public Iterable<GMethod> getUniqueMethods() {
		return methodsid.keySet();
	}

	/**
	 * @see tamagocc.generic.api.GTamago#getMethod(java.lang.String)
	 */
	public GMethod getMethod(String id) {
		return methods.get(id);
	}

	/**
	 * @see tamagocc.generic.api.GTamago#getMethodID()
	 */
	public Hashtable<GMethod, ? extends Iterable<String>> getMethodID() {
		return methodsid;
	}

	/**
	 * @see tamagocc.generic.api.GTamago#getMethods()
	 */
	public Hashtable<String, GMethod> getMethods() {
		return methods;
	}

	/**
	 * @see tamagocc.generic.api.GTamago#getProperties()
	 */
	public Iterable<GProperty> getProperties() {
		return properties.values();
	}
	
	/**
	 * @see tamagocc.generic.api.GTamago#searchMethod(tamagocc.generic.api.GMethod)
	 */
	public GMethod searchMethod(GMethod method) throws TamagoCCNotFoundMethod {
		
		for (GMethod m : getUniqueMethods()) {
			if(m.getName().equals(method.getName())
				&&(m.getParameterNumber() == method.getParameterNumber())
				&&NilIterator.areEqual(m.getParameters(), method.getParameters()) )
			{
				return m;
			}
		}
		
		throw new TamagoCCNotFoundMethod("searchMethod fail to find a matching method <"+method.getName()+"/"+method.getParameterNumber()+"> in the entity <"+getName()+">");
	}
	
	/**
	 * @see tamagocc.generic.api.GTamagoEntity#getModule()
	 */
	public GModule getModule() {
		return module;
	}

	/**
	 * @see tamagocc.generic.api.GTamagoEntity#getName()
	 */
	public String getName() {
		return name; 
	}

	public Iterable<GMethod> getAllMethods() {
		return allmethods;
	}
	
	public Iterable<GMethod> getAllMethods(GMethod method) {
		ArrayList<GMethod> methods = new ArrayList<GMethod>();
		for (GMethod m : allmethods) {
			if(method.sameSignature(m)) {
				methods.add(m);
			}
		}
		return methods;
	}
	
	public void registerMethod(GMethod method) throws TamagoCCException {
		TamagoCCLogger.println(3, "Register a method");
		
		boolean mustRecord = true;
		StringBuilder sb = new StringBuilder("The method ");
		
		for (GMethod m : allmethods) {
			if(m.sameSignature(method) && (m.getID().equals(method.getID())))
				mustRecord = false;
		}
		
		if(mustRecord)
			allmethods.add(method);
		
		
		// tout d'abord on test si une methode equivalente a deja ete definit
		try {
			GMethod main = searchMethod(method);
			// la methode existe deja
			sb.append(method.getName()); sb.append("/");sb.append(method.getParameterNumber());
			sb.append(" already defined in the entity.\n");
			
			if((method.getID() == null) || ("".equals(method.getID()))) {
				sb.append("*Warning* : no ID for the method ");
				sb.append(method.getName()); sb.append("/");
				sb.append(method.getParameterNumber());
				sb.append("\n");
				methodwithoutids.add(method);
				
				((GIMethod)method).setID(genIDName(method));
				registerMethod(method);				
			}
			else if(methods.keySet().contains(method.getID())) {
				sb.append("*Warning* : The ID already exist.\n");
				if(methods.get(method.getID()) != main) {
					if(mustRecord)
						allmethods.remove(method);
					throw new TamagoCCException("Duplicate ID for different method in the contract");
				}
			}
			else {
				sb.append("The ID does not exist.\n");
				methods.put(method.getID(), main);
				Collection<String> ids = methodsid.get(main);
				ids.add(method.getID());
			}
		}
		catch(TamagoCCNotFoundMethod notfound) {
			if((method.getID() == null) || ("".equals(method.getID()))) {
				sb.append("*Warning* : no ID for the method ");
				sb.append(method.getName()); sb.append("/");sb.append(method.getParameterNumber());
				sb.append("\n");
				methodwithoutids.add(method);
				((GIMethod)method).setID(genIDName(method));
				registerMethod(method);
			}
			else {
				sb.append(method.getName()); sb.append("/");sb.append(method.getParameterNumber());
				sb.append(" is not defined in the entity.");
				methods.put(method.getID(), method);
				ArrayList<String> i = new ArrayList<String>();
				i.add(method.getID());
				methodsid.put(method,i);
			}
		}
		finally {			
			TamagoCCLogger.println(3, sb.toString());			
		}
		
		TamagoCCLogger.println(3, "End of registration for the method");
	}	
	
	
	private String genIDName(GMethod method) {
		int i = 0;
		StringBuffer sb;
		do {
			i++;
			sb = new StringBuffer();
			sb.append("__tamagocc_");
			sb.append(method.getName());
			sb.append(i);
		} while(methods.containsKey("__tamagocc_"+method.getName()+i));
		return sb.toString();
	}

	public Iterable<GMethod> getMethodsWithoutIDs() {
		return methodwithoutids;
	}
	
	public void addInvariant(GInvariant inv) {
		this.invariants.add(inv);
	}
	
	
	public Iterable<GImplements> getImplements() {
		return impls;
	}
	
	public void addImplements(GImplements impl) {
		if(!impls.contains(impl))
			impls.add(impl);
	}
	
	public int countAllMethods() {
		return allmethods.size();
	}
	
	public Collection<GNamespace> getNamespaces() {
		return namespaces;
	}
	
	public void addNamespace(GNamespace namespace) {
		if(!namespaces.contains(namespace))
			namespaces.add(namespace);
	}

	public GProperty searchProperty(String name) {
		return properties.get(name);
	}
	
	public Iterable<GMethod> getMetodsByName(String name) {
		ArrayList<GMethod> buf = new ArrayList<GMethod>();
		for (GMethod method : allmethods) {
			if(method.getName().equals(name))
				buf.add(method);
		}
		return buf;
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
