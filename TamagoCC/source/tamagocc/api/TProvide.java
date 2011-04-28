package tamagocc.api;

/**
 * This interface represents, on the component level, a provided service.
 * 
 * @author Hakim BELHAOUARI
 */
public interface TProvide extends TObject {
    /**
     * 
     * @return Return information of the provided service
     */
    public TService getService();
    
    /**
     * 
     * @return Return the name of the provided service
     */
    public String getServiceName();
    
    /**
     * 
     * @return Return the module name of the provided service.
     */
    public String getModule();
    
    public boolean isParametric();
    
    public TType[] getParametrizedType();
}
