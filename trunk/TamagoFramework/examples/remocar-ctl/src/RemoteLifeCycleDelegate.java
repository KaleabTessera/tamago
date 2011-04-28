/*******************************************
 * Copyright (C) 2005, Frederic Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.*;

public class RemoteLifeCycleDelegate 
    extends CarLifeCycleDelegate
    implements RemoteLifeCycleController {

    public static final int ACTIVATED = 5;

    public RemoteLifeCycleDelegate() {
	super();
    }

    // LCActivateController interface
    public void activate()  throws LifeCycleException {
	if(state!=STARTED)
	    throw new LifeCycleException("Must be in STARTED mode to activate");
	state = ACTIVATED;
    }

    public void deactivate()  throws LifeCycleException {
	if(state!=ACTIVATED)
	    throw new LifeCycleException("Must be in ACTIVATED mode to deactivate");
	state = STARTED;
    }
}
