/**
 * 
 */
package tamagocc.parser;

import tamagocc.parser.cdlast.CDLExpression;
import tamagocc.parser.cdlast.CDLExpressionType;

/**
 * @author hbelhaou
 *
 */
public class WrongTypeOperator extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4649637860205306013L;

	/**
	 * 
	 */
	public WrongTypeOperator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public WrongTypeOperator(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public WrongTypeOperator(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public WrongTypeOperator(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}


	public WrongTypeOperator(CDLExpression e, CDLExpressionType expected,
			CDLExpressionType obtained) {
		super("Expression with incorrect type argument:"+ e.toString()+ "   expected: "+expected+" find: "+obtained);
	}

}
