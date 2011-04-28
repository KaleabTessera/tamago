/**
 * 
 */
package tamagocc.generic.api;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface GInLabel extends GExpression {
	GExpression getTarget();
	GExpression getSubExpression();	
}
