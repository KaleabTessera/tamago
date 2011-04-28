/*******************************************
 * Copyright (C) 2005, Frederic Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

package tamago;

/** The Component Type interface
 * Component types possess a name and an implementation (definition)
 * A subtyping hierarchy is provided, Component being the
 * top-level component type.
 * Note: A component type is a subtype of another component type
 * if it requires supertypes and provides subtypes of the services.
 * Also, it must implement at least the same controllers.
 *
 * @author Frederic Peschanski
 */
public interface ComponentType {
    /** get the name of the component type (a component type name is unique)
     *
     * @return the name of the component type
     */
    String getName();


    /** get the implementation of the component type
     *
     * @return the implementation of the component type
     */
    Class getImplementation();

    boolean isSuperType(ComponentType subtype);
    boolean isSubType(ComponentType supertype);
    boolean isSameType(ComponentType type2);
    boolean isStrictSuperType(ComponentType subtype);
    boolean isStrictSubType(ComponentType supertype);
    boolean isTypeOf(Object o);
}

