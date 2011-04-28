/*******************************************
 * Copyright (C) 2005, Frederic Peschanski*
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

package tamago;

public class Tamago {

    // XXX: this does not work yet, the problem is that we do not get
    //      the proper type of the service
    public static void Bind(RequireService requestor, Service provider) throws ServiceBindException {
	System.out.println("Requestor type = " + requestor);
	System.out.println("Service type = " + provider);
	requestor.bind(provider);
    }

    public static void Initialize(Component comp) throws LifeCycleException {
	try {
	    LCInitializeController ilc = (LCInitializeController) comp;
	    ilc.initialize();
	} catch(LifeCycleException e) {
	    throw e;
	} catch(ClassCastException e) {
	    throw new LifeCycleException("Initialize Life Cycle not supported");
	}
    }

    public static void Configure(Component comp) throws LifeCycleException {
	try {
	    LCConfigureController clc = (LCConfigureController) comp;
	    clc.configure();
	} catch(LifeCycleException e) {
	    throw e;
	} catch(ClassCastException e) {
	    throw new LifeCycleException("Configure Life Cycle not supported");
	}
    }

    public static void Start(Component comp) throws LifeCycleException {
	try {
	    LCStartStopController ilc = (LCStartStopController) comp;
	    ilc.start();
	} catch(LifeCycleException e) {
	    throw e;
	} catch(ClassCastException e) {
	    throw new LifeCycleException("Start Life Cycle not supported");
	}
    }

    public static void Stop(Component comp) throws LifeCycleException {
	try {
	    LCStartStopController ilc = (LCStartStopController) comp;
	    ilc.stop();
	} catch(LifeCycleException e) {
	    throw e;
	} catch(ClassCastException e) {
	    throw new LifeCycleException("Start Life Cycle not supported");
	}
    }

    public static void Activate(Component comp) throws LifeCycleException {
	try {
	    LCActivateController ilc = (LCActivateController) comp;
	    ilc.activate();
	} catch(LifeCycleException e) {
	    throw e;
	} catch(ClassCastException e) {
	    throw new LifeCycleException("Activate Life Cycle not supported");
	}
    }

    public static void DeActivate(Component comp) throws LifeCycleException {
	try {
	    LCActivateController ilc = (LCActivateController) comp;
	    ilc.deactivate();
	} catch(LifeCycleException e) {
	    throw e;
	} catch(ClassCastException e) {
	    throw new LifeCycleException("DeActivate Life Cycle not supported");
	}
    }
}
