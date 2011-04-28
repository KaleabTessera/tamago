/**
 * 
 */
package tamagocc.generic.api;

/**
 * @author Hakim Belhaouari
 *
 */
public interface GNamespace extends GObject {
	String getNamespace();
	String getType();
	boolean isPackage();
}
