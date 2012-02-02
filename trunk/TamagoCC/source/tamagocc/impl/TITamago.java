package tamagocc.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import tamagocc.api.TBehavior;
import tamagocc.api.TImplements;
import tamagocc.api.TInvariant;
import tamagocc.api.TMethod;
import tamagocc.api.TNamespace;
import tamagocc.api.TParameter;
import tamagocc.api.TProperty;
import tamagocc.api.TTamago;
import tamagocc.api.TType;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.NilIterator;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author Hakim BELHAOUARI
 * 15 juil. 2005 TITamago.java
 */
/**
 */
public abstract class TITamago implements TTamago {

    protected String module; 
    protected String name;
    protected Collection<TMethod> methods;
    protected TBehavior behavior;
    protected Collection<TInvariant> invariants;
    protected Collection<TProperty> properties;
    protected Collection<TImplements> impls;
    protected Collection<TNamespace> namespaces;
    protected TType[] generics;
    /**
     * 
     */
    public TITamago(String n,
    		String module,
    		Collection<TMethod> methods,
    		Collection<TProperty> properties,
    		Collection<TInvariant> invariants,
    		TBehavior behavior,
    		Collection<TImplements> impls,
    		Collection<TNamespace> namespaces,
    		Collection<TType> paramtypes) {
        super();
        name = n;
        this.methods = methods;
        this.module = module;
        this.invariants = invariants;
        this.behavior = behavior;
        this.properties = properties;
        this.impls = impls;
        this.namespaces = namespaces;
        generics = new TType[paramtypes.size()];
        System.arraycopy(paramtypes.toArray(), 0, generics, 0, generics.length);
    }
    
    public TITamago(String n, String module2) {
    	this.name = n;
    	this.methods = new ArrayList<TMethod>();
        this.module = module2;
        this.invariants = new ArrayList<TInvariant>();
        this.behavior = null;
        this.properties = new ArrayList<TProperty>();
        this.impls = new ArrayList<TImplements>();
        this.namespaces = new ArrayList<TNamespace>();
        generics = new TType[0];
        //System.arraycopy(paramtypes.toArray(), 0, generics, 0, generics.length);
	}

	public TBehavior getBehavior() {
    	return behavior;
    }
    
    public Iterator<TInvariant> getInvariants() {
    	return invariants.iterator();
    }
    
    public Iterator<TProperty> getProperties() {
    	return properties.iterator();
    }

    /**
     * @see tamagocc.api.TTamago#getName()
     */
    public String getName() {
        return (name);
    }
    
    public String getModule() {
        if(module == null)
            return "";
        else
            return module;
    }

    /**
     * @see tamagocc.api.TTamago#getMethods()
     */
    public Iterator<TMethod> getMethods() {
        return methods.iterator();
    }

    /**
     * @see tamagocc.api.TTamago#getMethod(java.lang.String)
     */
    public TMethod getMethod(String name) {
        //      TODO : peut etre l'optimiser un peu pas urgent
        Iterator<TMethod> ite = getMethods();
        while(ite.hasNext()) {
            TMethod n = ite.next();
            if(name.equals(n.getName()))
                return n;
        }
        throw new NoSuchElementException("Unfound a method with name : "+name);
    }
    
    public TMethod getDeclaredMethodID(String id) {
        //      TODO : peut etre l'optimiser un peu pas urgent
        Iterator<TMethod> ite = getMethods();
        while(ite.hasNext()) {
            TMethod n = ite.next();
            if(id.equals(n.getID()))
                return n;
        }
        throw new NoSuchElementException("Unfound a declared method with id : "+id);
    }

    /**
     * @see tamagocc.api.TTamago#getMethods(java.lang.String)
     */
    public TMethod[] getMethods(String name) {
       ArrayList<TMethod> al = new ArrayList<TMethod>();
       Iterator<TMethod> ite = getMethods();
       while(ite.hasNext()) {
           TMethod n = ite.next();
           if(name.equals(n.getName()))
               al.add(n);
       }
       return (TMethod[])al.toArray();
    }
    
