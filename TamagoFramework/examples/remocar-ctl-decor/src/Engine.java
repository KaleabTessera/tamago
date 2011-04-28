/*******************************************
 * Copyright (C) 2005, Fr�©d�©ric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import java.util.*;

import tamago.*;

public class Engine extends BasicComponent
    implements EngineService, 
	       RequireEffectorEngineService,
	       IntrospectionController,
	       ReflectionController {

    // Required services
    private EffectorEngineService effector;

    // attributes

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
    
    // Propri�t�s
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
}
