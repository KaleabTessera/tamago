/*******************************************
 * Copyright (C) 2005, Frederic Peschanski*
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

package tamago;

/** The controller interface for introspecting the life cycle
 * controllers.
 * Implementors of this controller may externally fetch
 * the current lifecycle state of a given component.
 * They may also fetch the whole possible states as well
 * as the possible successors of the current state
 * in the lifecycle.
 * 
 * @author Frederic Peschanski
 */
public interface LCIntrospectionController extends LifeCycleController {

    /** fetch the current lifecycle state of a component.
     *
     *  @return the description of the current lifecycle state
     */
    LCState getState();

    /** fetch the whole possible lifecycle states of a component.
     *
     *  @return the description of the current lifecycle state
     */
    LCState[] getAllStates();

    /** fetch the possible successors of the current lifecycle state 
     * of a component.
     *
     *  @return the description of the current lifecycle state
     */
    LCState[] getNextPossibleStates();

    // void setNextState(LCState state) throws LifeCycleException; // For intercession
}


