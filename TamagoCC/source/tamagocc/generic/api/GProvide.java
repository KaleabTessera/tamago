package tamagocc.generic.api;

public interface GProvide extends GObject {
	/**
	 * @return Return the module of the implemented service
	 */
	GModule getModule();
	
	/**
	 * @return Return the name of the implemented service.
	 */
	String getName();
	
	/**
	 * @return Indicates if the service is declared in the entity of if the service is a parent
	 * of another service.
	 */
	boolean	isDeclaredService();
	
	GServiceInterface getService();
	
	
	boolean isParametric();
	
	GType[] getParametrizedType();

	/**
	 * @return
	 */
	GType getNameAsType();
}
