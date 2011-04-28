/*******************************************
 * Copyright (C) 2005, Frederic Peschanski*
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

package tamago;

/** The controller interface for component reflection
 * Implementors of this controller publish their actual
 * bindings to the components providing the required
 * services. Clients may then interact directly with
 * the providers and temper with the overall composition.
 * Note: this powerfull feature should be used with care 
 * because it is a shortcut to implementation details at runtime.
 * 
 * @author Frederic Peschanski
 */
public interface ReflectionController extends Controller {

    /** fetch the implementations of the service providers
     * Note: there may be multiple providers for a given service
     *
     * @param type the service type that must be reflected upon.
     * @return the provider components for the given service, or null if none.
     */
    Object[] getProviders(ServiceType type);   
}
