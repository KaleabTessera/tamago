package tamagocc.api;

/**
 * 
 * This interface represents a parameter in a functionality definition.
 * 
 * @author Hakim BELHAOUARI
 */
public interface TParameter extends TObject {
    /**
     * @return Return the name of the parameter
     */
    public String getName();
    
    /**
     * Return the type of the parameter
     * @return Return the type of the parameter
     */
    public TType getType();
}
