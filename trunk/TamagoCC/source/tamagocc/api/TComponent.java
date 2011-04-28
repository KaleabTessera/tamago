package tamagocc.api;


import java.util.Iterator;

/**
 * This interface represents the component specified in the CDL language. 
 * 
 * @author Hakim BELHAOUARI
 */
public interface TComponent extends TTamago {
    /**
     * 
     * @return Return the name of the component
     */
	String getName();
    
    /**
     * 
     * @return Return the name of the module
     */
    String getModule();
    
    /**
     * This function allows to user to get all provided service.
     * @see tamagocc.api.TProvide
     * @return Return the iterator of all provided service 
     */
    Iterator<TProvide> getProvides();//TProvide
    
    /**
     * @see tamagocc.api.TRequire
     * @return Return the iterator of all required service
     */
    Iterator<TRequire> getRequires(); // TRequire
    
    
    /**
     * This function return the iterator of all allowed percolator specified by the
     * conceptor, in the CDL language.
     * 
     * @see tamagocc.api.TPercolator
     * @return Return the iterator of all allowed percolator for this component.
     */
    Iterator<TPercolator> getAllowedPercolators();
    
}
