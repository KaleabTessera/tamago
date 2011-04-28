/**
 * 
 */
package tamagocc.ast.api;

import java.util.Iterator;

import tamagocc.ast.TamagoCCAST;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AEntity extends TamagoCCAST {
	public static final int INTERFACE = 0;
	public static final int CLASS = 1;	
	
	String getName();
	AModule getModule();
	
	Iterator<AMemberVariable> getVariablesMembers();
	AMemberVariable searchAttribute(String name);
	
	Iterator<AMethod> getMethods();
	
	AComment getComment();
	
	boolean isInterface();
	
	Iterator<ANamespace> getUsedNamespaces();
	
	Iterator<AImplements> getImplements();
	AInherit getInherit();
	Iterable<AMethod> getMetodsByName(String name);
	
	AProperty searchProperty(String name);
	Iterator<AProperty> getProperties();
	Iterable<AProperty> properties();
	
	boolean isParametric();
	AType[] getParametrizedTypes();
	AType getParametrizedType(int i);
	public AType getNameAsType();
}
