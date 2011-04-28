package tamagocc.api;

import java.util.Iterator;

/**
 * Represents the specification of an assembly describe in the CDL contract.
 * @author Hakim Belhaouari
 *
 */
public interface TAssembly extends TTamagoEntity {
	/**
     * 
     * @return Return the name of an assembly
     */
    String getName();
    
    /**
     * 
     * @return Return the module name of the assembly
     */
    String getModule();
    
    /**
     * @see tamagocc.api.TDefinition
     * @return Return the list of all used component, in the sub architecture.
     */
    Iterator<TDefinition> getDefinitions();
    
    /**
     * @see tamagocc.api.TBind
     * @return Return the iterator of all binding, to connect provide/require services
     * of the sub architecture.
     */
    Iterator<TBind> getBinds();
}
