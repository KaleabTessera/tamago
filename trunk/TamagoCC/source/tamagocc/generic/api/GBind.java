package tamagocc.generic.api;

public interface GBind extends GObject {
	String getProvider();
	String getRequirer();
	String getLabel();
	GServiceInterface getService();
}
