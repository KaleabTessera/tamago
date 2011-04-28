/*******************************************
 * Copyright (C) 2005, Frederic Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import tamago.*;

public class CarLifeCycleDecorator extends Car implements CarLifeCycleController {
    public static final int UNINITIALIZED = 1;
    public static final int CONFIGURED = 2;
    public static final int STARTED = 3;
    public static final int STOPPED = 4;

    // Attributes
    protected int state; // main running state
    protected Car car;
    private CarLifeCycleDelegate lc_check;

    private CarLifeCycleDecorator() {
	lc_check = new CarLifeCycleDelegate();

    }

    public CarLifeCycleDecorator(Car car) {
	this();
	this.receiver = car.receiver;
	this.engine = car.engine;
	this.direction = car.direction;
	this.effector = car.effector;
    }

    // Interface fonctionnelle
    public void on() throws LifeCycleException {
	start();
	super.on();
    }

    public void off() throws LifeCycleException {
	stop();
	super.off();
    }

    // implantation de l'interface CarLifeCycleController
    public void initialize() throws LifeCycleException {
	lc_check.initialize();
	if(receiver==null || engine==null || direction==null || effector==null)
	    throw new LifeCycleException("Cannot initialize composite, some subcomponent not created");
    }

    // LCConfigureControler interface
    public void configure() throws LifeCycleException {
	lc_check.configure();

	// we bind the subcomponents here
	try { 
	    receiver.bind(engine);
	    receiver.bind(direction);
	    engine.bind(effector);
	    direction.bind(effector);
	} catch(ServiceBindException e) {
	    throw new LifeCycleException("Cannot configure, reason is : "+e.getMessage());
	}

    }

    // LCStartStopController interface
    public void start()  throws LifeCycleException {
	lc_check.start();
    }

    public void stop()  throws LifeCycleException {
	lc_check.stop();
    }

}
