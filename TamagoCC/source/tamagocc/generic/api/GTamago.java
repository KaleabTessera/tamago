/**
 * 
 */
package tamagocc.generic.api;

import java.util.Hashtable;

import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari
 *
 */
public interface GTamago extends GTamagoEntity {
	/**
	 * 
	 * @return Returns all invariants declared in this component. For all inherited invariant 
	 * see the method getAllInvariants
	 */
	// Iterator<GInvariant>
	Iterable<GInvariant> getInvariants();
	
	/**
	 * 
	 * @return Return the behavior of this entity (caution it may be a partial behavior)  
	 */
	GBehavior getBehavior();
	
	/**
	 * @return Return all properties of the entity
	 */
	Iterable<GProperty> getProperties();
	
	/**
	 * @return Return all method and methods inherited. indexed by the ID of the method
	 */
	Hashtable<String, GMethod> getMethods();
	
	/**
	 * @return Return an hashtable which contains IDs of the method
	 */
	Hashtable<GMethod,? extends Iterable<String>> getMethodID();
	
	
	Iterable<String> getAllMethodID();
	
	/**
	 * 
	 * @return Return the set of method without duplicate IDs for a same ID
	 */
	Iterable<GMethod> getUniqueMethods();
	
	GMethod getMethod(String id);

	GMethod searchMethod(GMethod method) throws TamagoCCException;
	
	
	/**
	 * 
	 * @return Return all methods independantly of the redundancy of the method
	 */
	Iterable<GMethod> getAllMethods();
	
	int countAllMethods();
	
	/**
	 * 
	 * @param method
	 * @return Return all methods corresponding of the signature of the method
	 */
	Iterable<GMethod> getAllMethods(GMethod method);
	
	
	/**
	 * Indicates all implemented type interface
	 * @return an iterable element which contains every interface implemented
	 */
	Iterable<GImplements> getImplements();
	
	/**
	 * Indicates all imported namespaces
	 * @return an iterable element which contains every used namespaces
	 */
	Iterable<GNamespace> getNamespaces();

	GProperty searchProperty(String name);

	Iterable<GMethod> getMetodsByName(String name);
	
}
