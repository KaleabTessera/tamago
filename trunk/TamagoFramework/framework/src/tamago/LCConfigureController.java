/*******************************************
 * Copyright (C) 2005, Frédéric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

package tamago;

/** The controller interface for separate configuration phase
 * in the component lifecycle.
 * Implementors of this controller may be externally 
 * configured, for example by setting properties or enabling
 * internal bindings for composites.
 * Note that reconfiguration may be provided if component
 * may enter the configuration phase at various moments in
 * the execution.
 * 
 * @author Frederic Peschanski
 */
public interface LCConfigureController extends LifeCycleController {

    /** Start the configuration phase of a component.
     * this method may be safely overloaded to allow parameters for
     * the configuration. It is a good practice to end the overloaded
     * configure with a call to the configure method without parameter
     * in order to cope with the lifecycle management issues.
     * 
     *  @throws LifeCycleException if the component may not be initialized
     */
    public void configure() throws LifeCycleException;
}

