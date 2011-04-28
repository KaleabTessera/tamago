/*******************************************
 * Copyright (C) 2005, Frederic Peschanski*
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

package tamago;

/** The controller interface for separate activation phase
 * in the component lifecycle.
 * Implementors of this controller may be externally 
 * activated and deactivated, allowing external
 * scheduling or temporary deactivation of components.
 * 
 * @author Frederic Peschanski
 */
public interface LCActivateController extends LifeCycleController {

    /** Activate a component
     *
     *  @throws LifeCycleException if the component may not be activated
     */
    public void activate() throws LifeCycleException; 

    /** Deactivate a component
     *
     *  @throws LifeCycleException if the component may not be deactivated
     */
    public void deactivate() throws LifeCycleException;
}

