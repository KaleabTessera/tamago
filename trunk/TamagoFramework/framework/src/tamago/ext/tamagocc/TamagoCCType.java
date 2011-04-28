/**
 * 
 */
package tamago.ext.tamagocc;

import java.util.Iterator;

/**
 * @author Hakim Belhaouari and Frederic Peschanski
 *
 */
public interface TamagoCCType{
	
	public static final int TYPE_SERVICE = 0;
	public static final int TYPE_COMPONENT = 1;
	public static final int TYPE_COMPOSITE = 2;
	public static final int TYPE_ASSEMBLY = 3;
	
	/** get the name of the service (a service name is unique)
    *
    * @return the name of the service
    */
   String getName();
   
   String getModule();

   /** get the implementation of the service
    * Note: this is not the implementation of a provider for the service.
    *
    * @return the class implementing the service description
    */
   Class getImplementation();

   /** tell if the service is a supertype of the argument
    * 
    * @param subtype the subtype candidate
    * @return true if the service type is a supertype of the candidate, 
    * or false in the other case.
    */
   boolean isSuperType(TamagoCCType subtype);

   /** tell if the service is a strict supertype of the argument
    * 
    * @param subtype the subtype candidate
    * @return true if the service type is a strict supertype of the candidate, 
    * or false in the other case.
    */
   boolean isStrictSuperType(TamagoCCType subtype);

   /** tell if the service is a subtype of the argument
    * 
    * @param supertype the supertype candidate
    * @return true if the service type is a subtype of the candidate, 
    * or false in the other case.
    */
   boolean isSubType(TamagoCCType supertype);

   /** tell if the service is a strict subtype of the argument
    * 
    * @param supertype the supertype candidate
    * @return true if the service type is a strict subtype of the candidate, 
    * or false in the other case.
    */
   boolean isStrictSubType(TamagoCCType supertype);

   /** tell if the service is identical to the argument
    * 
    * @param type2 the identical type candidate
    * @return true if the service type is identical to the candidate, 
    * or false if both types are different.
    */
   boolean isSameType(TamagoCCType type2);

   
   Iterator getExtends();
   
   int getTamagoCCType();
   
   TamagoCCPercolator[] getAvailablePercolator();

}
