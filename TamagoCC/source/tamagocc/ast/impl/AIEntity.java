/**
 * 
 */
package tamagocc.ast.impl;

import java.util.ArrayList;
import java.util.Iterator;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AComment;
import tamagocc.ast.api.AEntity;
import tamagocc.ast.api.AImplements;
import tamagocc.ast.api.AInherit;
import tamagocc.ast.api.AMemberVariable;
import tamagocc.ast.api.AMethod;
import tamagocc.ast.api.AModule;
import tamagocc.ast.api.ANamespace;
import tamagocc.ast.api.AProperty;
import tamagocc.ast.api.AType;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.NilIterator;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public class AIEntity implements AEntity {

	private ArrayList<AMemberVariable> variablesmembers;
	private ArrayList<AMethod> methods;
	private String name;
	private AModule module;
	private AComment comment;
	private ArrayList<AImplements> allimplements;
	private ArrayList<AProperty> properties;
	private AInherit inherit;
	private int category;
	
	private ArrayList<ANamespace> usednamespaces;
	private AType[] generics;
	
	/**
	 * 
	 */
	public AIEntity(String name,AModule module,AType[] types) {
		super();
		this.variablesmembers = new ArrayList<AMemberVariable>();
		this.methods = new ArrayList<AMethod>();
		this.name = name;
		this.module = module;
		allimplements = new ArrayList<AImplements>();
		inherit = new AINoInherit();
		category = INTERFACE;
		usednamespaces = new ArrayList<ANamespace>();
		properties = new ArrayList<AProperty>();
		comment = new AINoComment();
		
		generics = new AType[types.length];
		System.arraycopy(types, 0, generics, 0, types.length);
	}
	
	public AIEntity(String name,AModule module,AType[] types,int category) {
		this(name,module,types);
		this.category = category;
	}

	/**
	 * @see tamagocc.ast.api.AEntity#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see tamagocc.ast.api.AEntity#getModule()
	 */
	public AModule getModule() {
		return module;
	}

	/**
	 * @see tamagocc.ast.api.AEntity#getVariablesMembers()
	 */
	public Iterator<AMemberVariable> getVariablesMembers() {
		return variablesmembers.iterator();
	}

	/**
	 * @see tamagocc.ast.api.AEntity#getMethods()
	 */
	public Iterator<AMethod> getMethods() {
		return methods.iterator();
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitEntity(this);
	}

	public AComment getComment() {
		return comment;
	}

	public void setComment(AComment comment) {
		this.comment = comment;
	}
	
	public void addVariablesMembers(AMemberVariable varmem) {
		if(!variablesmembers.contains(varmem))
			this.variablesmembers.add(varmem);
	}
	
	public void addMethod(AMethod method) {
		if(!methods.contains(method))
			this.methods.add(method);
	}

	public Iterator<AImplements> getImplements() {
		return allimplements.iterator();
	}

	public AInherit getInherit() {
		return inherit;
	}
	
	public void addImplement(AImplements impl) {
		if(!allimplements.contains(impl)) {
			allimplements.add(impl);
		}
	}
	
	public void setInherit(AInherit inherit) {
		this.inherit = inherit;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public boolean isInterface() {
		return (category == INTERFACE); 
	}
	
	public void setCategory(int cat) {
		this.category = cat;
	}
	
	public boolean equals(Object o) {
		if (o instanceof AIEntity) {
			AIEntity e = (AIEntity) o;
			return e.getName().equals(getName())
				&&e.getModule().equals(getModule())
				&&(e.isInterface()==isInterface())
				&&e.getInherit().equals(getInherit())
				&&NilIterator.areEqual(getMethods(),e.getMethods())
				&&NilIterator.areEqual(getProperties(),e.getProperties())
				&&NilIterator.areEqual(getImplements(),e.getImplements())
				&&NilIterator.areEqual(getVariablesMembers(),e.getVariablesMembers());
		}
		else
			return false;
	}

	public Iterator<ANamespace> getUsedNamespaces() {
		return usednamespaces.iterator();
	}
	
	public void addUsedNamespaces(ANamespace n) {
		if((!usednamespaces.contains(n))&&(!n.getNamespace().equals(getModule().getFullName())) )
			usednamespaces.add(n);
	}
	
	public void addProperty(AProperty prop) {
		if(!properties.contains(prop)) {
			properties.add(prop);
		}
	}
	
	

	public Iterable<AMethod> getMetodsByName(String name) {
		ArrayList<AMethod> renv = new ArrayList<AMethod>();
		for (AMethod method : this.methods) {
			if(name.equals(method.getIdent().getName()))
				renv.add(method);
		}
		return renv;
	}

	public Iterator<AProperty> getProperties() {
		return properties.iterator();
	}

	public Iterable<AProperty> properties() {
		return properties;
	}

	public AProperty searchProperty(String name) {
		for (AProperty prop : properties) {
			if(prop.getName().equals(name))
				return prop;
		}
		return null;
	}

	public AMemberVariable searchAttribute(String name) {
		for (AMemberVariable att : variablesmembers) {
			if(att.getIdent().getName().equals(name))
				return att;
		}
		return null;
	}

	/**
	 * @see tamagocc.ast.api.AEntity#getParametrizedType(int)
	 */
	public AType getParametrizedType(int i) {
		return generics[i];
	}

	/**
	 * @see tamagocc.ast.api.AEntity#getParametrizedTypes()
	 */
	public AType[] getParametrizedTypes() {
		return generics;
	}
	
	public void setParametrizedTypes(AType[] types) {
		generics = types;
	}

	/**
	 * @see tamagocc.ast.api.AEntity#isParametric()
	 */
	public boolean isParametric() {
		return (generics.length > 0);
	}
	
	public AType getNameAsType() {
		StringBuilder sb = new StringBuilder();
		
		if((module != null) && (module.getFullName().length() > 0)) {
			sb.append(module.getFullName());
			sb.append(".");
		}
		
		sb.append(name);
		
		if(isParametric()) {
			sb.append("<");
			AType[] gens = getParametrizedTypes();
			for (AType type : gens) {
				sb.append(type.getType());
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(">");
		}
		
		return AIType.generateType(sb.toString());
	}
}
