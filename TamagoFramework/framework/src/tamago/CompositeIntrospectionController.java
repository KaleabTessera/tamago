/*******************************************
 * Copyright (C) 2005, Frederic Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

package tamago;

/** The controller interface for composite introspection
 * Implementors of this controller can describe their
 * characteristics in terms of required and provided
 * services, as well as exported (provided) services
 * in the case of composites. 
 * 
 * @todo Introspection of properties
 * @author Frederic Peschanski
 */
public interface CompositeIntrospectionController extends IntrospectionController {
    /** get the type of all services exported by the composite.
     *
     * @return an array will all exported service types or null if there is
     * no exported service.
     */
    ServiceType[] getExportedServiceTypes();

    /** fetch the type of an exported service by name.
     *
     * @return the exported service type or null if there is not exported.
     */
    ServiceType getExportedServiceType(String from_name);
}

