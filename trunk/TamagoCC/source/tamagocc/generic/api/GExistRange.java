/**
 * 
 */
package tamagocc.generic.api;


/**
 * @author Hakim Belhaouari
 *
 */
public interface GExistRange extends GQuantifier {	
	
	GExpression getMin();
	GExpression getMax();
}
