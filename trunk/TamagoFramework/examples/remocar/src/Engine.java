/*******************************************
 * Copyright (C) 2005, Frédéric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import java.util.*;

import tamago.*;

public class Engine extends BasicComponent
    implements EngineService, 
	       RequireEffectorEngineService {

    // Required services
    private EffectorEngineService effector;

    // properties
    private double max_thrust = 100.0;
    private double throttle = 0.9;

    // Interface fonctionnelle
    public Engine() {
        effector = null;
    }
    

    public void forward() {
	System.err.println("[ENGINE] : Delivered Forward POWER="+throttle*max_thrust);
	effector.deliverPower(throttle*max_thrust);
    }

    public void backward() {
	System.err.println("[ENGINE] : Delivered Backward POWER="+throttle*max_thrust);
	effector.deliverPower(-throttle*max_thrust);
    }


    // Laison des fournisseurs
    public void bind(EffectorEngineService effector) throws ServiceBindException {
	this.effector = effector;
    }

    // Cycle de vie
    
    // Propriét�s
    public void setMaxThrust(double max_thrust) {
	this.max_thrust = max_thrust;
    }

    public double getMaxThrust() {
	return max_thrust;
    }

    public void setThrottle(double max_thrust) {
	this.throttle = throttle;
    }

    public double getThrottle() {
	return throttle;
    }

}