    public TMethod getSimilarMethods(TMethod m) {
        ArrayList<TMethod> al = new ArrayList<TMethod>();
        Iterator<TMethod> ite = getMethods();
        while(ite.hasNext()) {
            TMethod n = ite.next();
            if((m.getName().equals(n.getName()))&&(m.getParameterNumber()==n.getParameterNumber()))
                al.add(n);
        }
        if(al.size()==0) {
            throw new NoSuchElementException("Unfound a method with name : "+m.getName());
        }
        else {
            return selectSameParameter(m,al.iterator());
        }            
    }

    protected TMethod selectSameParameter(TMethod m,Iterator<TMethod> al){
        // TODO : a l'heure actuelle, la methode impose que les methodes ait exactement le meme type.
    	while(al.hasNext()) {
            TMethod n =al.next();
            if(sameType(m.getParameters(),n.getParameters())) {
                return n;
            }
        }
        throw new NoSuchElementException("Unfound a method with name : "+m.getName());
    }
    protected boolean sameType(Iterator<TParameter> a1,Iterator<TParameter> a2) {
        while(a1.hasNext()) {
            if(!a2.hasNext())
                return false;
            TParameter aa1 = (TParameter)a1.next();
            TParameter aa2 = (TParameter)a2.next();
            if(!aa1.getType().getType().equals(aa2.getType().getType()))
                return false;
        }
        if(a2.hasNext())
            return false;
        else
            return true;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof TTamago) {
			TTamago p = (TTamago) o;
			return ( getName().equals(p.getName())
					&& getModule().equals(p.getModule())
					&& getBehavior().equals(p.getBehavior())
					&& NilIterator.areEqual(p.getInvariants(),getInvariants())
					&& NilIterator.areEqual(getMethods(),p.getMethods())
					&& NilIterator.areEqual(getProperties(),p.getProperties())
					);
		}
    	return false;
    }

    /**
     * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitTamago(this);
    }
    
    
    public void addProperty(TProperty prop) {
    	if(!properties.contains(prop))
    		properties.add(prop);
    }
    
    public void addMethod(TMethod method) {
    	methods.add(method);
    }
    
    public void addInvariant(TInvariant inv) {
    	invariants.add(inv);
    }
    
    public void setBehavior(TBehavior b) {
    	behavior = b;
    }
    
    public Iterable<TImplements> getImplements() {
    	return impls;
    }
    
    public Iterable<TNamespace> getUsedNamespace() {
    	return namespaces;
    }
    
	/**
	 * @see tamagocc.api.TTamagoEntity#getParametricType(int)
	 */
	public TType getParametricType(int i) {
		return generics[i];
	}

	
	
	/**
	 * @see tamagocc.api.TTamagoEntity#getParametrizedTypes()
	 */
	public TType[] getParametrizedTypes() {
		return generics;
	}

	/**
	 * @see tamagocc.api.TTamagoEntity#isParametric()
	 */
	public boolean isParametric() {
		return (generics.length > 0);
	}
	/**
	 * @see tamagocc.api.TTamagoEntity#getNameAsType()
	 */
	public TType getNameAsType() {
		if(module.length() > 0)
			return TIType.generateType(module+"."+name);
		else
			return TIType.generateType(name);
	}
	
	
	public static String extractNameWithoutParamInfo(String name) {
		int idx = name.indexOf("<");
    	if(idx == -1)
    		return name;
    	else {
    		return name.substring(0, idx);
    	}
	}
	
	public static ArrayList<TType> extractParametrizedTypesFromName(String name) {
		int idx = name.indexOf("<");
		ArrayList<TType> gens = new ArrayList<TType>();
		
    	if(idx == -1)
    		return gens;
    	else {
    		String gen = name.substring(idx+1,name.lastIndexOf(">"));
    		for (String string : gen.split(",")) {
    			gens.add(TIType.generateType(string));
    		}
    		return gens;
    	}
	}
	
	public static TType[] extractParametrizedTypesFromNameInArray(String name) {
		ArrayList<TType> types = extractParametrizedTypesFromName(name);
		TType[] res = new TIType[types.size()];
		System.arraycopy(types.toArray(), 0, res, 0, res.length);
		return res;
	}

	public void addParamType(TType type) {
		TType[] res = new TIType[generics.length + 1];
		System.arraycopy(generics,0,res,0,generics.length);
		res[generics.length] = type;
		generics = res;
	}
	
	public void setName(String string) {
		this.name = string;
	}
}
