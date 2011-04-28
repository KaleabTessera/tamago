/**
 * 
 */
package tamagocc.api;

/**
 * This enumeration indicates the category of the type.
 * @author Hakim Belhaouari
 */
public enum CatType {
	 VOID,
    INTEGER,
    REAL,
    BOOL,
    OBJECT,
    STRING,
    ARRAY,
    GENERIC /* exclusively for the platform in particular for null expression*/
}