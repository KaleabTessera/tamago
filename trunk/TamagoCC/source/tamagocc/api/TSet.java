/**
 * 
 */
package tamagocc.api;

/**
 * @author belhaouari
 *
 */
public interface TSet extends TObject {
	int size();
	TType getType();
	Iterable<TExpression> getElements();
}
