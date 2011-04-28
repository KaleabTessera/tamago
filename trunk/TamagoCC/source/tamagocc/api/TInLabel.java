/**
 * 
 */
package tamagocc.api;

/**
 * This interface allow a client to access in inner property or method.
 * It is used to write expression.
 * 
 * @author Hakim Belhaouari
 *
 */
public interface TInLabel extends TExpression {
	
	/**
	 * 
	 * @return Return the label, which contained the wished element
	 */
	TExpression getTarget();
	
	
	/**
	 * 
	 * @return Return the subterm expression to be accessed
	 */
	TExpression getSubTerm();
}
/*toto.method();

<read property="toto">
	<in label="assembly">
		<in label="service">
			<in property="prop1" />
		</in>
	</in>
</read>
*/