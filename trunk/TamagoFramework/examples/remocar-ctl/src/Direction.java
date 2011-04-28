/*******************************************
 * Copyright (C) 2005, Frédéric Peschanski *
 *         >> DO NOT DISTRIBUTE <<         *
 *******************************************/

import java.util.*;

import tamago.*;

public class Direction extends BasicComponent
    implements DirectionService, 
	       RequireEffectorDirectionService,
	       IntrospectionController,
	       ReflectionController,
	       CarLifeCycleController {
    
    // Required services
    private EffectorDirectionService effector;

    // attributes
    private CarLifeCycleDelegate lc_check;
    private double angle;

    // properties
    private double angle_step = Math.PI / 8.0;

    // Interface fonctionnelle
    public Direction() {
        effector = null;
	angle = 0.0;
	lc_check = new CarLifeCycleDelegate();
    }
    
    public void goLeft() {
	if(!lc_check.checkStarted())
	    return;

	angle-=angle_step;
	if(angle<0.0)
	    angle = 2.0 * Math.PI + angle;
	System.err.println("[DIRECTION] : Going Left ANGLE="+angle);
	effector.deliverDirection(angle);
    }

    public void goRight() {
	if(!lc_check.checkStarted())
	    return;

	angle+=angle_step;
	if(angle>2.0 * Math.PI)
	    angle -= 2.0 * Math.PI;
	System.err.println("[DIRECTION] : Going Right ANGLE="+angle);
	effector.deliverDirection(angle);
    }

    // Laison des fournisseurs
    public void bind(EffectorDirectionService effector) throws ServiceBindException {
	if(lc_check.checkStarted())
	    throw new ServiceBindException("Cannot configure while running");
	this.effector = effector;
    }
    
    // Cycle de vie
    
    // Propri�t�s
    public void setAngleStep(double angle_step) {
	this.angle_step = angle_step;
    }

    public double getAngleStep() {
	return angle_step;
    }

    // Implantation de l'interface IntrospectionController
    public ComponentType getComponentType() {
        return new ComponentTypeDescriptor(this.getClass().getName(),this.getClass());
    }
    
    public ServiceType[] getProvidedServiceTypes() {
        ServiceType[] ret = new ServiceType[1];
        ret[0] = new ServiceTypeDescriptor("DirectionService", DirectionService.class);
        return ret;
    }
            
    public ServiceType[] getRequiredServiceTypes() {
        ServiceType[] ret = new ServiceType[1];
        ret[0] = new ServiceTypeDescriptor("EffectorDirectionService", EffectorDirectionService.class);
        return ret;
    }    
    
    public ServiceType getProvidedServiceType(String from_name) {
        if(from_name.equals("DirectionService"))
            return new ServiceTypeDescriptor("DirectionService", DirectionService.class);
        else 
            return null;
    }
    
    public ServiceType getRequiredServiceType(String from_name) {
        if(from_name.equals("EffectorDirectionService"))
            return new ServiceTypeDescriptor("EffectorDirectionService", EffectorDirectionService.class);
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
