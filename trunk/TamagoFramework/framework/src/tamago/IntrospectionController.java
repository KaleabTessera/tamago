/*******************************************
 * Copyright (C) 2005, Frederic Peschanski*
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

package tamago;

/** The controller interface for component introspection
 * Implementors of this controller can describe their
 * characteristics in terms of required and provided
 * services. 
 * 
 * @todo Introspection of properties
 * @author Frederic Peschanski
 */
public interface IntrospectionController extends Controller {

    /** get the type of component.
     * Note that component types (structural information),
     * component definitions (implementation of the
     * component) and component instances (deployed code) 
     * are distinguished
     *
     * @return the type of the component
     */
    ComponentType getComponentType();

    /** get the type of all services provided by the component.
     *
     * @return an array will all provided service types or null if there is
     * no provided service.
     */
    ServiceType[] getProvidedServiceTypes();

    /** fetch the type of a provided service by name.
     *
     * @return the provided service type or null if not provided.
     */
    ServiceType getProvidedServiceType(String from_name);

    /** get the type of all services required by the component.
     *
     * @return an array will all required service types or null if there is
     * no required service
     */
    ServiceType[] getRequiredServiceTypes();

    /** fetch the type of a required service by name.
     *
     * @return the required service type or null if not required.
     */
    ServiceType getRequiredServiceType(String from_name);
}

