package tamagocc.api;

/**
 * This class allows manipulation of index in the concrete class.
 * @author hakim
 *
 */
public interface TIndexExpression extends TExpression {

	boolean hasIndex();
	
	TExpression getIndex();
	void setIndex(TExpression idx);
}
