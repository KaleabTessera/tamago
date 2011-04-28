package tamagocc.api;


/**
 * Represents the access of a property in the CDL file.
 * @author Hakim BELHAOUARI
 */
public interface TAccess extends TObject{

    /**
     * @return Return true if the property can be written, else false
     */
    boolean canWrite();
    
    /**
     * @return Return true if the property can be read, else false
     */
    boolean canRead();
}
