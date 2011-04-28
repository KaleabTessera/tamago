/**
 * 
 */
package tamagocc.generic.api;

/**
 * @author Hakim Belhaouari
 *
 */
public interface GCast extends GExpression {
	GType getType();
	GExpression getExpression();
}
