package tamagocc.api;

/**
 * This interface is used in component to represent dependance and more precisely all required
 * service.
 * 
 * @author Hakim BELHAOUARI
 */
public interface TRequire extends TObject {
    /**
     * 
     * @return Return the name of the required service
     */
    public String getServiceName();
    
    /**
     * 
     * @return Return the name of the associate label.
     */
    public String getLabel();
    
    /**
     * 
     * @return Return the module name of the required service
     */
    public String getModule();
    
    /**
     * @return Return information on the required service
     */
    public TService getService();
    
    public boolean isParametric();
    
    public TType[] getParametrizedType();
}
