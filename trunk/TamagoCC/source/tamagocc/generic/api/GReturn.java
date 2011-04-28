/**
 * 
 */
package tamagocc.generic.api;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface GReturn extends GExpression {
	GType getType();
	GVariable getVariable();
	
	boolean hasIndex();
	GExpression getIndex();
}
