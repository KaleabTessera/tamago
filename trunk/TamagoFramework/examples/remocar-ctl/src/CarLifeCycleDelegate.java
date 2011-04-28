/*******************************************
 * Copyright (C) 2005, Frederic Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.*;

public class CarLifeCycleDelegate implements CarLifeCycleController {
    public static final int UNINITIALIZED = 1;
    public static final int CONFIGURED = 2;
    public static final int STARTED = 3;
    public static final int STOPPED = 4;

    // Attributes
    protected int state; // main running state

    public CarLifeCycleDelegate() {
	state = UNINITIALIZED;
    }

    public boolean checkStarted() {
	return state == STARTED;
    }

    public boolean checkConfigured() {
	return state == CONFIGURED;
    }

    // LCInitializeController interface
    public void initialize() throws LifeCycleException {
	if(state!=UNINITIALIZED)
	    throw new LifeCycleException("Must be in UNITIALIZED mode to initialize");
	state = STOPPED;
    }

    // LCConfigureControler interface
    public void configure() throws LifeCycleException {
	if(state!=STOPPED)
	    throw new LifeCycleException("Must be in STOPPED mode to configure");
    }

    // LCStartStopController interface
    public void start()  throws LifeCycleException {
	if(state!=STOPPED)
	    throw new LifeCycleException("Must be in STOPPED mode to start");
	state = STARTED;
    }

    public void stop()  throws LifeCycleException {
	if(state!=STARTED)
	    throw new LifeCycleException("Must be in STARTED mode to stop");
	state = STOPPED;
    }
}
