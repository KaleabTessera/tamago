/*******************************************
 * Copyright (C) 2005, Frédéric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import java.util.*;

import tamago.*;

public class Engine extends BasicComponent
    implements EngineService, 
	       RequireEffectorEngineService,
	       IntrospectionController,
	       ReflectionController,
	       CarLifeCycleController {

    // Required services
    private EffectorEngineService effector;

    // attributes
    private CarLifeCycleDelegate lc_check;

    // properties
    private double max_thrust = 100.0;
    private double throttle = 0.9;

    // Interface fonctionnelle
    public Engine() {
        effector = null;
	lc_check = new CarLifeCycleDelegate();
    }
    

    public void forward() {
	if(!lc_check.checkStarted())
	    return;
	System.err.println("[ENGINE] : Delivered Forward POWER="+throttle*max_thrust);
	effector.deliverPower(throttle*max_thrust);
    }

    public void backward() {
	if(!lc_check.checkStarted())
	    return;

	System.err.println("[ENGINE] : Delivered Backward POWER="+throttle*max_thrust);
	effector.deliverPower(-throttle*max_thrust);
    }


    // Laison des fournisseurs
    public void bind(EffectorEngineService effector) throws ServiceBindException {
	if(lc_check.checkStarted())
	    throw new ServiceBindException("Cannot configure while running");
	this.effector = effector;
    }

    // Cycle de vie
    
    // Propri�t�s
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

    // Implantation de l'interface IntrospectionController
    public ComponentType getComponentType() {
        return new ComponentTypeDescriptor(this.getClass().getName(),this.getClass());
    }

    
    public ServiceType[] getProvidedServiceTypes() {
        ServiceType[] ret = new ServiceType[1];
        ret[0] = new ServiceTypeDescriptor("EngineService", EngineService.class);
        return ret;
    }
            
    public ServiceType[] getRequiredServiceTypes() {
        ServiceType[] ret = new ServiceType[1];
        ret[0] = new ServiceTypeDescriptor("EffectorEngineService", EffectorEngineService.class);
        return ret;
    }    
    
    public ServiceType getProvidedServiceType(String from_name) {
        if(from_name.equals("EngineService"))
            return new ServiceTypeDescriptor("EngineService", EngineService.class);
        else 
            return null;
    }
    
    public ServiceType getRequiredServiceType(String from_name) {
        if(from_name.equals("EffectorEngineService"))
            return new ServiceTypeDescriptor("EffectorEngineService", EffectorEngineService.class);
        else 
            return null;        
    }
    
    // implantation de l'interface ReflectionController
    public Object[] getProviders(ServiceType service) {
        Object[] ret = null;
        ArrayList list = new ArrayList();
        if(service.isProvidedBy(effector))
            list.add(effector);
        if(list.size()==0)
            return null;
        ret = new Object[list.size()];
        for(int i=0;i<list.size();i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
     // implantation de l'interface CarLifeCycleController
    public void initialize() throws LifeCycleException {
	lc_check.initialize();
    }

    // LCConfigureControler interface
    public void configure() throws LifeCycleException {
	lc_check.configure();
    }

    // LCStartStopController interface
    public void start()  throws LifeCycleException {
	lc_check.start();
    }

    public void stop()  throws LifeCycleException {
	lc_check.stop();
    }
   
}
