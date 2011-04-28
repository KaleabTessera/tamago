/**
 * 
 */
package tamagocc.generic.api;


/**
 * @author Hakim Belhaouari
 *
 */
public interface GForallRange extends GQuantifier {
	GExpression getMin();
	GExpression getMax();
}
