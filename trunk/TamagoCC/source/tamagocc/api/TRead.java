package tamagocc.api;

/**
 * This interface is used in assertion to read the value of a property observer. 
 * @author Hakim BELHAOUARI
 */
public interface TRead extends TVariable {
    /**
     * @return Return the name of the property
     */
    String getName();
}
