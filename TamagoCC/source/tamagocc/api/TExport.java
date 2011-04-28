package tamagocc.api;


/**
 * 
 * this interface represents an export service in a composite entity.
 * 
 * @author Hakim BELHAOUARI
 */
public interface TExport extends TObject {
    /**
     * 
     * @return Return the name of the exported service
     */
    public String getService();
    
    /**
     * 
     * @return Return the label of the component, which provide this service.
     */
    public TExpression getProvider();
    
    /**
     * 
     * @return Return the module name of the exported service.
     */
    public String getServiceModule();
    
}
