/**
 * 
 */
package tamagocc.generic.api;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface GExport extends GObject {
	String getServiceName();
	GModule getServiceModule();
	GExpression getProvider();
}
