package tamagocc.api;

/**
 * This interface represents a type in the Tamago Contract Compiler.
 * 
 * @author Hakim BELHAOUARI
 * 22 juin 2005 TType.java
 */
public interface TType extends TObject,Comparable<TType> {
    
    /**
     * Return the type
     * @return the name of the type
     */
    String getType();
    
    /**
     * @return Return the category of the type.
     */
    CatType catType();    
    
    /**
     * Indicates if the type is an array
     * @return Return true if the type is an array, else return false
     */
    boolean isArray();
        
    /**
     * Gets the type of element of the array.
     * @return Return type of element of the array
     */
    TType getArrayType();

    
    boolean isParametric();
    
    TType[] getParametricsTypes();
    
    TType getParametricType(int i);    
}
