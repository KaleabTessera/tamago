package tamagocc.api;


/**
 * 
 * Represents a link between a provide/require service for two components.
 * This class is used in the dynamic binding for assemblies and composites.
 * @author Hakim BELHAOUARI
 */
public interface TBind extends TObject {
    
    /**
     * 
     * @return Return the associate label of a component.
     */
    public String getProvider();
    
    /**
     * 
     * @return Return the associate label of a component
     */
    public String getRequirer();
    
    /**
     * @return Return a label of the linkage
     */
    public String getLabel();
    
    /**
     * Indicate if the service has been specified or not, for the current binding.
     * @return Returns true if TamagoCC must determine the service, or if the user specify it.
     */
    public boolean forcedService();
    
    /**
     * 
     * @return Return the service for the binding
     */
    public String getServiceName();
    /**
     * 
     * @return Returns the module of the service.
     */
    public String getServiceModule();
    
}
