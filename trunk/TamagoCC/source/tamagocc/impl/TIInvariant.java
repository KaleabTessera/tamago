/**
 * 
 */
package tamagocc.impl;

import tamagocc.api.TCategory;
import tamagocc.api.TExpression;
import tamagocc.api.TInvariant;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author hakim
 *
 */
public class TIInvariant extends TICondition implements TInvariant {

	/**
	 * @param expression
	 * @param category
	 * @param message
	 */
	public TIInvariant(TExpression expression, TCategory category,
			String message) {
		super(expression, category, message);
	}

	public boolean equals(Object o) {
		return (o instanceof TInvariant) && super.equals(o);
	}
	
	/**
     * @see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitInvariant(this);        
    }
}
