/**
 * 
 */
package tamagocc.generic.api;

/**
 * @author Hakim Belhaouari
 *
 */
public interface GImplements extends GObject {
	GType getType();
	GModule getModule();
}
