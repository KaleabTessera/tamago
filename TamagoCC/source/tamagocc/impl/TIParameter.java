package tamagocc.impl;

import tamagocc.api.TParameter;
import tamagocc.api.TType;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

/**
 * 
  * @author Hakim BELHAOUARI
 */
public class TIParameter implements TParameter {

    String name;
    TType type;
    /**
     * 
     */
    public TIParameter(String name, TType t) {
        super();
        this.name = name;
        type = t;
    }

    /**
     * @see tamagocc.api.TParameter#getName()
     */
    public String getName() {
        return name;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof TParameter) {
			TParameter p = (TParameter) o;
			return (getName().equals(p.getName())
					&& getType().equals(p.getType()));
		}
    	return false;
    }

    /**
     * @see tamagocc.api.TParameter#getType()
     */
    public TType getType() {
        return type;
    }

    public String toString() {
        return "<parameter name=\""+name+"\" >"+type.toString()+"</parameter>";
    }
    
    /**
     * @see tamagocc.api.TObject#visit()
     */
    public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
        return visitor.visitParameter(this);
    }
}
