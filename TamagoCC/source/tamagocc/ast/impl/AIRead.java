/**
 * 
 */
package tamagocc.ast.impl;

import tamagocc.ast.TamagoCCASTVisitor;
import tamagocc.ast.api.AExpression;
import tamagocc.ast.api.ARead;
import tamagocc.exception.TamagoCCException;

/**
 * @author Hakim Belhaouari
 *
 */
public class AIRead extends AIVariable implements ARead {

	private String prop;
	
	/**
	 * 
	 */
	public AIRead(String prop) {
		super(new AIIdent(prop));
		this.prop = prop;
	}
	
	public AIRead(String prop,AExpression idx) {
		super(new AIIdent(prop),idx);
		this.prop = prop;
	}


	/**
	 * @see tamagocc.ast.api.ARead#getName()
	 */
	public String getName() {
		return prop;
	}

	/**
	 * @see tamagocc.ast.api.AExpression#getExpressionType()
	 */
	public int getExpressionType() {
		return AExpression.READ;
	}

	/**
	 * @see tamagocc.ast.TamagoCCAST#visit(tamagocc.ast.TamagoCCASTVisitor)
	 */
	public Object visit(TamagoCCASTVisitor astvisitor) throws TamagoCCException {
		return astvisitor.visitRead(this);
	}
	
	public boolean equals(Object o) {
		if(o instanceof ARead) {
			return prop.equals(((ARead) o).getName());
		}
		return false;
	}

}
