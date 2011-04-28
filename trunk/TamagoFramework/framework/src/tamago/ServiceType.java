/*******************************************
 * Copyright (C) 2005, Frederic Peschanski*
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

package tamago;

/** The Service Type interface
 * Services possess a name and an implementation (definition)
 * A subtyping hierarchy is provided, Service being the
 * top-level service.
 *
 * @author Frederic Peschanski
 */
public interface ServiceType {
    /** get the name of the service (a service name is unique)
     *
     * @return the name of the service
     */
    String getName();

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
    boolean isSuperType(ServiceType subtype);

    /** tell if the service is a strict supertype of the argument
     * 
     * @param subtype the subtype candidate
     * @return true if the service type is a strict supertype of the candidate, 
     * or false in the other case.
     */
    boolean isStrictSuperType(ServiceType subtype);

    /** tell if the service is a subtype of the argument
     * 
     * @param supertype the supertype candidate
     * @return true if the service type is a subtype of the candidate, 
     * or false in the other case.
     */
    boolean isSubType(ServiceType supertype);

    /** tell if the service is a strict subtype of the argument
     * 
     * @param supertype the supertype candidate
     * @return true if the service type is a strict subtype of the candidate, 
     * or false in the other case.
     */
    boolean isStrictSubType(ServiceType supertype);

    /** tell if the service is identical to the argument
     * 
     * @param type2 the identical type candidate
     * @return true if the service type is identical to the candidate, 
     * or false if both types are different.
     */
    boolean isSameType(ServiceType type2);

    /** tell if the service is provided by the argument
     * 
     * @param o the provider candidate
     * @return true if the passed argument is a provider for the service type
     */
    boolean isProvidedBy(Object o);
}

