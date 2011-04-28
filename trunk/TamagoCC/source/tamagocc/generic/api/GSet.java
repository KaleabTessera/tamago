/**
 * 
 */
package tamagocc.generic.api;

/**
 * @author Hakim Belhaouari
 *
 */
public interface GSet extends GObject {
	int size();
	GType getType();
	Iterable<GExpression> getElements();
}
