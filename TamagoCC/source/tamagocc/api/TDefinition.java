package tamagocc.api;


/**
 * 
 *  This interface represents the declaration of a component in an assembly or a composite.
 *  
 * @author Hakim BELHAOUARI
 */
public interface TDefinition extends TObject {
    
    /**
     * 
     * @return Return the name of the component
     */
    public String getComponentName();
    
    /**
     * 
     * @return Return the module name of the target component
     */
    public String getComponentModule();
    
    /**
     * 
     * @return Return the label for specified component
     */
    public String getComponentLabel();
    
    
    /**
     * @return Return the used percolator for this specific definition.
     */
    public String getPercolator();
}
