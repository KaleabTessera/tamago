package tamagocc.generic.api;


public interface GInstantiateComponent extends GObject {
	String getComponentName();
	GModule getComponentModule();
	String getComponentLabel();
	
	String getPercolatorName();
	
}
