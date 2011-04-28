package tamagocc.api;

/**
 * this interface is the based of the inheritance of services. 
 * 
 * @author Hakim Belhaouari
 *
 */
public interface TExtendService extends TObject{
	/**
	 * 
	 * @return Return the name of the service to extend.
	 */
	public String getServiceName();
	
	/**
	 * 
	 * @return Return the module name of the service to extend.
	 */
	public String getModuleService();
	
	/**
	 * 
	 * @return Return information of the service to extend.
	 */
	public TService getService();
	
}
