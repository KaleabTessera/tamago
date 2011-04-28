/**
 * 
 */
package tamagocc.generic.api;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface GProperty extends GObject {
	String getName();
	GType getType();
	GAccess getAccess();
	
	boolean equivalentProperty(GProperty property);
}
