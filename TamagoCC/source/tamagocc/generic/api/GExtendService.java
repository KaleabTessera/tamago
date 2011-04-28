package tamagocc.generic.api;

public interface GExtendService extends GObject {
	String getServiceName();
	GModule getModule();
	boolean isRefined();
	boolean isInclude();
	
	GServiceInterface getService();
}
