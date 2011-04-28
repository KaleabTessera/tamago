/*******************************************
 * Copyright (C) 2005, Frederic Peschanski*
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

package tamago;

/** The controller interface for basic Start/Stop lifecycles
 * Implementors of this controller may be externally 
 * started and stopped.
 * 
 * @author Frederic Peschanski
 */
public interface LCStartStopController extends LifeCycleController {

    /** Start a component activity.
     *
     *  @throws LifeCycleException if the component may not be started
     */
    public void start() throws LifeCycleException;

    /** Stop a component activity.
     *
     *  @throws LifeCycleException if the component may not be stopped
     */
    public void stop() throws LifeCycleException;
}

