/**
 * 
 */
package tamagocc.ast.api;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface AConvertType extends AExpression {
	AType getNewType();
	AExpression getExprToConvert();
}
