/**
 * 
 */
package tamagocc.generic.api;


/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface GRequire extends GObject {
	String getServiceName();
	GModule getServiceModule();
	String getLabel();
	
	boolean isParametric();
	GType getNameAsType();
	GType[] getParametrizedType();
}
