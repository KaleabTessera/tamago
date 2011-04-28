package tamagocc.api;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * 
 * This interface is the root of followed tamago entity (service, component, composite).
 * (this class DO NOT include the assembly) 
 * 
 * @author Hakim BELHAOUARI
 */
public interface TTamago extends TTamagoEntity { 
    
	
    /**
     * @return Return all invariants of the entity
     */
    Iterator<TInvariant> getInvariants();
    
    /**
     * @return Return all information about the behavior of the entity
     */
    TBehavior getBehavior(); 
    
    /**
     * @see tamagocc.api.TMethod
     * @return Return the iterator of all functionality defined for this component
     */
    Iterator<TMethod> getMethods(); // TMethod
    
    /**
     * @see tamagocc.api.TProperty
     * @return Return the iterator of all property defined for this component
     */
    Iterator<TProperty> getProperties(); // TProperty
    
    /**
     * This function search the property in all specified property
     * @param name : represent the name of the searched property
     * @return Return the property with the specific name 
     * @throws NoSuchElementException
     */
    TProperty getProperty(String name) throws NoSuchElementException;
    
      
    /**
     * This method search a declared method. In the entity
     * @param name : correspond to the name of the searched method
     * @return All information of the asked method, or the pointer null
     */
    TMethod getMethod(String name);
    
    /**
     * This method search all declared method with the name <em>name</em>
     * @param name : name of searched method
     * @return Return an array of all method searched
     */
    TMethod[] getMethods(String name);
    
    /**
     * This method search all method declared in this entity, with the same signature as the argument
     * @param m : the signature of the searched method
     * @return return the first method in the entity which have the same signature or null else
     */
    TMethod getSimilarMethods(TMethod m);
    
    /**
     * @return An iterable elements for implemented type interface
     */
    Iterable<TImplements> getImplements();
    
    /**
     * @return An iterable elements for all used namespace
     */
    Iterable<TNamespace> getUsedNamespace();
}
