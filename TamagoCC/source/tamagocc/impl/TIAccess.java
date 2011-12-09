package tamagocc.impl;

import tamagocc.api.TAccess;
import tamagocc.exception.TamagoCCException;
import tamagocc.util.TamagoCCVisitor;

/**
 * @author Hakim BELHAOUARI
 * 10 oct. 2005 TIAccess.java
 */
/**
 */
public class TIAccess implements TAccess {

    private boolean isReadable;
    private boolean isWritable;
    
    /**
     * Contructeur permettant de generer les droits en fonction 
     * de ce qui a ete preciser dans la chaine 
     */
    public TIAccess(String rule) {
        super();
        if("r".equals(rule.trim()) || "read".equals(rule.trim())) {
            isReadable = true; isWritable = false;
        }
        else if("w".equals(rule.trim())||"write".equals(rule.trim())) {
            isReadable = false; isWritable = true;
        }
        else if(("rw".equals(rule.trim()))||("wr".equals(rule.trim()))||"readwrite".equals(rule.trim())) {
            isReadable = true; isWritable = true;
        }
    }
    
    /**
     * Constructeur par defaut. Par defaut j'ai decide que
     * qu'une propriete est en lecture seule!!
     */
    public TIAccess() {
        super();
        isReadable = true;
        isWritable = false;
    }

    /**
     * @see tamagocc.api.TAccess#canWrite()
     */
    public boolean canWrite() {
        return isWritable;
    }

    /**
     * @see tamagocc.api.TAccess#canRead()
     */
    public boolean canRead() {
        return isReadable;
    }
    
    public boolean equals(Object o) {
    	if (o instanceof TAccess) {
			TAccess p = (TAccess) o;
			return (canRead()==p.canRead())&&(canWrite()==p.canWrite());
		}
    	
    	return false;
    }

    /**
     * 
     * 	@see tamagocc.api.TObject#visit(tamagocc.util.TamagoCCVisitor)
     */
	public Object visit(TamagoCCVisitor visitor) throws TamagoCCException {
		return visitor.visitAccess(this);
	}
}
