package tamagocc.api;

/**
 * This interface represents a property declared for an entity.
 * @author Hakim BELHAOUARI
 */
public interface TProperty extends TObject {
    /**
     * @return Return the name of the property.
     */
    String getName();
    
    /**
     * @return Return the type of the property.
     */
    TType getType();
    
    /**
     * @return Return the access of the property.
     */
    TAccess getAccess();
    
}
